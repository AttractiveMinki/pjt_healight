<template>
  <div>
    <Navbar />
    <el-row style="margin: 2vh">
      <el-col :span="24">
        <h1>식단 기록하기</h1>
        <div v-if="recommend.calory - diet.calory > 0" style="font-size:18px">하루 권장 섭취량 {{ recommend.calory - diet.calory }}kcal 남았습니다.</div>
        <div v-else>하루 권장 섭취량을 달성했습니다.</div>
      </el-col>
    </el-row>

    <el-button>음식 추가</el-button>
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
        carbohydrate: 140, // 탄수화물
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
      ]
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
  },
  mounted: {
    // getScrapFeeds() {},
    // 사용자 성별, 신장, 체중
    // 가지고와서 하루 권장섭취량 계산
    // 오늘 섭취한 칼로리 탄단지 나트륨 계산 후 돌려주기
  },
}
</script>

<style scoped>

  #submit {
    position: fixed;
    bottom: 0rem;
  }
</style>