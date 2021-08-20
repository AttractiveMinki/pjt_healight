<template>
  <div>
    <div v-for="Image, idx in certifyImages" :key="idx">
      <img :src="imageServer + postImage.image" alt="">
    </div>

  </div>
</template>

<script>
export default {
  name: "CertifyList",
  data() {
    return {
      imageServer: SERVER.IMAGE_URL,
      certifyImages: [],
      userId: "",
      ChallengeId="",
    }
  },
  methods: {
    async getMyCertifyImages () {
      try {
        await axios.get(`${SERVER.URL}${SERVER.ROUTES.myChallengePhoto}?userId=${this.userId}&withChallengeId=${this.ChallengeId}`)
      } catch (err) {
        alert('이미지를 검증하는 과정에서 에러가 발생했습니다.')
      }
    },
  },
  created() {
    // 내 아이디 localStorage에서 가져오기
    this.$store.commit("GET_USERID")
    this.userId = this.$store.state.userId
    this.ChallengeId = this.$route.path.split('/')[3]
    this.getMyCertifyImages()
  },
}
</script>

<style>

</style>