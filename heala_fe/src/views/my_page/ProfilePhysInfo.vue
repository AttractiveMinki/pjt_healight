<template>
  <div>
    <Navbar />
    <h1 style="text-align: center; margin: 3vw; margin-bottom: 5vh; font-family: 'TmoneyRoundWindRegular';">신체정보 등록</h1>

    <!-- 사용자의 신체 정보가 있다면 vue component 내의 data에 미리 등록하는 함수 만들기 -->

    <el-row style="display: flex; text-align: space-between; align-items: center; ">
      <el-col :span="8">
        <span style="margin: 5vw; font-family: 'TmoneyRoundWindRegular';">성별</span>
      </el-col>
      <el-col :span="16">
        <span v-if="data.gender == true">
          <el-button size="small" type="primary" round style="background-color: #ADEC6E; border: 1px solid #ADEC6E; color: black; margin: 5vw" @click="setGender(1)">남자</el-button>
          <el-button size="small" type="primary" round style="background-color: white; border: 1px solid #ADEC6E; color: black; margin: 5vw" @click="setGender(0)"> 여자</el-button>
        </span>
        <span v-else-if="data.gender == false">
          <el-button size="small" type="primary" round style="background-color: white; border: 1px solid #ADEC6E; color: black; margin: 5vw" @click="setGender(1)">남자</el-button>
          <el-button size="small" type="primary" round style="background-color: #ADEC6E; border: 1px solid #ADEC6E; color: black; margin: 5vw" @click="setGender(0)"> 여자</el-button>
        </span>
      </el-col>
    </el-row>

    <el-row style="display: flex; text-align: space-between; align-items: center">
      <el-col :span="8">
        <span style="margin: 5vw; font-family: 'TmoneyRoundWindRegular';">생년월일</span>
      </el-col>
      <el-col :span="16">
        <input type="date" style="margin: 5vw" v-model="data.birth">
      </el-col>
    </el-row>

    <el-row style="display: flex; text-align: space-between; align-items: center">
      <el-col :span="8">
        <span style="margin: 5vw; font-family: 'TmoneyRoundWindRegular';">키</span>
      </el-col>
      <el-col :span="16">
        <input type="number" style="margin: 5vw" v-model="data.height">cm
      </el-col>
    </el-row>

    <el-row style="display: flex; text-align: space-between; align-items: center">
      <el-col :span="8">
        <span style="margin: 5vw; font-family: 'TmoneyRoundWindRegular';">몸무게</span>
      </el-col>
      <el-col :span="16">
        <input type="number" style="margin: 5vw" v-model="data.weight">kg
      </el-col>
    </el-row>
    <div id="submit" @click="submit(data)" style="background-color: #ADEC6E; color: black; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center; font-family: 'TmoneyRoundWindRegular';">등록하기</div>
  </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/drf.js"
import Navbar from "@/components/my_page/Navbar"

export default {
  name: "ProfilePhysInfo",
  data: function () {
    return {
      data: {
        userId: "",
        gender: true,
        birth: "",
        height: "",
        weight: "",
      },
    }
  },
  components: {
    Navbar,
  },
  methods: {
    setGender(num) {
      if (num == 1) {
        this.data.gender = true
      }
      else if (num == 0) {
        this.data.gender = false
      }
    },
    submit(data) {
      axios.post(`${SERVER.URL}${SERVER.ROUTES.profilePhysInfo}`, data)
      .then(() => {
        alert("신체 정보가 등록되었습니다!");
        this.$router.push({ name: "Profile", params: {id: this.data.userId} })
      })
      .catch(() => {
        alert("오류가 발생했습니다.");
      })
    },
  },
  created() {
    this.$store.commit("GET_USERID")
    this.data.userId = this.$store.state.userId
  },
}
</script>

<style scoped>
  #submit {
    position: fixed;
    bottom: 0rem;
  }
</style>