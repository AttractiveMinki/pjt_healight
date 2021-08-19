<template>
  <div>
    <div class="post-container">
      <div class="post-title">
        {{ title }}
      </div>
      <div class="post-time">
        {{ createdAt.substring(0, 10) }}
      </div>
      <div class="post-image-wrapper">
        <img :src="imageURL" alt="" class="post-image">
      </div>
      <div class="icon-wrapper">
        <star :like="postLike" @cancelStar="cancelStar" @star="star"></star>
        <font-awesome-icon :icon="['far', 'comment']" @click="newComment" class="icon" />
        <font-awesome-icon :icon="['fas', 'bookmark']" v-if="postScrap" @click="cancelScrap" class="icon icon-bookmark" />
        <font-awesome-icon :icon="['far', 'bookmark']" v-else @click="scrap" class="icon icon-bookmark" />
      </div>
      <div>
        <div class="post-user">
          <user-image :image="userImage"></user-image>
        </div>
        <div @click="elipsis ^= 1" :class="{ 'post-content': !elipsis, 'post-content-elipsis': elipsis }">
          <span class="post-user-name">{{ userName }}</span>
          {{ content }}
        </div>
        <div class="comment-open" @click="goToCommentMore">댓글 {{ commentCount }}개 더보기</div>
      </div>
    </div>
  </div>
</template>

<script>
import Star from "@/components/Star";
import UserImage from "@/components/UserImage";

import axios from "axios";
import SERVER from "@/api/drf.js";

export default {
  name: "Post",
  props: [ "id", "title", "createdAt", "image", "likes", "anonymous", "category", "subCategory", "access", "content", "userId"],
  data() {
    return {
      imageURL: SERVER.IMAGE_URL + this.image,
      currentUserId: 1,
      userImage: "",
      userName: "",
      commentCount: 0,
      postLike: Boolean,
      postScrap: Boolean,

      elipsis: true,
    }
  },
  created() {
    this.$store.commit("GET_USERID")
    this.currentUserId = this.$store.state.userId
    // user 불러오기
    this.getUser();
    // 댓글 개수 불러오기
    this.getCommentCount();
    // postLike 불러오기
    this.getPostLike();
    // postScrap 불러오기
    this.getPostScrap();
  },
  methods: {
    star() {
      axios.post(SERVER.URL + SERVER.ROUTES.postLike, {
        userId: this.currentUserId,
        postId: this.id,
      });
      this.postLike = true;
    },
    cancelStar() {
      axios.delete(SERVER.URL + SERVER.ROUTES.postLike
      + `?userId=${this.currentUserId}&postId=${this.id}`);
      this.postLike = false;
    },
    scrap() {
      axios.post(SERVER.URL + SERVER.ROUTES.postScrap, {
        userId: this.currentUserId,
        postId: this.id,
      });
      this.postScrap = true;
    },
    cancelScrap() {
      axios.delete(SERVER.URL + SERVER.ROUTES.postScrap
      + `?userId=${this.currentUserId}&postId=${this.id}`);
      this.postScrap = false;
    },
    newComment() {
      // 댓글 더보기 창으로 이동
    },
    async getUser() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.postUser + `?userId=${this.userId}`);
        let user = response.data;
        this.userImage = user.image;
        this.userName = user.name;
      } catch(exp) {
        console.log(exp);
      }
    },
    async getCommentCount() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.commentCount + this.id);
        this.commentCount = response.data;
      } catch(exp) {
        console.log(exp);
      }
    },
    async getPostScrap() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.postScrap + `?postId=${this.id}&userId=${this.currentUserId}`);
        this.postScrap = response.data;
      } catch(exp) {
        this.postScrap = exp.response.data;
      }
    },
    async getPostLike() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.postLike + `?postId=${this.id}&userId=${this.currentUserId}`);
        this.postLike = response.data;
        return response.data;
      } catch(exp) {
        console.log(exp);
      }
    },
    goToCommentMore() {
      this.$router.push({ name: "CommentMore", params: { id: this.id } });
    },
  },
  components: {
    Star,
    UserImage,
  }
}
</script>

<style scoped>
.post-container {
    font-size: 13px;
    margin: 0px 10px;
    text-align: left;
}
.post-title {
    font-size: 14px;
    font-weight: bold;
    margin: 10px 0px 10px 5px;
    width: calc(100% - 60px);
    text-align: left;
    display: inline-block;
}
.post-time {
    font-size: 11px;
    color: #999999;
    display: inline-block;
    vertical-align: bottom;
    text-align: right;
    margin: 0px 0px 10px 0px;
}
.post-image-wrapper {
    width: 100%;
    position: relative;
    overflow: hidden;
}
.post-image-wrapper::after {
    content: "";
    display: block;
    padding-bottom: 100%;
}
.post-image {
    position: absolute;
    top: 0px;
    left: 0px;
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.icon-wrapper {
    font-size: 20px;
    height: 20px;
    margin: 5px 0px;
}
.icon {
    color: #94EC3C;
    margin: 0px 0px 0px 5px;
    float: left;
}
.icon:hover {
    cursor: pointer;
}
.icon-bookmark {
    float: right;
}
.post-user {
    width: fit-content;
    overflow: hidden;
    float: left;
}
.post-user-name {
    font-weight: bold;
}
.post-content {
    width: calc(100% - 40px);
    margin: 0px 0px 0px 5px;
    display: inline-block;
}
.post-content-elipsis {
    width: calc(100% - 40px);
    margin: 0px 0px 0px 5px;
    padding: 0px 0px 0px 5px;
    /* display: inline-block; */
    white-space: normal;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
.comment-open {
    width: 100%;
    margin: 4px 0px 0px 5px;
    padding: 0px 0px 0px 35px;
    box-sizing: border-box;
    font-size: 11px;
    color: #999999;
}
</style>