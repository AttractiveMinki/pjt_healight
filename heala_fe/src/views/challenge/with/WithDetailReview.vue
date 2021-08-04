<template>
  <div>
    <h1>함께챌린지 상세화면</h1>
    {{ currentPageId }}
    <div class="display-flex justify-content-space-between align-items">
      <span><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" @click="goBack()"/></span>
      <span class="text-title">챌린지 상세</span>
      <!-- <button class="button-color">작성</button> -->
      <span id="block"></span>
    </div>
    <el-row id="category" class="justify-content-space-between">
      <el-col :span="8">
        <router-link :to="{ name: 'WithDetail' }" class="text-decoration-none ">소개</router-link> 
      </el-col>
      <el-col :span="8">
        <router-link :to="{ name: 'WithDetailCertify' }" class="text-decoration-none">인증</router-link> 
      </el-col>
      <el-col :span="8">
        <router-link :to="{ name: 'WithDetailReview' }" class="text-decoration-none selected-category">후기</router-link> 
        <div id="square"></div>
      </el-col>
    </el-row>
    <el-row>
      <el-col>참가 후기</el-col>
    </el-row>

  </div>
</template>

<script>
import { mapState } from "vuex"

export default {
  name: 'WithDetail',
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
  },  
  computed: {
    ...mapState([
      "currentPageId",
      "with_challenges",
    ])
  },
}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 10px;
    background: #ADEC6E;
  }
  #block {
    width: 10%;
    height: 0px;
    background: white;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .text-decoration-title {
    font-size: 22px;
    font-weight: bold;
  }
  .display-flex {
    display: flex;
  }
  .justify-content-space-between {
    justify-content: space-between;
  }  
  .align-items {
    align-items: center;
  }
  .padding-left {
    padding-left: 4%;
  }
  .cursor-pointer {
    cursor: pointer;
  }
</style>