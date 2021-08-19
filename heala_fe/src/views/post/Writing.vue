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
        <!--
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
        </el-dialog> -->
      </el-col>
    </el-row>
    <hr>
    <!-- 제목 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">제목</el-col>
      <el-col :span="17" style="padding-left: 10px;"><el-input placeholder="제목을 작성해주세요" v-model="data.post.title" clearable></el-input></el-col>
      <el-col :span="1"></el-col>
    </el-row><hr>
    <!-- 카테고리 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">카테고리</el-col>
      <el-col :span="3" class="category" style="font-size: 13px;">
        <label for="input-radio" style="cursor: pointer;">운동</label>
        <input @click="category_click" type="radio" id="input-radio" v-model="data.post.category" value="0" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="category" style="font-size: 13px;">
        <label for="input-radio2" style="cursor: pointer;">식단</label>
        <input @click="category_click" type="radio" id="input-radio2" v-model="data.post.category" value="1" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="category" style="font-size: 13px;">
        <label for="input-radio3" style="cursor: pointer;">마음</label>
        <input @click="category_click" type="radio" id="input-radio3" v-model="data.post.category" value="2" style="display: none;"/>
      </el-col>
      <el-col :span="9"></el-col>
    </el-row><hr>
    <!-- 주제 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">주제</el-col>
      <el-col :span="3" class="subCategory" style="font-size: 13px;">
        <label for="input-radio4" style="cursor: pointer;">피드</label>
        <input @click="subCategory_click" type="radio" id="input-radio4" v-model="data.post.subCategory" value="0" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="subCategory" style="font-size: 13px;">
        <label for="input-radio5" style="cursor: pointer;">정보</label>
        <input @click="subCategory_click" type="radio" id="input-radio5" v-model="data.post.subCategory" value="1" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="subCategory" style="font-size: 13px;">
        <label for="input-radio6" style="cursor: pointer;">질문</label>
        <input @click="subCategory_click" type="radio" id="input-radio6" v-model="data.post.subCategory" value="2" style="display: none;"/>
      </el-col>
      <el-col :span="9"></el-col>
    </el-row><hr>
    <!-- 공개범위 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">공개범위</el-col>
      <el-col :span="3" class="access" style="font-size: 13px;">
        <label for="input-radio7" style="cursor: pointer;">전체</label>
        <input @click="access_click" type="radio" id="input-radio7" v-model="data.post.access" value="0" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="access" style="font-size: 13px;">
        <label for="input-radio8" style="cursor: pointer;">팔로우</label>
        <input @click="access_click" type="radio" id="input-radio8" v-model="data.post.access" value="1" style="display: none;"/>
      </el-col>
      <el-col :span="3" class="access" style="font-size: 13px;">
        <label for="input-radio9" style="cursor: pointer;">비공개</label>
        <input @click="access_click" type="radio" id="input-radio9" v-model="data.post.access" value="2" style="display: none;"/>
      </el-col>
      <el-col :span="5"></el-col>
      <el-col :span="3" class="access" style="font-size: 13px;">
        <label v-if="data.post.category==2" for="input-radio10" style="cursor: pointer;">익명</label>
        <input @click="anonymous_click" id="input-radio10" v-model="data.post.anonymous" style="display: none;" />
      </el-col>
      <el-col :span="1"></el-col>
    </el-row><hr>
    <!-- 내용 -->
    <textarea class="textarea" rows="15" placeholder="글 내용을 작성해주세요" v-model="data.post.content"></textarea><hr>
    <!-- 해시태그 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 16px; font-size: 13px; font-weight: bold;">해시태그</el-col>
      <el-col :span="17" style="padding-left: 10px;"><el-input placeholder="#달리기 #추천_메뉴" v-model="data.hashtag.word" clearable></el-input></el-col>
      <el-col :span="1"></el-col>
    </el-row>
    <div id="submit" @click="checkData(data)" style="background-color: #ADEC6E; color: white; width: 100%; height: 50px; display:flex; align-items: center; justify-content: center; cursor: pointer;">작성</div>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from 'axios';
import router from "@/router/index.js"

