import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import test from "../views/test.vue";
import Modal from "../components/Modal.vue";

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
    path: "/test",
    name: "test",
    component: test,
  },
  {
    path: "/modalTest",
    name: "Modal",
    component: Modal,
  }
  
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
