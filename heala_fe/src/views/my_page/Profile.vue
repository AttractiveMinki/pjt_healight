<template>
  <div class="profile-container">
    <!-- 별명 로그인한 사용자 ->계정 주인으로 바꾸기, props 사용해야 할 듯 -->
    <Navbar :userIdentity="user.identity" />
    <el-row style="align-items:center">
      <el-col :span="6" style="padding: 4vw">
        <user-image v-if="user.image" :image="user.image" :width=62 :height=62></user-image>
      </el-col>
      <el-col :span="18" style="text-align: -webkit-right; padding: 3vw ">
        <!-- 사용자와 프로필 주인이 같다면 -->
        <span v-if="userId == myId" id="square" style="margin-top: 10px"><router-link :to="{ name: 'ProfileEdit' }" class="text-decoration-none challenge-make">프로필 편집</router-link></span>

        <!-- 사용자와 프로필 주인이 다르다면 -->
        <div v-else class="follow-button">
          <follow :following="isFollowing" :followId="userId" :isFeedFollow=true @follow="follow" @unfollow="unfollow"></follow>
        </div>
      </el-col>
    </el-row>
    <el-row style="text-align: start; padding: 2vw">
      <el-col :span="12">
        <span style="font-size: 14px; font-weight: bold; padding: 2vw"> {{ user.name }}</span>
        <span style="font-size: 11px"> Lv. {{ user.level }}</span>
      </el-col>
    </el-row>
    <el-row style="text-align: start; padding: 0 4vw 2vw 4vw">
      <el-col>
        <span style="font-size: 13px"> {{ user.introduction }}<!-- 나는야 스파르타꾹스. 유튜브 많이 보러 와주세요--></span>
      </el-col>
    </el-row>

    <el-row>
      <div class="follow-info-wrapper">
        <div class="follow-info" @click="following">{{ user.following }} 팔로잉</div>
        <div class="follow-info" @click="follower">{{ user.follower }} 팔로워</div>
      </div>
    </el-row>

    <!-- FollowList -->
    <div class="follow-list-container">
      <follow-list v-if="showFollowingList" :followListType=1 @follow="followBy" @unfollow="unfollowBy"></follow-list>
    </div>
    <div class="follow-list-container">
      <follow-list v-if="showFollowerList" :followListType=2 @follow="followBy" @unfollow="unfollowBy"></follow-list>
    </div>

    <div class="badge-container">
      <div v-if="user.badge.length">
        <div class="badge-image-wrapper" v-for="(badgeItem, idx) in user.badge" v-bind:key="idx">
          <img class="badge-image" :src="imageServer + badgeItem.image" :title=badgeItem.name alt="badge">
        </div>
      </div>
      <div v-else class="badge-notice">
        <span class="no-badge-text">아직 대표 뱃지가 설정되지 않았어요!</span>
        <!-- <span class="padding-left-3vw">
          <router-link :to="{ name: 'ProfileEdit' }" class="text-decoration-none challenge-make">+배지 설정하러 가기</router-link>
        </span> -->
        
      </div>
    </div>

    <el-row class="justify-content-space-between align-items-center">
      <div class="select-tab" :class="{ selected: showFeed }" @click="showFeed = true">
        <div><font-awesome-icon :icon="['far', 'images']" class="select-tab-icon" /></div> 
      </div>
      <div class="select-tab" :class="{ selected: !showFeed }" @click="showFeed = false">
        <div v-if="userId == myId" class="select-tab-title">오늘의 할 일</div>
        <div v-else class="select-tab-title">배지 보관함</div>
      </div>
    </el-row>
    <div v-if="showFeed">
      <Category />
      <profile-post></profile-post>
    </div>
    <div v-else-if="myId == userId">
      <profile-todo-list></profile-todo-list>
    </div>
    <div v-else-if="myId != userId">
      <profile-badge-container></profile-badge-container>
    </div>
    <Footer class="kiwi-footer"></Footer>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js";
import axios from 'axios';
import Navbar from "@/components/my_page/Navbar";
import Category from "@/components/my_page/profile/Category";
import Footer from "@/components/home/Footer";
import FollowList from "@/components/my_page/FollowList";
import Follow from "@/components/my_page/Follow";
import UserImage from "@/components/UserImage";
import ProfileTodoList from "@/components/my_page/profile/ProfileTodoList";
import ProfileBadgeContainer from "@/components/my_page/profile/ProfileBadgeContainer";
import ProfilePost from "@/components/my_page/profile/ProfilePost";
import { mapMutations } from "vuex";

