<template>
  <div>
    <div>
      <kiwi-header>
        <template v-slot:title>챌린지 만들기
        </template>
      </kiwi-header>
    </div>
    <div class="display-flex justify-content-space-between align-items">
    </div>
    <el-row type="flex" align="middle">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">
        대표 사진
      </el-col>
      <!-- <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">제목</el-col>
      <el-col :span="17" style="padding-left: 10px;"><el-input placeholder="제목을 작성해주세요" v-model="data.post.title" clearable></el-input></el-col>
      <el-col :span="1"></el-col>
    </el-row><hr> -->
      <el-col :span="18">
        <img v-if="data.withChallenge.image == ''" src="@/assets/img/challenge.png" alt="profile_image" width="92" height="92" style="border-radius: 10%; border-style: solid; border-color: #f0f0f0">
        <img v-else :src="data.withChallenge.image" alt="profile_image" width="92" height="92" style="border-radius: 10%;"><br>
        <label for="image" class="btn-file">
          <span style="font-size: 13px; font-weight: bold; color: #76D418;">이미지 선택하기</span>
          <input name="image" type="file" @change="selectFile" id="change_image"/>
        </label>
      </el-col>
    </el-row>
    <hr>
    <el-row type="flex" align="middle">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">
        제목
      </el-col>
      <el-col :span="17" style="padding-left: 10px;">
        <el-input type="text" class="get-input" v-model="data.withChallenge.title" style="width:90%" placeholder="예) 하루 1시간 운동하기"></el-input>
      </el-col>
    </el-row>
    <hr>
    <el-row type="flex" align="middle">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">
        카테고리
      </el-col>
      <el-col :span="17" style="padding-left: 10px;" >
        <el-button size="medium" id="category" class="getCategory" @click="changeCategory(0)">운동</el-button>
        <el-button size="medium" id="category" class="getCategory" @click="changeCategory(1)">식단</el-button>
        <el-button size="medium" id="category" class="getCategory" @click="changeCategory(2)">마음</el-button>
      </el-col>
    </el-row>
    <hr>
    <el-row type="flex" align="middle">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">
        기간 설정
      </el-col>
      <el-col :span="17" style="padding-left: 10px;">
        <el-input type="date" class="get-input" style="width:43%" v-model="data.withChallenge.startDate" placeholder="시작 날짜를 선택하세요." onfocus="(this.type='date')" id="date"></el-input>
        <span> ~ </span>
        <el-input type="date" class="get-input" style="width:43%" v-model="data.withChallenge.endDate" placeholder="종료 날짜를 선택하세요."></el-input>
      </el-col>
    </el-row>
    <hr>
    <el-row type="flex" align="middle">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">
        인증 방법
      </el-col>
     <el-col :span="17" style="padding-left: 10px;">
        <el-input type="textarea" class="get-input" style="width:90%; height:55px; resize: none" v-model="data.withChallenge.certifyInfo" placeholder="예) 오늘 날짜와 운동 기구 혹은 운동 결과가 보이는 인증샷 찍기"></el-input>
      </el-col>
    </el-row>
    <hr>
    <el-row type="flex" align="middle">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">
        소개글
      </el-col>
     <el-col :span="17" style="padding-left: 10px;">
        <el-input type="textarea" rows="9" class="get-input" style="width:90%; height:200px; resize: none; border-color: rgb(225 225 225); border-radius: 5px" v-model="data.withChallenge.introduction" placeholder="예) 매일 꾸준한 운동으로 기초 체력을 기릅시다!"></el-input>
      </el-col>
    </el-row>  
    <hr>
    <el-row type="flex" align="middle">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">
        해시태그
      </el-col>
      <el-col :span="17" style="padding-left: 10px;">
        <el-input type="text" class="get-input" v-model="data.challengeHashtag.word" style="width:90%" placeholder="예) #매일 #운동 #가족같은분위기"></el-input>
      </el-col>
    </el-row>       
      <!-- <el-button @click="checkChallenge(data)" size="small" type="primary" round style="background-color: #ADEC6E; border: 1px solid #ADEC6E; color: black;">만들기</el-button> -->
      <div id="submit" @click="checkChallenge(data)">만들기</div>
  <div style="height: 8vh"></div>
  </div>
</template>

<script>
import KiwiHeader from "@/components/KiwiHeader";
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
          this.createChallenge(data)
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
  created () {
    this.$store.commit("GET_USERID")
    this.data.withChallenge.userId = this.$store.state.userId
  },
  components: {
    KiwiHeader,
  }
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
  hr {
    border: 1px solid #f0f0f0;
  }
  #submit {
  position: fixed;
  bottom: 0px;
  background-color: #ADEC6E;
  color: black;
  width: 100%;
  height: 48px;
  display:flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-family: TmoneyRoundWindRegular;
}
textarea::placeholder {
	color: #cecfd1;
}
</style>