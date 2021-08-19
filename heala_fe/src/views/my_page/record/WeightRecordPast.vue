<template>
  <div>
    <Navbar />
    <h1 style="font-family: 'TmoneyRoundWindRegular';">체중 기록 확인</h1>
    <VueChartJS />
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"
import VueChartJS from '@/components/my_page/chart/VueChartJS'

export default {
  name: "WeightRecordPast",
  components: {
    Navbar,
    VueChartJS,
  },
  created() {
    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.path.split('/')[3]

    // 내 아이디 localStorage에서 가져오기
    this.$store.commit("GET_USERID")
    this.myId = this.$store.state.userId

    // 나만보기
    if (this.userId != this.myId) {
      alert("다른 사람의 계정은 열람하실 수 없습니다.");
      this.$router.go(-1)
    }
  },
}
</script>

<style scoped>

</style>