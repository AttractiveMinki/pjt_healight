<template>
  <div>
    <!-- Header -->
    <div style="padding-left: 20px; text-align: left;">
      <div style="font-size: 50px; font-weight: bold; margin-bottom: 10px;">Challenge</div>
      <el-row type="flex" align="middle" style="margin-bottom: 10px;">
        <el-col :span="12" style="font-size: 18px;">다양한 챌린지에<br>도전해보세요!</el-col>
        <el-col :span="12"><el-button type="primary" round style="background-color: white; border: 1px solid #ADEC6E; color: black; font-weight: bold;"><span style="font-weight: bold; color: #117516; margin-right: 10px;">+</span>나만의 챌린지 만들기</el-button></el-col>
      </el-row>
      <input type="search" style="border: none; border-radius: 50px; background-color: rgba(235,235,235,1); width: 96%; height: 30px;"><i class="el-icon-search"></i>
    </div>
  </div>
</template>

<script>

//import axios from 'axios';

export default {
  created() {
    this.$store.state.selectedRouter = 3;
  },
};
</script>

<style scoped>
  
</style>