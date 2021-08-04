import Signup from "@/views/user/Signup"
import Login from "@/views/user/Login"
import CommunityMain from "@/views/community/CommunityMain"
import Health from "@/views/community/Health"
import Diet from "@/views/community/Diet"
import Heart from "@/views/community/Heart"
import WithMain from "@/views/challenge/with/WithMain"
import WithMake from "@/views/challenge/with/WithMake"
import WithDetail from "@/views/challenge/with/WithDetail"
import WithDetailCertify from "@/views/challenge/with/WithDetailCertify"
import WithDetailReview from "@/views/challenge/with/WithDetailReview"
import WithMainDiet from "@/views/challenge/with/WithMainDiet"
import WithMainHeart from "@/views/challenge/with/WithMainHeart"
import KiwiMain from "@/views/challenge/kiwi/KiwiMain"
import KiwiHealth from "@/views/challenge/kiwi/KiwiHealth"
import KiwiDiet from "@/views/challenge/kiwi/KiwiDiet"
import KiwiHeart from "@/views/challenge/kiwi/KiwiHeart"
import KiwiSpecial from "@/views/challenge/kiwi/KiwiSpecial"
import MyMain from "@/views/challenge/my/MyMain"

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
        path: "/challenge/with/withmaindiet",
        name: "WithMainDiet",
        component: WithMainDiet,
      },
      {
        path: "/challenge/with/withmainheart",
        name: "WithMainHeart",
        component: WithMainHeart,
      },            
      {
        path: "/challenge/with/:id",
        name: "WithDetail",
        component: WithDetail,
      },
      {
        path: "/challenge/with/:id/certify",
        name: "WithDetailCertify",
        component: WithDetailCertify,
      },
      {
        path: "/challenge/with/:id/review",
        name: "WithDetailReview",
        component: WithDetailReview,
      },
      {
        path: "/challenge/kiwimain",
        name: "KiwiMain",
        component: KiwiMain,
      },
      {
        path: "/challenge/kiwihealth",
        name: "KiwiHealth",
        component: KiwiHealth,
      },
      {
        path: "/challenge/kiwidiet",
        name: "KiwiDiet",
        component: KiwiDiet,
      },
      {
        path: "/challenge/kiwiheart",
        name: "KiwiHeart",
        component: KiwiHeart,
      },
      {
        path: "/challenge/kiwispecial",
        name: "KiwiSpecial",
        component: KiwiSpecial,
      },
      {
        path: "/challenge/my/mymain",
        name: "MyMain",
        component: MyMain,
      },
]