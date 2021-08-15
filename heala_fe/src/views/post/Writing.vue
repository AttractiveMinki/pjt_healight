<template>
  <div>
    <!-- 이미지 -->
    <el-row style="display: flex; align-items: center">
      <el-col :span="2">
        <font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" @click="goBack()"/>
      </el-col>
      <el-col :span="22" style="display: flex; justify-content: start; align-items: center; padding-left: 10px;">
        <label class="input-file-button" for="input-file">+</label>
        <input type="file" id="input-file" style="display: none;" @change="selectFile"/>
        <img class="image" src="../../assets/img/writing_upload.png" alt="" @click="dialogVisible.first = true">
        <el-dialog :visible.sync="dialogVisible.first" width="95%">
          <img :src="imgUrl.first" alt="" width="99%">
        </el-dialog>
        <img class="image" src="../../assets/img/writing_upload.png" alt="" @click="dialogVisible.second = true">
        <el-dialog :visible.sync="dialogVisible.second" width="95%">
          <img :src="imgUrl.second" alt="" width="99%">
        </el-dialog>
        <img class="image" src="../../assets/img/writing_upload.png" alt="" @click="dialogVisible.third = true">
        <el-dialog :visible.sync="dialogVisible.third" width="95%">
          <img :src="imgUrl.third" alt="" width="99%">
        </el-dialog>
        <img class="image" src="../../assets/img/writing_upload.png" alt="" @click="dialogVisible.fourth = true">
        <el-dialog :visible.sync="dialogVisible.fourth" width="95%">
          <img :src="imgUrl.fourth" alt="" width="99%">
        </el-dialog>
        <img class="image" src="../../assets/img/writing_upload.png" alt="" @click="dialogVisible.fifth = true">
        <el-dialog :visible.sync="dialogVisible.fifth" width="95%">
          <img :src="imgUrl.fifth" alt="" width="99%">
        </el-dialog>
      </el-col>
    </el-row>
    <hr>
    <!-- 제목 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">제목</el-col>
      <el-col :span="17" style="padding-left: 10px;"><el-input placeholder="제목을 작성해주세요" v-model="title" clearable></el-input></el-col>
      <el-col :span="1"></el-col>
    </el-row><hr>
    <!-- 카테고리 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">카테고리</el-col>
      <el-col :span="3" class="category" style="font-size: 13px;">
        <label for="input-radio" style="cursor: pointer;">운동</label>
        <input @click="category_click" type="radio" id="input-radio" v-model="category" value="0" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="category" style="font-size: 13px;">
        <label for="input-radio2" style="cursor: pointer;">식단</label>
        <input @click="category_click" type="radio" id="input-radio2" v-model="category" value="1" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="category" style="font-size: 13px;">
        <label for="input-radio3" style="cursor: pointer;">마음</label>
        <input @click="category_click" type="radio" id="input-radio3" v-model="category" value="2" style="display: none;"/>
      </el-col>
      <el-col :span="9"></el-col>
    </el-row><hr>
    <!-- 주제 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">주제</el-col>
      <el-col :span="3" class="subject" style="font-size: 13px;">
        <label for="input-radio4" style="cursor: pointer;">피드</label>
        <input @click="subject_click" type="radio" id="input-radio4" v-model="subject" value="0" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="subject" style="font-size: 13px;">
        <label for="input-radio5" style="cursor: pointer;">정보</label>
        <input @click="subject_click" type="radio" id="input-radio5" v-model="subject" value="1" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="subject" style="font-size: 13px;">
        <label for="input-radio6" style="cursor: pointer;">질문</label>
        <input @click="subject_click" type="radio" id="input-radio6" v-model="subject" value="2" style="display: none;"/>
      </el-col>
      <el-col :span="9"></el-col>
    </el-row><hr>
    <!-- 공개범위 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">공개범위</el-col>
      <el-col :span="3" class="scope" style="font-size: 13px;">
        <label for="input-radio7" style="cursor: pointer;">전체</label>
        <input @click="scope_click" type="radio" id="input-radio7" v-model="scope" value="0" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="scope" style="font-size: 13px;">
        <label for="input-radio8" style="cursor: pointer;">팔로우</label>
        <input @click="scope_click" type="radio" id="input-radio8" v-model="scope" value="1" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="scope" style="font-size: 13px;">
        <label for="input-radio9" style="cursor: pointer;">자신</label>
        <input @click="scope_click" type="radio" id="input-radio9" v-model="scope" value="2" style="display: none;"/>
      </el-col>
      <el-col :span="5"></el-col>
      <el-col :span="3" class="scope" style="font-size: 13px;">
        <label v-if="category==2" for="input-radio10" style="cursor: pointer;">익명</label>
        <input @click="anonymous_click" id="input-radio10" v-model="anonymous" style="display: none;" />
      </el-col>
      <el-col :span="1"></el-col>
    </el-row><hr>
    <!-- 내용 -->
    <el-input type="textarea" rows="15" placeholder="글내용을 작성해주세요" v-model="contents"></el-input><hr>
    <!-- 해시태그 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">해시태그</el-col>
      <el-col :span="17" style="padding-left: 10px;"><el-input placeholder="해시태그를 작성해주세요" v-model="hashtag" clearable></el-input></el-col>
      <el-col :span="1"></el-col>
    </el-row>
    <div id="submit" @click="submit()" style="margin-top: 20px; background-color: #ADEC6E; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center; cursor: pointer;">저장</div>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from 'axios';

