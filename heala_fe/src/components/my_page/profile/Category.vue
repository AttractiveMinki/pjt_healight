<template>
  <div>
    <el-row style="padding-top: 1vh">
      <el-col :span="8">
        <div @click="changeProgileSelectedCategory(1)" class="align-text-center" style="height: 4vh">운동</div> 
        <div v-if="profileSelectedCategory == 1" id="square"></div>
      </el-col>
      <el-col :span="8">
        <div @click="changeProgileSelectedCategory(2)" class="align-text-center" style="height: 4vh">식단</div> 
        <div v-if="profileSelectedCategory == 2" id="square"></div>
      </el-col>
      <el-col :span="8">
        <div @click="changeProgileSelectedCategory(3)" class="align-text-center" style="height: 4vh">마음</div> 
        <div v-if="profileSelectedCategory == 3" id="square"></div>
      </el-col>
    </el-row>
    <el-row>
      <div v-if="profileSelectedCategory == 1">
        <!-- 필터에서 걸러진 운동 사진들 -->
        <!-- <el-col :span="22" v-for="(image, idx) in images" :key="idx" >
          <div v-if="with_challenge.category == 1">
          </div>
        </el-col> -->
      </div>
      <div v-else-if="profileSelectedCategory == 2">
        <!-- <el-col :span="22" v-for="(image, idx) in images" :key="idx" >
          <div v-if="with_challenge.category == 2">
          </div>
        </el-col> -->
        <!-- 필터에서 걸러진 식단 사진들 -->
      </div>
      <div v-else-if="profileSelectedCategory == 3">
        <!-- <el-col :span="22" v-for="(image, idx) in images" :key="idx" >
          <div v-if="with_challenge.category == 3">
          </div>
        </el-col> -->
        <!-- 필터에서 걸러진 마음 사진들 -->
      </div>
      <!-- <div v-else>
        <el-col :span="22" v-for="(image, idx) in images" :key="idx" >
        </el-col>
      </div> -->
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
</style>