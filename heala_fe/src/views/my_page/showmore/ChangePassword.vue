<template>
  <div>
    <Navbar />
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="22">
          <input type="password" class='get-input bg-gray' id="password" v-model="password" v-bind:class="{error : error.password, complete:!error.password&&password.length>0}" placeholder=" 현재 비밀번호" maxlength="20" required>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="22">
          <input type="password" class='get-input bg-gray' id="newpassword" v-model="newpassword" v-bind:class="{error : error.newpassword, complete:!error.newpassword&&newpassword.length>0}" placeholder=" 새 비밀번호" maxlength="20" required>
        </el-col>
      </el-row>
      <div class="error-text" v-if="error.newpassword">{{ error.newpassword }}</div>
      <el-row type="flex" justify="center" align="middle">
        <el-col :span="22">
          <input
            type="password" 
            id="passwordConfirm" 
            class="get-input bg-gray"
            maxlength="20"
            v-model="passwordConfirm"
            v-bind:class="{error : error.passwordConfirm, complete:!error.passwordConfirm&&passwordConfirm.length!==0}"
            placeholder=" 새 비밀번호 확인" 
            @keypress.enter="passwordChange(data)"
            required
          >
        </el-col>
      </el-row>
      <div class="error-text" v-if="error.passwordConfirm">{{ error.passwordConfirm }}</div>

      <button class="get-input bg-green join-button-setting" @click="passwordChange(data)" :disabled="!isSubmit" :class="{disabled : !isSubmit}">비밀번호 변경하기</button>
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"
import PV from "password-validator";
import axios from "axios"
import SERVER from "@/api/drf.js"
import router from "@/router/index.js"

export default {
  name: "Scrap",
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
    password: function() {
      this.data.password = this.password
      this.checkForm();
    },
    newpassword: function() {
      this.data.newpassword = this.newpassword
      this.checkForm();
    },
    passwordConfirm: function() {
      this.checkForm();
    },
    // isTerm: function() {
    //   this.checkForm();
    // },
  },
  data: function () {
    return {
      data: {
        password: "",
        newpassword: "",
      },
      error: {
        password: false,
        newpassword: false,
        passwordConfirm: false,
        // term: false
      },
      isSubmit: false,
      password: "",
      newpassword: "",
      passwordConfirm: "",
      passwordSchema: new PV(),
    }
  },
  components: {
    Navbar,
  },
  methods: {
    checkForm() {
      if (
        this.password.length >= 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;

      if (
        this.newpassword.length >= 0 &&
        !this.passwordSchema.validate(this.newpassword)
      )
        this.error.newpassword = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.newpassword = false;

      if (this.newpassword !== this.passwordConfirm) {
        this.error.passwordConfirm = "비밀번호가 일치하지 않습니다."
      }
      else this.error.passwordConfirm = false;

      let isSubmit = true;
      Object.values(this.error).map(v => {
        if (v) isSubmit = false;
      });
      this.isSubmit = isSubmit;
    },
    passwordChange: function () {
      axios.post(`${SERVER.URL}${SERVER.ROUTES.passwordChange}`, this.data)
        .then(() => {
          this.$alert("패스워드가 정상적으로 변경되었습니다.")
          router.push({ name: "Profile" })
      })
        .catch((err) => {
          console.log(`${SERVER.URL}${SERVER.ROUTES.passwordChange}`)
          console.log(this.data)
          this.$alert("비밀번호가 일치하지 않습니다.")
          console.error(err.response.data)
      })
    },
  },

}
</script>

<style scoped>
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
    margin-bottom: 10px;
  }
  .disabled {
    background: gray;
    color: gainsboro;
    cursor: default;
  }
</style>