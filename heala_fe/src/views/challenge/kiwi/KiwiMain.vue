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
              <img v-if="image == data[0].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
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
              <img v-if="image == data[1].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
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
              <img v-if="image == data[2].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
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
              <img v-if="image == data[3].image" src="@/assets/img/profile/user.png" alt="profile_image" width="70" height="70" style="border-radius: 50%;">
              <img v-else :src="image" alt="image" width="70" height="70" style="border-radius: 50%;"><br>
              <!-- {{ data[3].image }} -->
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
    height: 13vh;
    border: 1.5px solid darkgrey;
  }
</style>