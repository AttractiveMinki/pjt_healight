<template>
  <div>
    <div v-if="category == 0">
      <h1>운동 챌린지</h1>
    </div>
    <div v-else-if="category == 1">
      <h1>식단 챌린지</h1>
    </div>
    <div v-else-if="category == 2">
      <h1>마음 챌린지</h1>
    </div>
    <div v-else-if="category == 3">
      <h1>기간 한정 챌린지</h1>
    </div>
    <div class="display-flex justify-content-space-between align-items">
      <span><font-awesome-icon icon="arrow-left" class="padding-left cursor-pointer" style="margin: 2vw" @click="goBack()"/></span>
      <span class="text-title">챌린지 상세</span>
      <!-- <button class="button-color">작성</button> -->
      <span id="block"></span>
    </div>


  </div>
</template>

<script>
export default {
  name: "ChallengeContainerKiwiDetail",
  props: {
    category: {
      type: String
      // 같은 타입은 타입 작성 안해도 됨.
      // 이건 String
    }
  },
  methods: {
    goBack: function () {
      this.$router.go(-1)
    },
  },
}
</script>

<style scoped>
  #block {
    width: 10%;
    height: 0px;
    background: white;
  }
  .display-flex {
    display: flex;
  }
  .justify-content-space-between {
    justify-content: space-between;
  }  
  .align-items {
    align-items: center;
  }
  .padding-left {
    padding-left: 4%;
  }
  .cursor-pointer {
    cursor: pointer;
  }
  .text-decoration-title {
    font-size: 22px;
    font-weight: bold;
  }
  .text-title {
    font-weight: bold; 
    font-size: 25px;
    margin-bottom: 5px;
  }
</style>