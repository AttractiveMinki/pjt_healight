<template>
  <div>
    <h1>OCR Test</h1>
    <img v-if="image == ''" src="@/assets/img/profile/user.png" alt="profile_image" width="92" height="92" style="border-radius: 50%;">
    <img v-else :src="image" alt="profile_image" width="92" height="92" style="border-radius: 50%;"><br>
    <label for="image" class="btn-file">
      <span style="font-size: 13px; font-weight: bold; color: #ADEC6E;">프로필 사진 변경</span>
      <input name="image" type="file" @change="selectFile" id="change_image"/>
    </label>
    <button @click="submit()">제출하기</button>

    
    <div v-for="(value, idx) in data.result" :key="idx" >
      {{ value.recognition_words }}
        <!-- <span v-for="word, idx2 in find_words" :key="idx2">
          <span v-if="value.recognition_words.find(word)">
            {{ word }}찾았쥬
          </span>
        </span> -->
      <!-- <span v-if="value.recognition_words in find_words">
        영양성분찾았쥬
      </span> -->
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
      console.log('들어왔쥬')
      let formData = new FormData();
      let imgFile = document.getElementById('change_image');
      formData.append('image', imgFile.files[0])
      console.log(process.env.VUE_APP_OCR_REST_API_KEY)

      axios.post(`${SERVER.ROUTES.OCR}`, formData , { headers: {'Content-Type' : 'multipart/form-data', 'Authorization': `KakaoAK ${process.env.VUE_APP_OCR_REST_API_KEY}`}})
      .then((res) => {
        console.log(res)
        this.data = res.data
      })
      .catch((err) => {
        console.log(err)
      })
    },
  },
  data: () => {
    return {
      image: "",
      data: [],
      find_words: [
        '칼로리', '나트륨', '탄수화물', '지방', '단백질'
      ]
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