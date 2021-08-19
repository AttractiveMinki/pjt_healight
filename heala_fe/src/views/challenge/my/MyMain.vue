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
              <div style="text-align: start; font-size: 11px">
                달성률: {{ with_challenge.achievement }}%
              </div>
              <div style="display: flex; justify-content: start; align-items: center; padding-left: 10px;">
                <!-- 작게 보기 -->
                <img v-if="certify.image == ''" class="image" src="@/assets/img/writing_upload.png" alt="">
                <img v-else :src="certify.image" style="width: 25px; height: 25px; margin-right: 5px" alt="certify_image" @click="dialogVisible.first = true">
                <!-- 크게 보기 -->
                <el-dialog :visible.sync="dialogVisible.first" width="95%">
                  <img :src="certify.image" alt="certify_image" width="99%">
                </el-dialog>

                <button class="my-pictures-button" @click="goToCertifyImage(with_challenge.id)">내 인증사진 <br> 모아보기</button>
                
              </div>
            </el-col>
            <el-col :span="6">
              <label for="input-file">
                <font-awesome-icon icon="image" class="input-file-button" alt="사진을 선택하세요." />
                <div class="margin-tb">사진 첨부하기</div>
              </label>
              <input type="file" id="input-file" style="display: none;" @change="selectFile"/>
            </el-col>
            <button id="submit" class="my-pictures-button" @click="uploadImage(with_challenge.id)">인증하기</button>
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
      certify: {
        image: "",
        userId: "",
        withChallengeId: "",
      },
      imgCnt: 0,
      title: "",
      category: "",
      subject: "",
      scope: "",
      contents: "",
      hashtag: "",
      dialogVisible: {"first":false},
      imgUrl: {"first":""},
      values: "",
    }
  },
  components: {
    Navbar,
    ChallengeContainerMy,
    Footer,
  },
  created() {
    this.$store.commit("GET_USERID");
    this.certify.userId = this.$store.state.userId
    this.getMyChallenge();
    this.$store.state.selectedRouter = 3;
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
      this.certify.image = URL.createObjectURL(file);
      console.log(this.certify.image)
    },
    uploadImage(withChallengeId) {
      let formData = new FormData()
      let imgFile = document.getElementById("input-file").files[0]
      formData.append("file", imgFile)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.uploadImage}`, formData, { headers: {"Content-Type" : "multipart/form-data"}})
        .then(res => {
          this.certify.image = res.data
          this.certify.withChallengeId = withChallengeId
          this.submit(this.certify)
        })
        .catch(err => {
          alert("이미지 업로드에 실패했습니다.");
          console.error(err.response.data)
        })
      },
    submit(certify) {
      axios.post(SERVER.URL + SERVER.ROUTES.certify, certify)
        .then(response => {
          if(response.status === 200) {
            alert("등록 완료");
          }
        })
        .catch(error => {
          alert("오류가 발생했습니다.");
          console.log(error);
        });
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
    font-size: 15px;
    margin-bottom: 5px;
    white-space : nowrap; /* 한 줄 제한*/
    overflow : hidden; /* 넘어가는 글자 숨기기 */
    text-overflow: ellipsis; /* 말 줄임표 추가 */
    font-family: 'TmoneyRoundWindRegular';
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
    height: 30px;
    background-color:#ADEC6E;
    color: white;
    cursor: pointer;
    font-size: 20px;
    font-weight: bold;
    margin-top: 10px;
  }
  .image {
    width: 20px;
    height: 20px;
    margin-right: 10px; border-radius: 5px; cursor: pointer;
  }
  .margin-tb {
    font-size: 11px;
    margin-bottom: 10px;;
  }
</style>