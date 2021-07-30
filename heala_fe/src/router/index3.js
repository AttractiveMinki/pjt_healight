import Signup from "@/views/user/Signup"
import Login from "@/views/user/Login"
import CommunityMain from "@/views/community/CommunityMain"
import Health from "@/views/community/Health"
import Diet from "@/views/community/Diet"
import Heart from "@/views/community/Heart"
import WithMain from "@/views/challenge/with/WithMain"
import WithMake from "@/views/challenge/with/WithMake"
import WithDetail from "@/views/challenge/with/WithDetail"

export default [
    {
        path: "/user/signup",
        name: "Signup",
        component: Signup,
      },
      {
        path: "/user/login",
        name: "Login",
        component: Login,
      },
      {
        path: "/community/Communitymain",
        name: "CommunityMain",
        component: CommunityMain,
      },
      {
        path: "/community/health",
        name: "Health",
        component: Health,
      },
      {
        path: "/community/diet",
        name: "Diet",
        component: Diet,
      },
      {
        path: "/community/heart",
        name: "Heart",
        component: Heart,
      },
      {
        path: "/challenge/with/withmain",
        name: "WithMain",
        component: WithMain,
      },
      {
        path: "/challenge/with/withmake",
        name: "WithMake",
        component: WithMake,
      },
      {
        path: "/challenge/with/:id",
        name: "WithDetail",
        component: WithDetail,
      },
]