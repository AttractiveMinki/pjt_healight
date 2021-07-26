import Vue from "vue";
import Vuex from "vuex";

import SERVER from '@/api/drf.js'
import router from '@/router/index.js'
import axios from 'axios'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: 'annonymous_User',

    // jwt 사용시 적용
    authToken: localStorage.getItem('jwt'), // 로그인시 필요한 토큰
    decoded: '',
  },
  mutations: {
    // jwt 사용시 적용
    SET_TOKEN: function (state, token) {
      // 1. JWT를 변경
      state.authToken = token
      // 2. Local storage의 값도 변경
      state.decoded = localStorage.setItem('jwt', token)
    },
  },
  actions: {
    signup: function (context, data) {
      console.log(SERVER.URL)
      console.log(SERVER.ROUTES.signup)
      axios({
        url: SERVER.URL + SERVER.ROUTES.signup,
        method: 'post',
        data: data,
      })
      .then(() => {
        router.push({ name: 'Login' })
      })
      .catch((err) => {
        console.log(err)
      })
    },
    login: function (data) {
      // event.preventDefault()
      console.log(data)
      axios({
        url: SERVER.URL + SERVER.ROUTES.login,
        method: 'post',
        data: data,
      })
      .then((res) => {
        console.log(res)
        console.log('로그인 요청 성공')
        // commit('SET_TOKEN', res.data.token) // jwt 사용시 적용
        // commit('GET_USERNAME') // 가서 디코딩하기
        // commit('GET_USERID') // 가서 디코딩하기
        // dispatch('verifyUser', data) // 관리자 권한 검증
        router.push({ name: 'Home' })
      })
      .catch((err) => {
        console.log('로그인 에러 발생')
        console.log(err)
      })
    },
  },
  modules: {},
});
