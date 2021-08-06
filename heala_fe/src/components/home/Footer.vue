<template>
  <div id="footer" style="display: grid; align-items: center">
    <el-row>
      <router-link :to="{ name: 'home' }" class="text-decoration-none">
        <el-col :span="5">
          <font-awesome-icon icon="home" color="#ADEC6E" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'CommunityMain' }" class="text-decoration-none">
        <el-col :span="5">
          <font-awesome-icon icon="user-friends" color="#ADEC6E" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'writing' }" class="text-decoration-none">
        <el-col :span="4">
          <font-awesome-icon :icon="['far', 'plus-square']" color="#ADEC6E" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'WithMain' }" class="text-decoration-none">
        <el-col :span="5">
          <font-awesome-icon :icon="['fas', 'running']" color="#ADEC6E" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'Profile' }" class="text-decoration-none">
        <el-col :span="5">
          <font-awesome-icon icon="user" color="#ADEC6E" />
        </el-col>
      </router-link>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Footer",
  
}
</script>

<style scoped>
  #footer{
    position: fixed;
    background-color: whitesmoke; /*임의색상*/
    left: 0;
    right: 0;
    bottom: 0;
    height: 3rem;
  }
</style>