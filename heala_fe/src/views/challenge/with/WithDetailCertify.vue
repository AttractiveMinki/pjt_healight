<template>
  <div>
    <div class="display-flex justify-content-space-between align-items">
      <span><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" @click="goBack()"/></span>
      <span class="text-decoration-title">챌린지 상세</span>
      <!-- <button class="button-color">작성</button> -->
      <span id="block"></span>
    </div>
    <br>
    <hr>
    <br>
    <el-row id="category" class="justify-content-space-between">
      <el-col :span="12">
        <router-link :to="{ name: 'WithDetail' }" class="text-decoration-category selected-category" >소개</router-link> 
      </el-col>
      <el-col :span="12">
        <router-link :to="{ name: 'WithDetailCertify' }" class="text-decoration-category" >인증</router-link> 
        <div id="square" style="margin-top: 1vh"></div>
      </el-col>
      <!-- <el-col :span="8">
        <router-link :to="{ name: 'WithDetailReview' }" class="text-decoration-category">후기</router-link> 
      </el-col> -->
    </el-row>

    <el-row class="bgcolor-gray">
      <el-col :span="24" style="display: flex; justify-content: space-between; padding: 2vw">인증 방법
        <details style="width: 80%">
          <summary>펼쳐보기</summary>
          <p style="text-align: start; width: 100%">{{ value.withChallenge.certifyInfo }}</p>
        </details>
      </el-col>
    </el-row>

    <br>
    
    <el-row>
      <el-col>참가자 인증</el-col>
    </el-row>

  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from "axios"

export default {
  name: 'WithDetail',
  data: function () {
    return {
      value: [],
    }
  },
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
    getWithDetail: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getWithDetail}?userId=${localStorage.getItem('userId')}&withChallengeId=${this.$route.params.id}`)
        .then((res) => {
          this.value = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
    joinChallenge: function () {
      axios.post(`${SERVER.URL}${SERVER.ROUTES.joinWithChallenge}?userId=${localStorage.getItem('userId')}&withChallengeId=${this.$route.params.id}`)
        .then (() => {
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },  
  mounted: function () {
    this.getWithDetail()
  },
}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 10px;
    background: #ADEC6E;
  }
  #block {
    width: 10%;
    height: 0px;
    background: white;
  }
  #submit {
    position: fixed;
    bottom: 0rem;
  }
  .text-decoration-category {
    text-decoration: none;
    font-weight: bold;
    color: black;
  }
  .text-decoration-title {
    font-size: 22px;
    font-weight: bold;
  }
  .text-challenge-title {
    font-size: 30px;
    font-weight: bold;
  }
  .display-flex {
    display: flex;
  }
  .justify-content-space-between {
    justify-content: space-between;
  }  
  .align-items {
    align-items: center;
  }
  .padding-left {
    padding-left: 4vw;
  }
  .cursor-pointer {
    cursor: pointer;
  }
  .bgcolor-gray {
    background: #E8E8E8;
  }
</style>