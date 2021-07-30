<template>
  <div>
    <button class="following" v-if="isFollowing" @click="following">팔로잉</button>
    <button class="follow" @click="follow" v-else>팔로우</button>
    <modal v-if="showModal" @yes="cancelFollow" @no="showModal = false">
        <div slot="header">팔로우 취소</div>
        <div slot="body">팔로우를 취소하시겠습니까?</div>
    </modal>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js";
import Modal from "../Modal";
export default {
    name: 'follow',
    data() {
        return {
            isFollowing: false,
            showModal: false,
        }
    },
    methods: {
        follow() {
            this.isFollowing = true;
            axios.post(SERVER.URL + SERVER.ROUTES.follow, {
                    user_id: 1,
                    follow_id: 2,
                })
        },
        following() {
            this.showModal = true;
        },
        cancelFollow() {
            this.showModal = false;
            this.isFollowing = false;
            axios.delete(SERVER.URL + SERVER.ROUTES.follow, {
                user_id: 1,
                follow_id: 2,
            })
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