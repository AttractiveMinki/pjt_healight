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
      this.$store.commit("GET_USERID")
      if (this.$store.state.userId){
        router.push({ name: "HomeFeed" })
      }
      else {
        router.push({ name: "Login" })
      }
    },
  },
  created: function () {
    this.$store.commit("GET_USERID")
    this.sendMain()
  },
}
</script>

<style>

</style>
