<template>
  <div>
    <kiwi-header>
      <template v-slot:title>게시물 상세</template>
      <template v-if="userCheck()" v-slot:ellipsis><font-awesome-icon icon="ellipsis-v" @click="showMenu = true"/></template>
    </kiwi-header>
    <div class="post-container">
      <div class="post-title">
        {{ post.title }}
      </div>
      <div class="post-time">
        {{ post.createdAt.substring(0, 10) }}
      </div>
      <div class="post-image-wrapper">
        <img :src="imageServer + $store.state.postDetail.image" alt="" class="post-image">
      </div>
      <div class="icon-wrapper">
        <star :like="postLike" @cancelStar="cancelStar" @star="star"></star>
        <font-awesome-icon :icon="['far', 'comment']" @click="newComment" class="icon" />
        <font-awesome-icon :icon="['fas', 'bookmark']" v-if="postScrap" @click="cancelScrap" class="icon icon-bookmark" />
        <font-awesome-icon :icon="['far', 'bookmark']" v-else @click="scrap" class="icon icon-bookmark" />
      </div>
      <el-row>
        <div class="post-user">
          <user-image :image="postUser.image"></user-image>
        </div>
        <div class="post-content">
          <span class="post-user-name">{{ postUser.name }}</span>
          {{ post.content }}
        </div>
      </el-row>
      <div class="comment-container">
        <comment
          v-for="comment in comments"
          v-bind="comment"
          v-bind:key="comment.id"
          @reply="reply"
        ></comment>
      </div>
      <div class="rest"></div>
    </div>
    <el-row>
      <input-message :placeholderMsg="placeholderMsg" @write="createComment"></input-message>
    </el-row>
    <div v-if="showMenu" class="menu-background" @click="showMenu = false"></div>
    <div v-if="showMenu" class="menu-list">
      <!-- <div class="menu">수정하기</div> -->
      <div class="menu" @click="showDeleteModal = true">삭제하기</div>
    </div>
    <modal v-if="showDeleteModal" @yes="deletePost()" @no="showDeleteModal = false">
      <template v-slot:header>게시글 삭제</template>
      <template v-slot:body>게시글을 삭제하시겠습니까?</template>
    </modal>
  </div>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/drf.js";

import InputMessage from "@/components/InputMessage.vue";
import KiwiHeader from "@/components/KiwiHeader.vue";
import Star from "@/components/Star.vue";
import UserImage from "@/components/UserImage.vue";
import Comment from "@/components/Comment.vue";
import Modal from "@/components/Modal.vue";

export default {
  name: "PostDetail",
  data() {
    return {
      // scrapUI: this.$store.state.postScrap,
      userId: 1,
      imageServer: SERVER.IMAGE_URL,
      replyCommentId: Number,
      placeholderMsg: "댓글을 입력해주세요",
      showMenu: false,
      showDeleteModal: false,
    }
  },
  computed: {
    post() {
      return this.$store.state.postDetail;
    },
    comments() {
      return this.$store.state.comments;
    },
    postUser() {
      return this.$store.state.postUser;
    },
    postLike() {
      return this.$store.state.postLike;
    },
    postScrap() {
      return this.$store.state.postScrap;
    },
  },
  created() {
    this.userId = localStorage.getItem("userId");
    let postId = this.$route.params.id;
    this.$store.dispatch("setPostDetail", { postId });
    this.$store.dispatch("setPostComments", { postId });
    this.$store.dispatch("setPostScrap", { postId });
    this.$store.dispatch("setPostLike", { postId });
  },
  methods: {
    cancelStar() {
      this.$store.dispatch("cancelLikePost", { postId: this.post.id });
    },
    star() {
      this.$store.dispatch("likePost", { postId: this.post.id });
    },
    cancelScrap() {
      this.scrapUI = 0;
      this.$store.dispatch("cancelScrapPost", { postId: this.post.id });
    },
    scrap() {
      this.scrapUI = 1;
      this.$store.dispatch("scrapPost", { postId: this.post.id });
    },
    reply(id) {
      this.replyCommentId = id;
      this.placeholderMsg = "답글을 입력해주세요";
    },
    newComment() {
      this.replyCommentId = false;
      this.placeholderMsg = "댓글을 입력해주세요";
    },
    createComment(message) {
      this.$store.dispatch("createComment", { message, postId: this.post.id, commentId: this.replyCommentId });
    },
    userCheck() {
      return this.post.userId == this.userId;
    },
    deletePost() {
      this.showDeleteModal = false;
      this.showMenu = false;
      axios
        .delete(SERVER.URL + SERVER.ROUTES.feedPost
        + `?userId=${this.userId}&postId=${this.post.id}`)
        .then(() => {

        })
        .catch((exp) => {
            console.log(`게시글 삭제에 실패했습니다: ${exp}`)
        });
      this.$router.go(-1);
    },
  },
  components: { InputMessage, KiwiHeader, Star, UserImage, Comment, Modal, },
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
.comment-container {
  font-size: 13px;
  margin: 15px 0px 0px 0px;
}
.rest {
  height: 60px;
}
.menu-background {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0px;
  left: 0px;
  background-color: rgba(0, 0, 0, .5);
  transition: opacity .3s ease;
}
.menu-list {
  position: fixed;
  bottom: 0px;
  border-top: 1px solid;
}
.menu {
  width: 100vw;
  padding: 15px;
  border-bottom: 1px solid;
  border-left: 1px solid;
  border-right: 1px solid;
  box-sizing: border-box;
  background-color: #94EC3C;
}
</style>