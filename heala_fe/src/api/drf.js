// const SERVER_URL = process.env.VUE_APP_SERVER_URL
const SERVER_URL = 'http://127.0.0.1:8000'

// 모듈로 사용하기 위해 export default를 해야 한다.
export default {
  URL: SERVER_URL,
  ROUTES: {
    signup: '/user/signup/',
    login: '/user/login/',

    follow: '/user/follow',
  }
}
