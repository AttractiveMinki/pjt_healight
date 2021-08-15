<template>
  <div>
    <br>
    <h2>유효하지 않은 주소입니다.</h2>
    <br>
    <br>
    <h4>이전 페이지로 가려면 </h4>
    <h4>아래의 버튼을 눌러주세요.</h4>
    <br>
    <button class="bg-green get-input join-button-setting" @click="goBack()">뒤로 가기</button>

  </div>
</template>

<script>
export default {
  name: "PageNotFound",
    methods: {
      goBack(){
        this.$router.go(-1);
      }
    },
}
</script>

<style scoped>
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    width: 80%;
    height: 40px;
    margin-bottom: 20px;
    padding: 0px 2px;
  }
  .bg-green {
    background: #ADEC6E;
    border-width: 0px;
    border-radius: 5px;
    color: black;
    /* color: #99a9bf; */
  }
  .join-button-setting {
    margin-top: 30px;
    height: 45px;
    width: 80%;
    cursor: pointer;
  }
</style>