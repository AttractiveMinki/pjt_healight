import Vue from "vue";
import Vuex from "vuex";

import SERVER from "@/api/drf.js"
import router from "@/router/index.js"
import axios from "axios"

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 한길 #@

    // 원석 #@

    // 주엽 #@
    username: "",
    userid: "",
    currentPageId: 0,
    // check_email: true,
    // check_identity: true,

    // recent_challenges: [],
    recent_challenges: [
      {title: '숨쉬기'},
      {title: '1km 러닝'},
      {title: '수영하기'},
      {title: '숨쉬기'},
      {title: '1km 러닝'},
      {title: '수영하기'},
      {title: '숨쉬기'},
      {title: '1km 러닝'},
      {title: '수영하기'},
    ],
    // feeds: [],
    feeds: [
      // post
      {
        id: 1,
        image:"www.naver.com",
        title:"안뇽",
        category:0,
        sub_category:2,
        access:"",
        content:"오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?오늘 근육 잘먹었쥬?",
        likes:10,
        anonymous:0,
        created_at:"2021.07.27 22:45",
        updated_at:"2021.07.27 22:45",
        user_id: 12123,
      },
      {
        id: 2,
        image:"www.daum.net",
        title:"헬로우",
        category:2,
        sub_category:1,
        access:"",
        content:"만나서 반가워열",
        likes:3,
        anonymous:0,
        created_at:"2021.07.27 19:04",
        updated_at:"2021.07.27 19:04",
        user_id: 522,
      },
      {
        id: 3,
        image:"www.google.com",
        title:"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십",
        category:1,
        sub_category:0,
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
        id : 1,
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
        id : 2,
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
        id : 3,
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
    // 한길 #@

    // 원석 #@

    // 주엽 #@
    isLoggedIn: function (state) {
      // authToken이 있으면 True, 없으면 False
      return state.userid === "" ? true : false
    },
  },
  mutations: {
    // 한길 #@

    // 원석 #@

    // 주엽 #@
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
    // SET_CHECKIDENTITY: function (state) {
    //   state.check_identity = false
    // },
    // SET_CHECKEMAIL: function (state) {
    //   state.check_email = false
    // },
    // INIT_CHECKIDENTITY: function (state) {
    //   state.check_identity = true
    // },
    // INIT_CHECKEMAIL: function (state) {
    //   state.check_email = true
    // },
  },
  actions: {
    // 한길 #@

    // 원석 #@

    // 주엽 #@
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
