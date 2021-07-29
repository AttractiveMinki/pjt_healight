<template>
  <div>
    <div>
      heala-logos
    </div>
    <h1>회원 가입</h1>
    <el-row>
      <el-col :span="24"><div class="introduce-text-align-start"></div>
        <input type="text" class="get-input bg-gray" id="email" v-model="email" v-bind:class="{error : error.email, complete:!error.email&&identity.email>0}" placeholder=" 이메일" maxlength="30" required>
        <div class="error-text" v-if="error.email">{{error.email}}</div>
      </el-col>
      <button class="check-button-setting bg-blue" @click="checkEmail(email)">중복 확인</button>
      <div class="error-text" v-if="error.check_email">{{error.check_email}}</div>
      <!-- modal - 사용 가능합니다! 이미 있는 이메일입니다. -->
      <br>
      <el-col :span="24"><div class="introduce-text-align-start"></div>
        <input type="text" class='get-input bg-gray' id="identity" v-model="identity" v-bind:class="{error : error.identity, complete:!error.identity&&identity.length>0}" placeholder=" 아이디" maxlength="20" required>
        <div class="error-text" v-if="error.identity">{{error.identity}}</div>
      </el-col>
      <button class="check-button-setting bg-blue" @click="checkIdentity(identity)">중복 확인</button>
      <div class="error-text" v-if="error.check_identity">{{error.check_identity}}</div>
      <!-- modal - 사용 가능합니다! 이미 있는 아이디입니다. -->
      <br>
      <el-col :span="24"><div class="introduce-text-align-start"></div>
        <input type="text" class='get-input bg-gray' id="name" v-model="name" v-bind:class="{error : error.name, complete:!error.name&&name.length>0}" placeholder=" 닉네임" maxlength="15" required >
        <div class="error-text" v-if="error.name">{{error.name}}</div>
      </el-col>
      <br>
      <el-col :span="24"><div class="introduce-text-align-start"></div>
        <input type="password" class='get-input bg-gray' id="password" v-model="password" v-bind:class="{error : error.password, complete:!error.password&&password.length>0}" placeholder=" 비밀번호" maxlength="20" required>
        <div class="error-text" v-if="error.password">{{error.password}}</div>
      </el-col>
      <br>
      <el-col :span="24"><div class="introduce-text-align-start"></div>
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
        <div class="error-text" v-if="error.passwordConfirm">{{ error.passwordConfirm }}</div>
      </el-col>
      <br>
      <br>
      <button class="get-input bg-blue join-button-setting" @click="signup(data)" :disabled="!isSubmit" :class="{disabled : !isSubmit}">회원가입</button>
    </el-row>
    <br>

  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import { mapActions } from 'vuex'
import { store } from '../../store/index.js'

export default {
  name: 'join',
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
      console.log(this.isSubmit)
      this.checkForm();
    },
    // isTerm: function() {
    //   this.checkForm();
    // },
  },
  methods: {
    ...mapActions([
      'signup',
      'checkIdentity',
      'checkEmail',
    ]),
    checkForm() {
      if (this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (this.identity.length == 0)
        this.error.identity = "아이디를 입력해주세요."
      else this.error.identity = false;
      console.log(store)
      console.log('check_email:', store.check_email, 'check_identity:', store.check_identity)
      if (store.check_email == true)
        this.error.check_email = "아이디 중복 확인 버튼을 눌러주세요."
      else this.error.check_email = false;

      if (this.name.length == 0)
        this.error.name = "닉네임을 입력해주세요."
      else this.error.name = false;

      if (store.check_identity == true)
        this.error.check_identity = "아이디 중복 확인 버튼을 눌러주세요."
      else this.error.check_identity = false;

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
    checkEmail() {},
    checkIdentity() {},
  },
  data: function () {
    return {
      data: {
        email: '',
        identity: '',
        name: '',
        password: '',
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
      email: '',
      identity: '',
      name: '',
      password: '',
      passwordConfirm: '',
      passwordSchema: new PV(),
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
  }
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-top: 10px;
    margin-left: 10%;
    text-align: start;
  }
  .check-button-setting {
    margin-top: 5px;
    height: 20px;
    width: 30%;
    cursor: pointer;
  }
  .join-button-setting {
    margin-top: 30px;
    height: 45px;
    width: 80%;
    cursor: pointer;
  }
  .error-text {
    color: red;
    margin-bottom: 10px;
  }
  .disabled {
    background: gray;
    color: gainsboro;
    cursor: default;
  }
</style>