<template>
  <div v-if="dataLoaded">
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
    </el-row>

    <el-row class="bgcolor-gray">
      <el-col :span="24" style="display: flex; justify-content: space-between; padding: 2vw">인증 방법
        <details style="width: 80%">
          <summary>펼쳐보기</summary>
          <p style="text-align: start; width: 100%">{{ challengeInfo.withChallenge.certifyInfo }}</p>
        </details>
      </el-col>
    </el-row>

    <br>
    
    <el-row>
      <div v-for="(date, idx) in challengeInfo.certifyImage" :key=idx>
        <div class="date-setting">
          {{ date.date }}
        </div>
        <span v-for="challenge, idx2 in date.list" :key=idx2>
          <el-image class="image-file-setting"
          style="height: 100px"
          :src="imageServer + challenge.image"
          >
          </el-image>
        </span>
      </div>
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
      dataLoaded: false,
      challengeId: "",
      challengeInfo: [],
      imageServer: SERVER.IMAGE_URL,
    }
  },
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
    getWithChallengeInfo() {
      axios.get(SERVER.URL + SERVER.ROUTES.getWithDetail
            + `?userId=${this.$store.state.userId}&withChallengeId=${this.challengeId}`)
        .then((response) =>{
          this.challengeInfo = response.data
          this.dataLoaded = true
        }
            
        )
        .catch((exp) => 
            alert(`인증사진 불러오기에 실패했습니다: ${exp}`)
        );
    },
  },  
  created: function () {
    this.$store.commit("GET_USERID")
    this.challengeId = this.$route.path.split('/')[3]
    this.getWithChallengeInfo()
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

  .text-decoration-category {
    text-decoration: none;
    font-weight: bold;
    color: black;
  }
  .text-decoration-title {
    font-size: 22px;
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
  .date-setting {
    text-align: start;
    margin-top: 2vh;
    padding-left: 1vw;
    color: #5A5A5A;
    font-size: 11px;
  }
  .image-file-setting {
    margin: 5px;
  }
</style>