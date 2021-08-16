<template>
  <div >
    <Navbar />
    <h1>먹은 음식 등록</h1>
    <h2>검색해서 입력하기</h2>

    <!-- <input type="text" class='get-input bg-gray' id="foodName" v-model="data.foodName" > -->
    <input type="text" id="foodNameSearch" v-model="foodNameSearch" style="margin-right: 2vw" @keyup.enter="getFoodInfo()">
    <el-button @click="getFoodInfo()">음식 검색</el-button>
    <div v-if="foodList">
      <div v-for="food, idx in foodList" :key="idx">
        <button class="select-food-button" @click="selectNutrient(food)">
          {{ food.ANIMAL_PLANT }}, {{ food.DESC_KOR }}  
        </button>
      </div>
    </div>
    <hr>
    <h2>영양정보 사진으로 입력하기</h2>
    <div style="font-size: 13px">제품 뒷편에 있는</div>
    <div style="font-size: 13px">영양정보 사진을 첨부해보세요!</div>
    <br>
    <div>
      <img v-if="image == ''" src="@/assets/img/profile/user.png" alt="profile_image" width="92" height="92" style="border-radius: 50%;">
      <img v-else :src="image" alt="profile_image" width="92" height="92"><br>
      <label for="image" class="btn-file">
        <input name="image" type="file" @change="selectFile" id="change_image"/>
        <span style="font-size: 13px; font-weight: bold; color: #ADEC6E;">영양성분사진 등록
          
        </span>
        
      </label>
    </div>
    <div>
      <button class="bg-green get-input join-button-setting" @click="submit()">하단 자동입력</button>
    </div>

    <h2>직접 입력하기</h2>
    <el-row>
      <el-col :span="24"><div class="introduce-text-align-start">음식 이름</div>
        <input type="text" class='get-input bg-gray' id="foods-name" v-model="foods.foodName" maxlength="20" placeholder="음식 이름을 입력하세요.">
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
    <button @click="oneHalf" class="select-food-number">1/2배</button>
    <button @click="minus" class="select-food-number">-1개</button>
    {{ number }}개
    <button @click="plus" class="select-food-number">+1개</button>
    <button @click="twice" class="select-food-number">2배</button>
    <br>
    <button id="addFoods" class="bg-green get-input join-button-setting" @click="addFoods(foods)">추가하기</button>
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
        foodName: "",
        calory: "",
        carbohydrate: "", // 탄수화물
        protein: "",
        fat: "",
        sodium: "", // 나트륨
        userId: "",
      },
      oneFood: {
        calory: "",
        carbohydrate: "", // 탄수화물
        protein: "",
        fat: "",
        sodium: "", // 나트륨
      },
      foodNameSearch: "",
      foodList: [],
      image: "",
      data: [],
      temp_word: "",
      reserve_info: -1,
      find_words: [
        '칼로리', '탄수화물', '단백질', '지방', '나트륨'
      ],
      set_number: [0, 0, 0, 0, 0],
      number: 0,
    }
  },
  methods: {
    getFoodInfo() {
      if (this.foodNameSearch != '')
      {
        axios.get(`${SERVER.URL}${SERVER.ROUTES.getFoodnutrient}?desc_kor=${this.foodNameSearch}`)
          .then((res) => {
            this.foodList = res.data.body.items
          })
          .catch((err) => {
            console.log(err)
          })
      }
    },
    addFoods(foods) {
      console.log(foods)
      console.log(`${SERVER.URL}${SERVER.ROUTES.dietupload}`)
      axios.post(`${SERVER.URL}${SERVER.ROUTES.dietupload}`, foods)
        .then (() => {
          alert('추가 완료!')
          this.foods['foodName'] = ""
          this.foods['calory'] = ""
          this.foods['carbohydrate'] = ""
          this.foods['protein'] = ""
          this.foods['fat'] = ""
          this.foods['sodium'] = ""
          scrollBy(0, -document.body.scrollHeight) // 맨 위로 올랴줌
        })
        .catch(() => {
          console.log('추가 실패')
        })
    },
    selectFile(e) {
      const file = e.target.files[0];
      this.image = URL.createObjectURL(file);
    },
    submit: function () {
      let formData = new FormData();
      let imgFile = document.getElementById('change_image');
      formData.append('image', imgFile.files[0])
      axios.post(`${SERVER.ROUTES.OCR}`, formData , { headers: {'Content-Type' : 'multipart/form-data', 'Authorization': `KakaoAK ${process.env.VUE_APP_OCR_REST_API_KEY}`}})
      .then((res) => {
        // console.log(res)
        this.data = res.data
        this.findNutrient(this.data)
      })
      .catch((err) => {
        console.log(err)
      })
    },
    findNutrient: function (value) {
      for (let nutrient of value.result) {
        if (this.reserve_info != -1) {
          let regex = /[^0-9]/g;				// 숫자가 아닌 문자열을 선택하는 정규식
          this.set_number[this.reserve_info] = nutrient.recognition_words[0].replace(regex, "")
          this.reserve_info = -1
          continue
        }
        for (let i = 0; i < 5; i++) {
          if (nutrient.recognition_words[0] == this.find_words[i]) {
            this.reserve_info = i
            break
          }
        }
      }
      this.setNutrient()
    },
    setNutrient: function () {
      this.foods['calory'] = this.set_number[0]
      this.foods['carbohydrate'] = this.set_number[1]
      this.foods['protein'] = this.set_number[2]
      this.foods['fat'] = this.set_number[3]
      this.foods['sodium'] = this.set_number[4]
      scrollBy(0, document.body.scrollHeight) // 맨 밑으로 내려줌

      this.number = 1
      this.oneFood['calory'] = this.foods['calory']
      this.oneFood['carbohydrate'] = this.foods['carbohydrate']
      this.oneFood['protein'] = this.foods['protein']
      this.oneFood['fat'] = this.foods['fat']
      this.oneFood['sodium'] = this.foods['sodium']
    },
    selectNutrient: function (food) {
      this.foodList = []
      this.foods['foodName'] = food.DESC_KOR
      this.foods['calory'] = food.NUTR_CONT1
      this.foods['carbohydrate'] = food.NUTR_CONT2
      this.foods['protein'] = food.NUTR_CONT3
      this.foods['fat'] = food.NUTR_CONT4
      this.foods['sodium'] = food.NUTR_CONT6
      scrollBy(0, document.body.scrollHeight) // 맨 밑으로 내려줌

      this.number = 1
      this.oneFood['calory'] = this.foods['calory']
      this.oneFood['carbohydrate'] = this.foods['carbohydrate']
      this.oneFood['protein'] = this.foods['protein']
      this.oneFood['fat'] = this.foods['fat']
      this.oneFood['sodium'] = this.foods['sodium']
    },
    twice: function () {
      this.number *= 2
      this.foods['calory'] *= 2
      this.foods['calory'].toFixed(0)
      this.foods['carbohydrate'] *= 2
      this.foods['carbohydrate'].toFixed(2)
      this.foods['protein'] *= 2
      this.foods['protein'].toFixed(2)
      this.foods['fat'] *= 2
      this.foods['fat'].toFixed(2)
      this.foods['sodium'] *= 2
      this.foods['sodium'].toFixed(0)
    },
    oneHalf: function () {
      this.number /= 2
      this.foods['calory'] /= 2
      this.foods['calory'].toFixed(0)
      this.foods['carbohydrate'] /= 2
      this.foods['carbohydrate'].toFixed(2)
      this.foods['protein'] /= 2
      this.foods['protein'].toFixed(2)
      this.foods['fat'] /= 2
      this.foods['fat'].toFixed(2)
      this.foods['sodium'] /= 2
      this.foods['sodium'].toFixed(0)
    },
    plus: function () {
      this.number += 1
      this.foods['calory'] = (parseFloat(this.foods['calory']) + parseFloat(this.oneFood['calory'])).toFixed(0)
      this.foods['carbohydrate'] = (parseFloat(this.foods['carbohydrate']) + parseFloat(this.oneFood['carbohydrate'])).toFixed(2)
      this.foods['protein'] = (parseFloat(this.foods['protein']) + parseFloat(this.oneFood['protein'])).toFixed(2)
      this.foods['fat'] = (parseFloat(this.foods['fat']) + parseFloat(this.oneFood['fat'])).toFixed(2)
      this.foods['sodium'] = (parseFloat(this.foods['sodium']) + parseFloat(this.oneFood['sodium'])).toFixed(0)
    },
    minus: function () {
      if (this.number - 1 < 0) {
        return
      }
      this.number -= 1
      this.foods['calory'] = (parseFloat(this.foods['calory']) - parseFloat(this.oneFood['calory'])).toFixed(0)
      this.foods['carbohydrate'] = (parseFloat(this.foods['carbohydrate']) - parseFloat(this.oneFood['carbohydrate'])).toFixed(2)
      this.foods['protein'] = (parseFloat(this.foods['protein']) - parseFloat(this.oneFood['protein'])).toFixed(2)
      this.foods['fat'] = (parseFloat(this.foods['fat']) - parseFloat(this.oneFood['fat'])).toFixed(2)
      this.foods['sodium'] = (parseFloat(this.foods['sodium']) - parseFloat(this.oneFood['sodium'])).toFixed(0)
    },
  },
  created () {
    this.foods.userId = localStorage.getItem('userId')

    // 현재 보고 있는 프로필 주인의 id 주소창에서 가져오기
    this.userId = this.$route.path.split('/')[3]

    // 내 아이디 localStorage에서 가져오기
    this.myId = localStorage.getItem('userId')

    // 다른 사람꺼 보려 하면 추방
    if (this.userId != this.myId) {
      alert('다른 사람의 계정입니다. 열람하실 수 없습니다.')
      this.$router.go(-1)
    }
    this.number = 0
  },
}
</script>

<style scoped>
  #change_image:hover, #submit {
    cursor: pointer;
  }
  
  .btn-file{
    position: relative;
    overflow: hidden;
  }
  .btn-file input[type=file] {
    position: absolute;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
  }
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-left: 10%;
    text-align: start;
  }
  .bg-gray {
    background: #F0F0F0;
  }
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    width: 80%;
    height: 45px;
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
    cursor: pointer;
  }
  .select-food-button {
    background: #ADEC6E;
    color: black;
    width: 80%;
    height: 4vh;
    margin: 2vw;
    border-width: 0px;
    border-radius: 5px;
  }
  .select-food-number {
    width: 15%;
    height: 3vh;
    background: #ADEC6E;
    color: black;
    margin: 2vw;
    border-width: 0px;
    border-radius: 5px;
  }

</style>