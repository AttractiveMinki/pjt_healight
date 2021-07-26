<template>
  <div>
    <h1>회원 가입</h1>
    <div>
      <label for="username">이메일 </label>
      <input type="text" id="email" class="form-control form-control-lg" v-model="data.email">
    </div>
    <div>
      <label for="password">아이디</label>
      <input type="password" id="identity" class="form-control form-control-lg" v-model="data.identity">
    </div>
    <div>
      <label for="password">닉네임</label>
      <input type="password" id="password" class="form-control form-control-lg" v-model="data.password">
    </div>
    <div>
      <label for="password">비밀번호 입력</label>
      <input type="password" id="passwordConfirmation" class="form-control form-control-lg" v-model="passwordConfirmation">
    </div>
    <div>
      <label for="passwordConfirmation">비밀번호 확인 </label>
      <input 
        type="password" 
        id="passwordConfirmation" 
        class="form-control form-control-lg"
        v-model="data.passwordConfirmation"
        @keypress.enter="join(data)"
      >
    </div>
    <button @click="join(data)">회원가입</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'join',
  data: function () {
    return {
      data: {
        email: '',
        identity: '',
        name: '',
        password: '',
      },
    passwordConfirmation: ''
    }
  },
  methods: {
    ...mapActions([
      'signup',
    ])
  }
}
</script>

<style>

</style>