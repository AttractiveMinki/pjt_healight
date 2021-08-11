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
    <el-row style="display: flex; justify-content: center">
      <el-col :span="20">
        <div v-for="(value, idx) in values" :key="idx" style="margin: 4vw">
          <div class="food-box">
            {{ value.food }}
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
      ],
      values: [
        {
          food: "초코우유",
          calory:300,
          carbohydrate:100,
          protein:40,
          fat:14,
          sodium:50,
        },
        {
          food: "잡곡밥",
          calory:300,
          carbohydrate:200,
          protein:40,
          fat:14,
          sodium:50,
        },
        {
          food: "부대찌개",
          calory:802,
          carbohydrate:300,
          protein:40,
          fat:14,
          sodium:700,
        },
      ],
    }
  },
  methods: {
    // getDietRecord: function () {
    //   axios.get(`${SERVER.URL}${SERVER.ROUTES.getDietRecord}`)
    //     .then((res) => {
    //       values = res
    //   })
    //     .catch((err) => {
    //       alert("에러가 발생했습니다.")
    //       // console.log(`${SERVER.URL}${SERVER.ROUTES.checkEmail}${email}`)
    //       console.error(err.response.data)
    //   })
    // },
  },
  mounted: {
    // getDietRecord() {},
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
  .food-box {
    border: #F3F3F3 3px solid;
    box-shadow: 1px 1px 1px 1px gray;
    border-radius: 5px;
    font-weight: bold;
    font-size: 18px;
  }
</style>