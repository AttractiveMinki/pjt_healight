import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Profile from "../views/my_page/Profile.vue";
import Writing from "../views/post/Writing.vue";
import HealHome from "../views/challenge/heal/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/profile",
    name: "profile",
    component: Profile,
  },
  {
    path: "/writing",
    name: "writing",
    component: Writing,
  },
  {
    path: "/healHome",
    name: "healHome",
    component: HealHome,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
