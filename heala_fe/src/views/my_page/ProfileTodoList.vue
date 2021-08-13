<template>
  <div>
    <Navbar />
    <el-row style="align-items:center">
      <el-col :span="6" style="padding: 3vw">
        <img v-if="image == '' " src="@/assets/img/profile/user.png" alt="profile_image" width="62" height="62" style="border-radius: 50%;">
        <img v-else :src="image" alt="profile_image" width="62" height="62" style="border-radius: 50%;"><br>
      </el-col>
      <el-col :span="18" style="text-align: -webkit-right; padding: 3vw">
        <!-- 사용자와 프로필 주인이 같다면 -->
        <span id="square" style="margin-top: 10px"><router-link :to="{ name: 'ProfileEdit' }" class="text-decoration-none challenge-make">프로필 편집</router-link></span>

        <!-- 사용자와 프로필 주인이 다르다면 -->
        <!-- 메시지 -->
        <!-- 팔로잉 -->
      </el-col>
    </el-row>
    <el-row style="text-align: start; padding: 2vw">
      <el-col :span="12">
        <span style="font-size: 14px; font-weight: bold; padding: 1px"> <!-- {{ user.username }} --> 스파르타꾹스</span>
        <span style="font-size: 11px"> Lv. <!-- {{ user.userid }} -->77</span>
      </el-col>
    </el-row>
    <el-row style="text-align: start; padding: 2vw">
      <el-col>
        <span style="font-size: 13px"><!-- {{ user.content }} -->나는야 스파르타꾹스. 유튜브 많이 보러 와주세요</span>
      </el-col>
    </el-row>
    <el-row style="text-align: space-between">
      <el-col :span="12">
        <span style="padding: 2vw">  <span style="font-weight: bold; padding: 1vw">231<!-- {{ user.following }} --></span>팔로잉</span>  
        <span style="padding: 2vw"> <span style="font-weight: bold; padding: 1vw">583<!-- {{ user.follower }} --></span>팔로워</span>
      </el-col>
    </el-row>
    <div id="underline"></div>

    <el-row style="text-align: start">
      <!-- 배지 뭐갖고있는지 계산 -->
      <img class="image" src="@/assets/image/health/health_challenge.png" alt="health badge" style="width: 10vw; padding: 2vw">
      <el-dialog width="20px">
        <img alt="" width="20px">
      </el-dialog>
      <img class="image" src="@/assets/image/diet/diet_dia.png" alt="diet badge" style="width: 10vw; padding: 2vw">
      <el-dialog  width="95%">
        <img alt="" width="99%">
      </el-dialog>
      <img class="image" src="@/assets/image/heart/heart_silver.png" alt="herat badge" style="width: 10vw; padding: 2vw">
      <el-dialog width="95%">
        <img alt="" width="99%">
      </el-dialog>
    </el-row>

    <el-row>
      <el-col :span="12">
        <router-link :to="{ name: 'Profile' }" class="text-decoration-none"><font-awesome-icon :icon="['far', 'images']" style="margin-top: 1.5vh" /></router-link> 
      </el-col>
      <el-col :span="11" style="background: #ADEC6E; height: 5vh" class="display-inline-grid aligh-items-center">
        <!-- 내 계정이라면 -->
        <router-link :to="{ name: 'ProfileTodoList' }" class="text-decoration-none selected-category">오늘의 할 일</router-link> 
        <!-- 내 계정이 아니라면 -->
        <!-- 배지 모음 -->
      </el-col>
    </el-row>
    <div class="community">
      <el-row style="padding-top: 2vh">
        <el-col :span="24">
          <el-col :span="12" style="text-align: start; padding: 2vw">
            <span>오늘의 할 일</span>
          </el-col>
          <router-link :to="{ name: 'ProfilePhysInfo' }" class="text-decoration-none">
            <el-col :span="12" style="text-align: end; padding: 2vw" class="community-title">
              <i class="margin-left-10 el-icon-arrow-right"></i>
              <span>신체정보 등록</span>
            </el-col>
          </router-link>
        </el-col>
      </el-row>

      <router-link :to="{ name: 'DietRecord' }" class="text-decoration-none">
        <el-row class="community-inside">
          <el-col :span="18">
            <div>식단 기록하기</div>
          </el-col>
          <el-col :span="6">
            <!-- 그림 자리 -->
          </el-col>
        </el-row>
      </router-link>

      <router-link :to="{ name: 'WeightCalender' }" class="text-decoration-none">
        <el-row class="community-inside">
          <el-col :span="18">
              <div>체중 기록하기</div>
          </el-col>
          <el-col :span="6">
            <!-- 그림 자리 -->
          </el-col>
        </el-row>
      </router-link>
    </div>  
    <Footer />
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"
import { mapState } from "vuex"
import Footer from "@/components/home/Footer"

export default {
  name: "Profile",
  data: () => {
    return {
      image: "",
      name: "",
      identity: "",
      introduction: "",
    };
  },
  components: {
    Navbar,
    Footer,
  },
  computed: {
    ...mapState([
      "profileSelectedCategory",
    ])
  },
}
</script>

<style scoped>
  #square {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80%;
    background: white;
    border-style: solid;
    border-width: 2px;
    border-color: #ADEC6E;
    border-radius: 30px;
    height: 36px;
    
  }
  #underline {
    width: 48vw;
    height: 5px;
    background: #C4C4C4;
    /* background: linear-gradient(90deg, #C4C4C4 50%, gray 50%) 반반 나눠서 색칠하기*/
  }
  
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .challenge-make {
    font-size: 13px;
    padding-right: 5px;
  }
  .align-items-center {
    display: flex-inline;
    text-align: center;
    align-items: center;
  }
  .display-inline-grid {
    display: inline-grid;
    align-items: center;
  }

  .community {
    padding: 5px;
    /* margin-left: 2.5%; */
    border-radius: 5px;
    width: 97%;
    height: calc(50vh - 140px);
    border: 1px solid white;
    overflow : scroll;
  }
  .community-title {
    font-size: 11px;
    color: gray;
  }
  .community-inside {
    /* margin-left: 2.5%; */
    padding: 5px;
    padding-left: 10vw;
    padding-top: 3vh;
    font-size: 16;
    font-weight: bold;
    border-radius: 5px;
    width: 98%;
    height: 10vh;
    text-align: start;
    border: 1.5px solid darkgrey;
  }
</style>