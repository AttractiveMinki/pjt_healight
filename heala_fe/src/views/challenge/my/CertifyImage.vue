<template>
  <div>
    <kiwi-header>
      <template v-slot:title>인증사진 모아보기</template>
    </kiwi-header>
    <div class="certify-image-container">
      <div class="challenge-info">  
        <div class="challenge-title">
        {{ withChallenge.title }}
        </div>
        <div class="challenge-term">
        {{ withChallenge.startDate }} - {{ withChallenge.endDate }}
        </div>
      </div>
        <div class="certify-wrapper"
        v-for="(data, i) in certifyData"
        v-bind:key="i">
        <div class="certify-date">
            {{ data.date }}
        </div>
      <div class="certify-images-wrapper">
        <div class="certify-image-wrapper"
            v-for="(images) in data.list"
            v-bind:key="images.id">
            <img :src="require(`@/assets/image/${images.image}`)" alt="@/assets/image/error.jpg" class="certify-image">
        </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import KiwiHeader from "@/components/KiwiHeader";

import axios from "axios"
import SERVER from "@/api/drf.js"

export default {
    name: "CertifyImage",
    data() {
        return {
            userId: 1, // local Storage 에서 얻어오도록 변경
            withChallengeId: Number,
            withChallenge: {
                title: "임시 데이터: 챌린지 이름",
                startDate: "21/07/20",
                endDate: "21/08/02",
            },
            certifyData: [
                {
                    date: "21/07/20",
                    list: [
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
                    ],
                },
                {
                    date: "21/07/21",
                    list: [
                        {
                            id: 5,
                            image: "error.png",
                        },
                    ],
                }
            ]
        }
    },
    created() {
        // this.userId = localStorage.getItem("userId");
        this.withChallengeId = this.$route.params.withChallengeId;
        this.getWithChallengInfo();
        this.getImages();
    },
    methods: {
        getWithChallengInfo() {
            axios
                .get(SERVER.URL + SERVER.ROUTES.getWithDetail
                    + `?userId=${this.userId}&withChallengeId=${this.withChallengeId}`)
                .then((response) =>
                    this.withChallenge = response.data.withChallenge
                )
                .catch((exp) => 
                    console.log(`인증사진 불러오기에 실패했습니다: ${exp}`)
                );
        },
        getImages() {
            axios
                .get(SERVER.URL + SERVER.ROUTES.myChallengePhoto
                    + `?userId=${this.userId}&withChallengeId=${this.withChallengeId}`)
                .then((response) =>
                    this.certifyData = response.data
                )
                .catch((exp) => 
                    console.log(`인증사진 불러오기에 실패했습니다: ${exp}`)
                );
        }
    },
    components: { KiwiHeader }
}
</script>

<style scoped>
.certify-image-container {
    font-size: 13px;
    text-align: left;
}
.challenge-info {
    margin: 70px 0px 0px 0px;
    padding: 0px 0px 0px 30px;
}
.challenge-title {
    font-size: 15px;
    /* font-weight: 550; */
}
.challenge-term {
    font-size: 12px;
    color: #999999;
    padding: 3px 0px 0px 3px;
}
.certify-wrapper {
    margin: 30px 0px 0px 0px;
}
.certify-date {
    margin: 0px 0px 5px 10px;
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
    padding-bottom: 100%;
}
.certify-image {
    position: absolute;
    width: 100%;
    height: 100%;
    object-fit: cover;
}
</style>