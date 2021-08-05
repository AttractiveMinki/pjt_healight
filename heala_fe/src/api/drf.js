// const SERVER_URL = process.env.VUE_APP_SERVER_URL
const SERVER_URL = "http://127.0.0.1:8080"

// 모듈로 사용하기 위해 export default를 해야 한다.
export default {
  URL: SERVER_URL,
  ROUTES: {
    // 한길 #@
    follow: "/user/follow/",
    post: "/community/post/",
    comment: "/comment/",
    like: "/like/",
    scrap: "/scrap/",
    commentUsers: "/user/comment/",

    // 원석 #@

    // 주엽 #@
    signup: "/user/signup/",
    login: "/user/login/",
    community: "/community/",
    checkEmail: "/user/checkemail/",
    checkIdentity: "/user/checkidentity/",
    withmake: "/challenge/with/",
    userProfile: "/user/profile/",
    getKiwiChallenge: "/challenge/kiwi/",
    getKiwiHealthChallenge: "/challenge/kiwi/1/",
    getKiwiDietChallenge: "/challenge/kiwi/2/",
    getKiwiHeartChallenge: "/challenge/kiwi/3/",
    getKiwiSpecialChallenge: "/challenge/kiwi/4/",
    getWithHealthChallenge: "/challenge/with?category=0",
    getWithDietChallenge: "/challenge/with?category=1",
    getWithHeartChallenge: "/challenge/with?category=2",
  }
}
