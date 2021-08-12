<template>
  <div>
    <h1>OCR Test</h1>
    <img v-if="image == ''" src="@/assets/img/profile/user.png" alt="profile_image" width="92" height="92" style="border-radius: 50%;">
    <img v-else :src="image" alt="profile_image" width="92" height="92" style="border-radius: 50%;"><br>
    <label for="image" class="btn-file">
      <span style="font-size: 13px; font-weight: bold; color: #ADEC6E;">프로필 사진 변경</span>
      <input name="image" type="file" @change="selectFile" id="change_image"/>
    </label>
    <div>
      <button @click="submit()">제출하기</button>
    </div>
    <div v-for="food, idx in current_foods" :key="idx">
      {{ idx }} : {{ food }}
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import SERVER from "@/api/drf.js"

export default {
  name: "OCRTest",
  methods: {
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
      this.current_foods['calory'] = this.set_number[0]
      this.current_foods['carbohydrate'] = this.set_number[1]
      this.current_foods['protein'] = this.set_number[2]
      this.current_foods['fat'] = this.set_number[3]
      this.current_foods['sodium'] = this.set_number[4]
    },
  },
  data: () => {
    return {
      image: "",
      data: [],
      temp_word: "",
      reserve_info: -1,
      find_words: [
        '칼로리', '탄수화물', '단백질', '지방', '나트륨'
      ],
      set_number: [0, 0, 0, 0, 0],
      current_foods: {
        name: "",
        calory: "",
        carbohydrate: "", // 탄수화물
        protein: "",
        fat: "",
        sodium: "", // 나트륨
      },
    };
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
    top: 0;
    right: 0;
    min-width: 100%;
    min-height: 100%;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
  }
</style>