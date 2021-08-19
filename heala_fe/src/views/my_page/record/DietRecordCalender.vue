<template>
  <div>
    <Navbar />
    <h1>식단 캘린더</h1>
    <DietCallender />
    <br>
    <div class="notice"> 내가 기록한 식단 정보를 확인해보세요! </div>
    <br>
    <div class="selected-date notice"> 식단이 입력된 날을 클릭해보세요!</div>
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"
import DietCallender from '@/components/my_page/DietCallender.vue'

export default {
  name: "DietRecordCalender",
  components: {
    Navbar,
    DietCallender
  },
  created() {
    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.path.split('/')[3]

    // 내 아이디 localStorage에서 가져오기
    this.$store.commit("GET_USERID")
    this.myId = this.$store.state.userId

    // 나만보기
    if (this.userId != this.myId) {
      alert("다른 사람의 계정은 열람하실 수 없습니다.")
      this.$router.go(-1)
    }
  },
}
</script>


<style scoped>
  .is-selected {
    color: #1989FA;
  }
  .selected-date {
    text-decoration: underline;
    text-decoration-color: orange;
    padding-bottom: 1vw;
  }
  .notice {
    font-weight: bold;
    font-size: 13px;
  }
</style>