<template>
  <div>
    <div class="display-flex justify-content-space-between align-items">
      <span><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" @click="goBack()"/></span>
      <span class="text-title">챌린지 만들기</span>
      <!-- <button class="button-color">작성</button> -->
      <el-button @click="checkChallenge(data)" size="small" type="primary" round style="background-color: #ADEC6E; border: 1px solid #ADEC6E; color: black;">작성</el-button>
    </div>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold; font-size: 15px">챌린지</span> <br>
        <span style="font-weight: bold; font-size: 15px">대표 이미지</span>
      </el-col>
      <el-col :span="18">
        <img v-if="data.withChallenge.image == ''" src="@/assets/img/profile/user.png" alt="profile_image" width="92" height="92" style="border-radius: 50%;">
        <img v-else :src="data.withChallenge.image" alt="profile_image" width="92" height="92" style="border-radius: 50%;"><br>
        <label for="image" class="btn-file">
          <span style="font-size: 13px; font-weight: bold; color: #ADEC6E;">챌린지 대표 이미지 변경</span>
          <input name="image" type="file" @change="selectFile" id="change_image"/>
        </label>
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">제목</span>
      </el-col>
      <el-col :span="18">
        <input type="text" class="get-input" v-model="data.withChallenge.title" style="width:90%" placeholder="예) 하루 1시간 운동하기">
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">카테고리</span>
      </el-col>
      <el-col :span="18" >
        <el-button size="small" id="category" class="getCategory" @click="changeCategory(0)">운동</el-button>
        <el-button size="small" id="category" class="getCategory" @click="changeCategory(1)">식단</el-button>
        <el-button size="small" id="category" class="getCategory" @click="changeCategory(2)">마음</el-button>
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold; font-size: 15px">챌린지 기간</span>
      </el-col>
      <el-col :span="18">
        <input type="date" class="get-input" style="width:35%" v-model="data.withChallenge.startDate" placeholder="시작 날짜를 선택하세요." onfocus="(this.type='date')" id="date">
        <span> ~ </span>
        <input type="date" class="get-input" style="width:35%" v-model="data.withChallenge.endDate" placeholder="종료 날짜를 선택하세요.">
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">인증 방법</span>
      </el-col>
     <el-col :span="18">
        <textarea class="get-input" style="width:90%; height:50px; resize: none" v-model="data.withChallenge.certifyInfo" placeholder="예) 오늘 날짜와 운동 기구 혹은 운동 결과가 보이는 인증샷 찍기"></textarea>
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold; font-size: 15px">챌린지 소개</span>
      </el-col>
     <el-col :span="18" type="flex">
        <textarea class="get-input" style="width:90%; height:150px; resize: none" v-model="data.withChallenge.introduction" placeholder="예) 매일 꾸준한 운동으로 기초 체력을 기릅시다!"></textarea>
      </el-col>
    </el-row>  
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">해시태그</span>
      </el-col>
      <el-col :span="18">
        <input type="text" class="get-input" v-model="data.challengeHashtag.word" style="width:90%" placeholder="예) #매일 #운동 #가족같은분위기">
      </el-col>
    </el-row>       
  </div>
</template>

<script>
import axios from 'axios'
import SERVER from "@/api/drf.js"
import router from "@/router/index.js"

export default {
  name: "WithMake",
  data: function () {
    return {
      data: {
        withChallenge: {
          // id: "",
          image: "",
          title: "",
          category: -1,
          startDate: "",
          endDate: "",
          certifyInfo: "",
          introduction: "",
          userId:"",
          hashtags: "",
          // certify_day: "",
          // certify_week: "",
        },
        challengeHashtag: {
          id: "",
          word: ""
        },
      },
      isvisible: false,
    }
  },
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
    changeCategory: function (num) {
      this.data.withChallenge.category = num
      this.category_click()
    },
    category_click () {
      for(let i = 0; i < 3; i++) {
        if (i != this.data.withChallenge.category) {
          document.getElementsByClassName("getCategory")[i].style.fontWeight = "normal"
          document.getElementsByClassName("getCategory")[i].style.color = "black"
        } else {
          document.getElementsByClassName("getCategory")[i].style.fontWeight = "bold"
          document.getElementsByClassName("getCategory")[i].style.color = "#ADEC6E"
        }
      }
    },
    checkChallenge: function (data) {
      let start = new Date(data.withChallenge.startDate)
      let end = new Date(data.withChallenge.endDate)
      // data.user_id = userid
      if (data.withChallenge.title.length == 0){
        alert('제목을 작성해주세요.')
      }
      else if (data.withChallenge.category == -1){
        alert('카테고리를 선택해주세요.')
      }
      else if (data.withChallenge.startDate == ''){
        alert('챌린지 시작 날짜를 선택해주세요.')
      }
      else if (data.withChallenge.endDate == ''){
        alert('챌린지 종료 날짜를 선택해주세요.')
      }
      else if (start > end) {
        alert('시작 날짜는 종료 날짜보다 일러야 합니다.')
      }
      else if (data.withChallenge.certifyInfo.length == 0){
        alert('인증 방법을 작성해주세요.')
      }
      else if (data.withChallenge.introduction.length == 0){
        alert('챌린지 소개를 작성해주세요.')
      }
      else if (data.withChallenge.image == "") {
        alert('챌린지 대표 이미지를 설정해주세요.')
      }
      else {
        this.uploadImage(data)
      }
    },
    uploadImage(data) {
      let formData = new FormData()
      let imgFile = document.getElementById("change_image").files[0]
      formData.append("file", imgFile)
      console.log(`${SERVER.URL}${SERVER.ROUTES.uploadImage}`)
      console.log(imgFile)
      console.log(imgFile.file)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.uploadImage}`, formData, { headers: {"Content-Type" : "multipart/form-data"}})
        .then(res => {
          data.withChallenge.image = res.data
          // this.createChallenge(data)
        })
        .catch(err => {
          console.error(err.response.data)
        })
      },
    createChallenge: function (data) {
      axios.post(`${SERVER.URL}${SERVER.ROUTES.withMake}`, data)
        .then(() => {
          alert('성공적으로 글이 작성되었습니다.')
          router.push({ name: "WithMain" })
        })
        .catch((err) => {
          alert('전송 실패')
          console.error(err)
        })
    },
    selectFile(e) {
      const file = e.target.files[0];
      this.data.withChallenge.image = URL.createObjectURL(file);
    },
  },
  mounted () {
    this.data.withChallenge.userId = localStorage.getItem('userId')
  },
}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 2px;
    background: darkgrey;
  }
  #category {
    border-width: 0px;
  }
  #change_image:hover, #submit {
    cursor: pointer;
  }

  .btn-file{
    position: relative;
    overflow: hidden;
  }
  .btn-file input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
  }
  .cursor-pointer {
    cursor: pointer;
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
  .margin-line {
    margin: 10px 0px;
  }
  .padding-left {
    padding-left: 4%;
  }
  .text-title {
    font-size: 25px;
    font-weight: bold;
  }
  .button-color {
    background: #ADEC6E;
    border: 1.5px solid #ADEC6E;
    border-radius: 15px;
  }
  .get-input:focus {
    outline: none; 
  }
</style>