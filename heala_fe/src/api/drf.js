const SERVER_URL = process.env.VUE_APP_SERVER_URL

// 모듈로 사용하기 위해 export default를 해야 한다.
export default {
  URL: SERVER_URL,
  ROUTES: {
    join: '/user/join/',
  }
}
