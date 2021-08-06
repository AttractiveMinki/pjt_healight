import Vue from "vue";
import Vuex from "vuex";

import SERVER from "@/api/drf.js"
import router from "@/router/index.js"
import util from "@/util.js"
import axios from "axios"

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 한길 #@
    loginUser: {
      id: 1,
      name: "나다",
      image: "blue.jpg",
    },
    postDetail: {
      id: 1,
      title: "일이삼사오육칠팔구십일이삼사오육칠팔구십일이",
      createdAt: "07/27 13:56",
      image: "cat.jpg",
      likes: 0,
      anonymous: 0,
      category: 1,
      subCategory:2,
      access:"",
      content: "오늘 정말 너무 힘들고 지치는 하루였는데 우리 귀여운 치즈 덕에 힘난다 ㅠ ㅠ 우리네들 파이팅~!!",
      userId: 2,
    },
    postUser: {
      id: 2,
      name: "안나짱",
      image: "user.png",
    },
    postScrap: {
      scrap: false,
    },
    postLike: {
      like: true,
    },
    comments: [
      {
        id: 1,
        text: "오늘도 치즈 귀여움에 힐링 받고 갑니다.. 치즈는 사랑..♥",
        likes: 1,
        createdAt: "00/00 11:11",
        commentId: "",
        userId: 2,
        postId: 2,
      },
      {
        id: 2,
        text: "치즈는 사랑이지",
        likes: 1,
        createdAt: "11/11 22:22",
        commentId: 1,
        userId: 1,
        postId: 2,
      },
      {
        id: 3,
        text: "test33333333333333333333",
        likes: 0,
        createdAt: "00/00 33:33",
        commentId: "",
        userId: 2,
        postId: 2,
      },
      {
        id: 4,
        text: "test44444444444444444",
        likes: 0,
        createdAt: "11/11 44:44",
        commentId: 1,
        userId: 1,
        postId: 2,
      },
    ],
    commentUsers: [
      {
        id: 1,
        name: "1번 유저",
        image: "user.png",
      },
      {
        id: 2,
        name: "2번 유저",
        image: "blue.jpg",
      },
    ],
    commentLikes: [
      {
        commentId: 1,
        like: true,
      },
      {
        commentId: 2,
        like: false,
      },
      {
        commentId: 3,
        like: true,
      },
      {
        commentId: 4,
        like: false,
      },
    ],
    isFollowingList: [
      {
        follow_id: 2,
        isFollowing: true,
      },
      {
        follow_id: 3,
        isFollowing: false,
      },
    ],

    // 원석 #@

    // 주엽 #@
    username: "",
    userid: 0,
    currentPageId: 0,
    profileSelectedCategory: 1,
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
    // getFollowUserById: (state) => (id) => {
    //   return state.isFollowingList.find(followUser => followUser.follow_id == id);
    // },

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
    setIsFollowing: (state, payload) => {
      state.isFollowingList[payload.index].isFollowing = payload.isFollowing;
    },
    setCommentUsers: (state, payload) => {
      state.commentUsers = payload.commentUsers;
    },
    setPostScrap: (state, payload) => {
      state.postScrap = payload.postScrap;
    },
    setPostLike: (state, payload) => {
      state.postLike = payload.postLike;
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
    SET_USERID: function (state, res) {
      state.userid = res.userid
    },
    SET_USERNAME: function (state, res) {
      state.username = res.username
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
    setPostDetail(store, { postId }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + postId)
        .then((response) =>
          store.commit("setPostDetail", { post: response.data })
        )
        .catch((exp) => {
          console.log(`게시글 조회에 실패했습니다: ${exp}`);
          // console.log(util.convertToTree(store.state.comments, "id", "commentId", "childrenComment"))
        });
    },
    setPostComments(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + postId + SERVER.ROUTES.comment)
        .then((response) => {
          let commentTree = util.convertToTree(response.data, "id", "commentId", "childrenComment");
          store.commit("setPostComments", { comments: commentTree });
          let commentUserIds = new Set();
          store.state.comments.forEach(comment => {
            commentUserIds.add(comment.userId);
          });
          store.dispatch("setCommentUsers", { commentUserIds });
        })
        .catch((exp) => {
          console.log(`댓글 조회에 실패했습니다: ${exp}`);
          let commentTree = util.convertToTree(store.state.comments, "id", "commentId", "childrenComment");
          store.commit("setPostComments", { comments: commentTree });
        });
    },
    setPostScrap(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.scrap + `?postId=${postId}&userId=${store.state.loginUser.id}`)
        .then((response) =>
          store.commit("setPostScrap", { scrap: response.data })
        )
        .catch((exp) =>
        console.log(`게시글 스크랩 여부 조회에 실패했습니다: ${exp}`)
      );
    },
    setPostLike(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.like + `?postId=${postId}&userId=${store.state.loginUser.id}`)
        .then((response) =>
          store.commit("setPostLike", { like: response.data })
        )
        .catch((exp) =>
        console.log(`게시글 좋아요 여부 조회에 실패했습니다: ${exp}`)
      );
    },
    setCommentUsers(store, { commentUserIds }) {
      axios
      .post(SERVER.URL + SERVER.ROUTES.commentUsers, {
        commentUserIds,
      })
      .then((response) =>
        store.commit("setCommentUsers", { commentUsers: response.data })
      )
      .catch((exp) =>
        console.log(`댓글 작성자 조회에 실패했습니다: ${exp}`)
      );
    },
    follow(store, { follow_id }){
      axios
        .post(SERVER.URL + SERVER.ROUTES.follow, {
          user_id: store.state.loginUser.id,
          follow_id,
        })
        .then(() => {
          // 이 부분은 필요없나? 헷갈림
          const index = store.state.isFollowingList.findIndex(element => element.follow_id == follow_id);
          store.commit("setIsFollowing", { index, isFollowing: true });
        })
        .catch((exp) => {
          console.log(`팔로우에 실패했습니다: ${exp}`)
        });
    },
    cancelFollow(store, { follow_id }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.follow, {
          user_id: store.state.loginUser.id,
          follow_id,
        })
        .then(() => {
          // 이 부분은 필요없나? 헷갈림
          const index = store.state.isFollowingList.findIndex(element => element.follow_id == follow_id);
          store.commit("setIsFollowing", { index, isFollowing: false });
        })
        .catch((exp) => {
          console.log(`팔로우 취소에 실패했습니다: ${exp}`)
        });
    },
    likePost(store, { postId }){
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.like, {
          user_id: store.state.loginUser.id,
          post_id: postId,
        })
        .catch((exp) => {
          console.log(`게시글 좋아요에 실패했습니다: ${exp}`)
        });
    },
    cancelLikePost(store, { postId }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.like, {
          user_id: store.state.loginUser.id,
          post_id: postId,
        })
        .catch((exp) => {
          console.log(`게시글 좋아요 취소에 실패했습니다: ${exp}`)
        });
    },
    scrapPost(store, { postId }){
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.scrap, {
          user_id: store.state.loginUser.id,
          post_id: postId,
        })
        .catch((exp) => {
          console.log(`게시글 스크랩에 실패했습니다: ${exp}`)
        });
    },
    cancelScrapPost(store, { postId }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.scrap, {
          user_id: store.state.loginUser.id,
          post_id: postId,
        })
        .catch((exp) => {
          console.log(`게시글 스크랩 취소에 실패했습니다: ${exp}`)
        });
    },
    createComment(store, payload) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment, {
          text: payload.message,
          user_id: store.state.loginUser.id,
          post_id: payload.postId,
        })
        .catch((exp) => {
          console.log(`댓글 작성에 실패했습니다: ${exp}`)
        });
    },
    deleteComment(store, payload) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment + payload.commetId)
        .then((response) =>
          store.commit("setPostComments", { comments: response.data })
        )
        .catch((exp) =>
          console.log(`댓글 삭제에 실패했습니다: ${exp}`)
        );
    },
    likeComment(store, payload) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment + SERVER.ROUTES.like, {
          user_id: store.state.loginUser.id,
          comment_id: payload.commentId,
        })
        .catch((exp) => {
          console.log(`댓글 좋아요에 실패했습니다: ${exp}`)
        });
    },
    cancelLikeComment(store, payload) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment + SERVER.ROUTES.like, {
          user_id: store.state.loginUser.id,
          comment_id: payload.commentId,
        })
        .catch((exp) => {
          console.log(`댓글 좋아요 취소에 실패했습니다: ${exp}`)
        });
    },

    // 원석 #@

    // 주엽 #@
    signup: function (context, data) {
      console.log(SERVER.URL)
      console.log(SERVER.ROUTES.signup)
      console.log(data)
      axios.post(SERVER.URL + SERVER.ROUTES.signup, data)
        .then(() => {
          router.push({ name: "Login" })
      })
        .catch((err) => {
          console.log(err)
          console.error(err.response.data)
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
          commit("SET_USERNAME", res)
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
