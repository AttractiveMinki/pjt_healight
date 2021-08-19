<template>
  <div>
    <Navbar />
    <el-row>
      <el-col :span="12">
        <router-link :to="{ name: 'ProfilePhysInfo' }" class="text-decoration-none">
          <span v-if="register == false" style="color: red; font-size: 13px">신체 정보 등록 후 이용하세요</span>
        </router-link>
        
        <h2 style="font-family: 'TmoneyRoundWindRegular';">하루 영양 정보</h2>
        <div style="font-size:18px"> <span style="font-weight: bold">{{ diet.calory }}kcal </span> / {{ recommend.calory }}</div>
        <div v-if="diet.calory - recommend.calory < 0" style="font-size:18px; color: #289B2D; font-family: 'TmoneyRoundWindRegular';">{{ (100 - (diet.calory / recommend.calory) * 100).toFixed(2)}}% 남음</div>
        <div v-else style="font-size:18px; color: #F57053">{{ ((diet.calory / recommend.calory) * 100).toFixed(2)}}% 초과</div>
        <br>
        <div>
          <router-link :to="{ name: 'DietRecordCalender' }" class="text-decoration-none cursor-pointer">
            <i class="el-icon-date"></i> {{ year }} / {{ month }} / {{ date }}
          </router-link>
        </div>
      </el-col>
      <el-col :span="12">
        <el-progress :stroke-width="24" type="circle" :percentage="parseFloat(((diet.calory / recommend.calory) * 100).toFixed(2))" :color="colors" style="margin: 1vw"></el-progress>
      </el-col>
    </el-row>
    <div id="line"></div>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:18px;">탄수화물</div>
        <div style="margin: 0.5vh"><span style="font-size:18px; font-weight: bold">{{ parseInt(diet.carbohydrate) }}g</span><span style="font-size:13px;"> / {{ recommend.carbohydrate }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseInt(((diet.carbohydrate / recommend.carbohydrate) * 100).toFixed(2))" :color="colors" style="margin: 0.5vh"></el-progress>
      </el-col>
    </el-row>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:18px;">단백질</div>
        <div style="margin: 0.5vh"><span style="font-size:18px; font-weight: bold">{{ parseInt(diet.protein) }}g</span><span style="font-size:13px;"> / {{ recommend.protein }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseInt(((diet.protein / recommend.protein) * 100).toFixed(2))" :color="colors"></el-progress>
      </el-col>
    </el-row>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:18px;">지방</div>
        <div style="margin: 0.5vh"><span style="font-size:18px; font-weight: bold">{{ parseInt(diet.fat) }}g</span><span style="font-size:13px;"> / {{ recommend.fat }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseInt(((diet.fat / recommend.fat) * 100).toFixed(2))" :color="colors"></el-progress>
      </el-col>
    </el-row>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:16px; font-family: 'TmoneyRoundWindRegular';">나트륨</div>
        <div style="margin: 0.5vh"><span style="font-size:16px; font-weight: bold">{{ diet.sodium }}mg</span><span style="font-size:13px;"> / {{ recommend.sodium }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseInt(((diet.sodium / recommend.sodium) * 100).toFixed(2))" :color="colors"></el-progress>
      </el-col>
    </el-row>
    <router-link :to="{ name: 'DietRecordDetail' }" class="text-decoration-none cursor-pointer">
      <div id="submit" style="background-color: #ADEC6E; color: black; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center; font-size: 13px;">식단 기록보기</div>
    </router-link>
  </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/drf.js"
import Navbar from "@/components/my_page/Navbar"

export default {
  name: "DietRecord",
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
        calory: 1800,
        carbohydrate: 141, // 탄수화물
        protein: 48,
        fat: 17,
        sodium: 2300, // 나트륨
      },
      recommend: {
        calory: 2300,
        carbohydrate: 280, // 탄수화물
        protein: 76,
        fat: 51,
        sodium: 2000, // 나트륨
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
      today: "",
      year: "", // 년도
      month: "",  // 월
      date: "", // 날짜
      day: "",
      register: "",
      minCalory: "",
    }
  },
  methods: {
    getDate() {
      let today = new Date();   

      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1;  // 월
      let date = today.getDate();  // 날짜
      let day = today.getDay();  // 요일,

      this.today = today
      this.year = ('0000' + year).slice(-4)
      this.month = ('00' + month).slice(-2)
      this.date = ('00' + date).slice(-2)
      this.day = day
    },
    getDietRecordToday: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getDietRecordToday}?day=${this.year}${this.month}${this.date}&userId=${this.$store.state.userId}`)
        .then((res) => {
          this.diet.calory = res.data.calory
          this.diet.carbohydrate = res.data.carbohydrate
          this.diet.protein = res.data.protein
          this.diet.fat = res.data.fat
          this.diet.sodium = res.data.sodium
          this.recommend.calory = res.data.totalCalory
          this.recommend.carbohydrate = res.data.totalCarbohydrate
          this.recommend.protein = res.data.totalProtein
          this.recommend.fat = res.data.totalFat
          this.recommend.sodium = res.data.totalSodium

          this.register = res.data.register
          this.minCalory = res.data.minCalory
        })
        .catch((err) => {
          console.error(err.response.data)
        })
    },
  },
  mounted() {
    this.getDate()
    // getScrapFeeds() {},
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
      this.getDietRecordToday()
    }
    // 다른 사람꺼 보기
    else {
      alert("다른 사람의 계정은 열람하실 수 없습니다.")
      this.$router.go(-1)
    }
  },
}
</script>

<style scoped>
  #line {
    margin: 2vh 0vh;
    width: 100%;
    height: 5px;
    background: #F2F2F2;
  }
  #submit {
  position: fixed;
  bottom: 0px;
  background-color: #ADEC6E;
  color: black;
  width: 100%;
  height: 48px;
  display:flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-family: TmoneyRoundWindRegular; 
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .cursor-pointer {
    cursor: pointer;
  }
</style>