<template>
  <div>
    <h1>함께챌린지 상세화면</h1>
    {{ currentPageId }}
    {{ datas }}
    <div class="display-flex justify-content-space-between align-items">
      <span><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" @click="goBack()"/></span>
      <span class="text-decoration-title">챌린지 상세</span>
      <!-- <button class="button-color">작성</button> -->
      <span id="block"></span>
    </div>
    <el-row id="category" class="justify-content-space-between">
      <el-col :span="12">
        <router-link :to="{ name: 'WithDetail' }" class="text-decoration-none selected-category">소개</router-link> 
        <div id="square"></div>
      </el-col>
      <el-col :span="12">
        <router-link :to="{ name: 'WithDetailCertify' }" class="text-decoration-none">인증</router-link> 
      </el-col>
      <!-- <el-col :span="8">
        <router-link :to="{ name: 'WithDetailReview' }" class="text-decoration-none">후기</router-link> 
      </el-col> -->
    </el-row>
    <!-- <el-row id="challenge-image" style="margin: 1%">
      {{ with_challenges[currentPageId - 1].image }}
    </el-row> -->
    <el-row id="challenge-title" style="margin: 1%">
      <el-col :span="16" class="text-decoration-title">
        {{ with_challenges[currentPageId - 1].title }}
      </el-col>
    </el-row>
    <!-- <el-row id="challenge-code">
      <el-col>
        챌린지 코드: 
      </el-col>
    </el-row> -->
    <el-row>
      <el-col id="challenge-duration" style="margin: 1%">
        {{ with_challenges[currentPageId - 1] }}
        기간: {{ with_challenges[currentPageId - 1].start_date }} ~ {{ with_challenges[currentPageId - 1].end_date }}
      </el-col>
    </el-row>
    <!-- <el-row>
      <el-col id="challenge-hashtags">
        해시태그: {{ with_challenges[currentPageId - 1].certify_infoy }}
      </el-col>
    </el-row> -->
    <hr>
    <el-row>
      <el-col id="challenge-certifyinfo" style="margin: 1%">
        해시태그: {{ datas.participantsNum }}
      </el-col>
    </el-row>
    <el-row>
      <el-col id="challenge-certifyinfo" style="margin: 1%">
        소개: {{ with_challenges[currentPageId - 1].certify_infoy }}
      </el-col>
    </el-row>
    <hr>
    <el-row>
      <el-col id="challenge-people" style="margin: 1%">
        참가 인원: {{ datas.participantsNum }}
      </el-col>
    </el-row>
    <el-row>
      <el-col id="challenge-exp" style="margin: 1%">
        키위 점수: 
      </el-col>
    </el-row>
    <!-- 유저가 참가중이 아니라면 -->
    <!-- <div id="submit" @click="submit(data)" style="background-color: #ADEC6E; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">참여하기</div> -->

    <!-- 유저가 참가중이라면 -->
    <div id="submit" style="background-color: #C4C4C4; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">참여중</div>
  </div>
</template>

<script>
import { mapState } from "vuex"
import SERVER from "@/api/drf.js"
import axios from "axios"

export default {
  name: 'WithDetail',
  data: function () {
    return {
      datas: [],
    }
  },
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
    getWithDetail: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getWithDetail}?userId=${localStorage.getItem('userid')}&withChallengeId=${this.$store.state.currentPageCategory}`)
      // axios.get(`${SERVER.URL}${SERVER.ROUTES.getWithDetail}?challengeId=${this.$store.state.currentPageCategory}&userId=${this.$store.state.userid}`) // 원본
        .then((res) => {
          console.log(`${SERVER.URL}${SERVER.ROUTES.getWithDetail}?userId=${localStorage.getItem('userid')}&withChallengeId=${this.$store.state.currentPageCategory}`)
          this.datas = res.data
        })
        .catch((err) => {
          // console.log(`${SERVER.URL}${SERVER.ROUTES.getWithDetail}?userId=${this.$store.state.userid}&withChallengeId=${this.$store.state.currentPageCategory}`)
          console.log(err)
        })
    },
  },  
  computed: {
    ...mapState([
      "currentPageId",
      "with_challenges",
      "currentPageCategory",
    ])
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
  .text-decoration-none {
    text-decoration: none;
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
    padding-left: 4%;
  }
  .cursor-pointer {
    cursor: pointer;
  }
</style>