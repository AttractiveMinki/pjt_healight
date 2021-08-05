<template>
  <div>
    <Navbar />
    <ChallengeContainerKiwi />
    <el-row class="community">
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiSpecial' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">{{ data[0].name }}</div>
              <div class="text-align-start">{{ data[0].introduction }}</div>
            </el-col>
            <el-col :span="6">
              image
              <!-- {{ data[0].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiHealth' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">{{ data[1].name }}</div>
              <div class="text-align-start">{{ data[1].introduction }}</div>
            </el-col>
            <el-col :span="6">
              image
              <!-- {{ data[1].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiDiet' }" class="text-decoration-none"> 
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">{{ data[2].name }}</div>
              <div class="text-align-start">{{ data[2].introduction }}</div>
            </el-col>
            <el-col :span="6">
              image
              <!-- {{ data[2].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiHeart' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">{{ data[3].name }}</div>
              <div class="text-align-start">{{ data[3].introduction }}</div>
            </el-col>
            <el-col :span="6">
              image
              <!-- {{ data[3].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <!-- <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiSpecial' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">기간 한정</div>
              <div class="text-align-start text-title">챌린지</div>
              <div class="text-align-start">기간 한정 챌린지에 도전해보세요!</div>
              <div class="text-align-end">참가자 수:</div>
            </el-col>
            <el-col :span="6">
              image
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiHealth' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">운동</div>
              <div class="text-align-start text-title">챌린지</div>
              <div class="text-align-start">운동 챌린지에 도전해보세요!</div>
            </el-col>
            <el-col :span="6">
              image
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiDiet' }" class="text-decoration-none"> 
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">식단</div>
              <div class="text-align-start text-title">챌린지</div>
              <div class="text-align-start">식단 챌린지에 도전해보세요!</div>
            </el-col>
            <el-col :span="6">
              image
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiHeart' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">마음</div>
              <div class="text-align-start text-title">챌린지</div>
              <div class="text-align-start">마음 챌린지에 도전해보세요!</div>
            </el-col>
            <el-col :span="6">
              image
            </el-col>
          </router-link>
        </el-row>
      </el-col> -->
    </el-row>
  </div>
</template>

<script>
import Navbar from "@/components/challenge/Navbar"
import ChallengeContainerKiwi from "@/components/challenge/kiwi/ChallengeContainerKiwi"
import SERVER from "@/api/drf.js"
import axios from "axios"


export default {
  name: "KiwiMain",
  data: function () {
    return {
      data: {
        // id: "",
        // name: "",
        // introduction: "",
        // category: "",
        // startDate: "",
        // endDate: "",
      },
    }
  },
  components: {
    Navbar,
    ChallengeContainerKiwi,
  },
  methods: {
    getKiwiChallenge: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getKiwiChallenge}`)
        .then((res) => {
          this.data = res.data
          // console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
  mounted: function () {
    this.getKiwiChallenge()
  },
}
</script>

<style scoped>
  .text-title {
    font-weight: bold; 
    font-size: 25px;
    margin-bottom: 5px;
    white-space : nowrap; /* 한 줄 제한*/
    overflow : hidden; /* 넘어가는 글자 숨기기 */
    text-overflow: ellipsis; /* 말 줄임표 추가 */
  }
  .text-align-start {
    text-align: start;
  }
  /* .text-align-end {
    text-align: end;
  } */
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .community {
    padding: 5px;
    /* margin-left: 2.5%; */
    border-radius: 5px;
    width: 100%;
    height: calc(80vh - 140px);
    border: 3px solid #ADEC6E;
    overflow : scroll;
  }
  .community-box {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    width: 100%;
    height: 14vh;
    border: 1.5px solid darkgrey;
  }
  .community-inside {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    /* width: 100%; */
    height: 14vh;
    border: 1.5px solid darkgrey;
  }
</style>