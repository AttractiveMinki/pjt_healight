<template>
  <div>
    <Navbar />
    <ChallengeContainerKiwi />
    <el-row class="community" v-if="dataLoaded">
      <el-col :span="24" class="community-box-special">
        <el-row>
          <router-link :to="{ name: 'KiwiSpecial' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside-special">
              <div class="text-align-start text-title">{{ data[3].name }}</div>
              <div class="text-align-start">{{ data[3].introduction }}</div>
              <br>
              <div>기간: {{ data[3].startDate }} ~ {{ data[3].endDate }}</div>
            </el-col>
            <el-col :span="6">
              <img v-if="image == data[3].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
              <!-- {{ data[3].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiHealth' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">{{ data[0].name }}</div>
              <div class="text-align-start">{{ data[0].introduction }}</div>
            </el-col>
            <el-col :span="6">
              <img v-if="image == data[0].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
              <!-- {{ data[0].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiDiet' }" class="text-decoration-none">
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">{{ data[1].name }}</div>
              <div class="text-align-start">{{ data[1].introduction }}</div>
            </el-col>
            <el-col :span="6">
              <img v-if="image == data[1].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
              <!-- {{ data[1].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
      <el-col :span="24" class="community-box">
        <el-row>
          <router-link :to="{ name: 'KiwiHeart' }" class="text-decoration-none"> 
            <el-col :span="18" class="community-inside">
              <div class="text-align-start text-title">{{ data[2].name }}</div>
              <div class="text-align-start">{{ data[2].introduction }}</div>
            </el-col>
            <el-col :span="6">
              <img v-if="image == data[2].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
              <!-- {{ data[2].image }} -->
            </el-col>
          </router-link>
        </el-row>
      </el-col>
    </el-row>
   <Footer />
  </div>
</template>

<script>
import Navbar from "@/components/challenge/Navbar"
import ChallengeContainerKiwi from "@/components/challenge/kiwi/ChallengeContainerKiwi"
import Footer from "@/components/home/Footer"
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
      dataLoaded: false,
    }
  },
  components: {
    Navbar,
    ChallengeContainerKiwi,
    Footer,
  },
  methods: {
    getKiwiChallenge: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getKiwiChallenge}`)
        .then((res) => {
          this.data = res.data
          this.dataLoaded = true
        })
        .catch(() => {
          alert('정보를 가져오던 중 오류가 발생했습니다.')
        })
    },
  },
  created: function () {
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
    height: calc(98vh - 162px - 3rem);
    border: 3px solid #ADEC6E;
    overflow : scroll;
  }
  .community-box {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    width: 100%;
    height: 15vh;
    border: 1.5px solid darkgrey;
  }
  .community-box-special {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    width: 100%;
    height: 22vh;
    border: 1.5px solid darkgrey;
  }
  .community-inside {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    /* width: 100%; */
    height: 13vh;
    border: 1.5px solid darkgrey;
  }
  .community-inside-special {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    /* width: 100%; */
    height: 20vh;
    border: 1.5px solid darkgrey;
  }
</style>