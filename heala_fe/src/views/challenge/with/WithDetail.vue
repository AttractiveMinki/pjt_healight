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
        <div id="square" style="margin-top: 1vh"></div>
      </el-col>
      <el-col :span="12">
        <router-link :to="{ name: 'WithDetailCertify' }" class="text-decoration-category" >인증</router-link> 
      </el-col>
      <!-- <el-col :span="8">
        <router-link :to="{ name: 'WithDetailReview' }" class="text-decoration-category">후기</router-link> 
      </el-col> -->
    </el-row>

    <el-row style="margin: 1%">
      <el-col :span="24" class="text-decoration-title">
        <el-image class="margin-left-10"
          style="width: 100%; height: 20vh"
          :src="imageServer + value.withChallenge.image" 
          >
        </el-image>
      </el-col>
    </el-row>

    <el-row id="challenge-title" class="challenge-info">
      <el-col :span="16" class="text-challenge-title">
        {{ value.withChallenge.title }}
      </el-col>
    </el-row>

    <el-row>
      <el-col id="challenge-certifyinfo" class="challenge-info">
        <span v-if="value.withChallenge.category == 0">
          챌린지 카테고리: 운동
        </span>
        <span v-else-if="value.withChallenge.category == 1">
          챌린지 카테고리: 식단
        </span>
        <span v-else-if="value.withChallenge.category == 2">
          챌린지 카테고리: 마음
        </span>
      </el-col>
    </el-row>

    <el-row>
      <el-col id="challenge-duration" class="challenge-info">
        {{ value.withChallenge.startDate }} ~ {{ value.withChallenge.endDate }}
      </el-col>
    </el-row>

    <hr>
    <el-row style="text-align: start">
      <span id="challenge-certifyinfo" style="padding: 2%; box-sizing: border-box;" v-for="(hashtag, idx) in value.hashtags" :key="idx">
        <span class="text-hashtag-detail">#{{ hashtag }}</span> 
      </span>
    </el-row>

    <el-row>
      <el-col id="challenge-certifyinfo" class="challenge-info">
        소개: {{ value.withChallenge.introduction }}
      </el-col>
    </el-row>

    <el-row>
      <el-col id="challenge-certifyinfo" class="challenge-info">
        <router-link :to="{ name: 'MyMain' }" class="text-decoration-category selected-category" >인증하러가기</router-link> 
      </el-col>
    </el-row>

    <hr>
    <el-row>
      <el-col id="challenge-people" class="challenge-info">
        <font-awesome-icon icon="user" /> 참가 인원: {{ value.participantsNum }}
      </el-col>
    </el-row>

    <el-row>
      <el-col id="challenge-exp" class="challenge-info">
        <font-awesome-icon icon="star" /> 키위 점수: {{ value.withChallenge.kiwiPoint }}
      </el-col>
    </el-row>
    <!-- 유저가 참가중이 아니라면 -->
    <!-- <div id="submit" @click="submit(data)" style="background-color: #ADEC6E; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">참여하기</div> -->

    <!-- 유저가 참가중이라면 -->

    <div v-if="value.participated == true" id="submit" style="background-color: #C4C4C4; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">참여중</div>
    <div v-else id="submit" style="background-color: #65C506; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;" class="cursor-pointer" @click="joinChallenge()">참여하기</div>
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
      dataLoaded: false,
      imageServer: SERVER.IMAGE_URL,
    }
  },
  // props: {
  //   id: {
  //     type: String,
  //   }
  // },
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
    getWithDetail: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getWithDetail}?userId=${this.$store.state.userId}&withChallengeId=${this.$route.params.id}`)
        .then((res) => {
          this.value = res.data
          this.dataLoaded = true
        })
        .catch(() => {
          alert('정보를 가져오던 중 오류가 발생했습니다.')
        })
    },
    joinChallenge: function () {
      axios.post(`${SERVER.URL}${SERVER.ROUTES.joinWithChallenge}?userId=${this.$store.state.userId}&withChallengeId=${this.$route.params.id}`)
        .then (() => {
          alert("챌린지에 참가하셨습니다.");
          this.$router.push({ name: "WithMain" });
        })
        .catch(() => {
          alert('챌린지에 참가하던 중 오류가 발생했습니다.')
        })
    },
  },  
  created: function () {
    this.$store.commit("GET_USERID")
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
  .challenge-info {
    /* margin: 2% 0px 2% 0px; */
    padding: 2%;
    box-sizing: border-box;
    text-align: start
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
    font-size: 20px;
    font-weight: bold;
  }
  .text-hashtag-detail {
    border-radius: 30px;
    width: 98%;
    height: 22px;
    border: 3px solid #ADEC6E;
    background: #ADEC6E;
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

</style>