import Profile from "../views/my_page/Profile.vue";
import Writing from "../views/post/Writing.vue";
import HealHome from "../views/challenge/heal/Home.vue";

export default [
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
]