import Vue from "vue";
import Vuex from "vuex";

import SERVER from '@/api/drf.js'
// import router from '@/router/index.js'
import axios from 'axios'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    // 한길 #@
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
        id: 2,
        image:"cat.jpg",
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
        id: 3,
        image:"cat.jpg",
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

    // 원석 #@

    // 주엽 #@
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
  },
  modules: {},
});
