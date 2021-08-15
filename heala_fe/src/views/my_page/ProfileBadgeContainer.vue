<template>
  <div>
    <Navbar 
    />
    <el-row style="align-items:center">
      <el-col :span="6" style="padding: 3vw">
        <img v-if="user.image == '' " src="@/assets/img/profile/user.png" alt="profile_image" width="62" height="62" style="border-radius: 50%;">
        <img v-else :src="user.image" alt="profile_image" width="62" height="62" style="border-radius: 50%;"><br>
      </el-col>
      <el-col :span="18" style="text-align: -webkit-right; padding: 3vw">
        <!-- 사용자와 프로필 주인이 같다면 -->
        <span v-if="userId == myId" id="square" style="margin-top: 10px"><router-link :to="{ name: 'ProfileEdit' }" class="text-decoration-none challenge-make">프로필 편집</router-link></span>

        <!-- 사용자와 프로필 주인이 다르다면 -->
        <span v-else id="square" style="margin-top: 10px"><router-link :to="{ name: 'ProfileEdit' }" class="text-decoration-none challenge-make">팔로잉 팔로우 구현하기</router-link></span>
        <!-- 메시지 -->
        <!-- 팔로잉 -->
      </el-col>
    </el-row>
    <el-row style="text-align: start; padding: 2vw">
      <el-col :span="12">
        <span style="font-size: 14px; font-weight: bold; padding: 1px"> {{ user.name }} <!-- 스파르타꾹스 --> </span>
        <span style="font-size: 11px"> Lv. {{ user.id }} <!-- exp--></span>
      </el-col>
    </el-row>
     <el-row style="text-align: start; padding: 2vw">
      <el-col>
        <span style="font-size: 13px"> {{ user.introduction }}<!-- 나는야 스파르타꾹스. 유튜브 많이 보러 와주세요--></span>
      </el-col>
    </el-row>
    <el-row style="text-align: space-between; font-size: 12px">
      <el-col :span="12">
        <!-- 팔로잉 팔로워 수를 구하는 통신이 필요할 듯! -->
        <span style="padding: 2vw"> <span style="font-weight: bold; font-size: 13px; padding: 1vw">231<!-- {{ user.following }} --></span>팔로잉</span>  
        <span style="padding: 2vw"> <span style="font-weight: bold; font-size: 13px; padding: 1vw">583<!-- {{ user.follower }} --></span>팔로워</span>
      </el-col>
    </el-row>
    <div id="underline"></div>

    <el-row style="text-align: start">
      <!-- 배지 뭐갖고있는지 계산 -->
      <img class="image" src="@/assets/image/health/health_challenge.png" alt="health badge" style="width: 10vw; padding: 2vw">
      <el-dialog width="20px">
        <img alt="" width="20px">
      </el-dialog>
      <img class="image" src="@/assets/image/diet/diet_dia.png" alt="diet badge" style="width: 10vw; padding: 2vw">
      <el-dialog  width="95%">
        <img alt="" width="99%">
      </el-dialog>
      <img class="image" src="@/assets/image/heart/heart_silver.png" alt="herat badge" style="width: 10vw; padding: 2vw">
      <el-dialog width="95%">
        <img alt="" width="99%">
      </el-dialog>
    </el-row>

    <el-row>
      <el-col :span="12">
        <router-link :to="{ name: 'Profile', params: { id: userId } }" class="text-decoration-none"><font-awesome-icon :icon="['far', 'images']" style="margin-top: 1.5vh" /></router-link> 
      </el-col>
      <el-col :span="11" style="background: #ADEC6E; height: 5vh" class="display-inline-grid aligh-items-center">
        <router-link :to="{ name: 'ProfileBadgeContainer', params: { id: userId } }" class="text-decoration-none selected-category">배지 보관함</router-link> 
      </el-col>
    </el-row>
    <div class="community">
      <el-row style="padding-top: 2vh">
        <el-col :span="24">
          <el-col :span="24" style="text-align: start; padding: 2vw">
            <span>배지 보관함</span>
          </el-col>
          <div v-for="badge, idx in badges" :key="idx">
            <span>
              {{ badge }}
            </span>
          </div>
          <!-- <router-link :to="{ name: 'ProfilePhysInfo' }" class="text-decoration-none">
            <el-col :span="12" style="text-align: end; padding: 2vw" class="community-title">
              <i class="margin-left-10 el-icon-arrow-right"></i>
              <span>신체정보 등록</span>
            </el-col>
          </router-link> -->
        </el-col>
      </el-row>
    
      <el-row>
        <!-- 배지 목록 -->
      </el-row>

    </div>  
    <Footer />
  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from 'axios';
import Navbar from "@/components/my_page/Navbar"
import Footer from "@/components/home/Footer"

export default {
  name: "ProfileBadgeContainer",
  data: () => {
    return {
      user: {
        id: "",
        identity: "",
        image: "",
        name: "",
        exp: "",
        introduction: "",
        badge: [],
        follower: "",
        following: "",
        exercise: [],
        food: [],
        heart: [],
      },
      // 보고 있는 프로필 계정 주인의 id
      userId: "",
      
      // 로그인 한 내 아이디
      myId: "",

      badges: [],
    };
  },
  components: {
    Navbar,
    Footer,
  },
  methods: {
    getBadgeInfo: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.community}`)
        .then ((res) => {
          this.communityArticles = res.data
        })
        .catch ((err) => {
          console.log(err)
        })
    },
  },
  mounted() {
    // footer 4로 설정
    this.$store.state.selectedRouter = 4
  
    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.path.split('/')[2]

    // 내 아이디 localStorage에서 가져오기
    this.myId = localStorage.getItem('userId')

    axios.get(`${SERVER.URL}${SERVER.ROUTES.userProfile}${this.userId}/badge`)
      .then(response => {     
        // this.image = require("@/assets/img/profile/" + response.data.image);
        this.badges = response.data
      })
      .catch(error => {
        console.log(error);
        console.error(error.response.data);
      });
    
  },
}
</script>

<style scoped>
  #square {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    background: white;
    border-style: solid;
    border-width: 2px;
    border-color: #ADEC6E;
    border-radius: 30px;
    height: 36px;
    
  }
  #underline {
    width: 48vw;
    height: 5px;
    background: #C4C4C4;
    /* background: linear-gradient(90deg, #C4C4C4 50%, gray 50%) 반반 나눠서 색칠하기*/
  }
  
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .challenge-make {
    font-size: 13px;
    padding-right: 5px;
  }
  .align-items-center {
    display: flex-inline;
    text-align: center;
    align-items: center;
  }
  .display-inline-grid {
    display: inline-grid;
    align-items: center;
  }

  .community {
    padding: 5px;
    /* margin-left: 2.5%; */
    border-radius: 5px;
    width: 97%;
    height: calc(50vh - 140px);
    border: 1px solid white;
    overflow : scroll;
  }
  .community-title {
    font-size: 11px;
    color: gray;
  }
  .community-inside {
    /* margin-left: 2.5%; */
    padding: 5px;
    padding-left: 10vw;
    padding-top: 3vh;
    font-size: 16;
    font-weight: bold;
    border-radius: 5px;
    width: 98%;
    height: 10vh;
    text-align: start;
    border: 1.5px solid darkgrey;
  }
</style>