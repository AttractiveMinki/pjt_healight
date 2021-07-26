import Vue from "vue";
import Vuex from "vuex";

import SERVER from '@/api/drf.js'
import router from '@/router/index.js'
import axios from 'axios'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    username: 'annonymous_User',
  },
  mutations: {},
  actions: {
    join: function (context, data) {
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
  },
  modules: {},
});
