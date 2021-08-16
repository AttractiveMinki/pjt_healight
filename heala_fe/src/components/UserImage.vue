<template>
  <div class="user-image-wrapper" v-bind:style="{ width: width + 'px', height: height + 'px'}">
    <img :src="imageURL" alt="@/assets/image/user.png" class="user-image">
  </div>
</template>

<script>
import SERVER from "@/api/drf.js";

export default {
    name: "UserImage",
    props: [ "image", "width", "height" ],
    data() {
      return {
        imageURL: SERVER.IMAGE_URL + this.image,
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