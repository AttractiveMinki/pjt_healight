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
      if (localStorage.getItem('userId')){
        router.push({ name: "HomeFeed" })
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
