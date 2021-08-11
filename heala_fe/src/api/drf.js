// const SERVER_URL = process.env.VUE_APP_SERVER_URL
const SERVER_URL = "http://127.0.0.1:8080"

// 모듈로 사용하기 위해 export default를 해야 한다.
export default {
  URL: SERVER_URL,
  ROUTES: {
    // 한길 #@
    follow: "/user/follow",
    post: "/community/post/",
    comment: "/comment",
    like: "/like",
    scrap: "/scrap",
    commentUsers: "/user/comment",
    commentLikes: "/user/comment/like",
    postUser: "/user/simple",
    myChallengePhoto: "/challenge/my/photo",
    feedPost: "/feed/post",

    // 원석 #@

    // 주엽 #@
    signup: "/user/signup/",
    login: "/user/login/",
    community: "/community/",
    feedpost: "/feed/post/",
    checkEmail: "/user/checkemail/",
    checkIdentity: "/user/checkidentity/",
    withmake: "/challenge/with/",
    userProfile: "/user/profile/",
    getKiwiChallenge: "/challenge/kiwi/",
    getKiwiHealthChallenge: "/challenge/kiwi/0/",
    getKiwiDietChallenge: "/challenge/kiwi/1/",
    getKiwiHeartChallenge: "/challenge/kiwi/2/",
    getKiwiSpecialChallenge: "/challenge/kiwi/3/",
    getWithHealthChallenge: "/challenge/with?category=0",
    getWithDietChallenge: "/challenge/with?category=1",
    getWithHeartChallenge: "/challenge/with?category=2",
    getWithDetail: "/challenge/with/detail",
    getMyChallenge: "/challenge/my/",
    joinWithChallenge: "/challenge/with/join",
  }
}
