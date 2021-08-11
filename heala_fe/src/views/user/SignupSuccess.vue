<template>
  <div>
    <div>
      heala-logos
    </div>
    <h1>{{ userIdentity }} 회원님</h1>
    <h1>가입을 축하합니다!</h1>
    <br>
    <h2>내 손 안의 건강 SNS, kiwi</h2>
    <h2>지금 시작해보세요!</h2>
    <br>
    <button class="bg-green get-input join-button-setting" @click="gotoLogin()">로그인 화면으로 이동</button>
  </div>
</template>

<script>
import router from "@/router/index.js"
import { mapState } from 'vuex'

export default {
  name: "SignupSuccess",
  methods: {
    gotoLogin() {
      router.push({ name: "Login" })
    },
  },
  computed: {
    ...mapState([
      "userIdentity",
    ])
  },
}
</script>

<style scoped>
  .bg-green {
    background: #ADEC6E;
    color: white;
    /* color: #99a9bf; */
  }
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    width: 80%;
    height: 40px;
    margin-bottom: 20px;
    padding: 0px 2px;
  }
  .join-button-setting {
    margin-top: 30px;
    height: 45px;
    width: 80%;
    cursor: pointer;
  }
</style>