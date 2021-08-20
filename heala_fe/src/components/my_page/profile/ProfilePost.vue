<template>
  <div class="profile-post-container">
    <div class="post-image-wrapper"
      v-for="(postImage, i) in feedImages"
      v-bind:key="i">
      <img :src="imageServer + postImage.image" alt="" class="post-image" @click="goToPost(postImage.id)">
    </div>
    <infinite-loading :identifier="infiniteId" @infinite="infiniteHandler" spineer="waveDots">
      <div slot="no-more"></div>
      <div slot="no-results"></div>
    </infinite-loading>
    <div class="rest"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
import SERVER from "@/api/drf.js";
import InfiniteLoading from "vue-infinite-loading";
export default {
  name: "ProfilePost",
  data() {
    return {
      imageServer: SERVER.IMAGE_URL,
      feedImages: [],
      page: 0,
      userId: this.$route.params.id,
      infiniteId: 0,
    }
  },
  created() {
    this.getInitialFeedPost();
  },
  computed: {
    ...mapState([
      "profileSelectedCategory",
    ]),
    paramUserId() {
      return this.$route.params.id;
    }
  },
  watch: {
    profileSelectedCategory() {
      this.page = 0;
      this.infiniteId += 1;
      this.getInitialFeedPost();
    },
    paramUserId() {
      this.userId = this.$route.params.id;
      this.getInitialFeedPost();
    }
  },
  methods: {
    async getInitialFeedPost() {
      try {
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.feedPost + `/${this.userId}/my/${this.profileSelectedCategory}?page=${this.page}`);
        this.feedImages = response.data;
      } catch (error) {
        console.log(error);
      }
    },
    async infiniteHandler($state) {
      const EACH_LEN = 30;
      this.page += 1;
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.feedPost + `/${this.userId}/my/${this.profileSelectedCategory}?page=${this.page}`);
        let data = response.data;
        if(data.length) {
          this.feedImages = this.feedImages.concat(data);
          $state.loaded();

          if(data.length < EACH_LEN) {
            $state.complete();
          }
        }
        else {
          $state.complete();
        }
      } catch(exp) {
        console.log(exp);
      }
    },
    goToPost(id) {
      this.$router.push({ name: "PostDetail", params: { id: id} });
    }
  },
  components: { InfiniteLoading },
}
</script>

<style scoped>
.profile-post-container {
  text-align: left;
  margin-top: 5px;
}
.post-image-wrapper {
  width: 31%;
  position: relative;
  margin: 0.5% 1%;
  display: inline-block;
}
.post-image-wrapper:after {
  content: "";
  display: block;
  background-color: rgb(216, 216, 216);
  padding-bottom: 100%;
}
.post-image {
  position: absolute;
  left: 0px;
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.rest {
  height: 48px;
}
</style>