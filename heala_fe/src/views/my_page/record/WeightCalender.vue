<template>
  <div>
    <Navbar />
    <h1>체중 기록하기</h1>
    <WeiCalender />
    <input type="date"><input type="number">kg <el-button>저장</el-button>
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"
import WeiCalender from '@/components/my_page/WeiCalender.vue'

export default {
  name: "WeightCalender",
  components: {
    Navbar,
    WeiCalender
  },
}
</script>


<style scoped>
  .is-selected {
    color: #1989FA;
  }
</style>