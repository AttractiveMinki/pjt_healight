<template>
  <div>
    <Navbar />
    <CallenderTest />
    <h1>식단 기록하기</h1>
    <el-calendar>
      <!-- Use 2.5 slot syntax. If you use Vue 2.6, please use new slot syntax-->
      <template
        slot="dateCell"
        slot-scope="{data}">
        <p :class="data.isSelected ? 'is-selected' : ''">
          {{ data.day.split('-').slice(1).join('-') }} {{ data.isSelected ? '✔️' : ''}}
        </p>
      </template>
    
    </el-calendar>
    <input type="date"><input type="number">kg <el-button>저장</el-button>
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar"
import CallenderTest from '@/components/my_page/CallenderTest.vue'

export default {
  name: "DietRecordCalender",
  components: {
    Navbar,
    CallenderTest
  },
}
</script>


    CallenderTest<style scoped>
  .is-selected {
    color: #1989FA;
  }
</style>