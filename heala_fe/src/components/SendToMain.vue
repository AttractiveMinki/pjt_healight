<template>
  <div>
    
  </div>
</template>

<script>
import router from "@/router/index.js"

export default {
  name: "SendToMain",
  methods: {
    sendMain: function () {
      if (this.$store.state.userid){
        router.push({ name: "CommunityMain" })
        // 홈피드 생기면 변경
      }
      else {
        router.push({ name: "Login" })
      }
    },
  },
  mounted: function () {
    this.sendMain()
  },
}
</script>

<style>

</style>