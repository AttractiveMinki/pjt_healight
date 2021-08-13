<template>
  <div>
    <Navbar />
    <h1>식단 기록하기</h1>
    <DietCallender />
    <input type="date"><input type="number">kg <el-button>저장</el-button>
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
</style>