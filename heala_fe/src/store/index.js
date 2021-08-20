import Vue from "vue";
import Vuex from "vuex";

import SERVER from "@/api/drf.js"
import router from "@/router/index.js"
import util from "@/util.js"
import axios from "axios"
import jwt_decode from "jwt-decode"

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 한길 #@
    postDetail: {
      id: 1,
      title: "기본 init 데이터",
      createdAt: "07/27 13:56",
      image: "admin/default",
      likes: 0,
      anonymous: 0,
      category: 1,
      subCategory:2,
      access:"",
      content: "기본 init 데이터",
      userId: 2,
    },
    postUser: {
      id: 2,
      name: "안나짱",
      image: "admin/default",
    },
    postScrap: false,
    postLike: false,
    comments: [],
    commentUsers: [
      {
        id: 1,
        name: "1번 유저",
        image: "admin/default",
      },
      {
        id: 2,
        name: "2번 유저",
        image: "admin/default",
      },
    ],
    commentLikes: [],

    // 원석 #@

    // 주엽 #@
    userIdentity: "",
    userId: "",
    userName: "",
    currentPageId: 0,
    currentPageCategory: 0,
    profileSelectedCategory: 1,
    selectedRouter: 0,
    selectedSubCategory: 3,
    // check_email: true,
    // check_identity: true,

    // jwt 사용시 적용
    authToken: localStorage.getItem("jwt"), // 로그인시 필요한 토큰
    decoded: "",
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
      return state.authToken ? true : false
      // return localStorage.getItem('userId') === "" ? true : false
    },
    // header에 붙일 정보
    config: function (state) {
      return {
        Authorization: `JWT ${state.authToken}`
      }
    },
  },
  mutations: {
    // 한길 #@
    setPostDetail: (state, payload) => {
      state.postDetail = payload.post;
    },
    setPostUser: (state, payload) => {
      state.postUser = payload.postUser;
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
    setCommentLikes: (state, payload) => {
      state.commentLikes = payload.commentLikes;
    },
    setPostScrap: (state, payload) => {
      state.postScrap = payload.scrap;
    },
    setPostLike: (state, payload) => {
      state.postLike = payload.like;
    },
    setCommentLike: (state, payload) => {
      state.commentLikes.push(payload.commentId);
    },
    setCommentLikeCancel: (state, payload) => {
      const index = state.commentLikes.findIndex(element => element.userId == payload.userId);
      state.commentLikes.splice(index, 1);
    },

    // 원석 #@

    // 주엽 #@
    // jwt 사용시 적용
    SET_TOKEN: function (state, token) {
      // 1. JWT를 변경
      state.authToken = token
      // 2. Local storage의 값도 변경
      state.decoded = localStorage.setItem("jwt", token)
    },

    // token 으로 userid 가져오기
    GET_USERID: function (state) {
      state.decoded = jwt_decode(state.authToken)
      if (state.authToken) {
        state.userId = state.decoded.sub
      } else {
        state.decoded = ""
        state.userId = ""
      }
    },

    SET_FEEDS: function (state, feeds) {
      state.feeds = feeds
    },
    // SET_USERID: function (state, res) {
    //   localStorage.setItem('userId', res.data.id); 
    // },
    SET_USERIDENTITY: function (state, data) {
      state.userIdentity = data.identity
      localStorage.setItem('userIdentity', data.identity);
    },
    SET_USERIDENTITY2: function (state, data) {
      state.userIdentity = data.identity
    },
    SET_USERNAME: function (state, res) {
      state.userName = res.data.name
      localStorage.setItem('userName', res.data.name);
    },
    SET_USERIMAGE: function (state, res) {
      localStorage.setItem('userImage', res.data.image);
    },
    INIT_USERIDENTITY: function (state) {
      state.userIdentity = ""
      localStorage.removeItem('userIdentity');
    },
    INIT_USERNAME: function (state) {
      state.userName = ""
      localStorage.removeItem('userName');
    },
    INIT_USERIMAGE: function () {
      localStorage.removeItem('userImage');
    },
    REMOVE_TOKEN: function (state) {
      // 1. JWT 변경
      localStorage.removeItem('jwt')
      // 2. Local Storage에서 JWT 삭제
      state.authToken = ""
      state.decoded = ""
    },

  },
  actions: {
    // 한길 #@
    setPostDetail(store, { postId }) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + postId)
        .then((response) => {
          store.commit("setPostDetail", { post: response.data });
          store.dispatch("setPostUser", { userId: response.data.userId });
        })
        .catch((exp) => {
          alert(`게시글 조회에 실패했습니다: ${exp}`)
        });
    },
    setPostUser(store, payload) {
      axios
        .get(SERVER.URL + SERVER.ROUTES.postUser
          + `?userId=${payload.userId}`)
        .then((response) => {
          store.commit("setPostUser", { postUser: response.data })
        })
        .catch((exp) => {
          alert(`게시글 작성자 조회에 실패했습니다: ${exp}`)
        })
    },
    setPostComments(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + postId + SERVER.ROUTES.comment)
        .then((response) => {
          let comments = response.data;
          let commentUserIds = new Set();
          let commentIds = new Set();
          comments.forEach(comment => {
            commentUserIds.add(comment.userId);
            commentIds.add(comment.id);
          });
          let commentUserIdArr = Array.from(commentUserIds);
          let commentIdArr = Array.from(commentIds);
          store.dispatch("setCommentUsers", { commentUserIds: commentUserIdArr });
          store.dispatch("setCommentLikes", { commentIds: commentIdArr });
          let commentTree = util.convertToTree(comments, "id", "commentId", "childrenComment");
          store.commit("setPostComments", { comments: commentTree });
        })
        .catch((exp) => {
          alert(`댓글 조회에 실패했습니다: ${exp}`)
          // 이 밑 두 줄 나중에 삭제
          let commentTree = util.convertToTree(store.state.comments, "id", "commentId", "childrenComment");
          store.commit("setPostComments", { comments: commentTree });
        });
    },
    setCommentUsers(store, { commentUserIds }) {
      axios
      .post(SERVER.URL + SERVER.ROUTES.commentUsers, {
        userIdSet: commentUserIds,
      })
      .then((response) =>
        store.commit("setCommentUsers", { commentUsers: response.data })
      )
      .catch((exp) =>
      alert(`댓글 작성자 조회에 실패했습니다: ${exp}`)
      );
    },
    setCommentLikes(store, { commentIds }) {
      axios
      .post(SERVER.URL + SERVER.ROUTES.commentLikes, {
        commentIdSet: commentIds,
        userId: store.state.userId,
      })
      .then((response) =>
        store.commit("setCommentLikes", { commentLikes: response.data })
      )
      .catch((exp) =>
      alert(`댓글 좋아요 조회에 실패했습니다: ${exp}`)
      );
    },
    setPostScrap(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.postScrap + `?postId=${postId}&userId=${store.state.userId}`)
        .then((response) => {
          store.commit("setPostScrap", { scrap: response.data })
        })
        .catch((exp) => {
          alert(`게시글 스크랩 여부 조회에 실패했습니다: ${exp}`)
        });
    },
    setPostLike(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.postLike + `?postId=${postId}&userId=${store.state.userId}`)
        .then((response) => {
          store.commit("setPostLike", { like: response.data })
        })
        .catch((exp) => {
          alert(`게시글 좋아요 여부 조회에 실패했습니다: ${exp}`)
        });
    },
    likePost(store, { postId }){
      axios
        .post(SERVER.URL + SERVER.ROUTES.postLike, {
          userId: store.state.userId,
          postId: postId,
        })
        .then((response) => {
          store.commit("setPostLike", { like: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 409) {
            alert("이미 존재하는 게시글 좋아요 정보가 있습니다.")
          }
          else {
            alert(`게시글 좋아요에 실패했습니다: ${exp}`)
          }
        });
    },
    cancelLikePost(store, { postId }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.postLike
          + `?userId=${store.state.userId}&postId=${postId}`)
        .then((response) => {
          store.commit("setPostLike", { like: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            alert("게시글 좋아요 정보가 존재하지 않습니다.")
          }
          else {
            alert(`게시글 좋아요 취소에 실패했습니다: ${exp}`)
          }
        });
    },
    scrapPost(store, { postId }){
      axios
        .post(SERVER.URL + SERVER.ROUTES.postScrap, {
          userId: store.state.userId,
          postId,
        })
        .then((response) => {
          store.commit("setPostScrap", { scrap: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 409) {
            alert("이미 존재하는 게시글 스크랩 정보가 있습니다.")
          }
          else {
            alert(`게시글 스크랩에 실패했습니다: ${exp}`)
          }
        });
    },
    cancelScrapPost(store, { postId }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.postScrap
          + `?userId=${store.state.userId}&postId=${postId}`)
        .then((response) => {
          store.commit("setPostScrap", { scrap: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            alert("게시글 스크랩 정보가 존재하지 않습니다.")
          }
          else {
            alert(`게시글 스크랩 취소에 실패했습니다: ${exp}`)
          }
        });
    },
    createComment(store, payload) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.postComment, {
          text: payload.message,
          userId: store.state.userId,
          postId: payload.postId,
          commentId: payload.commentId,
        })
        .then(() =>
          store.dispatch("setPostComments", { postId: payload.postId })
        )
        .catch((exp) => {
          alert(`댓글 작성에 실패했습니다: ${exp}`)
        });
    },
    deleteComment(store, payload) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment + payload.commentId)
        .then(() =>
          store.dispatch("setPostComments", { postId: payload.postId })
        )
        .catch((exp) =>
          alert(`댓글 삭제에 실패했습니다: ${exp}`)
        );
    },
    likeComment(store, payload) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.postComment + SERVER.ROUTES.like, {
          userId: store.state.userId,
          commentId: payload.commentId,
        })
        .then(() => {
          store.commit("setCommentLike", { commentId: payload.commentId });
        })
        .catch((exp) => {
          if(exp.response.status == 409) {
            alert("이미 존재하는 댓글 좋아요 정보가 있습니다.")
          }
          else{
            alert(`댓글 좋아요에 실패했습니다: ${exp}`)
          }
        });
    },
    cancelLikeComment(store, payload) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.postComment + SERVER.ROUTES.like
          + `?userId=${store.state.userId}&commentId=${payload.commentId}`)
        .then(() => {
          store.commit("setCommentLikeCancel", { commentId: payload.commentId, userId: store.state.userId })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            alert("존재하는 댓글 좋아요 정보가 없습니다.")
          }
          else
            alert(`댓글 좋아요 취소에 실패했습니다: ${exp}`)
        });
    },

    // 원석 #@

    // 주엽 #@
    signup: function ({ commit }, data) {
      axios.post(SERVER.URL + SERVER.ROUTES.signup, data)
        .then(() => {
          commit("SET_USERIDENTITY2", data)
          router.push({ name: "SignupSuccess" })
      })
        .catch(() => {
          alert('회원가입 중 오류가 발생했습니다.')
      })
    },
    login: function ({ commit }, data) {
      // event.preventDefault()
      axios.post(SERVER.URL + SERVER.ROUTES.login, data)
        .then((res) => {
          commit('SET_TOKEN', res.data.tokenDto.accessToken)
          commit('GET_USERID')
          commit("SET_USERIDENTITY", data)
          commit("SET_USERNAME", res)
          commit("SET_USERIMAGE", res)
          router.push({ name: "HomeFeed" }) // 홈 피드 구현 후 변경
      })
        .catch(() => {
          alert("존재하지 않는 회원이거나, 비밀번호가 일치하지 않습니다.");
      })
    },
    logout: function ({ commit }) {
      commit("INIT_USERIDENTITY")
      commit("INIT_USERNAME")
      commit("INIT_USERIMAGE")
      commit("REMOVE_TOKEN")
      router.push({ name: "Login" })
    },


    getFeeds: function ({ commit }) {
      // const headers = getters.config
      axios.get(SERVER.URL + SERVER.ROUTES.community + "/main/")
        .then(() => {
          commit("SET_FEEDS")
      })
        .catch(() => {
          alert('정보를 가져오던 중 오류가 발생했습니다.')
      })
    },
  },
  modules: {},
});