export default {
  methods: {
    selectFile(e) {
      const file = e.target.files[0];
      document.getElementsByClassName("image")[this.imgCnt].src = URL.createObjectURL(file);
      if(this.imgCnt === 0) this.imgUrl.first = URL.createObjectURL(file);
      else if(this.imgCnt === 1) this.imgUrl.second = URL.createObjectURL(file);
      else if(this.imgCnt === 2) this.imgUrl.third = URL.createObjectURL(file);
      else if(this.imgCnt === 3) this.imgUrl.fourth = URL.createObjectURL(file);
      else if(this.imgCnt === 4) this.imgUrl.fifth = URL.createObjectURL(file);
      this.imgCnt++;
    },
    submit() {
      // FormData에 전송할 데이터 저장
      var formData  = new FormData();
      for(var i = 0; i < this.imgCnt; i++) {
        console.log(document.getElementsByClassName("image")[i]);
        var imgFile = document.getElementsByClassName("image")[i];
        var key = "image[" + i + "]";
        formData.append(key, imgFile);
      }
      formData.append("title", this.title);
      formData.append("contents", this.contents);
      formData.append("hashtag", this.hashtag);
      if (this.anonymous == false || this.category != 2) {
        formData.append("anonymous", false);
      } else {
        formData.append("anonymous", true);
      }
      console.log(formData, 'formData')
      // 서버로 FormData 전송
      axios.post(`${SERVER.URL}${SERVER.ROUTES.feedPost}`, formData, { headers: {'Content-Type' : 'multipart/form-data'}})
        .then(response => {
          if(response.status === 200) {
            console.log("등록 완료");
          }
        })
        .catch(error => {
          console.log(error);
        });
    },
    category_click(e) {
      // if (this.anonymous != false && e.target.defaultValue != 2) {
      //   alert('마음 카테고리만 익명을 사용할 수 있습니다.')
      //   return
      // }
      for(var i = 0; i < 3; i++) {
        document.getElementsByClassName("category")[i].style.fontWeight = "normal";
        document.getElementsByClassName("category")[i].style.color = "black";
      }
      let dom = e.target.parentNode;
      dom.style.fontWeight = "bold";
      dom.style.color = "#ADEC6E";
    },
    subject_click(e) {
      for(var i = 0; i < 3; i++) {
        document.getElementsByClassName("subject")[i].style.fontWeight = "normal";
        document.getElementsByClassName("subject")[i].style.color = "black";
      }
      let dom = e.target.parentNode;
      dom.style.fontWeight = "bold";
      dom.style.color = "#ADEC6E";
    },
    scope_click(e) {
      for(var i = 0; i < 3; i++) {
        document.getElementsByClassName("scope")[i].style.fontWeight = "normal";
        document.getElementsByClassName("scope")[i].style.color = "black";
      }
      let dom = e.target.parentNode;
      dom.style.fontWeight = "bold";
      dom.style.color = "#ADEC6E";
    },
    anonymous_click(e) {
      // if (this.category != 2 && this.anonymous == false) {
      //   alert('마음 카테고리만 익명을 사용할 수 있습니다.')
      //   return
      // }
      let dom = e.target.parentNode;
      if (this.anonymous != false) {
        this.anonymous = false
        dom.style.fontWeight = "normal";
        dom.style.color = "black";
      } else {
        this.anonymous = true
        dom.style.fontWeight = "bold";
        dom.style.color = "#ADEC6E";
      }
    },
    goBack: function () {
      this.$router.go(-1)
    },
  },
  data: () => {
    return {
      imgCnt: 0,
      title: "",
      category: "",
      subject: "",
      scope: "",
      contents: "",
      hashtag: "",
      dialogVisible: {"first":false, "second":false, "third":false, "fourth":false, "fifth":false},
      imgUrl: {"first":"", "second":"", "third":"", "fourth":"", "fifth":""},
      anonymous: "",
    }; 
  },
};
</script>

<style scoped>
  #submit {
    position: fixed;
    bottom: 0rem;
  }
  hr {
    border: 1px solid rgba(240,240,240,1);
  }

  .input-file-button{
    width: 40px;
    height: 40px;
    background-color:#ADEC6E;
    border-radius: 50%;
    color: white;
    margin-right: 35px;
    cursor: pointer;
    font-size: 30px;
    font-weight: bold;
  }

  .image {
    width: 50px;
    height: 50px;
    margin-right: 10px; border-radius: 5px; cursor: pointer;
  }
</style>