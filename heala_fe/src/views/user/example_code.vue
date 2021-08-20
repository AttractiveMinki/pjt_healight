<template>
  <div>
    <div style="font-weight: bold; font-size: 20px; margin-bottom: 30px;">프로필 편집</div>
    <div>
      <!-- 프로필 사진 -->
      <img :src="image" alt="profile_image" width="92" height="92" style="border-radius: 50%;"><br>
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
        <el-col :span="6"><el-button size="small" type="primary" round style="background-color: white; border: 1px solid #ADEC6E; color: black;">중복확인</el-button></el-col>
      </el-row>
      <!-- 내 소개 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">내 소개</el-col>
        <el-col :span="17"><el-input type="textarea" rows="5" placeholder="내 소개를 작성해주세요" v-model="introduction"></el-input></el-col>
        <el-col :span="1"></el-col>
      </el-row>
      <!-- 대표 뱃지 설정 -->
      <el-row type="flex" align="middle" style="margin-bottom: 20px;">
        <el-col :span="6" style="font-weight: bold; font-size: 13px;">대표 뱃지 설정</el-col>
        <el-col :span="18"><div style="font-size: 13px; color: #606266;">최대 6개 설정 가능합니다</div></el-col>
      </el-row>
    </div>
    <div id="submit" @click="submit" style="background-color: #ADEC6E; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center;">저장</div>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  methods: {
    selectFile(e) {
      const file = e.target.files[0];
      this.image = URL.createObjectURL(file);
    },
    submit() {
      // FormData에 전송할 데이터 저장
      var formData  = new FormData();
      var imgFile = document.getElementById("change_image");
      formData.append("image", imgFile.files[0]);
      formData.append("name", this.name);
      formData.append("identity", this.identity);
      formData.append("introduction", this.introduction);
      // 서버로 FormData 전송
      axios.patch("http://localhost:8080/user/profile", formData , { headers: {'Content-Type' : 'multipart/form-data'}})
        .then(response => {
          if(response.status === 200) {
            alert('등록이 완료되었습니다.')
          }
        })
        .catch(error => {
          alert('정보를 제출하던 중 오류가 발생했습니다.')
        });
    },
  },
  data: () => {
    return {
      image: "https://newsimg.hankookilbo.com/cms/articlerelease/2021/06/17/b8223d84-e72f-42a3-a984-0ae23ba70aa4.jpg",
      name: "",
      identity: "",
      introduction: "",
    };
  },
};
</script>

<style scoped>
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
</style>