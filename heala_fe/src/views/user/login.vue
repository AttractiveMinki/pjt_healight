<template>
  <div>
    <div>
      heala-logos
    </div>
    <h1>로그인</h1>
    <el-row>
      <el-col :span="24"><div class="introduce-text-align-start">아이디</div>
        <input type="text" class='get-input bg-gray' id="identity" v-model="data.identity" maxlength="20" required>
      </el-col>
      <br>
      <el-col :span="24"><div class="introduce-text-align-start">비밀번호</div>
        <div class="password-box get-input bg-gray introduce-text-align-start">
          <input type="password" id="password" class="get-input bg-gray artificial-padding-right" v-model="data.password" maxlength="20" required>
          <span class="el-icon-chat-line-round text-align:right" @click="toggleIcon"></span>
        </div>
      </el-col>
      <el-col :span="24" class="signup-forgetpassword-button justify-content-space-between">
        <span><router-link :to="{ name: 'signup' }" class="text-decoration-none">가입하기</router-link></span>
        <span><router-link :to="{ name: 'signup' }" class="text-decoration-none">[]비밀번호를 잊으셨나요?</router-link> </span>
      </el-col>
      <button class="bg-blue get-input join-button-setting" @click="login(data)">로그인</button>
    </el-row>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: "login",
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
  .bg-blue {
    background: #1A8EFA;
    color: white;
    /* color: #99a9bf; */
  }
  .bg-gray {
    background: #F0F0F0;
  }
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    width: 80%;
    height: 40px;
    margin-bottom: 20px;
    padding: 0px 2px;
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
  .artificial-padding-right {
    padding-right: 10%;
  }
</style>