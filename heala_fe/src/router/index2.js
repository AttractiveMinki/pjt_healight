import ProfileEdit from "../views/my_page/ProfileEdit.vue";
import Writing from "../views/post/Writing.vue";

export default [
  {
    path: "/profile/edit",
    name: "ProfileEdit",
    component: ProfileEdit,
  },
  {
    path: "/writing",
    name: "writing",
    component: Writing,
  },
]