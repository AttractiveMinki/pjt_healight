<template>
  <div class="my-main">
    <Navbar />
    <ChallengeContainerMy />
    <el-row class="community">
      <el-col :span="24" style="text-align: end; padding: 1px;" class="community-title">
        <i class="margin-left-10 el-icon-arrow-right"></i>
        <!-- v-if with_challenge.achievement == true 이런식 혹은 endDate가 현재 날짜보다 미래일 때 -->
        <span>진행중인 챌린지</span>
      </el-col>

      <el-col class="community-box">
        <el-col :span="24" v-for="(with_challenge, idx) in values" :key="idx" class="community-inside ">
          <div @click="SetCurrentPageId(with_challenge.id)">
            <el-col :span="18" class="margin-left-10">
              <router-link :to="{ name: 'WithDetail' , params: { id: with_challenge.id } }" class="text-decoration-none">
                <div class="text-align-start text-title">{{ with_challenge.title }}</div>
                <div class="text-align-start text-overflow">
                  <span v-for="(hashtag, idx1) in with_challenge.hashtag" :key="idx1" class="text-hashtag">
                    <span>#{{ hashtag }} </span>
                  </span>
                </div>
                <el-col style="text-align: start"><span>{{ with_challenge.startDate }} ~ {{ with_challenge.endDate  }}</span></el-col>
              </router-link>
              <div style="display: flex; justify-content: start; align-items: center; padding-left: 10px;">
                <img class="image" src="@/assets/img/writing_upload.png" alt="" @click="dialogVisible.first = true">
                <el-dialog :visible.sync="dialogVisible.first" width="95%">
                  <img :src="imgUrl.first" alt="" width="99%">
                </el-dialog>
                <img class="image" src="@/assets/img/writing_upload.png" alt="" @click="dialogVisible.second = true">
                <el-dialog :visible.sync="dialogVisible.second" width="95%">
                  <img :src="imgUrl.second" alt="" width="99%">
                </el-dialog>
                <img class="image" src="@/assets/img/writing_upload.png" alt="" @click="dialogVisible.third = true">
                <el-dialog :visible.sync="dialogVisible.third" width="95%">
                  <img :src="imgUrl.third" alt="" width="99%">
                </el-dialog>
                <img class="image" src="@/assets/img/writing_upload.png" alt="" @click="dialogVisible.fourth = true">
                <el-dialog :visible.sync="dialogVisible.fourth" width="95%">
                  <img :src="imgUrl.fourth" alt="" width="99%">
                </el-dialog>
                <button class="my-pictures-button" @click="goToCertifyImage(with_challenge.id)">내 인증사진 <br> 모아보기</button>
                
              </div>
            </el-col>
            <el-col :span="6">
              <label class="input-file-button" for="input-file">+</label>
              <input type="file" id="input-file" style="display: none;" @change="selectFile"/>
              <!-- <img src="" alt="">
              {{ with_challenge.image }} -->
            </el-col>
            <button id="submit" class="my-pictures-button" @click="uploadImage(with_challenge.id)" :class="{ disabled : !isSubmit }">인증하기</button>
          </div>
        </el-col>
      </el-col>
    </el-row>
    <Footer />
  </div>
</template>

<script>
import Navbar from "@/components/challenge/Navbar"
import ChallengeContainerMy from "@/components/challenge/my/ChallengeContainerMy"
import Footer from "@/components/home/Footer"
import { mapState } from "vuex"
import SERVER from "@/api/drf.js"
import axios from 'axios';

export default {
  name: "MyMain",
  data() {
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
      values: "",
      userId: 1,
    }
  },
  components: {
    Navbar,
    ChallengeContainerMy,
    Footer,
  },
  created() {
    this.$store.commit("GET_USERID");
    this.getMyChallenge();
  },
  methods: {
    goToCertifyImage(withChallengeId) {
      this.$router.push({ name: "CertifyImage", params: { withChallengeId }})
    },
    SetCurrentPageId: function (getId) {
      this.$store.state.currentPageId = getId
    },
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
    uploadImage(withChallengeId) {
      let formData = new FormData()
      let imgFile = document.getElementById("input-file").files[0]
      formData.append("file", imgFile)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.uploadImage}`, formData, { headers: {"Content-Type" : "multipart/form-data"}})
        .then(res => {
          this.submit(res.data, withChallengeId)
        })
        .catch(err => {
          console.error(err.response.data)
        })
      },
    submit(certifyImage, withChallengeId) {
      axios.post(SERVER.URL + SERVER.ROUTES.certify, {
        image: certifyImage,
        userId: this.userId,
        withChallengeId,
      })
        .then(response => {
          if(response.status === 200) {
            alert('등록 완료')
          }
        })
        .catch(error => {
          console.log(error);
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
    getMyChallenge: function () {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.getMyChallenge}${this.$store.state.userId}`)
        .then((res) => {
          this.values = res.data
        })
        .catch((err) => {
          console.log(err)
        })
    },
  },
  computed: {
    ...mapState([
      "with_challenges",
    ])
  },
}
</script>

<style scoped>
  .community {
    padding: 5px;
    /* margin-left: 2.5%; */
    border-radius: 5px;
    width: 100%;
    height: calc(98vh - 162px - 3rem);
    border: 3px solid #ADEC6E;
    overflow : scroll;
  }
  .community-title {
    font-size: 10px;
    color: gray;
  } 
  .community-inside {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    width: 100%;
    border: 1.5px solid darkgrey;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .text-align-start {
    text-align: start;
  }
  .text-title {
    font-weight: bold; 
    font-size: 16px;
    margin-bottom: 5px;
    white-space : nowrap; /* 한 줄 제한*/
    overflow : hidden; /* 넘어가는 글자 숨기기 */
    text-overflow: ellipsis; /* 말 줄임표 추가 */
  }
  .text-content {
    font-size: small;
    margin-bottom: 3px;
    overflow : hidden; 
    text-overflow: ellipsis; 
    word-wrap: break-word; /* 단어 단위로 줄 바꿈*/
    display: -webkit-box; /* 유연하게 height 증감시킬 수 있는 flex-box 형태로 변환*/
    -webkit-line-clamp: 2; /* 보여줄 줄 수 */
    -webkit-box-orient: vertical; /* 플렉스 박스의 방향 설정(가로) */
  }
  .text-overflow {
    white-space : nowrap ; /* 한 줄 제한*/
    overflow : hidden ; /* 넘어가는 글자 숨기기 */
    text-overflow: ellipsis ; /* 말 줄임표 추가 */
  }
  .text-hashtag {
    font-size: 12px;
    color: #1A8EFA;
    width: 100%;
    margin-bottom: 3px;
    display: contents;
  }
  .my-pictures-button {
    /* width: 25vw; */
    /* height: 5vh; */
    background: white;
    border: 2px solid #ADEC6E;
    padding: 1px 5px;
    font-size: 10px;
  }
  .input-file-button{
    width: 40px;
    height: 40px;
    background-color:#ADEC6E;
    border-radius: 50%;
    color: white;
    cursor: pointer;
    font-size: 30px;
    font-weight: bold;
  }
  .image {
    width: 20px;
    height: 20px;
    margin-right: 10px; border-radius: 5px; cursor: pointer;
  }
</style>