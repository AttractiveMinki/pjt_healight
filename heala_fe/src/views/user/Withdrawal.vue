<template>
  <div>
    <Navbar />
    <div>
      <img class="logo-image" src="@/assets/image/kiwi_logo.png" />
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
      <button class="bg-red get-input join-button-setting" @click="showDeleteModal = true">회원 탈퇴하기</button>
      <modal v-if="showDeleteModal" @yes="Withdrawal(data)" @no="showDeleteModal = false">
          <template v-slot:header>회원 탈퇴</template>
          <template v-slot:body>정말 회원탈퇴하시겠습니까?</template>
      </modal>
    </el-row>
    <br>

  </div>
</template>

<script>
import SERVER from "@/api/drf.js"
import axios from "axios"
import Navbar from "@/components/my_page/Navbar"
import router from "@/router/index.js"
import Modal from "@/components/Modal.vue";

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
      console.log(SERVER.URL + SERVER.ROUTES.withdrawal, data)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.withdrawal}/checkAuthorization`, data)
        .then(() => {
          console.log('회원 인증 완료')
          if (localStorage.getItem('userIdentity') == data.identity) {
            axios.delete(`${SERVER.URL}${SERVER.ROUTES.withdrawal}/${this.$store.state.userId}`)
            .then((res) => {
              console.log(res)
              alert('삭제가 완료되었습니다. 그동안 이용해주셔서 감사합니다.')
              localStorage.removeItem('userIdentity')
              localStorage.removeItem('jwt')
              localStorage.removeItem('userName')
              localStorage.removeItem('userImage')
              router.push({ name: "Login" })
            })
            .catch((err) => {
              console.log(err.response.data)
            })
          } else {
            alert('현재 접속한 아이디와 탈퇴하는 아이디가 다릅니다. 재접속 후 시도해주세요.')
          }
          this.showDeleteModal = false


          // 아이디 삭제 요청을 보낸다.
      })
        .catch((err) => {
          console.log(err)
          console.error(err.response.data)
          alert('아이디나 비밀번호가 일치하지 않습니다.')
          this.showDeleteModal = false
      })
    },
  },
  data: function () {
    return {
      data: {
        identity: "",
        password: "",
      },
      isvisible: false,
      showDeleteModal: false,
    }
  },
  created () {
    this.$store.commit("GET_USERID")
  },
  components: { 
    Modal, 
    Navbar,
  }
}
</script>

<style scoped>
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-left: 10%;
    text-align: start;
  }
  .bg-red {
    background: red;
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