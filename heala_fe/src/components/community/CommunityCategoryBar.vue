<template>
  <div>
    <el-row class="justify-content-space-between padding-top-setting">
      <el-col :span="4" class="container-box-detail">
        <span @click="changeSelectedSubCategory(0)" class="text-decoration-none align-self">BEST</span> 
        <div v-if="selectedSubCategory == 0" id="square"></div>
      </el-col>
      <el-col :span="4" class="container-box-detail">
        <span @click="changeSelectedSubCategory(1)" class="text-decoration-none align-self">일반</span> 
        <div v-if="selectedSubCategory == 1" id="square"></div>
      </el-col>
      <el-col :span="4" class="container-box-detail">
        <span @click="changeSelectedSubCategory(2)" class="text-decoration-none align-self">정보</span>
        <div v-if="selectedSubCategory == 2" id="square"></div>
      </el-col>
      <el-col :span="4" class="container-box-detail">
        <span @click="changeSelectedSubCategory(3)" class="text-decoration-none align-self">질문</span>
        <div v-if="selectedSubCategory == 3" id="square"></div>
      </el-col>
      <el-col :span="4" class="container-box-detail" v-if="category==2">
        <!-- 마음 카테고리만 존재 -->
        <span @click="changeSelectedSubCategory(4)" class="text-decoration-none align-self">익명</span>
        <div v-if="selectedSubCategory == 4" id="square"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
  name: "ChallengeCategoryBar",
  props: {
    category: {
      type: Number,
    }
  },
  methods: {
    changeSelectedSubCategory (num) {
      this.$store.state.selectedSubCategory = num
    }
  },
  computed: {
    ...mapState([
      "selectedSubCategory",
    ])
  },
}
</script>

<style scoped>
  #square {
    width: 100%;
    height: 4px;
    background: #ADEC6E;
  }
  .justify-content-space-between {
    display: flex;
    justify-content: space-between;
  }
  .padding-top-setting {
    padding-top: 20px;
  }
  .container-box-detail {
    border-radius: 5px;
    height: 30px;
  }
  .align-self {
    display: inline-block;
    padding-top: 5px;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .selected-category {
    background: #ADEC6E;
  }
</style>