<template>
  <div>
    <kiwi-header>
      <template v-slot:title>댓글</template>
    </kiwi-header>
    <div class="comment-more-container">
      <div>
        <div class="post-user">
          <user-image :image="postUser.image"></user-image>
        </div>
        <div class="post-content">
          <span class="post-user-name">{{ postUser.name }}</span>
          {{ post.content }}
        </div>
      </div>
      <hr>
      <div class="comment-container">
        <comment
          v-for="comment in comments"
          v-bind="comment"
          v-bind:key="comment.id"
          @reply="reply"
        ></comment>
      </div>
      <div class="rest"></div>
      <el-row>
        <input-message :placeholderMsg="placeholderMsg" @write="createComment"></input-message>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/drf.js";
import KiwiHeader from "@/components/KiwiHeader";
import Comment from "@/components/Comment";
import InputMessage from "@/components/InputMessage";
import UserImage from "@/components/UserImage";
export default {
  name: "CommentMore",
  data() {
    return {
      userId: 1,
      postId: Number,
      replyCommentId: Number,
      imageServer: SERVER.IMAGE_URL,
      placeholderMsg: "댓글을 입력해주세요",
      post: {
        content: "",
      },
      postUser: {
        name: "",
        image: "",
      }
    }
  },
  created() {
    this.$store.commit("GET_USERID")
    this.userId = this.$store.state.userId
    this.postId = this.$route.params.id;
    this.$store.dispatch("setPostComments", { postId: this.postId });
    this.getPost();
  },
  computed: {
    comments() {
      return this.$store.state.comments;
    },
  },
  methods: {
    reply(id) {
      this.replyCommentId = id;
      this.placeholderMsg = "답글을 입력해주세요";
    },
    createComment(message) {
      this.$store.dispatch("createComment", { message, postId: this.postId, commentId: this.replyCommentId });
    },
    async getPost() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.post + this.postId);
        this.post = response.data;
        this.getPostUser();
      } catch (error) {
        alert('게시물 정보를 가져오던 중 오류가 발생했습니다.')
      }
    },
    async getPostUser() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.postUser + `?userId=${this.post.userId}`);
        this.postUser = response.data;
      } catch (error) {
        alert('유저 정보를 가져오던 중 오류가 발생했습니다.')
      }
    },
  },
  components: {
    KiwiHeader,
    Comment,
    InputMessage,
    UserImage,
  }
}
</script>

<style scoped>
.comment-more-container {
  font-size: 13px;
  margin: 0px 15px;
  text-align: left;
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
hr {
  margin: 10px 3px 0px 3px;
  border-top: 1px solid #bbb;
  border-right: 0px;
  border-bottom: 0px;
  border-left: 0px;
}
.comment-container {
  font-size: 13px;
  margin-top: 15px;
  text-align: left;
}
.rest {
  height: 60px;
}
</style>