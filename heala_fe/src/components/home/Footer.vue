<template>
  <div id="footer" style="display: grid; align-items: center">
    <el-row>
      <router-link :to="{ name: 'HomeFeed' }" class="text-decoration-none color-black" @click.native="changeRouter(0)" :class="{selected: selectedRouter == 0}">
        <el-col :span="5">
          <font-awesome-icon icon="home"/>
        </el-col>
      </router-link>
      <router-link :to="{ name: 'CommunityNewMain' }" class="text-decoration-none color-black" @click.native="changeRouter(1)" :class="{selected:selectedRouter == 1}">
        <el-col :span="5">
          <font-awesome-icon icon="user-friends"/>
        </el-col>
      </router-link>
      <router-link :to="{ name: 'Writing' }" class="text-decoration-none color-black" @click.native="changeRouter(2)" :class="{selected: selectedRouter == 2}">
        <el-col :span="4">
          <font-awesome-icon :icon="['far', 'plus-square']" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'WithMain' }" class="text-decoration-none color-black" @click.native="changeRouter(3)" :class="{selected: selectedRouter == 3}" >
        <el-col :span="5">
          <font-awesome-icon :icon="['fas', 'crown']" />
        </el-col>
      </router-link>
      <router-link :to="{ name: 'Profile', params: { id: userId } }" @click="changeRouter(4)" :class="{selected: selectedRouter == 4}" class="text-decoration-none color-black" >
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
      userId: "",
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
  created () {
    this.$store.commit("GET_USERID")
    this.userId = this.$store.state.userId
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
  left: 0px;
  right: 0px;
  bottom: 0px;
  height: 48px;
  font-size: 17px;
}
.selected {
  color: #ADEC6E !important;
}
.color-black {
  color: black;
}
</style>