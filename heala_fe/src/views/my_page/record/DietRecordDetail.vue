<template>
  <div>
    <Navbar />
    <el-row style="margin: 2vh">
      <el-col :span="24">
        <h1>식단 기록하기</h1>
        <div v-if="recommend.calory - diet.calory > 0" style="font-size:18px">하루 권장 섭취량 {{ recommend.calory - diet.calory }}kcal 남았습니다.</div>
        <div v-else>하루 권장 섭취량에서 {{ diet.calory - recommend.calory }}kcal 초과하였습니다.</div>
      </el-col>
    </el-row>
    <el-row style="display: flex; justify-content: center">
      <el-col :span="20">
        <div v-for="(food, idx) in food_list" :key="idx" style="margin: 4vw">
          <div class="food-box">
            {{ food }}
          </div>
        </div>
      </el-col>
    </el-row>
    <router-link :to="{ name: 'DietRecordCreate' }" class="text-decoration-none">
      <div id="submit" style="background-color: #ADEC6E; color: black; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">식단 추가하기</div>
    </router-link>


  </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/drf.js"
import Navbar from "@/components/my_page/Navbar"

export default {
  name: "DietRecordDetail",
  components: {
    Navbar,
  },
  data: function () {
    return {
      // diet: {
      //   calory: "",
      //   carbohydrate: "", // 탄수화물
      //   protein: "",
      //   fat: "",
      //   sodium: "", // 나트륨
      // }
      diet: {
        calory: "",
        carbohydrate: "", // 탄수화물
        protein: "",
        fat: "",
        sodium: "", // 나트륨
      },
      recommend: {
        calory: "",
        carbohydrate: "", // 탄수화물
        protein: "",
        fat: "",
        sodium: "", // 나트륨
      },
      colors: [
        {color: '#F57053', percentage: 1},
        {color: '#f56c6c', percentage: 20},
        {color: '#e6a23c', percentage: 40},
        {color: '#5cb87a', percentage: 60},
        {color: '#1989fa', percentage: 80},
        {color: '#ADEC6E', percentage: 100},
        {color: '#F57053', percentage: 101},
      ],
      food_list: [],
      values: [],
      today: "",
      year: "", // 년도
      month: "",  // 월
      date: "", // 날짜
    }
  },
  methods: {
    getDate() {
      let today = new Date();   

      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1;  // 월
      let date = today.getDate();  // 날짜

      this.today = today
      this.year = ('0000' + year).slice(-4)
      this.month = ('00' + month).slice(-2)
      this.date = ('00' + date).slice(-2)
    },
    getDietRecord: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getDietRecord}${this.$store.state.userId}`)
        .then((res) => {
          this.food_list = res.data
      })
        .catch((err) => {
          alert("에러가 발생했습니다.")
          console.error(err.response.data)
      })
    },
    getDietRecordToday: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getDietRecordToday}?day=${this.year}${this.month}${this.date}&userId=${this.$store.state.userId}`)
        .then((res) => {
          this.diet.calory = res.data.calory
          this.recommend.calory = res.data.totalCalory
        })
        .catch((err) => {
          console.error(err.response.data)
        })
    },

  },
  created () {
    this.getDate()

    // 사용자 성별, 신장, 체중
    // 가지고와서 하루 권장섭취량 계산
    // 오늘 섭취한 칼로리 탄단지 나트륨 계산 후 돌려주기

    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.path.split('/')[3]

    // 내 아이디 localStorage에서 가져오기
    this.$store.commit("GET_USERID")
    this.myId = this.$store.state.userId

    // 내 꺼 보기
    if (this.userId == this.myId) {
      this.getDietRecord()
      this.getDietRecordToday()
    }
    // 다른 사람꺼 보기
    else {
      alert('다른 사람의 계정입니다. 열람하실 수 없습니다.')
      this.$router.go(-1)
    }
  },
}
</script>

<style scoped>
  #submit {
    position: fixed;
    bottom: 0rem;
  }
  .food-box {
    border: #F3F3F3 3px solid;
    box-shadow: 1px 1px 1px 1px gray;
    border-radius: 5px;
    font-weight: bold;
    font-size: 18px;
  }
</style>