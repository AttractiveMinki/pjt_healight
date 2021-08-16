<template>
  <div>
    <Navbar />
    <h1>식단 캘린더</h1>
    <DietCallender />
    <br>
    <div> 내 식단 정보를 달력에서 확인해보세요! </div>
    <br>
    <div class="selected-date"> 식단이 입력된 날을 클릭해보세요!</div>
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
</style>