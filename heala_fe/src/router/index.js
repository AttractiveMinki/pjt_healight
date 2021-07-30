import Vue from "vue";
import VueRouter from "vue-router";
import index1 from "./index1";
import index3 from "./index3";

Vue.use(VueRouter);

const routes = [
  ...index1,
  ...index3,
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
