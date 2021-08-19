<template>
  <div>
    <button class="following" v-if="isFollowing" @click.stop="clickFollowing">팔로잉</button>
    <button class="follow" @click.stop="follow" v-else>팔로우</button>
    <!-- $store.state.userid 확인해보고 비어있으면 팔로우 눌렀을 때 로그인 화면으로 이동 -->
    <modal v-if="showModal" @yes="cancelFollow" @no="showModal = false">
      <div slot="header">팔로우 취소</div>
      <div slot="body">팔로우를 취소하시겠습니까?</div>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal";
import axios from "axios";
import SERVER from "@/api/drf.js";

export default {
  name: 'follow',
  props: [ 'following', 'followId', 'isFeedFollow' ],
  data() {
    return {
      userId: 1,
      isFollowing: this.following,
      showModal: false,
      feedFollow: this.isFeedFollow,
    }
  },
  created() {
    this.$store.commit("GET_USERID")
    this.userId = this.$store.state.userId
  },
  methods: {
    clickFollowing() {
      this.$emit("unfollow");
      if(this.feedFollow) this.showModal = true;
      else this.cancelFollow();
    },
    async follow() {
      this.isFollowing = true;
      this.$emit("follow");
      try{
        await axios.post(SERVER.URL + SERVER.ROUTES.follow, {
          userId: this.userId,
          followId: this.followId,
        });
      }
      catch(e) {
        this.isFollowing = false;
      }
    },
    async cancelFollow() {
      if(this.feedFollow) this.showModal = false;
      this.isFollowing = false;
      try{
        await axios.delete(SERVER.URL + SERVER.ROUTES.follow
        + `?userId=${this.userId}&followId=${this.followId}`);
      }
      catch(e) {
        this.isFollowing = true;
      }
    }
  },
  components: {
    Modal,
  }
}
</script>

<style scoped>
button {
  width: 90px;
	height: 30px;
  font-size: 13px;
  border-radius: 20px;
  border: 2px solid;
  font-weight: 500;
}
button:hover{
	cursor: pointer;
}
button.following{
  color: rgb(71, 71, 71);
	background:#ADEC6E;
	border:none;
	/* box-shadow: 0 4px 16px rgba(0,79,255,0.3); */
	/* transition:0.3s; */
	/* position: absolute;
	left:50%;
	top:50%; */
	/* transform: translate(-50%,-50%); */
}
button.following:active {
  background: #9cd464;
}
button.follow {
  background:#fff;
  color:#444444;
  border-color: #ADEC6E;
}
button.follow:active {
  background: #95ccff;
}

button.modal-default-button {
  float: right;
}
</style>