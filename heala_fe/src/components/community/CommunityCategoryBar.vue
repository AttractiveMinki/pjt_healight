<template>
  <div>
    <el-row class="justify-content-space-between padding-top-setting">
      <el-col :span="4" class="container-box-detail">
        <span @click="changeSelectedSubCategory(3)" class="text-decoration-none align-self" :class="{ 'selected': selectedSubCategory == 3 }">BEST</span> 
      </el-col>
      <el-col :span="4" class="container-box-detail">
        <span @click="changeSelectedSubCategory(0)" class="text-decoration-none align-self" :class="{ 'selected': selectedSubCategory == 0 }">일반</span> 
      </el-col>
      <el-col :span="4" class="container-box-detail">
        <span v-if="category != 3" @click="changeSelectedSubCategory(1)" class="text-decoration-none align-self" :class="{ 'selected': selectedSubCategory == 1 }">정보</span>
      </el-col>
      <el-col :span="4" class="container-box-detail">
        <span v-if="category != 3" @click="changeSelectedSubCategory(2)" class="text-decoration-none align-self" :class="{ 'selected': selectedSubCategory == 2 }">질문</span>
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
    padding-top: 5px;
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
  .selected {
    color: #9cd463;
  }
</style>