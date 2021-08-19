<template>
  <div>
    <div>
      <img class="logo-image" src="@/assets/image/kiwi_logo.png" />
    </div>
    <h1 class="font" style="font-size: 25px; margin: 0px"><span class="green">K</span>eep<span class="green">i</span>ng <span class="green">W</span>ellbe<span class="green">i</span>ng</h1>
    <h1 class="font" style="font-size: 20px; margin-top: 35px"><span style="font-size: 30px">{{ userIdentity }}</span> 님</h1>
    <h1 class="font" style="font-size: 20px">환영합니다.</h1>
    <br>
    <h2 class="font" style="font-size: 18px; margin-bottom: 0px">내 손 안의 건강 SNS, KIWI</h2>
    <h2 class="font" style="font-size: 18px; margin-top: 7px">지금 시작해보세요!</h2>
    <br>
    <button class="bg-green get-input join-button-setting font"  @click="gotoLogin()">로그인 화면으로 이동</button>
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
  .logo-image {
    padding-top: 20%;
    width: 60%;
  }
  .bg-green {
    background: #ADEC6E;
    color: black;
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

  .green{
    color: #ADEC6E;
  }

  .font{
    font-family: TmoneyRoundWindRegular;
  }

</style>