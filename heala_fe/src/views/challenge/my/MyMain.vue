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
    </el-row>
  </div>
</template>

<script>
import Navbar from "@/components/challenge/Navbar"
import ChallengeContainerMy from "@/components/challenge/my/ChallengeContainerMy"
import { mapState } from "vuex"

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
</style>