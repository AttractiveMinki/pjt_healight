import Vue from "vue";
import VueRouter from "vue-router";
import index1 from "./index1";

Vue.use(VueRouter);

const routes = [
  ...index1,
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
