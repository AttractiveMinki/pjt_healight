<template>
  <div class="follow-list-item">
    <div class="user-image-wrapper">
      <user-image :image="image" :width=50 :height=50></user-image>
    </div>
    <div class="follow-user-info">
      <div class="follow-user-name">{{ name }}</div>
      <div class="follow-user-identity">{{ identity }}</div>
    </div>
    <div class="follow-button">
      <follow :following="follow" :followId="id" @follow="$emit('follow')" @unfollow="$emit('unfollow')"></follow>
    </div>
  </div>
</template>

<script>
import UserImage from "@/components/UserImage";
import Follow from "@/components/my_page/Follow";

export default {
    name: "FollowListItem",
    props: [ "id", "identity", "name", "image", "follow"],
    data() {
        return {
            
        }
    },
    methods: {
        
    },
    components: {
        UserImage,
        Follow,
    }
}
</script>

<style scoped>
.follow-list-item {
    position: relative;
    height: fit-content;
    overflow: hidden;
    margin: 15px;
}
.user-image-wrapper {
    width: fit-content;
    overflow: hidden;
    float: left;
}
.follow-user-info {
  text-align: left;
  padding: 0px 100px 0px 60px;
  box-sizing: border-box;
}
.follow-user-name {
  text-align: left;
  font-weight: bold;
  word-wrap: break-word;
  word-break: break-all;
}
.follow-button {
  position: absolute;
  top: 25px;
  right: 5px;
  transform: translate(0, -50%);
}
</style>