<template>
  <div>
    <Navbar />
    <h1 style="font-family: 'TmoneyRoundWindRegular';">체중 기록하기</h1>
    <WeiCalender />
    <br>
    <div>
      <router-link :to="{ name: 'WeightRecordPast' }" class="past-weight-button" >과거 기록 확인하기</router-link>
    </div>
    
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"
import WeiCalender from '@/components/my_page/WeiCalender.vue'

export default {
  name: "WeightCalender",
    data () {
      return {
        userId: "",
      }
    },
  components: {
    Navbar,
    WeiCalender
  },
  methods: {

  },
  mounted() {
    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.path.split('/')[3]

    // 내 아이디 localStorage에서 가져오기
    this.$store.commit("GET_USERID")
    this.myId = this.$store.state.userId

    // 나만보기
    if (this.userId != this.myId) {
      alert('다른 사람의 계정입니다. 열람하실 수 없습니다.')
      this.$router.go(-1)
    }
  },
}
</script>


<style scoped>
  .is-selected {
    color: #1989FA;
  }
  .past-weight-button {
    text-decoration: none;
    background: #ADEC6E;
    border-width: 0px;
    border-radius: 5px;
    color: black;
    font-size: 13px;
    margin-top: 2vh;
  }
</style>