export default {
  name: "Profile",
  data: () => {
    return {
      imageServer: SERVER.IMAGE_URL,
      user: {
        id: 0,
        identity: "",
        image: "",
        name: "",
        exp: 0,
        level: 0,
        introduction: "",
        badge: [],
        follower: 0,
        following: 0,
        exercise: [],
        food: [],
        heart: [],
      },
      // 보고 있는 프로필 계정 주인의 id
      userId: "",
      isFollowing: "",
      
      // 로그인 한 내 아이디
      myId: "",

      showFollowingList: false,
      showFollowerList: false,

      showFeed: true,
    };
  },
  components: {
    Navbar,
    Category,
    Footer,
    Follow,
    FollowList,
    UserImage,
    ProfileTodoList,
    ProfileBadgeContainer,
    ProfilePost,
  },
  created() {
    // footer 4로 설정
    this.$store.state.selectedRouter = 4

    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.params.id;
    // 내 아이디 localStorage에서 가져오기
    this.$store.commit("GET_USERID")
    this.myId = this.$store.state.userId

    this.getProfile();
    this.getFollowing();
  },
  computed: {
    paramUserId() {
      return this.$route.params.id;
    }
  },
  watch: {
    paramUserId() {
      this.$store.commit("GET_USERID")
      this.myId = this.$store.state.userId
      this.userId = this.$route.params.id;
      this.showFollowingList = false;
      this.showFollowerList = false;
      this.getProfile();
      this.getFollowing();
    }
  },
  methods: {
    async getProfile() {
      try {
        let response;
        if(this.userId == this.myId) {
          response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.userProfile}${this.myId}/my`);
        } else {
          response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.userProfile}${this.userId}/${this.myId}`);
        }
        this.user = response.data;
        this.setLevel(this.user.exp);
      } catch (error) {
        console.log(error);
      }
    },
    async getFollowing() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.follow + `?userId=${this.myId}&followId=${this.userId}`);
        this.isFollowing = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    following() {
      if(this.showFollowerList) this.showFollowerList = false;
      this.showFollowingList ^= 1;
    },
    follower() {
      if(this.showFollowingList) this.showFollowingList = false;
      this.showFollowerList ^= 1;
    },
    follow() {
      this.isFollowing = true;
      this.user.follower++;
    },
    unfollow() {
      this.isFollowing = false;
      this.user.follower--;
    },
    followBy() {
      if(this.user.id == this.myId) {
        this.user.following++;
      }
    },
    unfollowBy() {
      if(this.user.id == this.myId) {
        this.user.following--;
      }
    },
    setLevel(exp) {
      for (let i = 0; i < 100; i++) {
        if(exp >= 10000 * i){
          exp -= 10000 * i;
          continue;
        }
        let levelOne = Math.floor(exp / (1000 * i));
        this.user.level = (i - 1) * 10 + levelOne;
        break;
      }
    },
    ...mapMutations([
      'GET_USERID',
    ])
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
  .profile-container {
    font-size: 13px;
  }
  .follow-button {
    position: relative;
    top: 15px;
    text-align: center;
    height: 62px;
  }
  .follow-info-wrapper {
    float: left;
    border-bottom: 1px solid #C4C4C4;
    text-align: left;
    padding: 2px 5px;
  }
  .follow-info {
    display: inline-block;
    font-weight: bold;
    margin: auto 3vw;
  }
  .follow-list-container {
    position: relative;
  }
  .select-tab {
    width: 50%;
    height: 5vh;
    background: #f5f5f5;
    position: relative;
  }
  .select-tab:hover {
    cursor: pointer;
  }
  .select-tab-title {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
  .select-tab-icon {
    margin-top: 1.5vh;
    font-size: 15px;
  }
  .selected {
    background: #ADEC6E;
  }
  .badge-container {
    display: flex;
    align-items: center;
    width: 100%;
    height: 50px;
    text-align: left;
    padding: 0px 5px;
    box-sizing: border-box;
  }
  .badge-image-wrapper {
    width: 30px;
    height: 30px;
    position: relative;
    margin: 10px 5px;
    display: inline-block;
  }
  .badge-image {
    position: absolute;
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  .no-badge-text {
    color: #bebebe;
    padding: 15px;
  }
  .kiwi-footer {
    z-index: 5;
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
  .badge-notice {
    width: 100%;
  }
  .padding-left-3vw {
    padding-left: 3vw;
  }
</style>