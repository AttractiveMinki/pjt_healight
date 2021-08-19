<template>
  <div>
    <div>
      <kiwi-header>
        <template v-slot:title>글쓰기
        </template>
      </kiwi-header>
    </div>
    <!-- 이미지 -->
    <el-row type="flex" align="middle" style="padding: 0.5vh 0">
      <el-col :span="6" class="community" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">사진</el-col>
      <el-col :span="18" style="display: flex; justify-content: start; align-items: center; padding-left: 10px;">
        <label class="input-file-button" for="input-file">+</label>
        <input type="file" id="input-file" style="display: none;" @change="selectFile"/>
        <img v-if="editing && !imageChanged" class="image" :src="imageServer + data.post.image" alt="" @click="dialogVisible.first = true">
        <img v-else-if="imageChanged" class="image" :src="imgUrl.first" alt="" @click="dialogVisible.first = true">
        <img v-else class="image" src="../../assets/img/writing_upload.png" alt="" @click="dialogVisible.first = true">
        <el-dialog :visible.sync="dialogVisible.first" width="95%">
          <img v-if="!editing || imageChanged" :src="imgUrl.first" alt="" width="99%">
          <img v-if="editing && !imageChanged" :src="imageServer + data.post.image" alt="" width="99%">
        </el-dialog>
      </el-col>
    </el-row>
    <hr>
    <!-- 제목 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">제목</el-col>
      <el-col :span="17" style="padding-left: 10px;"><el-input placeholder="제목을 작성해주세요" v-model="data.post.title" clearable></el-input></el-col>
      <el-col :span="1"></el-col>
    </el-row><hr>
    <!-- 카테고리 -->
    <el-row type="flex" align="middle" style="padding: 0.5vh 0">
      <el-col :span="6" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">카테고리</el-col>
      <el-col :span="4" class="category" style="font-size: 14px;">
        <div :class="{ selected: data.post.category == 0}" @click="data.post.category = 0">운동</div>
      </el-col>
      <el-col :span="4" class="category" style="font-size: 14px;">
        <div :class="{ selected: data.post.category == 1}" @click="data.post.category = 1">식단</div>
      </el-col>
      <el-col :span="4" class="category" style="font-size: 14px;">
        <div :class="{ selected: data.post.category == 2}" @click="data.post.category = 2">마음</div>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row><hr>
    <!-- 주제 -->
    <el-row type="flex" align="middle" style="padding: 0.5vh 0">
      <el-col :span="6" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">주제</el-col>
      <el-col :span="4" class="subCategory" style="font-size: 14px;">
        <div :class="{ selected: data.post.subCategory == 0}" @click="data.post.subCategory = 0">일반</div>
      </el-col>
      <el-col :span="4" class="subCategory" style="font-size: 14px;">
        <div :class="{ selected: data.post.subCategory == 1}" @click="data.post.subCategory = 1">정보</div>
      </el-col>
      <el-col :span="4" class="subCategory" style="font-size: 14px;">
        <div :class="{ selected: data.post.subCategory == 2}" @click="data.post.subCategory = 2">질문</div>
      </el-col>
      <el-col :span="6"></el-col>
    </el-row><hr>
    <!-- 공개범위 -->
    <el-row type="flex" align="middle" style="padding: 0.5vh 0">
      <el-col :span="6" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">공개범위</el-col>
      <el-col :span="4" class="access" style="font-size: 14px;">
        <div :class="{ selected: data.post.access == 0}" @click="data.post.access = 0">전체</div>
      </el-col>
      <el-col :span="4" class="access" style="font-size: 14px;">
        <div :class="{ selected: data.post.access == 1}" @click="data.post.access = 1">친구</div>
      </el-col>
      <el-col :span="4" class="access" style="font-size: 14px;">
        <div :class="{ selected: data.post.access == 2}" @click="data.post.access = 2">비공개</div>
      </el-col>
      <el-col :span="2"></el-col>
      <el-col :span="3" class="access" style="font-size: 14px;">
        <div v-if="data.post.category==2" :class="{ selected: data.post.anonymous}" @click="data.post.anonymous ^= 1">익명</div>
      </el-col>
      <el-col :span="1"></el-col>
    </el-row><hr>
    <!-- 내용 -->
    <textarea class="textarea" style="padding-left: 30px; font-size: 14px;" placeholder="글 내용을 작성해주세요" v-model="data.post.content"></textarea>
    <hr>
    <!-- 해시태그 -->
    <el-row type="flex" align="middle">
      <el-col :span="6" style="text-align: left; padding-left: 30px; font-size: 14px; font-weight: bold;">해시태그</el-col>
      <el-col :span="17" style="padding-left: 10px;"><el-input placeholder="#달리기 #추천_메뉴" v-model="data.hashtag.word" clearable></el-input></el-col>
      <el-col :span="1"></el-col>
    </el-row>
    <div class="rest"></div>
    <div id="submit" @click="checkData()">저장</div>
  </div>
