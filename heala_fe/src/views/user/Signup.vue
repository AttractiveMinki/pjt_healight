<template>
  <div>
    <div>
      <img class="logo-image" src="@/assets/image/kiwi_logo.png" />
    </div>
    <br>
    <!-- <h1>회원 가입</h1> -->
    <div>
      <el-row type="flex" justify="center" align="middle" style="margin: 0 0 0 0;">
          <el-col :span="15" align="middle">
            <input type="text" class="get-input bg-gray" id="email" v-model="email" v-bind:class="{error : error.email, complete:!error.email&&identity.email>0}" placeholder=" 이메일" maxlength="30" required>
          </el-col>
          <el-col :span="1"></el-col>
          <el-col :span="6" align="middle">
            <button class="check-button-setting bg-green" @click="checkEmail(email)">중복 확인</button>
          </el-col>
      </el-row>
      <div class="error-text" v-if="error.email">{{ error.email }}</div>
      <div class="error-text" v-if="error.check_email">{{ error.check_email }}</div>
      <!-- modal - 사용 가능합니다! 이미 있는 이메일입니다. -->

      <el-row type="flex" justify="center" align="middle"> 
        <el-col :span="15">
          <input type="text" class='get-input bg-gray' id="identity" v-model="identity" v-bind:class="{error : error.identity, complete:!error.identity&&identity.length>0}" placeholder=" 아이디" maxlength="20" required>
        </el-col>
        <el-col :span="1"></el-col>
        <el-col :span="6" align="middle">
          <button class="check-button-setting bg-green" @click="checkIdentity(identity)">중복 확인</button>
        </el-col>
      </el-row>
      <div class="error-text" v-if="error.identity">{{ error.identity }}</div>
      <div class="error-text" v-if="error.check_identity">{{ error.check_identity }}</div>
      <!-- modal - 사용 가능합니다! 이미 있는 아이디입니다. -->

      <el-row type="flex" justify="center" align="middle">
        <el-col :span="22">
          <input type="text" class='get-input bg-gray' id="name" v-model="name" v-bind:class="{error : error.name, complete:!error.name&&name.length>0}" placeholder=" 닉네임" maxlength="15" required >
        </el-col> 
      </el-row>
      <div class="error-text" v-if="error.name">{{error.name}}</div>

      <el-row type="flex" justify="center" align="middle">
        <el-col :span="22">
          <input type="password" class='get-input bg-gray' id="password" v-model="password" v-bind:class="{error : error.password, complete:!error.password&&password.length>0}" placeholder=" 비밀번호" maxlength="20" required>
        </el-col>
      </el-row>
      <div class="error-text" v-if="error.password">{{ error.password }}</div>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="22">
          <input
            type="password" 
            id="passwordConfirm" 
            class="get-input bg-gray"
            maxlength="20"
            v-model="passwordConfirm"
            v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&passwordConfirm.length!==0}"
            placeholder=" 비밀번호 확인" 
            @keypress.enter="signup(data)"
            required
          >
        </el-col>
      </el-row>
      <div class="error-text" v-if="error.passwordConfirm">{{ error.passwordConfirm }}</div>

      <button class="get-input bg-green join-button-setting" @click="signup(data)" :disabled="!isSubmit" :class="{disabled : !isSubmit}">회원가입</button>
      <br>
      <button class="bg-white get-input join-button-setting" @click="goLogin()">로그인 화면으로 이동</button>
    </div>
    <br>

  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import { mapActions } from 'vuex'
import axios from "axios"
import SERVER from "@/api/drf.js"
import router from "@/router/index.js"

