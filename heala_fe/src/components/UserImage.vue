<template>
  <div class="user-image-wrapper" v-bind:style="{ width: width + 'px', height: height + 'px'}">
    <img v-if="image" :src="imageServer + image" alt="profile_image" class="user-image">
    <img v-else src="@/assets/image/user.png" alt="" class="user-image">
  </div>
</template>

<script>
import SERVER from "@/api/drf.js";

export default {
    name: "UserImage",
    props: [ "image", "width", "height" ],
    data() {
      return {
        imageServer: SERVER.IMAGE_URL,
      }
    },
}
</script>

<style scoped>
.user-image-wrapper {
    width: 30px;
    height: 30px;
    border-radius: 70%;
    overflow: hidden;
    float: left;
}
.user-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>