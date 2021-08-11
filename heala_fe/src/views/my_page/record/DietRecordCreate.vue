<template>
  <div>
    <Navbar />
    <h1>먹은 음식 등록</h1>
    <h2>검색해서 입력하기</h2>

    <!-- <input type="text" class='get-input bg-gray' id="foodName" v-model="data.foodName" > -->
    <input type="text" id="foodName" v-model="foodName" style="margin-right: 2vw">
    <el-button @click="getFoodInfo()">음식 등록</el-button>
    <hr>

    <h2>직접 입력하기</h2>
    <el-row>
      <el-col :span="24"><div class="introduce-text-align-start">음식 이름</div>
        <input type="text" class='get-input bg-gray' id="foods-name" v-model="foods.name" maxlength="20" placeholder="음식 이름을 입력하세요.">
      </el-col>
      <el-col :span="24"><div class="introduce-text-align-start">칼로리(kcal)</div>
        <input type="number" class='get-input bg-gray' id="foods-calory" v-model="foods.calory" maxlength="10" placeholder="단위: kcal">
      </el-col>
      <el-col :span="24"><div class="introduce-text-align-start">탄수화물(g)</div>
        <input type="number" class='get-input bg-gray' id="foods-carbohydrate" v-model="foods.carbohydrate" maxlength="10" placeholder="단위: g">
      </el-col>
      <el-col :span="24"><div class="introduce-text-align-start">단백질(g)</div>
        <input type="number" class='get-input bg-gray' id="foods-protein" v-model="foods.protein" maxlength="10" placeholder="단위: g">
      </el-col>
      <el-col :span="24"><div class="introduce-text-align-start">지방(g)</div>
        <input type="number" class='get-input bg-gray' id="foods-fat" v-model="foods.fat" maxlength="10" placeholder="단위: g">
      </el-col>
      <el-col :span="24"><div class="introduce-text-align-start">나트륨(mg)</div>
        <input type="number" class='get-input bg-gray' id="foods-sodium" v-model="foods.sodium" maxlength="10" placeholder="단위: mg">
      </el-col>
    <button class="bg-green get-input join-button-setting" @click="addFoods(foods)">추가하기</button>
    </el-row>
  </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/drf.js"
import Navbar from "@/components/my_page/Navbar"

export default {
  name: "DietRecordCreate",
  components: {
    Navbar,
  },
  data: function () {
    return {
      foods: {
        name: "",
        calory: "",
        carbohydrate: "", // 탄수화물
        protein: "",
        fat: "",
        sodium: "", // 나트륨
      },
      foodName: "",
    }
  },
  methods: {
    getFoodInfo() {
      // let request = require('request');
      let proxy_url = 'https://cors-anywhere.herokuapp.com'
      let url = 'http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList';
      let queryParams = '?' + encodeURIComponent('ServiceKey') + '=' + process.env.VUE_APP_APISDATA_API_KEY; /* Service Key*/
      queryParams += '&' + encodeURIComponent('desc_kor') + '=' + this.foodName; /* */
      console.log('queryParams', url + queryParams)
      // axios.get(`${proxy_url}/${url}?ServiceKey=${process.env.VUE_APP_APISDATA_API_KEY}&desc_kor=${this.foodName}`)
      axios.get(`${url}?ServiceKey=${process.env.VUE_APP_APISDATA_API_KEY}&desc_kor=${this.foodName}`)
        .then(res => {
          console.log(res)
        })
        .catch(err => {
          console.log(err)
          console.error(err.response.data)
        })
    },
    getFoodInfo2() {
      let request = require('request');

      let url = 'http://apis.data.go.kr/1470000/FoodNtrIrdntInfoService/getFoodNtrItdntList';
      let queryParams = '?' + encodeURIComponent('ServiceKey') + '=' + process.env.VUE_APP_APISDATA_API_KEY; /* Service Key*/
      queryParams += '&' + encodeURIComponent('desc_kor') + '=' + this.foodName; /* */
      console.log('queryParams', url + queryParams)
      request({
          url: url + queryParams,
          method: 'GET'
      }, function (error, response, body) {
          console.log('Status', response.statusCode);
          console.log('Headers', JSON.stringify(response.headers));
          console.log('Reponse received', body);
      });
    },
    addFoods(foods) {
      axios.post(`${SERVER.URL}${SERVER.ROUTES.registerfoods}`, foods)
        .then (() => {
          console.log('추가 완료')
        })
        .catch(() => {
          console.log('추가 실패')
        })

    },
  },
}
</script>

<style scoped>
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-left: 10%;
    text-align: start;
  }
  .bg-green {
    background: #ADEC6E;
    color: white;
    /* color: #99a9bf; */
  }
  .bg-gray {
    background: #F0F0F0;
  }
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    width: 80%;
    height: 40px;
    margin-bottom: 20px;
    padding: 0px 2px;
  }
  .join-button-setting {
    margin-top: 30px;
    height: 45px;
    width: 80%;
    cursor: pointer;
  }
</style>