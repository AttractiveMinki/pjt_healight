<template>
  <div id="container">
    <Navbar />
    <el-row>
      <el-col :span="24">
        <span style="font-weight: bold; font-size: 20px; margin-bottom: 30px;">프로필 편집</span>
      </el-col>
    </el-row>
    <el-row class="justify-content-right">
      <!-- 회원 탈퇴하기 -->
      <router-link :to="{ name: 'Withdrawal' }">
        <span id="submitwithdrawal" class="go-withdrawal text-decoration-none">회원 탈퇴</span>
      </router-link>
    </el-row>
    <div>
      <!-- 프로필 사진 -->
      <img v-if="data.user.image == ''" src="@/assets/img/profile/user.png" alt="profile_image" width="92" height="92" style="border-radius: 50%;">
      <img v-else-if="this.selectKey != 1" :src="imageServer + data.user.image" alt="profile_image" width="92" height="92" style="border-radius: 50%;">
      <img v-else :src="data.user.image" alt="profile_image" width="92" height="92" style="border-radius: 50%;"><br>
      <label for="change_image" class="btn-file">
        <span style="font-size: 13px; font-weight: bold; color: #ADEC6E;">프로필 사진 변경</span>
      <input type="file" id="change_image" style="display: none;" @change="selectFile" /></label>
      <!-- 이름 -->
      <el-row type="flex" align="middle" style="margin: 30px 0 20px 0;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">이름</el-col>
        <el-col :span="12"><el-input placeholder="이름을 작성해주세요" v-model="data.user.name" clearable></el-input></el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <!-- 아이디 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">아이디</el-col>
        <el-col :span="12"><el-input placeholder="아이디를 작성해주세요" v-model="identity" clearable style="background-color"></el-input></el-col>
        <el-col :span="6"><el-button size="small" type="primary" round style="background-color: white; border: 1px solid #ADEC6E; color: black;" @click="checkIdentity(data.user.identity)">중복확인</el-button></el-col>
      </el-row>
      <div class="error-text" v-if="error.identity">{{ error.identity }}</div>
      <div class="error-text" v-if="error.check_identity">{{ error.check_identity }}</div>
      <!-- 내 소개 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">내 소개</el-col>
        <el-col :span="17"><el-input type="textarea" rows="5" placeholder="내 소개를 작성해주세요" v-model="data.user.introduction"></el-input></el-col>
        <el-col :span="1"></el-col>
      </el-row>
      <!-- 대표 뱃지 설정 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">대표 뱃지 설정</el-col>
        <el-col :span="18">
          <span v-for="(value, idx) in data.badges" :key="idx" >
            <span :class="{selected: value.selected == true}" class="margin-2 post-image" @click="selectBadge(value)">
              <!-- {{ value.badge.image }} -->
              <img :src="imageServer + value.badge.image" alt="" class="post-image">
              <!-- <img :src="imageServer + value.badge.image" alt="" class="post-image"> -->
            </span>
          </span>
          <br>
          <div style="font-size: 13px; color: #606266; margin-top: 2vw">최대 6개 설정 가능합니다</div>
          </el-col>
      </el-row>
      <br>

    </div>
    <button id="submit" class="get-input" @click="uploadImage(data)" :disabled="!isSubmit" :class="{disabled : !isSubmit}">저장</button>

  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from 'axios';
import Navbar from "@/components/my_page/Navbar"
import router from "@/router/index.js"

