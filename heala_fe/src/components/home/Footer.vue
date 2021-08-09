<template>
  <div id="footer" style="display: grid; align-items: center">
    <el-row>
      <router-link :to="{ name: 'home' }" class="text-decoration-none" @click.native="changeRouter(0)" :class="{selected: selectedRouter == 0}">
        <el-col :span="5">
          <font-awesome-icon icon="home"/>
        </el-col>
      </router-link>
      <router-link :to="{ name: 'CommunityMain' }" class="text-decoration-none" @click.native="changeRouter(1)" :class="{selected: selectedRouter == 1}">
        <el-col :span="5">
          <font-awesome-icon icon="user-friends"/>
        </el-col>
      </router-link>
      <router-link :to="{ name: 'writing' }" class="text-decoration-none" @click.native="changeRouter(2)" :class="{selected: selectedRouter == 2}">
        <el-col :span="4">
          <font-awesome-icon :icon="['far', 'plus-square']" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'WithMain' }" class="text-decoration-none" :class="{selected: selectedRouter == 3}" @click.native="changeRouter(3)" >
        <el-col :span="5">
          <font-awesome-icon :icon="['fas', 'running']" />
        </el-col>
      </router-link>
      <span @click="changeRouter(4)">
        <router-link :to="{ name: 'Profile' }" :class="{selected: selectedRouter == 4}" class="text-decoration-none" >
          <el-col :span="5">
            <font-awesome-icon icon="user" />
          </el-col>
        </router-link>
      </span>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Footer",
  data: () => {
    return {
      selectedRouter: 2,
    };
  },
  methods: {
    changeRouter (num) {
      console.log(num)
      this.selectedRouter = num
      // this.$set(this.selectedRouter, this.selectedRouter, num)
      console.log(this.selectedRouter)
    },
  },
  watch: {
    selectedRouter: function () {
      console.log(this.selectedRouter, 'watching')
    },
  },
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
  .selected {
    color: #ADEC6E;
  }
  .normal {
    color: black;
  }
</style>