export default {
  name: "Writing",
  data: () => {
    return {
      // imgCnt: 0,
      data: {
        post: {
          title: "",
          category: "",
          subCategory: "",
          access: "",
          anonymous: "",
          content: "",
          image: "",
          userId: "",
        },
        hashtag: {
          word: "",
        }
      },
     
      dialogVisible: {"first":false, "second":false, "third":false, "fourth":false, "fifth":false},
      imgUrl: {"first":"", "second":"", "third":"", "fourth":"", "fifth":""},
    }; 
  },
  methods: {
    selectFile(e) {
      const file = e.target.files[0];
      // 클릭했을 때 미리보기
      this.imgUrl.first = URL.createObjectURL(file);

      // 작은 칸 미리보기
      document.getElementsByClassName("image")[0].src = URL.createObjectURL(file);

      // document.getElementsByClassName("image")[this.imgCnt].src = URL.createObjectURL(file);
      // if(this.imgCnt === 0) this.imgUrl.first = URL.createObjectURL(file);
      // else if(this.imgCnt === 1) this.imgUrl.second = URL.createObjectURL(file);
      // else if(this.imgCnt === 2) this.imgUrl.third = URL.createObjectURL(file);
      // else if(this.imgCnt === 3) this.imgUrl.fourth = URL.createObjectURL(file);
      // else if(this.imgCnt === 4) this.imgUrl.fifth = URL.createObjectURL(file);
      // this.imgCnt++;
    },
    checkData(data) {
      if (document.getElementById("input-file").files[0] == undefined) {
        alert('이미지를 입력하세요.')
      }
      else if (data.post.title == "" ) {
        alert('제목을 입력하세요.')
      }
      else if (data.post.title.length > 40 ) {
        alert('제목은 40자 이내로 작성해주세요.')
      }
      else if (data.post.category == "") {
        alert('카테고리를 선택하세요.')
      }
      else if (data.post.subCategory == "") {
        alert('주제를 선택하세요.')
      }
      else if (data.post.access == "") {
        alert('공개범위를 선택하세요.')
      }
      else if (data.post.content == "") {
        alert('글 내용을 작성하세요.')
      }
      else if (data.post.content.length > 1500) {
        alert('내용은 1500자 이내로 작성해주세요.')
      }
      else if (data.hashtag.word.length > 20) {
        alert('해시태그는 20자 이내로 작성해주세요.')
      }
      else {
        this.uploadImage(data)
      }
    },
    uploadImage(data) {
      let formData = new FormData()
      let imgFile = document.getElementById("input-file").files[0]
      formData.append("file", imgFile)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.uploadImage}`, formData, { headers: {"Content-Type" : "multipart/form-data"}})
        .then(res => {
          this.data.post.image = res.data
          this.submit(data)
        })
        .catch(err => {
          alert('이미지를 업로드하는 도중 에러가 발생했습니다.')
          console.log('통신 실패')
          console.error(err.response.data)
        })
    // submit(res)
    },
    submit(data) {
      if (data.anonymous == false || data.category != 2) {
        data.post.anonymous = 0
      } else {
        data.post.anonymous = 1
      }
      data.post.userId = this.$store.state.userId
      // 서버로 FormData 전송
      axios.post(`${SERVER.URL}${SERVER.ROUTES.feedPost}`, data)
        .then(response => {
          if(response.status === 200) {
            console.log(data)
            console.log(response)
            alert('등록이 완료되었습니다.')
            router.push({ name: "CommunityNewMain" })
          }
        })
        .catch(err => {
          if (err.response.status == 500) {
            alert('글자 수 제한을 확인해주세요.\n 제목: 40자, 내용: 1500자, 해시태그: 20자')
          } else {
          alert('오류가 발생했습니다.')
          console.error(err.response.data)
          }
        });
    },
    category_click(e) {
      for(var i = 0; i < 3; i++) {
        document.getElementsByClassName("category")[i].style.fontWeight = "normal";
        document.getElementsByClassName("category")[i].style.color = "black";
      }
      let dom = e.target.parentNode;
      dom.style.fontWeight = "bold";
      dom.style.color = "#ADEC6E";
    },
    subCategory_click(e) {
      for(var i = 0; i < 3; i++) {
        document.getElementsByClassName("subCategory")[i].style.fontWeight = "normal";
        document.getElementsByClassName("subCategory")[i].style.color = "black";
      }
      let dom = e.target.parentNode;
      dom.style.fontWeight = "bold";
      dom.style.color = "#ADEC6E";
    },
    access_click(e) {
      for(var i = 0; i < 3; i++) {
        document.getElementsByClassName("access")[i].style.fontWeight = "normal";
        document.getElementsByClassName("access")[i].style.color = "black";
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
  created () {
    this.$store.commit("GET_USERID")
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
  .textarea {
    border: none;
    resize: none;
    width: 100%;
    height: calc(60vh - 92px - 3rem);
  }
  .textarea:focus {
    outline: none;
  }
</style>