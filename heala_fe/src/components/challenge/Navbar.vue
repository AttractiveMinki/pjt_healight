<template>
  <div>
    <div class="head-text" style="font-family:SDSamliphopangche_Outline">Challenge</div>
    <el-row>
      <el-col :span="24" class="justify-content-space-between">
        <span class="challenge-text" style="font-family:IM_Hyemin-Bold">다양한 챌린지에<br>도전해보세요!</span>
        <span id="square" style="text-align:center">
          <router-link :to="{ name: 'WithMake' }" class="text-decoration-none challenge-make" style="font-family:TmoneyRoundWindRegular">챌린지 만들기</router-link>
          <font-awesome-icon icon="plus" class="plus-button"/>
        </span>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Navbar",
}
</script>

<style scoped>
  #square {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 40%;
    border-style: solid;
    border-width: 2px;
    border-color: #ADEC6E;
    border-radius: 30px;
    height: 36px;
    margin-right: 5vw;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .justify-content-space-between {
    display: flex;
    justify-content: space-between;
    padding-left: 5%;
    padding-right: 5%;
  }
  .head-text {
    text-align: start;
    font-size: 50px;
    /* font-weight: bold; */
    margin-left: 5%;
    margin-bottom: 2vh;
    margin-top: 20px;
  }
  .challenge-text {
    width: 40%;
    font-weight: bold; 
    font-size: 17px;
    text-align: left;
    padding-left: 10px; 
  }
  .challenge-make {
    font-size: 13px;
    padding-right: 5px;
  }
  .plus-button {
    color: #ADEC6E;
    font-size: 22px;
  }
</style>
