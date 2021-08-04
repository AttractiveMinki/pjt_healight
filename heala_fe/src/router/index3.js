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
import Profile from "@/views/my_page/Profile";
import ProfileTodoList from "@/views/my_page/ProfileTodoList";
import ProfilePhysInfo from "@/views/my_page/ProfilePhysInfo";
import Scrap from "@/views/my_page/showmore/Scrap";
import ChangePassword from "@/views/my_page/showmore/ChangePassword";

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
    path: "/profile",
    name: "Profile",
    component: Profile,
  },
  {
    path: "/profiletodolist",
    name: "ProfileTodoList",
    component: ProfileTodoList,
  },
  {
    path: "/profile/physinfo",
    name: "ProfilePhysInfo",
    component: ProfilePhysInfo,
  },
  {
    path: "/profile/Scrap",
    name: "Scrap",
    component: Scrap,
  },
  {
    path: "/profile/ChangePassword",
    name: "ChangePassword",
    component: ChangePassword,
  },
]