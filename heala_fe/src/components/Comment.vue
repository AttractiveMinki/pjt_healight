<template>
  <el-row class="comment-wrapper">
    <div :class="{ reply: commentId }">
      <div class="comment-user">
        <user-image :image="commentUser.image"></user-image>
      </div>
      <div class="comment-content-wrapper">
        <div class="comment-content">
          <span class="comment-user-name">{{ commentUser.name }}</span>
          {{ text }}
          <div class="comment-info">
            <button class="comment-button">{{ createdAt.substring(0, 10) }}</button>
            <button class="comment-button">추천 {{ likes + likeUI }}개</button>
            <button v-if="!commentId" @click="showReplyModal = true" class="comment-button comment-reply">답글 달기</button>
            <modal v-if="showReplyModal" @yes="replyComment" @no="showReplyModal = false">
              <div slot="header">답글</div>
              <div slot="body">답글을 작성하시겠습니까?</div>
            </modal>
            <button v-if="userId == myId" @click="showDeleteModal = true" class="comment-button comment-delete">삭제</button>
            <modal v-if="showDeleteModal" @yes="deleteComment" @no="showDeleteModal = false">
              <div slot="header">댓글 삭제</div>
              <div slot="body">댓글을 삭제하시겠습니까?</div>
            </modal>
          </div>
        </div>
        <star :like="commentLike" @cancelStar="cancelStar" @star="star" class="star"></star>
      </div>
    </div>
    <div v-if="childrenComment">
      <comment 
        v-for="childComment in childrenComment"
        v-bind="childComment"
        v-bind:key="childComment.id">
      </comment>
    </div>
  </el-row>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/drf.js";
import UserImage from "@/components/UserImage";
import Star from "@/components/Star";
import Modal from "@/components/Modal";

export default {
  name: "Comment",
  props: [ 'id', 'text', 'likes', 'createdAt', 'commentId', 'userId', 'postId', 'childrenComment'],
  data() {
    return {
      showReplyModal: false,
      showDeleteModal: false,

      myId: 0,
      likeUI: 0,
      commentLike: false,
    }
  },
  created() {
    this.$store.commit("GET_USERID")
    this.myId = this.$store.state.userId
    // this.commentLike = this.$store.state.commentLikes.find(commentId => commentId == this.id) > -1;
  },
  computed: {
    commentUser() {
      return this.$store.state.commentUsers.find(user => user.id == this.userId);
    },
    like() {
      return this.$store.state.commentLikes.find(commentId => commentId == this.id) > -1;
    },
  },
  watch: {
    like() {
      this.commentLike = this.like;
    }
  },
  methods: {
    async cancelStar() {
      this.likeUI -= 1;
      // this.$store.dispatch("cancelLikeComment", { commentId: this.id });
      try {
        const response = await axios
          .delete(SERVER.URL + SERVER.ROUTES.postComment + SERVER.ROUTES.like +
            `?userId=${this.myId}&commentId=${this.id}`);
        this.commentLike = response.data;
      } catch (error) {
        alert("댓글 좋아요 취소에 오류가 발생했습니다.");
      }
    },
    async star() {
      this.likeUI += 1;
      // this.$store.dispatch("likeComment", { commentId: this.id });
      try {
        const response = await axios
          .post(SERVER.URL + SERVER.ROUTES.postComment + SERVER.ROUTES.like, {
            userId: this.myId,
            commentId: this.id,
          });
        this.commentLike = response.data;
      } catch (error) {
        alert("댓글 좋아요에 오류가 발생했습니다.");
      }
    },
    replyComment() {
      this.showReplyModal = false;
      this.$emit('reply', this.id);
    },
    deleteComment() {
      this.showDeleteModal = false;
      this.$store.dispatch("deleteComment", { commentId: this.id, postId: this.postId });
    },
    async getCommentUser() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.postUser + `?userId=${this.userId}`);
        this.commentUser = response.data;
      } catch (error) {
        alert('댓글 정보를 가져오던 중 오류가 발생했습니다.')
      }
    }
  },
  components: { UserImage, Star, Modal, },
}
</script>

<style scoped>
.comment-wrapper {
  margin: 10px 0px;
  position: relative;
}
.reply {
  margin: 0px 0px 0px 35px;
}
.comment-user {
  width: fit-content;
  overflow: hidden;
  float: left;
}
.comment-user-name {
  font-weight: bold;
}
.comment-content {
  width: calc(100% - 60px);
  margin: 0px 5px 0px 5px;
  display: inline-block;
  word-break: break-all;
  word-wrap: break-word;
}
.star {
  display: inline-block;
  position: absolute;
  top: 15px;
  transform: translate(0%, -50%);
}
.comment-info {
  font-size: 10px;
  color: #999999;
}
.comment-button {
  border: none;
  background-color: inherit;
  font-size: 10px;
  color: #999999;
}
.comment-reply:hover {
  cursor: pointer;
}
.comment-delete {
  color: rgb(255, 116, 116);
}
.comment-delete:hover {
  cursor: pointer;
}
</style>