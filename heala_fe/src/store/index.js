import Vue from "vue";
import Vuex from "vuex";

import SERVER from "@/api/drf.js"
import router from "@/router/index.js"
import axios from "axios"

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: "",
    userid: "",
    check_email: true,
    check_identity: true,

    // feeds: [],
    feeds: [
      // post
      {
        image:"www.naver.com",
        title:"안뇽",
        category:"운동",
        sub_category:"질문",
        access:"",
        content:"오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?",
        likes:10,
        anonymous:0,
        created_at:"2021.07.27 22:45",
        updated_at:"2021.07.27 22:45",
        user_id: 12123,
      },
      {
        image:"www.daum.net",
        title:"헬로우",
        category:"마음",
        sub_category:"정보",
        access:"",
        content:"만나서 반가워열",
        likes:3,
        anonymous:0,
        created_at:"2021.07.27 19:04",
        updated_at:"2021.07.27 19:04",
        user_id: 522,
      },
      {
        image:"www.google.com",
        title:"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십",
        category:"식단",
        sub_category:"일반",
        access:"",
        content:"오늘 정말 힘들고 지치는 하루였는데 우리 치즈 때문에 힘이 난다~!",
        likes:6,
        anonymous:0,
        created_at:"2021.07.16 16:45",
        updated_at:"2021.07.16 16:45",
        user_id: 329,
      },
    ],
    with_challenges: [
      {
        id : "12323",
        title : "Daily 1km 러닝",
        category : "운동",
        start_date : "",
        end_date : "",
        image : "www.naver.com",
        certify_info : "",
        introduction : "러닝 어렵지 않아요! 운동화만 있으면 준비 끝",
        user_id : "",
        certify_day : "",
        certify_week : "",
      },
      {
        id : "12323",
        title : "Daily 1km 러닝",
        category : "운동",
        start_date : "",
        end_date : "",
        image : "www.naver.com",
        certify_info : "",
        introduction : "러닝 어렵지 않아요! 운동화만 있으면 준비 끝",
        user_id : "",
        certify_day : "",
        certify_week : "",
      },
      {
        id : "12323",
        title : "Daily 1km 러닝",
        category : "운동",
        start_date : "",
        end_date : "",
        image : "www.naver.com",
        certify_info : "",
        introduction : "러닝 어렵지 않아요! 운동화만 있으면 준비 끝",
        user_id : "",
        certify_day : "",
        certify_week : "",
      },
    ]
    // jwt 사용시 적용
    // authToken: localStorage.getItem("jwt"), // 로그인시 필요한 토큰
    // decoded: "",
  },
  getters: {
    isLoggedIn: function (state) {
      // authToken이 있으면 True, 없으면 False
      return state.userid === "" ? true : false
    },
  },
  mutations: {
    // jwt 사용시 적용
    // SET_TOKEN: function (state, token) {
    //   // 1. JWT를 변경
    //   state.authToken = token
    //   // 2. Local storage의 값도 변경
    //   state.decoded = localStorage.setItem("jwt", token)
    // },
    SET_FEEDS: function (state, feeds) {
      state.feeds = feeds
    },
    SET_USERID: function (state, userid) {
      state.userid = userid
    },
    INIT_USERID: function (state) {
      state.userid = ""
    },
    INIT_USERNAME: function (state) {
      state.username = ""
    },
    SET_CHECKIDENTITY: function (state) {
      state.check_identity = false
    },
    SET_CHECKEMAIL: function (state) {
      state.check_email = false
    },
    INIT_CHECKIDENTITY: function (state) {
      state.check_identity = true
    },
    INIT_CHECKEMAIL: function (state) {
      state.check_email = true
    },
  },
  actions: {
    signup: function (context, data) {
      console.log(SERVER.URL)
      console.log(SERVER.ROUTES.signup)
      axios.post(SERVER.URL + SERVER.ROUTES.signup, data)
        .then(() => {
          router.push({ name: "Login" })
      })
        .catch((err) => {
          console.log(err)
      })
    },
    login: function ({ commit }, data) {
      // event.preventDefault()
      console.log(data)
      axios.post(SERVER.URL + SERVER.ROUTES.login, data)
        .then((res) => {
          
          console.log(res)
          console.log("로그인 요청 성공")
          commit("SET_USERID", res)
          // commit("SET_TOKEN", res.data.token) // jwt 사용시 적용
          // commit("GET_USERNAME") // 가서 디코딩하기
          // commit("GET_USERID") // 가서 디코딩하기
          // dispatch("verifyUser", data) // 관리자 권한 검증
          router.push({ name: "Home" })
      })
        .catch((err) => {
          console.log("로그인 에러 발생")
          console.log(err)
      })
    },
    logout: function ({ commit }) {
      commit("INIT_USERID")
      commit("INIT_USERNAME")
      router.push({ name: "Login" })
    },
    checkIdentity: function ({ commit }, identity ) {
      axios.post(SERVER.URL + SERVER.ROUTES.checkIdentity + identity, identity)
        .then(() => {
          console.log("DB에 없는 이메일")
          commit("SET_CHECKIDENTITY")
      })
        .catch(() => {
          console.log("DB에 있는 이메일")
          commit("INIT_CHECKIDENTITY")
      })
    },
    checkEmail: function ({ commit }, email ) {
      axios.post(SERVER.URL + SERVER.ROUTES.checkEmail + email, email)
        .then(() => {
          console.log("DB에 없는 아이디")
          commit("SET_CHECKEMAIL", )
      })
        .catch(() => {
          console.log("DB에 있는 아이디")
          commit("INIT_CHECKEMAIL")
      })
    },
    getFeeds: function ({ commit }) {
      // const headers = getters.config
      axios.get(SERVER.URL + SERVER.ROUTES.community + "/main/")
        .then((res) => {
          console.log(res)
          commit("SET_FEEDS")
      })
        .catch((err) => {
          console.err(err)
      })
    },
  },
  modules: {},
});
