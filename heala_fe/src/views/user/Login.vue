<template>
  <div>
    <div style="padding-top: 20vh">
      <img class="logo-image" src="@/assets/image/kiwi_logo.png" />
    </div>
    <!-- <h1>로그인</h1> -->
    <el-row>
      <br>
      <br>
      <input type="text" class='get-input bg-gray' id="identity" v-model="data.identity" maxlength="20" placeholder="  아이디" required>
      <br>
      <br>
      <input type="password" id="password" class="get-input bg-gray" v-model="data.password" maxlength="20" placeholder="  비밀번호(문자, 숫자 포함 8자리 이상)" @keyup.enter="login(data)" required>
      <!-- <div class="password-box get-input bg-gray introduce-text-align-start">
        
        <span class="el-icon-chat-line-round text-align:right" @click="toggleIcon"></span> 
      </div> -->
      <br>
      <button class="bg-green get-input join-button-setting" @click="login(data)">로그인</button>
      <div class="signup-button" @click="goSignup()">가입하기</div>
    </el-row>
  </div>
</template>

<script>
import router from "@/router/index.js"
import { mapActions } from 'vuex'

export default {
  name: "Login",
  methods: {
    ...mapActions([
      'login',
    ]),
    toggleIcon: function () {
      if (this.isvisible == false) {
        this.isvisible = true
      } else {
        this.isvisible = false
      }

      if (this.isvisible == true) {
        document.getElementsByClassName('el-icon-chat-line-round')[0].setAttribute('class', "el-icon-chat-round")
        document.getElementById('password').setAttribute("type", "text")
      } else {
        document.getElementsByClassName('el-icon-chat-round')[0].setAttribute('class', "el-icon-chat-line-round")
        document.getElementById('password').setAttribute("type", "password")
      }
    },
    goSignup() {
      router.push({ name: "Signup" })
    },
  },
  data: function () {
    return {
      data: {
        identity: '',
        password: '',
      },
      isvisible: false,
    }
  },
}
</script>

<style scoped>
  .logo-image {
    width: 60%;
  }
  .bg-white {
    background: rgb(197, 223, 189);
    border-width: 0px;
    border-radius: 5px;
    color: black;
  }
  .bg-green {
    background: #ADEC6E;
    color: black;
    /* color: #99a9bf; */
  }
  .bg-gray {
    background: #F0F0F0;
    margin: 0;
  }
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    width: 80%;
    height: 40px;
    padding-left: 10px;
    box-sizing: border-box;
    /* margin-bottom: 10px; */
  }
  .signup-button {
    text-align: center;
    /* border-width: 0px; */
    /* border-radius: 5px; */
    width: 100px;
    height: 30px;
    margin: 20px auto;
    /* padding: 0px 2px; */
    color: #76D418;
  }
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-left: 10%;
    text-align: start;
  }
  .join-button-setting {
    margin-top: 30px;
    height: 45px;
    width: 80%;
    cursor: pointer;
  }
  .signup-forgetpassword-button {
    display: flex;
    margin-left: 10%;
    margin-right: 10%;
    width: 80%;
  }
  .justify-content-space-between {
    justify-content: space-between;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .get-input:focus {
    outline: none;
  }
</style>