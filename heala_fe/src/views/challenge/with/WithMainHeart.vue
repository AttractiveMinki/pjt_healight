<template>
  <div>
    <Navbar />
    <ChallengeContainerWith />
    <el-row class="community">
      <el-col :span="24">
        <el-row>
          <el-col :span="8" class="container-box-detail">
            <router-link router-link :to="{ name: 'WithMain' }" class="text-decoration-none">운동</router-link> 
          </el-col>
          <el-col :span="8" class="container-box-detail">
            <router-link router-link :to="{ name: 'WithMainDiet' }" class="text-decoration-none">식단</router-link> 
          </el-col>
          <el-col :span="8" class="container-box-detail selected-category">
            <router-link router-link :to="{ name: 'WithMainHeart' }" class="text-decoration-none">마음</router-link>
            <div id="square"></div>
          </el-col>
        </el-row>
        <el-col class="community-box">
          <el-col :span="24" v-for="(value, idx) in withChallenges" :key="idx" class="community-inside ">
            <div v-if="value.withChallenge.category == 2">
              <div @click="SetCurrentPageId(value.withChallenge.id)">
                <router-link :to="{ name: 'WithDetail' , params: { id: value.withChallenge.id } }" class="text-decoration-none">
                  <el-col :span="18" class="margin-left-10">
                    <div class="text-align-start text-title">{{ value.withChallenge.title }}</div>
                    <div class="text-align-start text-content">{{ value.withChallenge.introduction }}</div>
                    <div class="text-align-start text-overflow">
                      <span v-for="(hashtag, idx2) in value.hashtags" :key="idx2">
                        <span class="text-align-start text-hashtag">#{{ hashtag }} </span>
                      </span>
                    </div>
                  </el-col>
                  <el-col :span="6">
                    <el-image class="margin-left-10"
                      style="width: 100%; height: 80px"
                      :src="imageServer + value.withChallenge.image" 
                      >
                    </el-image>
                  </el-col>
                </router-link>
              </div>
            </div>
          </el-col>
        </el-col>
        <infinite-loading @infinite="infiniteHandler" spineer="waveDots">
          <div slot="no-more"></div>
          <div slot="no-results"></div>
        </infinite-loading>
      </el-col>
    </el-row>   
    <Footer />
  </div>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import Navbar from "@/components/challenge/Navbar"
import ChallengeContainerWith from "@/components/challenge/with/ChallengeContainerWith"
import Footer from "@/components/home/Footer"
import SERVER from "@/api/drf.js"
import axios from "axios"
import { mapState } from "vuex"

export default {
  name: "WithMain",
  data: () => {
    return {
      withChallenges: [],
      limit: 0,
    };
  },
  components: {
    Navbar,
    ChallengeContainerWith,
    Footer,
    InfiniteLoading,
  },
  methods: {
    SetCurrentPageId: function (getId) {
      this.$store.state.currentPageId = getId
    },
    async getWithHeartChallenge () {
      try {
        const res = await axios.get(`${SERVER.URL}${SERVER.ROUTES.getWithHeartChallenge}&page=${this.limit}`)
        this.withChallenges = res.data
      } catch(err) {
        console.log(err)
      }
    },
    async infiniteHandler($state) {
      const EACH_LEN = 10;
      this.limit += 1
      const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.getWithHeartChallenge}&page=${this.limit}`)
      setTimeout(() => {
        if (response.data != undefined && response.data.length) {
          this.withChallenges = this.withChallenges.concat(response.data)
          $state.loaded() // 데이터 로딩

          if (response.data.length < EACH_LEN) {
            $state.complete()
          }
        }
        else {
          $state.complete()
        }
      }, 300)
    },
  },
  computed: {
    ...mapState([
      "recent_challenges",
    ])
  },
  mounted: function () {
    this.getWithHeartChallenge()
    this.$store.state.selectedRouter = 3
    this.$store.state.currentPageCategory = 2

  },
}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 5px;
    background: #8CBE88;
  }
  #recent-square {
    display: flex;
    align-items: center;
    border-radius: 30px;
    width: 98%;
    height: 34px;
    border: 3px solid darkgrey;
  }
  #recent-square-detail {
    border-radius: 30px;
    width: 98%;
    height: 22px;
    border: 3px solid darkgrey;
  }
  .community {
    padding: 5px;
    /* margin-left: 2.5%; */
    border-radius: 5px;
    width: 100%;
    height: calc(98vh - 162px - 3rem);
    border: 3px solid #ADEC6E;
    overflow : scroll;
  }
  .community-box {
    /* margin-left: 2.5%; */
    padding: 5px;
    border-radius: 5px;
    width: 100%;
    border: 1.5px solid darkgrey;
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
  .text-align-end {
    text-align: end;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .text-title {
    font-weight: bold; 
    font-size: 15px;
    margin-bottom: 5px;
    white-space : nowrap; /* 한 줄 제한*/
    overflow : hidden; /* 넘어가는 글자 숨기기 */
    text-overflow: ellipsis; /* 말 줄임표 추가 */
  }
  .recent-title {
    white-space : nowrap; /* 한 줄 제한*/
    overflow : scroll; /* 넘어가는 글자 넘기기 */
    /* overflow : hidden; 넘어가는 글자 숨기기 */
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
  .text-hashtag {
    font-size: small;
    color: #1A8EFA;
    width: 100%;
    margin-bottom: 3px;
    display: contents; 
  }
  .text-overflow {
    white-space : nowrap ; /* 한 줄 제한*/
    overflow : hidden ; /* 넘어가는 글자 숨기기 */
    text-overflow: ellipsis ; /* 말 줄임표 추가 */
  }
  .padding-1 {
    padding: 1%;
  }
  .community-title-13 {
    font-size: 13px;
  }
  .community-title {
    font-size: 10px;
    color: gray;
  } 
  .selected-category {
    background: #8CBE88;
  }
  .container-box-detail {
    padding-top: 5px;
    border-radius: 5px;
    height: 30px;
  }
</style>