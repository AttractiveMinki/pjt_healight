<template>
  <div>
    <kiwi-header>
      <template v-slot:title>챌린지 결과</template>
    </kiwi-header>
    <div v-if="resultData.achievement >= 85" >
      <img class="result-image" src="@/assets/image/success.png" alt="">
      <div class="result-message">챌린지 성공! 축하드립니다!!</div>
    </div>
    <div v-else>
      <img class="result-image" src="@/assets/image/fail.png" alt="">
      <div class="result-message">챌린지 실패! ㅠㅠ</div>
    </div>
    <div class="achieve">
      <div class="label">달성률</div>
      <div class="progress-bar">
        <div class="progress-container">
          <div v-bind:style="{ width: resultData.achievement + '%' }" class="progress-content"></div>
        </div>
        <div class="progress-info">
          <div class="zero">0</div>
          <div v-if="achievementCheck()" v-bind:style="{ width: resultData.achievement + '%' }" class="achievement">{{ resultData.achievement }}</div>
          <div class="max">100</div>
        </div>
      </div>
    </div>
    <div class="achieve">
      <div class="exp-label">획득 경험치</div>
      <div class="progress-bar">
        <div class="level">Lv.{{ level }}</div>
        <div class="progress-container">
          <div v-bind:style="{ width: levelPercent + '%' }" class="progress-content">+{{ resultData.point }}</div>
          <div v-if="levelPercent == 0 && resultData.point != 0" class="progress-content-exp">+{{ resultData.point }} Level Up!</div>
        </div>
        <div class="progress-info">
          <div class="zero">0</div>
          <div v-if="expCheck()" v-bind:style="{ width: levelPercent + '%' }" class="level-percent">{{ levelExp }}</div>
          <div class="max">{{ levelNeedExp }}</div>
        </div>
      </div>
    </div>
    <div class="certify-image-container">
      <div class="certify-image-header">인증 모아보기</div>
      <div class="certify-images-wrapper">
        <!-- <div class="certify-image-wrapper"
          v-for="(images, index) in resultData.certifyImage"
          v-bind:key="images.id">
          <img v-show="index < 5" :src="require(`@/assets/image/${images.image}`)" alt="@/assets/image/error.jpg" class="certify-image">
          <div v-show="index == 5" class="certify-image">전체사진 보러가기</div>
        </div> -->
        <div class="certify-image-wrapper"
          v-for="(index, i) in certifyImageLimit"
          v-bind:key="i">
          <img v-show="i < 5" :src="require(`@/assets/image/${resultData.certifyImage[index].image}`)" alt="@/assets/image/error.jpg" class="certify-image">
          <div v-show="i == 5" class="certify-image-link-text">
            전체사진<br>보러가기
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import KiwiHeader from "@/components/KiwiHeader";
import SERVER from "@/api/drf.js";
import axios from "axios";

