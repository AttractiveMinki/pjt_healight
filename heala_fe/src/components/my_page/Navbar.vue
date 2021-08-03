<template>
  <div>
    <div class="display-flex justify-content-space-between align-items">
      <span class="display-flex"><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" style="margin: 2vw" @click="goBack()"/><span class="text-title">{{ username }}spartakim</span></span>
      
      <!-- <button class="button-color">작성</button> -->
      <span style="margin: 2vw">
        <font-awesome-icon icon="ellipsis-v" style="margin: 2vw" />
        <font-awesome-icon :icon="['far', 'bell']" style="margin: 2vw" />
      </span>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex"

export default {
  name: "Navbar",
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
  },
  computed: {
    ...mapState([
      "username",
    ])
  },
}
</script>

<style scoped>
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
  .text-title {
    font-weight: bold; 
    font-size: 25px;
    margin-bottom: 5px;
  }
</style>
