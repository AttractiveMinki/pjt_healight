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
      id: 2,
      name: "나다",
      image: "blue.jpg",
    },
    postDetail: {
      id: 1,
      title: "기본 init 데이터",
      createdAt: "07/27 13:56",
      image: "blue.jpg",
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
      image: "user.png",
    },
    postScrap: false,
    postLike: false,
    comments: [],
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
    commentLikes: [],
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
    userid: 2,
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
        .then((response) => {
          store.commit("setPostDetail", { post: response.data });
          store.dispatch("setPostUser", { userId: response.data.userId });
        })
        .catch((exp) => {
          console.log(`게시글 조회에 실패했습니다: ${exp}`);
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
          console.log(`게시글 작성자 조회에 실패했습니다: ${exp}`);
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
          console.log(`댓글 조회에 실패했습니다: ${exp}`);
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
        console.log(`댓글 작성자 조회에 실패했습니다: ${exp}`)
      );
    },
    setCommentLikes(store, { commentIds }) {
      axios
      .post(SERVER.URL + SERVER.ROUTES.commentLikes, {
        commentIdSet: commentIds,
        userId: store.state.loginUser.id,
      })
      .then((response) =>
        store.commit("setCommentLikes", { commentLikes: response.data })
      )
      .catch((exp) =>
        console.log(`댓글 좋아요 조회에 실패했습니다: ${exp}`)
      );
    },
    setPostScrap(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.scrap + `?postId=${postId}&userId=${store.state.loginUser.id}`)
        .then((response) => {
          store.commit("setPostScrap", { scrap: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            store.commit("setPostScrap", { scrap: exp.response.data });
          }
          else {
            console.log(`게시글 스크랩 여부 조회에 실패했습니다: ${exp}`)
          }
        });
    },
    setPostLike(store, { postId }){
      axios
        .get(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.like + `?postId=${postId}&userId=${store.state.loginUser.id}`)
        .then((response) => {
          store.commit("setPostLike", { like: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            store.commit("setPostLike", { like: exp.response.data });
          }
          else {
            console.log(`게시글 좋아요 여부 조회에 실패했습니다: ${exp}`)
          }
        });
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
    cancelFollow(store, { followId }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.follow
          + `?userId=${store.state.loginUser.id}&followId=${followId}`)
        .then(() => {
          // 이 부분은 필요없나? 헷갈림
          const index = store.state.isFollowingList.findIndex(element => element.follow_id == followId);
          store.commit("setIsFollowing", { index, isFollowing: false });
        })
        .catch((exp) => {
          console.log(`팔로우 취소에 실패했습니다: ${exp}`)
        });
    },
    likePost(store, { postId }){
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.like, {
          userId: store.state.loginUser.id,
          postId: postId,
        })
        .then((response) => {
          store.commit("setPostLike", { like: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 409) {
            console.log("이미 존재하는 게시글 좋아요 정보가 있습니다.");
          }
          else {
            console.log(`게시글 좋아요에 실패했습니다: ${exp}`)
          }
        });
    },
    cancelLikePost(store, { postId }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.like
          + `?userId=${store.state.loginUser.id}&postId=${postId}`)
        .then((response) => {
          store.commit("setPostLike", { like: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            console.log("게시글 좋아요 정보가 존재하지 않습니다.");
          }
          else {
            console.log(`게시글 좋아요 취소에 실패했습니다: ${exp}`)
          }
        });
    },
    scrapPost(store, { postId }){
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.scrap, {
          userId: store.state.loginUser.id,
          postId,
        })
        .then((response) => {
          store.commit("setPostScrap", { scrap: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 409) {
            console.log("이미 존재하는 게시글 스크랩 정보가 있습니다.");
          }
          else {
            console.log(`게시글 스크랩에 실패했습니다: ${exp}`)
          }
        });
    },
    cancelScrapPost(store, { postId }){
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.scrap
          + `?userId=${store.state.loginUser.id}&postId=${postId}`)
        .then((response) => {
          store.commit("setPostScrap", { scrap: response.data })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            console.log("게시글 스크랩 정보가 존재하지 않습니다.");
          }
          else {
            console.log(`게시글 스크랩 취소에 실패했습니다: ${exp}`)
          }
        });
    },
    createComment(store, payload) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment, {
          text: payload.message,
          userId: store.state.loginUser.id,
          postId: payload.postId,
          commentId: payload.commentId,
        })
        .then(() =>
          store.dispatch("setPostComments", { postId: payload.postId })
        )
        .catch((exp) => {
          console.log(`댓글 작성에 실패했습니다: ${exp}`)
        });
    },
    deleteComment(store, payload) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment + payload.commentId)
        .then(() =>
          store.dispatch("setPostComments", { postId: payload.postId })
        )
        .catch((exp) =>
          console.log(`댓글 삭제에 실패했습니다: ${exp}`)
        );
    },
    likeComment(store, payload) {
      axios
        .post(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment + SERVER.ROUTES.like, {
          userId: store.state.loginUser.id,
          commentId: payload.commentId,
        })
        .then(() => {
          // store.commit("setCommentLike", { userId: store.state.loginUser.id, like: true })
          store.commit("setCommentLike", { commentId: payload.commentId });
        })
        .catch((exp) => {
          if(exp.response.status == 409) {
            console.log("이미 존재하는 댓글 좋아요 정보가 있습니다.");
          }
          else{
            console.log(`댓글 좋아요에 실패했습니다: ${exp}`)}
        });
    },
    cancelLikeComment(store, payload) {
      axios
        .delete(SERVER.URL + SERVER.ROUTES.post + SERVER.ROUTES.comment + SERVER.ROUTES.like
          + `?userId=${store.state.loginUser.id}&commentId=${payload.commentId}`)
        .then(() => {
          store.commit("setCommentLikeCancel", { commentId: payload.commentId, userId: store.state.loginUser.id })
        })
        .catch((exp) => {
          if(exp.response.status == 404) {
            console.log("존재하는 댓글 좋아요 정보가 없습니다.");
          }
          else
            console.log(`댓글 좋아요 취소에 실패했습니다: ${exp}`);
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
