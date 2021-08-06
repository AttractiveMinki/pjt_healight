<template>
  <div>
    <el-row class="justify-content-space-between padding-top-setting">
      <el-col :span="8" class="container-box-detail">
        <router-link :to="{ name: 'KiwiMain' }" class="text-decoration-none align-self">키위 챌린지</router-link> 
      </el-col>
      <el-col :span="8" class="container-box-detail">
        <router-link :to="{ name: 'WithMain' }" class="text-decoration-none align-self">함께 챌린지</router-link> 
      </el-col>
      <el-col :span="8" class="container-box-detail selected-category">
        <router-link :to="{ name: 'MyMain' }" class="text-decoration-none align-self">나의 챌린지</router-link>
        <div id="square"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "ChallengeContainerMy",

}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 4px;
    background: #ADEC6E;
  }
  .justify-content-space-between {
    display: flex;
    justify-content: space-between;
  }
  .padding-top-setting {
    padding-top: 20px;
  }
  .container-box-detail {
    border-radius: 5px;
    height: 30px;
  }
  .align-self {
    display: inline-block;
    padding-top: 5px;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .selected-category {
    background: #ADEC6E;
  }
</style>