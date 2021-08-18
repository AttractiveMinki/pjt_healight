<template>
  <div>
    <el-row style="padding-top: 1vh">
      <el-col :span="8">
        <div @click="changeProgileSelectedCategory(0)" class="align-text-center cursor-pointer" style="height: 4vh">운동</div> 
        <div v-if="profileSelectedCategory == 0" id="square"></div>
      </el-col>
      <el-col :span="8">
        <div @click="changeProgileSelectedCategory(1)" class="align-text-center cursor-pointer" style="height: 4vh">식단</div> 
        <div v-if="profileSelectedCategory == 1" id="square"></div>
      </el-col>
      <el-col :span="8">
        <div @click="changeProgileSelectedCategory(2)" class="align-text-center cursor-pointer" style="height: 4vh">마음</div> 
        <div v-if="profileSelectedCategory == 2" id="square"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapState } from "vuex"

export default {
  name: "Category",
  methods: {
    changeProgileSelectedCategory(num) {
      this.$store.state.profileSelectedCategory = num
    },
  },
  computed: {
    ...mapState([
      "profileSelectedCategory",
    ])
  },
}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 1vh;
    background: #ADEC6E;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .justify-content-space-between {
    justify-content: space-between;
  }
  .align-text-center {
    display: inline-flex;
    text-align: center;
    align-items: center;
  }
  .cursor-pointer {
    cursor: pointer;
  }
</style>