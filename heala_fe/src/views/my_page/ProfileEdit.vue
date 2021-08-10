<template>
  <div>
    <Navbar />
    <div style="font-weight: bold; font-size: 20px; margin-bottom: 30px;">프로필 편집</div>
    <div>
      <!-- 프로필 사진 -->
      <img v-if="image == ''" src="@/assets/img/profile/user.png" alt="profile_image" width="92" height="92" style="border-radius: 50%;">
      <img v-else :src="image" alt="profile_image" width="92" height="92" style="border-radius: 50%;"><br>
      <label for="image" class="btn-file"><span style="font-size: 13px; font-weight: bold; color: #ADEC6E;">프로필 사진 변경</span><input name="image" type="file" @change="selectFile" id="change_image"/></label>
      <!-- 이름 -->
      <el-row type="flex" align="middle" style="margin: 30px 0 20px 0;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">이름</el-col>
        <el-col :span="12"><el-input placeholder="이름을 작성해주세요" v-model="name" clearable></el-input></el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <!-- 아이디 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">아이디</el-col>
        <el-col :span="12"><el-input placeholder="아이디를 작성해주세요" v-model="identity" clearable style="background-color"></el-input></el-col>
        <el-col :span="6"><el-button size="small" type="primary" round style="background-color: white; border: 1px solid #ADEC6E; color: black;" @click="checkIdentity(identity)">중복확인</el-button></el-col>
      </el-row>
      <div class="error-text" v-if="error.identity">{{ error.identity }}</div>
      <div class="error-text" v-if="error.check_identity">{{ error.check_identity }}</div>
      <!-- 내 소개 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">내 소개</el-col>
        <el-col :span="17"><el-input type="textarea" rows="5" placeholder="내 소개를 작성해주세요" v-model="introduction"></el-input></el-col>
        <el-col :span="1"></el-col>
      </el-row>
      <!-- 대표 뱃지 설정 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">대표 뱃지 설정</el-col>
        <el-col :span="18">
          <span v-for="(badge, idx) in badges" :key="idx" >
            <span :class="{selected: badge.selected == true}" class="margin-2" @click="selectBadge(badge)">
              <!-- {{ badge.badge.id}}
              이름: {{ badge.badge.name}} -->
              {{ badge.badge.image}}
            </span>
          </span>
          <br>
          <div style="font-size: 13px; color: #606266;">최대 6개 설정 가능합니다</div>
          </el-col>
      </el-row>
    </div>
    <button id="submit" class="get-input" @click="submit()" :disabled="!isSubmit" :class="{disabled : !isSubmit}">저장</button>

  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from 'axios';
import Navbar from "@/components/my_page/Navbar"
import router from "@/router/index.js"

export default {
  name: "ProfileEdit",
  methods: {
    selectFile(e) {
      const file = e.target.files[0];
      this.image = URL.createObjectURL(file);
    },
    submit() {
      // FormData에 전송할 데이터 저장
      let formData  = new FormData();
      let imgFile = document.getElementById('change_image');
      formData.append('image', imgFile.files[0]);
      formData.append('name', this.name);
      formData.append('identity', this.identity);
      formData.append('introduction', this.introduction);
      // console.log(this.$store.state.userid)
      // for (var key of formData.keys()) {
      //   console.log(key, '--key');
      // }

      // for (var value of formData.values()) {
      //   console.log(value, '--value');
      // }

      
      // 서버로 FormData 전송
      // axios.patch("http://localhost:8080/user/profile", formData , { headers: {'Content-Type' : 'multipart/form-data'}})
      axios.patch(`${SERVER.URL}${SERVER.ROUTES.userProfile}${localStorage.getItem('userId')}`, formData , { headers: {'Content-Type' : 'multipart/form-data'}})
        .then(response => {
          if(response.status === 200) {
            alert('설정 변경이 완료되었습니다.');
            router.push({ name: "Profile" })
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
          if (this.originalIdentity == this.identity){
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
    checkForm() {
      if (this.identity.length == 0)
        this.error.identity = "아이디를 입력해주세요."
      else this.error.identity = false;

      if (this.originalIdentity != this.identity && this.identity.length <= 1)
        this.error.check_identity = "아이디 중복 확인 버튼을 눌러주세요."
      
      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    selectBadge(badge) {
      let number = 0
      for (let i = 0; i < this.badges.length; i++) {
        if (this.badges[i].selected == true) {
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
  data: () => {
    return {
      image: "",
      name: "",
      identity: "",
      introduction: "",
      badges: [],
      error: {
        identity: false,
        check_identity: false,
      },
      check_identity: "",
      isSubmit: false,
      originalIdentity: "",
    };
  },
  watch: {
    identity: function() {
      this.error.check_identity = "아이디 중복 확인 버튼을 눌러주세요."
      this.checkForm();
    },
  },
  mounted() {
    // 프로필 기존 정보 불러오기
    axios.get(`${SERVER.URL}${SERVER.ROUTES.userProfile}${localStorage.getItem('userId')}`)
      .then(response => {     
        // this.image = require("@/assets/img/profile/" + response.data.image);
        this.identity = response.data.identity;
        this.name = response.data.name;
        this.introduction = response.data.introduction;
        this.badges = response.data.badges;
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
    display:flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 50px;
    background-color: #ADEC6E;
    color: white;
  }
  .selected {
    border: 2px #7EE01D solid;
  }
  .margin-2 {
    margin: 2px;
  }
</style>