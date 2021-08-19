import Vue from "vue";
import VueRouter from "vue-router";
import index1 from "./index1";
import index2 from "./index2";
import index3 from "./index3";

Vue.use(VueRouter);

const routes = [
  ...index1,
  ...index2,
  ...index3,
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;


router.beforeEach((to, from, next) => {
  
  //1-1. 로그인이 필요한 컴포넌트
  const authPages = [
    'HomeFeed', 
    'Profile',
  ]
  //1-2. 로그아웃이 필요한 컴포넌트(로그인 상태가 아닌 경우에 방문해야 하는 컴포넌트)
  const publicPages = [
    'Login', 
    'Signup',
  ]

  //2. 
  // 가려고 하는 곳(to)이 로그인이 필요한 곳인지 여부를 체크하자 -> true / false 
  const authRequired = authPages.includes(to.name)
  // 가려고 하는 곳이 로그인이 필요하지 않은 곳은지 여부를 체크하자 -> true / false 
  const authNotRequired = publicPages.includes(to.name)
  // 로그인이 되어있는지 여부를 체크하자 -> true / false
  const isLoggedIn = localStorage.getItem('jwt') ? true : false


  //3. 
  //3-1. 만약 로그인이 필요한 컴포넌트인데 로그인이 안되어 있는 경우에 강제로 가려고 하면?
  if (authRequired && !isLoggedIn) {
    // 로그인을 할 수 있도록 (가드) -> Login 컴포넌트로 보내자
    next({ name: 'Login' })
  //3-2. 만약 로그인이 필요하지 않은 컴포넌트인데 로그인이 되어있는 상태에서 강제로 가려고 하면?
  } else if (authNotRequired && isLoggedIn) {
    next({ name: 'HomeFeed' })
  } else {
    next()
  }
})
