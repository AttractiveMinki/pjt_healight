<template>
  <div>
    <el-row class="justify-content-space-between">
      <el-col :span="6">
        <router-link :to="{ name: 'CommunityMain' }" class="text-decoration-none">전체</router-link> 
      </el-col>
      <el-col :span="6">
        <router-link :to="{ name: 'Health' }" class="text-decoration-none selected-category">운동</router-link> 
        <div id="square"></div>
        
      </el-col>
      <el-col :span="6">
        <router-link :to="{ name: 'Diet' }" class="text-decoration-none">식단</router-link> 
      </el-col>
      <el-col :span="6">
        <router-link :to="{ name: 'Heart' }" class="text-decoration-none">마음</router-link>
      </el-col>
    </el-row>
    <h1>운동</h1>
  </div>
</template>

<script>

export default {
  name: 'health',
}
</script>

<style scoped>  
  #square {
    width: 100%;
    height: 10px;
    background: #75BCFE;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .justify-content-space-between {
    justify-content: start;
  }
</style>