export default {
    name: "Result",
    data() {
        return {
            userId: 1,
            certifyImageLimit: [0, 0, 0, 0, 0, 0],
            resultData: {
                achievement: 1,
                point: 1700,
                certifyImage: [
                  {
                    id: 1,
                    image: "cat.jpg",
                  },
                  {
                    id: 2,
                    image: "cat.jpg",
                  },
                  {
                    id: 3,
                    image: "cat.jpg",
                  },
                  {
                    id: 4,
                    image: "cat.jpg",
                  },
                  {
                    id: 5,
                    image: "cat.jpg",
                  },
                  {
                    id: 6,
                    image: "cat.jpg",
                  },
                  {
                    id: 7,
                    image: "cat.jpg",
                  },
                  {
                    id: 8,
                    image: "cat.jpg",
                  },
                  {
                    id: 9,
                    image: "cat.jpg",
                  },
                ],
            },
            userData: {
              exp: 33000,
            },
            level: 32,
            levelNeedExp: 2000,
            levelExp: 1000,
            levelPercent: 99,
        }
    },
    created() {
        // user setting
        // this.userId = localStorage.getItem("userId");
        // 결과 정보 불러오기 + certifyImageLimit 세팅
        this.getResultData();
        // user exp 정보 불러오기 + level 계산
        this.getUserData();
    },
    methods: {
      getResultData() {
        axios
          .get(SERVER.URL + SERVER.ROUTES.myChallengeResult
          + `?userId=${this.userId}&withChallengeId=${this.$route.params.withChallengeId}`)
          .then((response) => {
            this.resultData = response.data;
            let arrLength = this.resultData.certifyImage.length < 6 ? this.resultData.certifyImage.length : 6;
            this.certifyImageLimit = Array.from({length: arrLength}, () => 0);
          })
          .catch((exp) => {
            console.log(`챌린지 결과 불러오기에 실패했습니다: ${exp}`)
          })
      },
      getUserData() {
        axios
          .get(SERVER.URL + SERVER.ROUTES.userExp
          + `?userId=${this.userId}`)
          .then((response) => {
            this.userData.exp = response.data;
            this.setLevel(this.userData.exp);
          })
          .catch((exp) => {
            console.log(`사용자 exp 불러오기에 실패했습니다: ${exp}`)
          })
      },
      setLevel(exp) {
        for (let i = 0; i < 100; i++) {
          if(exp >= 10000 * i){
            exp -= 10000 * i;
            continue;
          }
          let levelOne = Math.floor(exp / (1000 * i));
          this.level = (i - 1) * 10 + levelOne;
          this.levelNeedExp = 1000 * i;
          this.levelExp = exp % (1000 * i);
          this.levelPercent = Math.floor(this.levelExp / this.levelNeedExp * 100);
          break;
        }
      },
      achievementCheck() {
        return this.resultData.achievement != 0 && this.resultData.achievement != 100;
      },
      expCheck() {
        return this.levelPercent != 0 && this.levelPercent != 100;
      },
    },
    components: { KiwiHeader },
}
</script>

<style scoped>
.result-image {
    width: 40%;
    margin: 10% 10% 5% 10%;
}
.achieve {
    margin: 40px auto;
    position: relative;
}
.label {
  width: 25%;
  font-size: 13px;
  display: inline-block;
  vertical-align: top;
}
.progress-bar {
    width: 70%;
    height: 15px;
    display: inline-block;
}
.progress-container {
    width: 100%;
    height: 100%;
    background-color: rgb(235, 235, 235);
    position: relative;
    border-radius: 0.5rem;
}
.progress-content {
    background-color: #FFA7A7;
    height: 100%;
    color: whitesmoke;
    border-radius: 0.5rem;
    position: absolute;
    top: 0px;
    left: 0px;
    font-size: 12px;
}
.progress-content-exp {
  font-size: 12px;
}
.progress-info {
  font-size: 12px;
  margin: 2px 0px 0px 0px;
  position: relative;
  text-align: left;
}
.zero {
  float: left;
}
.achievement {
  min-width: 17px;
  max-width: calc(100% - 24px);
  height: 14px;
  color: #FFA7A7;
  display: inline-block;
  text-align: right;
  position: absolute;
  left: 0px;
}
.max {
  float: right;
}
.level {
  width: 100%;
  font-size: 11px;
  text-align: right;
  position: absolute;
  top: -15px;
  right: 3%;
}
.exp-label {
  width: 25%;
  font-size: 13px;
  display: inline-block;
  vertical-align: top;
}
.level-percent {
  min-width: 17px;
  max-width: calc(100% - 30px);
  height: 14px;
  color: #FFA7A7;
  display: inline-block;
  text-align: right;
  position: absolute;
  left: 0px;
}
.certify-image-container {
  text-align: left;
}
.certify-image-header {
  margin: 0px 0px 10px 10px;
}
.certify-images-wrapper {
    width: 100%;
    box-sizing: border-box;
}
.certify-image-wrapper {
    width: 31%;
    position: relative;
    margin: 0.5% 1%;
    display: inline-block;
}
.certify-image-wrapper:after {
    content: "";
    display: block;
    background-color: rgb(216, 216, 216);
    padding-bottom: 100%;
}
.certify-image {
    position: absolute;
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.certify-image-link-text {
  font-size: 12px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>