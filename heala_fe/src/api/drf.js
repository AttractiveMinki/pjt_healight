// const SERVER_URL = process.env.VUE_APP_SERVER_URL
const SERVER_URL = "http://localhost:8080/api"
// const SERVER_URL = "http://i5a605.p.ssafy.io:8080/api"
const IMAGE_SERVER_URL = "https://kiwiimg-bucket.s3.ap-northeast-2.amazonaws.com/"

// 모듈로 사용하기 위해 export default를 해야 한다.
export default {
  URL: SERVER_URL,
  IMAGE_URL: IMAGE_SERVER_URL,
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
    myChallengeResult: "/challenge/my/result",
    userExp: "/user/exp",
    feedPost: "/feed/post",
    getFeedPostData: "/feed/home/",
    commentCount: "/community/post/comment/count/",
    postScrap: "/community/post/scrap",
    postLike: "/community/post/like",
    feed: "/feed/",
    followList: "/followList",
    userSearch: "/user/search",

    // 원석 #@

    // 주엽 #@
    signup: "/user/signup/",
    login: "/user/login/",
    community: "/community/",
    checkEmail: "/user/checkemail/",
    checkIdentity: "/user/checkidentity/",
    withMake: "/challenge/with/",
    userProfile: "/feed/",
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
    withdrawal: "/user",
    OCR: "https://dapi.kakao.com/v2/vision/text/ocr",
    dietupload: "/diet/upload",
    getDietRecord: "/diet/list/",
    getDietRecordToday: "/diet/record",
    getMonthDietRecord: "/diet/calender",
  }
}
