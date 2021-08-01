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
    postDetail: {
      id: 1,
      title: "일이삼사오육칠팔구십일이삼사오육칠팔구십일이",
      created_at: "13:56",
      image: "cat.jpg",
      likes: 0,
      scrap: 0,
      anonymouse: 0,
      category:"운동",
      sub_category:"질문",
      access:"",
      content: "오늘 정말 너무 힘들고 지치는 하루였는데 우리 귀여운 치즈 덕에 힘난다 ㅠ ㅠ 우리네들 파이팅~!!",
      user_id: 2,
    },
    feeds: [
      {
        id: 1,
        image:"cat.jpg",
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
        image:"cat.jpg",
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
        image:"cat.jpg",
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
    comments: [
      {
        id: 1,
        text: "오늘도 치즈 귀여움에 힐링 받고 갑니다.. 치즈는 사랑..♥",
        likes: 1,
        created_at: "07/17 22:45",
        comment_id: "",
        user_id: 2,
        post_id: 1,
      },
      {
        id: 2,
        text: "치즈는 사랑이지",
        likes: 1,
        created_at: "07/17 22:50",
        comment_id: "1",
        user_id: 1,
        post_id: 1,
      },
    ],
    with_challenges: [
      {
        id : 1,
        title : "Daily 1km 러닝",
        category : 0,
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
        category : 0,
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
        category : 0,
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
    setPostDetail: (state, payload) => {
      state.postDetail = payload.post;
    },
    setPostComments: (state, payload) => {
      state.comments = payload.comments;
    },

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
    setPostDetail(store, { id }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.getPost + id)
        .then((response) =>
          store.commit('setPostDetail', { post: response.data })
        )
        .catch((exp) =>
          console.log(`게시글 조회에 실패했습니다: ${exp}`)
        );

      axios
      .get(SERVER.URL + SERVER.ROUTES.getPost + id + SERVER.ROUTES.getComment)
      .then((response) =>
        store.commit('setPostComments', { comments: response.data })
      )
      .catch((exp) =>
        console.log(`댓글 조회에 실패했습니다: ${exp}`)
      );
    },

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
