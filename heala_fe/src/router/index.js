import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";

import join from "@/views/user/join"
import login from "@/views/user/login"

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },

  {
    path: '/user/join',
    name: 'join',
    component: join,
  },
  {
    path: '/user/login',
    name: 'login',
    component: login,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
