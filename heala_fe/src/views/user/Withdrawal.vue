<template>
  <div>
    <div>
      heala-logos
    </div>
    <h1>회원 탈퇴</h1>
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
      <button class="bg-green get-input join-button-setting" @click="Withdrawal(data)">회원 탈퇴하기</button>
    </el-row>
    <br>

  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import router from "@/router/index.js"
import axios from "axios"

export default {
  name: "Withdrawal",
  methods: {
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
    Withdrawal(data) {
      axios.post(SERVER.URL + SERVER.ROUTES.login, data)
        .then(() => {
          console.log('존재하는 아이디')
          // 아이디 삭제 요청을 보낸다.
      })
        .catch(() => {
          alert('아이디나 비밀번호가 일치하지 않습니다.')
      })
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
    login: function ({ commit }, data) {
      // event.preventDefault()
      console.log(data)
      axios.post(SERVER.URL + SERVER.ROUTES.login, data)
        .then((res) => {
          // console.log(res)
          // console.log("로그인 요청 성공")
          commit("SET_USERID", res)
          commit("SET_USERNAME", data)
          // commit("SET_TOKEN", res.data.token) // jwt 사용시 적용
          // commit("GET_USERNAME") // 가서 디코딩하기
          // commit("GET_USERID") // 가서 디코딩하기
          // dispatch("verifyUser", data) // 관리자 권한 검증
          router.push({ name: "CommunityMain" }) // 홈 피드 구현 후 변경
      })
        .catch((err) => {
          console.log("로그인 에러 발생")
          console.log(err)
          console.error(err.response.data)
      })
    },
}
</script>

<style scoped>
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-left: 10%;
    text-align: start;
  }
  .bg-green {
    background: #ADEC6E;
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
  .artificial-padding-right {
    padding-right: 10%;
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
  .join-button-setting {
    margin-top: 30px;
    height: 45px;
    width: 80%;
    cursor: pointer;
  }
</style>