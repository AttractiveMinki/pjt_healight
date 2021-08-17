<template>
  <div>
    <!-- 별명 로그인한 사용자 ->계정 주인으로 바꾸기, props 사용해야 할 듯 -->
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
    <!-- <el-row style="text-align: space-between; font-size: 12px">
      <el-col :span="12">
        <span style="padding: 2vw"> <span style="font-weight: bold; font-size: 13px; padding: 1vw">231--><!-- {{ user.following }} --><!--</span>팔로잉</span>  
        <span style="padding: 2vw"> <span style="font-weight: bold; font-size: 13px; padding: 1vw">583--><!-- {{ user.follower }} --><!--</span>팔로워</span>
      </el-col>
    </el-row>
    <div id="underline"></div> -->

    <el-row>
      <!-- 팔로우/팔로잉 수 구하는 통신 필요함 -->
      <div class="follow-info-wrapper">
        <div class="follow-info" @click="following">231 팔로잉</div>
        <div class="follow-info" @click="follower">583 팔로워</div>
      </div>
    </el-row>

    <!-- FollowList -->
    <div class="follow-list-container">
      <follow-list v-if="showFollowingList" :followListType=1></follow-list>
    </div>
    <div class="follow-list-container">
      <follow-list v-if="showFollowerList" :followListType=2></follow-list>
    </div>

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
      <img class="image" src="@/assets/image/heart/heart_silver.png" alt="heart badge" style="width: 10vw; padding: 2vw">
      <el-dialog width="95%">
        <img alt="" width="99%">
      </el-dialog>
    </el-row>

    <el-row class="justify-content-space-between align-items-center">
      <el-col :span="12" style="background: #ADEC6E; height: 5vh">
        <router-link :to="{ name: 'Profile', params: { id: userId } }" class="text-decoration-none selected-category"><font-awesome-icon :icon="['far', 'images']" style="margin-top: 1.5vh" /></router-link> 
      </el-col>
      <el-col :span="11" v-if="userId == myId">
        <!-- 내 계정이라면 -->
        <router-link :to="{ name: 'ProfileTodoList', params: { id: userId } }" class="text-decoration-none">오늘의 할 일</router-link> 
      </el-col>
      <!-- 내 계정이 아니라면 -->
      <el-col :span="11" v-else>
        <router-link :to="{ name: 'ProfileBadgeContainer', params: { id: userId } }" class="text-decoration-none">배지 보관함</router-link> 
      </el-col>
    </el-row>
    <Category />
    <Footer class="kiwi-footer"></Footer>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from 'axios';
import Navbar from "@/components/my_page/Navbar"
import Category from "@/components/my_page/profile/Category"
import Footer from "@/components/home/Footer"
import FollowList from "@/components/my_page/FollowList"

export default {
  name: "Profile",
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

      showFollowingList: false,
      showFollowerList: false,
    };
  },
  // props: {
  //   id: {
  //     type: String,
  //   }
  // },
  components: {
    Navbar,
    Category,
    Footer,
    FollowList,
  },
  created() {
    // footer 4로 설정
    this.$store.state.selectedRouter = 4
  
    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.path.split('/')[2]

    // 내 아이디 localStorage에서 가져오기
    this.myId = localStorage.getItem('userId')

    // 내 꺼 보기
    if (this.userId == this.myId) {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.userProfile}${this.myId}/my`)
        .then(response => {     
          // this.image = require("@/assets/img/profile/" + response.data.image);
          this.user = response.data
        })
        .catch(error => {
          console.log(error);
          console.error(error.response.data);
        });
    }
    // 다른 사람꺼 보기
    else {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.userProfile}${this.userId}/${this.myId}`)
        .then(response => {     
          // this.image = require("@/assets/img/profile/" + response.data.image);
          this.user = response.data
        })
        .catch(error => {
          console.log(error);
          console.error(error.response.data);
        });
    }
  },
  methods: {
    following() {
      if(this.showFollowerList) this.showFollowerList = false;
      this.showFollowingList ^= 1;
    },
    follower() {
      if(this.showFollowingList) this.showFollowingList = false;
      this.showFollowerList ^= 1;
    }
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
  height: 1px;
  background: #C4C4C4;
  /* background: linear-gradient(90deg, #C4C4C4 50%, gray 50%) 반반 나눠서 색칠하기*/
}
.follow-info-wrapper {
  float: left;
  border-bottom: 1px solid #C4C4C4;
  text-align: left;
  padding: 2px 5px;
}
.follow-info {
  display: inline-block;
  font-size: 13px;
  font-weight: bold;
  margin: auto 5px;
}
.follow-list-container {
  position: relative;
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
  display: flex;
  align-items: center;
}
.kiwi-footer {
  z-index: 5;
}
</style>