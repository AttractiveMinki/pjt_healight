<template>
  <div>
    <Navbar />
    <ChallengeContainerWith />
    <el-row class="community">
      <el-col :span="12" class="text-align-start padding-1 community-title-13">
        <span>최근 본 챌린지</span>
      </el-col>
      <el-col :span="12" class="text-align-end padding-1 community-title">
        <i class="margin-left-10 el-icon-arrow-right"></i>
        <span>전체 보기</span>
      </el-col>
      <el-col style="margin-bottom: 5px">
        <div id="recent-square" class="recent-title">
          <span v-for="(recent_challenge, idx) in recent_challenges" :key="idx" id="recent-square-detail" style="margin: 3px">
            {{ recent_challenge.title }}
            <!-- 클릭 시 상세 페이지로 이동 -->
          </span>
        </div>
      </el-col>

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
          <el-col :span="24" v-for="(with_challenge, idx) in with_challenges" :key="idx" class="community-inside ">
            <div v-if="with_challenge.category == 2">
              <div @click="SetCurrentPageId(with_challenge.id)">
                <router-link :to="{ name: 'WithDetail' , params: { id: with_challenge.id } }" class="text-decoration-none">
                  <el-col :span="18" class="margin-left-10">
                    <div class="text-align-start text-title">{{ with_challenge.title }}</div>
                    <br>
                    <div class="text-align-start text-content">{{ with_challenge.introduction }}</div>
                    <el-col class="display-flex justify-content-space-between"><span class="community-title">{{ with_challenge.start_date  }}</span> <span class="fix-width"><font-awesome-icon :icon="['far', 'star']" class="padding-right"/>{{ with_challenge.end_date  }}</span></el-col>
                  </el-col>
                  <el-col :span="6">
                    {{ with_challenge.image }}
                    <el-image class="margin-left-10"
                      style="width: 100%; height: 80px"
                      :src="with_challenge.image" 
                      >
                    </el-image>
                  </el-col>
                </router-link>
              </div>
            </div>
          </el-col>
        </el-col>
      </el-col>
    </el-row>   
  </div>
</template>

<script>
import Navbar from "@/components/challenge/Navbar"
import ChallengeContainerWith from "@/components/challenge/with/ChallengeContainerWith"
import { mapState } from "vuex"

export default {
  name: "WithMain",
  components: {
    Navbar,
    ChallengeContainerWith,
  },
  methods: {
    SetCurrentPageId: function (getId) {
      console.log('클릭 감지1')
      this.$store.state.currentPageId = getId
      console.log('클릭 감지2')
    },
  },
  computed: {
    ...mapState([
      "with_challenges",
      "recent_challenges",
    ])
  },
}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 10px;
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
    height: calc(80vh - 140px);
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
    font-size: 25px;
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
    border-radius: 5px;
    height: 30px;
  }
</style>