</template>

<script>
import KiwiHeader from "@/components/KiwiHeader";
import SERVER from "@/api/drf.js"
import axios from 'axios';

export default {
  name: "Writing",
  data: () => {
    return {
      // imgCnt: 0,
      data: {
        post: {
          title: "",
          category: 0,
          subCategory: 0,
          access: 0,
          anonymous: false,
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
    
      postId: 0,
      editing: false,
      imageChanged: false,
      originalImage: "",
      imageServer: SERVER.IMAGE_URL,
    };
  },
  created () {
    if(this.$route.params.postId) {
      this.editing = true;
      this.postId = this.$route.params.postId;
      this.getPost();
      this.getHashtag();
    }
    this.$store.commit("GET_USERID");
  },
  methods: {
    async getPost() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.post + `${this.postId}`);
        this.data.post = response.data;
        this.originalImage = response.data.image;
      } catch (error) {
        console.log(error);
      }
    },
    async getHashtag() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.postHashtag + `${this.postId}`);
        this.data.hashtag.word = "";
        let hashtags = response.data;
        if(hashtags.length) {
          for(let i = 0; i < hashtags.length; i++) {
            this.data.hashtag.word = this.data.hashtag.word.concat(hashtags[i] + " ");
          }
        }
      } catch (error) {
        console.log(error);
      }
    },
    selectFile(e) {
      const file = e.target.files[0];
      document.getElementsByClassName("image")[0].src = URL.createObjectURL(file);
      this.imageChanged = true;
      this.imgUrl.first = URL.createObjectURL(file);
    },
    checkData() {
      if (this.data.post.image == "" && document.getElementById("input-file").files[0] == undefined) {
        this.$alert("이미지를 입력하세요.");
      }
      else if (this.data.post.title == "" ) {
        this.$alert("제목을 입력하세요.");
      }
      else if (this.data.post.title.length > 40 ) {
        this.$alert("제목은 40자 이내로 작성해주세요.");
      }
      else if (this.data.post.content == "") {
        this.$alert("글 내용을 작성하세요.");
      }
      else if (this.data.post.content.length > 1500) {
        this.$alert("내용은 1500자 이내로 작성해주세요.");
      }
      else if (this.data.hashtag.word.length > 20) {
        this.$alert("해시태그는 20자 이내로 작성해주세요.");
      }
      else {
        this.uploadImage()
      }
    },
    uploadImage() {
      if(this.$route.params.postId && this.originalImage == this.data.post.image) {
        this.submit();
        return;
      }
      let formData = new FormData()
      let imgFile = document.getElementById("input-file").files[0]
      formData.append("file", imgFile)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.uploadImage}`, formData, { headers: {"Content-Type" : "multipart/form-data"}})
        .then(res => {
          this.data.post.image = res.data
          this.submit()
        })
        .catch(err => {
          this.$alert("이미지를 업로드하는 도중 에러가 발생했습니다.");
          console.log('통신 실패')
          console.error(err.response.data)
        })
    // submit(res)
    },
    async submit() {
      let data = this.data;
      if (data.post.anonymous == false || data.post.category != 2) {
        data.post.anonymous = 0
      } else {
        data.post.anonymous = 1
      }
      data.post.userId = this.$store.state.userId;
      try {
        if(this.$route.params.postId) {
          await axios.patch(SERVER.URL + SERVER.ROUTES.feedPost + `?postId=${this.$route.params.postId}`, data);
        }
        else await axios.post(`${SERVER.URL}${SERVER.ROUTES.feedPost}`, data);
        this.$alert("등록이 완료되었습니다.");
        if(this.editing) {
          this.$router.go(-1);
        } else {
          this.$router.push({ name: "CommunityNewMain" })
        }
      } catch (error) {
        // alert('글자 수 제한을 확인해주세요.\n 제목: 40자, 내용: 1500자, 해시태그: 20자')
        console.log(error);
      }
    },
    goBack: function () {
      this.$router.go(-1)
    },
  },
  components: {
    KiwiHeader,
  }
};
</script>

<style scoped>
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

hr {
  border-top: 2px solid rgba(240,240,240,1);
  border-left: 0px;
  border-right: 0px;
  border-bottom: 0px;
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
.input-post-image {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
.image {
  width: 50px;
  height: 50px;
  margin-right: 10px; border-radius: 5px; cursor: pointer;
}
.selected {
  font-weight: bold;
  color: #ADEC6E;
}
.textarea {
  border: none;
  resize: none;
  width: 100%;
  height: 40vh;
  padding: 5px 15px;
  box-sizing: border-box;
}
.textarea:focus {
  outline: none;
}
.rest {
  height: 48px;
}
.el-message-box {
  width: 90% !important;
}
</style>