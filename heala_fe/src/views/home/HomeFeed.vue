<template>
  <div class="feed-container">
    <div class="feed-header">
      <img class="logo-image" src="@/assets/image/kiwi_logo.png" />
      <div class="icon-wrapper">
        <font-awesome-icon :icon="['fas', 'search']" class="icon" />
        <font-awesome-icon :icon="['far', 'envelope']" class="icon" />
        <font-awesome-icon :icon="['far', 'bell']" class="icon" />
      </div>
    </div>
    <div class="post-wrapper">
      <post></post>
    </div>
    <div class="post-wrapper">
      <post></post>
    </div>
    <div class="post-wrapper">
      <post></post>
    </div>
  </div>
</template>

<script>
import Post from "@/components/home/Post";

export default {
    data() {
      return {

      }
    },
    methods: {
    },
    components: {
      Post,
    }
}
</script>

<style scoped>
.feed-header {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  padding: 15px;
  margin: 0px;
  box-sizing: border-box;
}
.logo-image {
  width: 25%;
  float: left;
}
.icon-wrapper {
  width: 25%;
  float: right;
  position: absolute;
  top: 50%;
  right: 15px;
  transform: translate(0%, -50%);
}
.icon {
  margin: 0px 0px 0px 5px;
}
.post-wrapper {
  margin: 0px 0px 15px 0px;
}
</style>