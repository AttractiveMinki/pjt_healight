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
        <span style="font-weight: bold">챌린지</span> <br>
        <span style="font-weight: bold">대표 이미지</span>
      </el-col>
      <el-col :span="18">
        <!-- <img :src="image" alt="profile_image" width="92" height="92" style="border-radius: 50%;"><br>
        <label for="image" class="btn-file"><span style="font-size: 13px; font-weight: bold; color: #ADEC6E;">챌린지 대표 이미지 변경</span><input name="image" type="file" @change="selectFile" id="change_image"/></label> -->
        <input type="text" v-model="data.title" style="width:90%" placeholder="대표 이미지 URL을 입력하세요.">
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">제목</span>
      </el-col>
      <el-col :span="18">
        <input type="text" v-model="data.title" style="width:90%" placeholder="예) 하루 1시간 운동하기">
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">카테고리</span>
      </el-col>
      <el-col :span="18">
        <el-button size="small" id="category" @click="changeCategory(0)">운동</el-button>
        <el-button size="small" id="category" @click="changeCategory(1)">식단</el-button>
        <el-button size="small" id="category" @click="changeCategory(2)">마음</el-button>
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">챌린지 기간</span>
      </el-col>
      <el-col :span="18">
        <input type="date" style="width:35%" v-model="data.start_date" placeholder="시작 날짜를 선택하세요." onfocus="(this.type='date')" id="date">
        <span> ~ </span>
        <input type="date" style="width:35%" v-model="data.end_date" placeholder="종료 날짜를 선택하세요.">
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">인증 방법</span>
      </el-col>
     <el-col :span="18">
        <textarea style="width:90%; height:50px" v-model="data.certify_info" placeholder="예) 오늘 날짜와 운동 기구 혹은 운동 결과가 보이는 인증샷 찍기"></textarea>
      </el-col>
    </el-row>
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">챌린지 소개</span>
      </el-col>
     <el-col :span="18" type="flex">
        <textarea style="width:90%; height:150px" v-model="data.introduction" placeholder="예) 매일 꾸준한 운동으로 기초 체력을 기릅시다!"></textarea>
      </el-col>
    </el-row>  
    <div id="square" class="margin-line"></div>
    <el-row>
      <el-col :span="6" class="community">
        <span style="font-weight: bold">해시태그</span>
      </el-col>
      <el-col :span="18">
        <input type="text" v-model="data.hashtags" style="width:90%" placeholder="예) #매일 #운동 #가족같은분위기">
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
        // id: "",

        image: "",
        title: "",
        category: -1,
        start_date: "",
        end_date: "",
        certify_info: "",
        introduction: "",
        user_id:"",
        hashtags: "",
        // certify_day: "",
        // certify_week: "",
      },
      isvisible: false,
    }
  },
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
    changeCategory: function (num) {
      this.data.category = num
    },
    checkChallenge: function (data) {
      let start = new Date(data.start_date)
      let end = new Date(data.end_date)
      // data.user_id = userid
      if (data.title.length == 0){
        alert('제목을 작성해주세요.')
      }
      else if (data.category == -1){
        alert('카테고리를 선택해주세요.', data.category)
        console.log(data.category)
      }
      else if (data.start_date == ''){
        alert('챌린지 시작 날짜를 선택해주세요.', data.start_date)
      }
      else if (data.end_date == ''){
        alert('챌린지 종료 날짜를 선택해주세요.', data.end_date)
      }
      else if (start > end) {
        alert('시작 날짜는 종료 날짜보다 일러야 합니다.')
      }
      else if (data.certify_info.length == 0){
        alert('인증 방법을 작성해주세요.')
      }
      else if (data.introduction.length == 0){
        alert('챌린지 소개를 작성해주세요.')
      }
      else if (data.image == "") {
        alert('챌린지 대표 이미지를 설정해주세요.')
      }
      else {
        this.createChallenge(data)
      }
      
    },
    createChallenge: function (data) {
      axios.post(`${SERVER.URL}${SERVER.ROUTES.withmake}`, data)
        .then(() => {
          alert('성공적으로 글이 작성되었습니다.')
          router.push({ name: "WithMain" })
        })
        .catch((err) => {
          alert('전송 실패')
          console.log('에러가 발생했습니다.')
          console.error(err)
        })
    },
    // selectFile(e) {
    //   const file = e.target.files[0];
    //   this.image = URL.createObjectURL(file);
    // },
    // submit() {
    //   // FormData에 전송할 데이터 저장
    //   var formData  = new FormData();
    //   var imgFile = document.getElementById("change_image");
    //   formData.append("image", imgFile.files[0]);
    //   formData.append("name", this.name);
    //   formData.append("identity", this.identity);
    //   formData.append("introduction", this.introduction);
    //   // 서버로 FormData 전송
    //   axios.patch("http://localhost:8080/user/profile", formData , { headers: {'Content-Type' : 'multipart/form-data'}})
    //     .then(response => {
    //       if(response.status === 200) {
    //         console.log("등록 완료");
    //       }
    //     })
    //     .catch(error => {
    //       console.log(error);
    //     });
    // },
  },
  watch: {
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
</style>