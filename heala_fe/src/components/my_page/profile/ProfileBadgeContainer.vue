<template>
  <div>
    <div class="badge-container">
      <div class="badge-image-wrapper" v-for="(badge, idx) in badges" :key="idx">
        <img class="badge-image" :src="imageServer + badge.image" :title="badge.name" alt="badge image">
      </div>
    </div>
    <div class="rest"></div>
  </div>
</template>

<script>
import axios from "axios";
import SERVER from "@/api/drf.js";

export default {
  name: "ProfileBadgeContainer",
  data: () => {
    return {
      imageServer: SERVER.IMAGE_URL,
      badges: [],
    };
  },
  created() {
    this.getBadgeInfo();
  },
  computed: {
    paramUserId() {
      return this.$route.params.id;
    }
  },
  watch: {
    paramUserId() {
      this.getBadgeInfo();
    }
  },
  methods: {
    async getBadgeInfo() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.feed + `${this.$route.params.id}` + SERVER.ROUTES.badge);
        this.badges = response.data;
      } catch (error) {
        alert('배지를 가져오던 중 오류가 발생했습니다.')
      }
    },
  },
}
</script>

<style scoped>
.badge-container {
  text-align: left;
  margin-bottom: 4vw;
}
.badge-image-wrapper {
  margin: 4vw 4vw 0px 4vw;
  display: inline-block;
}
.badge-image {
  width: 40px;
  height: 40px;
}
.rest {
  height: 48px;
}
</style>