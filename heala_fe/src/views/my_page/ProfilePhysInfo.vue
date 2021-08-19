<template>
  <div>
    <Navbar />
    <h1 style="text-align: start; margin: 3vw; margin-bottom: 5vh">신체정보 등록</h1>

    <!-- 사용자의 신체 정보가 있다면 vue component 내의 data에 미리 등록하는 함수 만들기 -->

    <el-row style="display: flex; text-align: space-between; align-items: center">
      <el-col :span="8">
        <span style="margin: 5vw">성별</span>
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
        <span style="margin: 5vw">생년월일</span>
      </el-col>
      <el-col :span="16">
        <input type="date" style="margin: 5vw" v-model="data.birth">
      </el-col>
    </el-row>

    <el-row style="display: flex; text-align: space-between; align-items: center">
      <el-col :span="8">
        <span style="margin: 5vw">키</span>
      </el-col>
      <el-col :span="16">
        <input type="number" style="margin: 5vw" v-model="data.height">cm
      </el-col>
    </el-row>

    <el-row style="display: flex; text-align: space-between; align-items: center">
      <el-col :span="8">
        <span style="margin: 5vw">몸무게</span>
      </el-col>
      <el-col :span="16">
        <input type="number" style="margin: 5vw" v-model="data.weight">kg
      </el-col>
    </el-row>
    <div id="submit" @click="submit(data)" style="background-color: #ADEC6E; color: black; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">등록하기</div>
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
      console.log(data)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.profilePhysInfo}`, data)
      .then(() => {
        alert('신체 정보가 등록되었습니다!')
        this.$router.push({ name: "Profile", params: {id: this.data.userId} })
      })
      .catch((err) => {
        alert('오류가 발생했습니다.')
        console.log(err)
      })
    },
  },
  created() {
    this.$store.commit("GET_USERID")
    this.data.userId = this.$store.state.userId
  },
  // mounted() {
  //   // 프로필 기존 정보 불러오기 -> 함수를 선언하고 따로 뺀 뒤, mounted에는 함수 이름만 호출하면 될 듯.
  //   axios.get(`${SERVER.URL}${SERVER.ROUTES.userProfile}`)
  //     .then(response => {     
  //       this.image = require("@/assets/img/profile/" + response.data.image);
  //       this.identity = response.data.identity;
  //       this.name = response.data.name;
  //       this.introduction = response.data.introduction;
  //     })
  //     .catch(error => {
  //       console.log(error);
  //       console.error(error.response.data);
  //     });
  // },
}
</script>

<style scoped>
  #submit {
    position: fixed;
    bottom: 0rem;
  }
</style>