export default {
  name: 'Signup',
  created() {
    this.component = this;

    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(20)
      .has()
      .digits()
      .has()
      .letters();
      // .has() 특수문자 제한 설정
      // .punctuation();
  },
  watch: {
    email: function() {
      this.data.email = this.email
      this.checkForm();
    },
    identity: function() {
      this.data.identity = this.identity
      this.checkForm();
    },
    name: function() {
      this.data.name = this.name
      this.checkForm();
    },
    password: function() {
      this.data.password = this.password
      this.checkForm();
    },
    passwordConfirm: function() {
      this.checkForm();
    },
    // isTerm: function() {
    //   this.checkForm();
    // },
  },
  methods: {
    ...mapActions([
      'signup',
    ]),

    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (this.identity.length == 0)
        this.error.identity = "아이디를 입력해주세요."
      else this.error.identity = false;

      if (this.name.length == 0)
        this.error.name = "닉네임을 입력해주세요."
      else this.error.name = false;

      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      if (this.password !== this.passwordConfirm) {
        this.error.passwordConfirm = "비밀번호가 일치하지 않습니다."
      }
      else this.error.passwordConfirm = false;

      if (this.email.length <= 1)
        this.error.check_email = "이메일 중복 확인 버튼을 눌러주세요."

      if (this.identity.length <= 1)
        this.error.check_identity = "아이디 중복 확인 버튼을 눌러주세요."


      // if (this.isTerm == false){
      //   this.error.term = "약관에 동의해주세요."
      // }
      // else this.error.term = ''

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    checkIdentity: function (identity) {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.checkIdentity}${identity}`)
        .then(() => {
          alert("사용 가능합니다!");
          this.error.check_identity = false
          // commit("SET_CHECKIDENTITY", res)
          this.checkForm();
      })
        .catch(() => {
          alert("이미 존재하는 아이디입니다.");
          this.error.check_identity = "이미 존재하는 아이디입니다."
          
          // commit("INIT_CHECKIDENTITY")
      })
    },
    checkEmail: function (email) {
      axios.get(`${SERVER.URL}${SERVER.ROUTES.checkEmail}${email}`)
        .then(() => {
          alert("사용 가능합니다!");
          //// commit("SET_CHECKEMAIL", res)
          this.error.check_email = false
          this.checkForm();
      })
        .catch(() => {
          alert("이미 존재하는 이메일입니다.");
          // commit("INIT_CHECKEMAIL")
          this.error.check_email = "이미 존재하는 이메일입니다."
          // console.log(`${SERVER.URL}${SERVER.ROUTES.checkEmail}${email}`)
      })
    },
    goLogin(){
      router.push({ name: "Login" })
    }
  },
  data: function () {
    return {
      data: {
        email: "",
        identity: "",
        name: "",
        password: "",
      },
      error: {
        email: false,
        identity: false,
        name: false,
        password: false,
        passwordConfirm: false,
        check_email: false,
        check_identity: false,
        // term: false
      },
      isSubmit: false,
      email: "",
      identity: "",
      name: "",
      password: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
      check_email: "",
      check_identity: "",
    }
  },
}
</script>

<style scoped>
  .logo-image {
    padding-top: 10%;
    width: 50%;
  }
  .bg-white {
    background: rgb(197, 223, 189);
    border-width: 0px;
    border-radius: 5px;
    color: black;
  }
  .bg-green {
    background: #ADEC6E;
    border-width: 0px;
    border-radius: 5px;
    color: black;
    /* color: #99a9bf; */
  }
  .bg-gray {
    background: #F0F0F0;
  }
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    margin-top: 10px;
    margin-bottom: 10px;
    width: 100%;
    height: 40px;
    padding-left: 10px;
    box-sizing: border-box;
  }
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-top: 10px;
    margin-left: 10%;
    text-align: start;
  }
  .check-button-setting {
    height: 40px;
    width: 100%;
    cursor: pointer;
    font-weight: bold; 
    font-size: 13px;
  }
  .join-button-setting {
    margin-top: 30px;
    height: 45px;
    width: 80%;
    cursor: pointer;
  }
  .error-text {
    color: red;
    font-size: 11px;
    margin-bottom: 8px;
  }
  .disabled {
    background: gray;
    color: gainsboro;
    cursor: default;
  }
  .get-input:focus {
    outline: none;
  }
</style>