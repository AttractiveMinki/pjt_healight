<template>
  <div class="user-image-wrapper">
    <img :src="require(`@/assets/image/${image}`)" alt="@/assets/image/user.png" class="user-image">
  </div>
</template>

<script>
export default {
    name: "UserImage",
    props: [ 'image' ],
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