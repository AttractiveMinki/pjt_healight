<template>
  <div>
    <Navbar />
    <el-row>
      <el-col :span="12">
        <h1>하루 영양 정보</h1>
        <div style="font-size:18px"> <span style="font-weight: bold">{{ diet.calory }}kcal </span> / {{ recommend.calory }}</div>
        <div style="font-size:18px; color: #289B2D">{{ (100 - (diet.calory / recommend.calory) * 100).toFixed(2)}}% 남음</div>
        <br>
        <div>
          <router-link :to="{ name: 'DietRecordCalender' }" class="text-decoration-none cursor-pointer">
            <i class="el-icon-date"></i> {{ year }} / {{ month }} / {{ date }}
          </router-link>
        </div>
      </el-col>
      <el-col :span="12">
        <el-progress type="circle" :percentage="parseFloat(((diet.calory / recommend.calory) * 100).toFixed(2))" :stroke-width="24" :color="colors"></el-progress>
      </el-col>
    </el-row>
    <div id="line"></div>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:18px;">탄수화물</div>
        <div style="margin: 0.5vh"><span style="font-size:18px; font-weight: bold">{{ diet.carbohydrate }}g</span><span style="font-size:13px;"> / {{ recommend.carbohydrate }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseFloat(((diet.carbohydrate / recommend.carbohydrate) * 100).toFixed(2))" :color="colors" style="margin: 0.5vh"></el-progress>
      </el-col>
    </el-row>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:18px;">단백질</div>
        <div style="margin: 0.5vh"><span style="font-size:18px; font-weight: bold">{{ diet.protein }}g</span><span style="font-size:13px;"> / {{ recommend.protein }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseFloat(((diet.protein / recommend.protein) * 100).toFixed(2))" :color="colors"></el-progress>
      </el-col>
    </el-row>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:18px;">지방</div>
        <div style="margin: 0.5vh"><span style="font-size:18px; font-weight: bold">{{ diet.fat }}g</span><span style="font-size:13px;"> / {{ recommend.fat }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseFloat(((diet.fat / recommend.fat) * 100).toFixed(2))" :color="colors"></el-progress>
      </el-col>
    </el-row>

    <el-row style="margin:1vh">
      <el-col :span="11">
        <div style="font-size:18px;">나트륨</div>
        <div style="margin: 0.5vh"><span style="font-size:18px; font-weight: bold">{{ diet.sodium }}mg</span><span style="font-size:13px;"> / {{ recommend.sodium }}</span></div>
      </el-col>
      <el-col :span="11">
        <el-progress :stroke-width="24" :percentage="parseFloat(((diet.sodium / recommend.sodium) * 100).toFixed(2))" :color="colors"></el-progress>
      </el-col>
    </el-row>
    <router-link :to="{ name: 'DietRecordDetail' }" class="text-decoration-none cursor-pointer">
      <div id="submit" style="background-color: #ADEC6E; color: black; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">식단 기록보기</div>
    </router-link>
  </div>
</template>

<script>
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
    }
  },
  methods: {
    // getScrapFeeds: function () {
    //   axios.get(`${SERVER.URL}${SERVER.ROUTES.getScrapFeeds}`)
    //     .then((res) => {
    //       feeds = res
    //   })
    //     .catch((err) => {
    //       alert("에러가 발생했습니다.")
    //       // console.log(`${SERVER.URL}${SERVER.ROUTES.checkEmail}${email}`)
    //       console.error(err.response.data)
    //   })
    // },
    getDate() {
      let today = new Date();   

      let year = today.getFullYear(); // 년도
      let month = today.getMonth() + 1;  // 월
      let date = today.getDate();  // 날짜
      let day = today.getDay();  // 요일,

      this.today = today
      this.year = year
      this.month = month
      this.date = date
      this.day = day
    },
  },
  mounted() {
    this.getDate()
    // getScrapFeeds() {},
    // 사용자 성별, 신장, 체중
    // 가지고와서 하루 권장섭취량 계산
    // 오늘 섭취한 칼로리 탄단지 나트륨 계산 후 돌려주기
  },
}
</script>

<style scoped>
  #line {
    margin: 2vh;
    width: 100%;
    height: 5px;
    background: #F2F2F2;
  }
  #submit {
    position: fixed;
    bottom: 0rem;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .cursor-pointer {
    cursor: pointer;
  }
</style>