export default {
  name: "ProfileEdit",
  data: () => {
    return {
      data: {
        badges: [
          {
            badge: {
              id: "",
              image: "",
              name: "",
            },
            badge_id: "",
            id: "",
            selected: "",
            user_id: "",
          }
        ],
        user: {
          id: "",
          name: "",
          identity: "",
          image: "",
          introduction: "",
        },
      },
      error: {
        identity: false,
        check_identity: false,
      },
      check_identity: "",
      isSubmit: false,
      originalIdentity: "",
      imageServer: SERVER.IMAGE_URL,
      identity: "",
      selectKey: 0, // 0이면 web에 있는 프로필 사진 갖고오기, 1이면 변경된 프로필 사진 갖고오기
      originalImage: "",
    };
  },
  methods: {
    selectFile(e) {
      const file = e.target.files[0];
      this.data.user.image = URL.createObjectURL(file);
      // 사진을 변경했으므로 변경한 사진을 보여준다.
      this.selectKey = 1
    },
    uploadImage(data) {
      if (this.originalImage == this.data.user.image) {
        this.submit(data)
      }
      let formData = new FormData()
      let imgFile = document.getElementById("change_image").files[0]
      formData.append("file", imgFile)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.uploadImage}`, formData, { headers: {"Content-Type" : "multipart/form-data"}})
        .then(res => {
          this.data.user.image = res.data
          this.submit(data)
        })
        .catch(err => {
          console.error(err.response.data)
        })
      },
    submit(data) {
      axios.patch(`${SERVER.URL}${SERVER.ROUTES.profile}${this.data.user.id}`, data)
        .then(response => {
          if(response.status === 200) {
            alert('설정 변경이 완료되었습니다.');
            router.push({ name: "Profile", params: { id: this.data.user.id }})
          }
        })
        .catch(error => {
          console.error(error.response.data)
        });
    },
    checkIdentity: function (identity) {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.checkIdentity}${identity}`)
        .then(() => {
          this.error.check_identity = false
          this.checkForm();
      })
        .catch((error) => {
          if (this.originalIdentity == this.data.user.identity){
            this.error.check_identity = false
            this.checkForm();
          }
          else if (error.response.status == 400){
            this.error.check_identity = "이미 존재하는 아이디입니다."
          }
          else{
            this.error.check_identity = "통신에 실패하였습니다."
          }
      })
    },
    watchIdentityChange () {
      if (this.originalIdentity != this.data.user.identity && this.data.user.identity.length >= 1)
      {
        this.error.check_identity = "아이디 중복 확인 버튼을 눌러주세요."
      }
      this.checkForm()
    },
    checkForm() {
      if (this.data.user.identity.length == 0)
        this.error.identity = "아이디를 입력해주세요."
      else this.error.identity = false;
      
      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    selectBadge(badge) {
      let number = 0
      for (let i = 0; i < this.data.badges.length; i++) {
        if (this.data.badges[i].selected == true) {
          number += 1
        }
      }

      if (badge.selected == true) {
        badge.selected = false
      } else {
        if (number < 6) {
          badge.selected = true
        }
      }
    },
  },
  components: {
    Navbar,
  },

  watch: {
    identity: function() {
      this.data.user.identity = this.identity
      this.error.check_identity = false
      this.watchIdentityChange();
    },
  },
  created() {
    this.data.user.id = localStorage.getItem('userId');
    // 프로필 기존 정보 불러오기
    axios.get(`${SERVER.URL}${SERVER.ROUTES.profile}${this.data.user.id}`)
      .then(response => {     
        // this.image = require("@/assets/img/profile/" + response.data.image);
        this.data.user.image = response.data.image
        this.data.user.identity = response.data.identity;
        this.identity = response.data.identity;
        this.data.user.name = response.data.name;
        this.data.user.introduction = response.data.introduction;
        this.data.badges = response.data.badges;
        this.originalImage = response.data.image
        console.log(this.data.user.image)
        console.log(this.data.badges)
        this.originalIdentity = response.data.identity;
      })
      .catch(error => {
        console.log(error);
        console.error(error.response.data);
      });
  },
};
</script>

<style scoped>
  #change_image:hover, #submit {
    cursor: pointer;
  }
  #submit {
    position: fixed;
    bottom: 0rem;
  }
  #container {
    margin-bottom: 50px;
  }
  
  .btn-file{
    position: relative;
    overflow: hidden;
  }

  .btn-file input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    min-width: 100%;
    min-height: 100%;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
  }
  .justify-content-right {
    display: grid;
    justify-content: right;
  }
  .error-text {
    color: red;
    margin-bottom: 10px;
  }
  .disabled {
    background: gray !important;
    color: gainsboro !important;
    cursor: default !important;
  }
  .get-input {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 50px;
    background-color: #ADEC6E;
    border: 0px;
    color: black;
  }
  .go-withdrawal {
    display: inline;
    align-items: center;
    justify-content: center;
    font-size: 13px;
    width: 100%;
    height: 3vh;
    background-color: red;
    color: white;
    padding: 1vw;
    margin: 2vw;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .selected {
    border: 2px #7EE01D solid;
  }
  .margin-2 {
    margin: 2px;
  }
  .post-image {
    width: 6%;
  }
</style>