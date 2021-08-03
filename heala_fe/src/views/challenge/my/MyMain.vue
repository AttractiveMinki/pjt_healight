<template>
  <div>
    <Navbar />
    <ChallengeContainerMy />
    <el-row class="community">
      <el-col :span="24" style="text-align: end; padding: 1px;" class="community-title">
        <i class="margin-left-10 el-icon-arrow-right"></i>
        <span>진행중인 챌린지</span>
      </el-col>
      <!-- 이하 내가 참가하고 있는 챌린지 명단을 받아온 뒤에 갱신 -->
      <el-col class="community-box">
        <el-col :span="24" v-for="(with_challenge, idx) in with_challenges" :key="idx" class="community-inside ">
          <div v-if="with_challenge.category == 0">
            <div @click="SetCurrentPageId(with_challenge.id)">
              <el-col :span="18" class="margin-left-10">
                <router-link :to="{ name: 'WithDetail' , params: { id: with_challenge.id } }" class="text-decoration-none">
                  <div class="text-align-start text-title">{{ with_challenge.title }}</div>
                  <br>
                  <div class="text-align-start text-content">{{ with_challenge.introduction }}</div>
                  <el-col class="display-flex justify-content-space-between"><span class="community-title">{{ with_challenge.start_date  }}</span> <span class="fix-width"><font-awesome-icon :icon="['far', 'star']" class="padding-right"/>{{ with_challenge.end_date  }}</span></el-col>
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
                  <button class="my-pictures-button">내 인증사진 모아보기</button>
                  
                </div>
              </el-col>
                
                <!-- 인증하기 -->
                <!-- <div style="display: flex; justify-content: start; align-items: center; padding-left: 10px;">
                  <label class="input-file-button" for="input-file">+</label>
                  <input type="file" id="input-file" style="display: none;" @change="selectFile"/>
                  <img class="image" src="../../assets/img/writing_upload.png" alt="" @click="dialogVisible.first = true">
                </div> -->
                <el-col :span="6">
                  <label class="input-file-button" for="input-file">+</label>
                  <input type="file" id="input-file" style="display: none;" @change="selectFile"/>
                  {{ with_challenge.image }}
                  <el-image class="margin-left-10"
                    style="width: 100%; height: 80px"
                    :src="with_challenge.image" 
                    >
                  </el-image>
                </el-col>
              
            </div>
          </div>
        </el-col>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import Navbar from "@/components/challenge/Navbar"
import ChallengeContainerMy from "@/components/challenge/my/ChallengeContainerMy"
import { mapState } from "vuex"
import axios from 'axios';

export default {
  name: "MyMain",
  components: {
    Navbar,
    ChallengeContainerMy,
  },
  methods: {
    SetCurrentPageId: function (getId) {
      console.log('클릭 감지1')
      this.$store.state.currentPageId = getId
      console.log('클릭 감지2')
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
    submit() {
      // FormData에 전송할 데이터 저장
      var formData  = new FormData();
      for(var i = 0; i < this.imgCnt; i++) {
        console.log(document.getElementsByClassName("image")[i]);
        var imgFile = document.getElementsByClassName("image")[i];
        var key = "image[" + i + "]";
        formData.append(key, imgFile.files[0]);
      }
      formData.append("title", this.title);
      formData.append("contents", this.contents);
      formData.append("hashtag", this.hashtag);
      // 서버로 FormData 전송
      axios.post("http://localhost:8080/feed/post", formData, { headers: {'Content-Type' : 'multipart/form-data'}})
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
  },
  computed: {
    ...mapState([
      "with_challenges",
    ])
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
    }; 
  },
}
</script>

<style scoped>
  .community {
    padding: 5px;
    /* margin-left: 2.5%; */
    border-radius: 5px;
    width: 100%;
    height: calc(80vh - 140px);
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
  .text-align-start {
    text-align: start;
  }
  .text-title {
    font-weight: bold; 
    font-size: 25px;
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
  .my-pictures-button {
    width: 25vw;
    height: 5vh;
    background: white;
    border: 2px solid #ADEC6E;
    padding: 2px;
    font-size: 10px;
  }

  .input-file-button{
    width: 40px;
    height: 40px;
    background-color:#ADEC6E;
    border-radius: 50%;
    color: white;
    margin-right: 75px;
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