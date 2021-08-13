<template>
  <div id="footer" style="display: grid; align-items: center">
    <el-row>
      <router-link :to="{ name: 'HomeFeed' }" class="text-decoration-none color-black" @click.native="changeRouter(0)" :class="{selected: selectedRouter == 0}">
        <el-col :span="5">
          <font-awesome-icon icon="home"/>
        </el-col>
      </router-link>
      <router-link :to="{ name: 'CommunityMain' }" class="text-decoration-none color-black" @click.native="changeRouter(1)" :class="{selected:selectedRouter == 1}">
        <el-col :span="5">
          <font-awesome-icon icon="user-friends"/>
        </el-col>
      </router-link>
      <router-link :to="{ name: 'writing' }" class="text-decoration-none color-black" @click.native="changeRouter(2)" :class="{selected: selectedRouter == 2}">
        <el-col :span="4">
          <font-awesome-icon :icon="['far', 'plus-square']" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'WithMain' }" class="text-decoration-none color-black" @click.native="changeRouter(3)" :class="{selected: selectedRouter == 3}" >
        <el-col :span="5">
          <font-awesome-icon :icon="['fas', 'running']" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'Profile' }" class="text-decoration-none color-black" @click.native="changeRouter(4)" :class="{selected: selectedRouter == 4}">
        <el-col :span="5">
          <font-awesome-icon icon="user" />
        </el-col>
      </router-link>
    </el-row>
  </div>
</template>

<script>
import { mapState } from "vuex"

export default {
  name: "Footer",
  data: () => {
    return {
    };
  },
  methods: {
    changeRouter (num) {
      // this.selectedRouter = num
      this.$store.state.selectedRouter = num
    },
  },
  computed: {
    ...mapState([
      "selectedRouter",
    ])
  },
  // watch: {
  //   selectedRouter: {
  //     handler: 'changeRouter',
  //     immediate: true,
      
  //     // changeRouter: function () {
  //     //   console.log(this.selectedRouter, 'watching')
  //     // },
  //   },
  // },
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
    color: #ADEC6E !important;
  }
  .color-black {
    color: black;
  }
</style>