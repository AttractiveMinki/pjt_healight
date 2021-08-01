<template>
  <div>
    <button class="following" v-if="isFollowing" @click="following">팔로잉</button>
    <button class="follow" @click="follow" v-else>팔로우</button>
    <!-- $store.state.userid 확인해보고 비어있으면 팔로우 눌렀을 때 로그인하시겠습니까? 모달 뜨도록 구현 -->
    <modal v-if="showModal" @yes="cancelFollow" @no="showModal = false">
        <div slot="header">팔로우 취소</div>
        <div slot="body">팔로우를 취소하시겠습니까?</div>
    </modal>
  </div>
</template>

<script>
import Modal from "@/components/Modal";
export default {
    name: 'follow',
    props: {
        InitialIsFollowing: {
            required: true
        },
        follow_id: {
            required: true
        },
    },
    data() {
        return {
            isFollowing: this.InitialIsFollowing,
            showModal: false,
        }
    },
    methods: {
        follow() {
            this.isFollowing = true;
            this.$store.dispatch("follow", { follow_id: this.follow_id });
        },
        following() {
            this.showModal = true;
        },
        cancelFollow() {
            this.showModal = false;
            this.isFollowing = false;
            this.$store.dispatch("cancelFollow", { follow_id: this.follow_id });
        }
    },
    components: {
        Modal
    },
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
    color:#fff;
	background:#1a8efa;
	border:none;
	/* box-shadow: 0 4px 16px rgba(0,79,255,0.3); */
	/* transition:0.3s; */
	/* position: absolute;
	left:50%;
	top:50%; */
	/* transform: translate(-50%,-50%); */
}
button.following:active {
    background: #107bdf;
}
button.follow {
    background:#fff;
    color:#444444;
    border-color: #1a8efa;
}
button.follow:active {
    background: #95ccff;
}

button.modal-default-button {
    float: right;
}
</style>