<template>
  <div class="feed-container">
    <div class="feed-header">
      <img class="logo-image" src="@/assets/image/kiwi_logo.png" />
      <div class="icon-wrapper">
        <font-awesome-icon :icon="['fas', 'search']" class="icon" />
        <font-awesome-icon :icon="['far', 'envelope']" class="icon" @click="userSearch"/>
        <font-awesome-icon :icon="['far', 'bell']" class="icon" />
      </div>
    </div>
    <div class="header-height"></div>
    <div v-for="(post, index) in postData" v-bind:key="index" class="post-wrapper">
      <post v-bind="post"></post>
    </div>
    <infinite-loading @infinite="infiniteHandler" spineer="waveDots">
      <div slot="no-more"></div>
      <div slot="no-results"></div>
    </infinite-loading>
    <div class="rest"></div>
    <Footer></Footer>
  </div>
</template>

<script>
import Post from "@/components/home/Post";
import InfiniteLoading from "vue-infinite-loading";
import Footer from "@/components/home/Footer";

import axios from "axios";
import SERVER from "@/api/drf.js";

export default {
    data() {
      return {
        userId: 1,
        postData: [],
        limit: 0,
      }
    },
    created() {
      // this.userId = localStorage.getItem("userId");
      this.getPostData();
      // footer 4로 설정
      this.$store.state.selectedRouter = 0
    },
    methods: {
      async getPostData() {
        try {
          const response = await axios.get(SERVER.URL + SERVER.ROUTES.getFeedPostData + this.userId + `?page=${this.limit}`);
          this.postData = response.data;
        } catch(exp) {
          console.log(exp);
        }
      },
      async infiniteHandler($state) {
        const EACH_LEN = 10;
        this.limit += 1;

        const response = await axios.get(SERVER.URL + SERVER.ROUTES.getFeedPostData + this.userId + `?page=${this.limit}`);
        setTimeout(() => {
          if(response.data.length) {
            this.postData = this.postData.concat(response.data);
            $state.loaded();

            if(response.data.length < EACH_LEN) {
              $state.complete();
            }
          }
          else {
            $state.complete();
          }
        }, 300);
      },
      userSearch() {
        this.$router.push({ name: 'SearchUser' });
      }
    },
    components: {
      Post,
      InfiniteLoading,
      Footer,
    }
}
</script>

<style scoped>
.feed-header {
  position: relative;
  width: 100%;
  padding: 5px 15px 15px 15px;
  margin: 0px;
  box-sizing: border-box;
  overflow: hidden;
}
.logo-image {
  width: 25%;
  float: left;
}
.icon-wrapper {
  width: 25%;
  float: right;
  position: absolute;
  top: 50%;
  right: 15px;
  transform: translate(0%, -50%);
}
.icon {
  margin: 0px 0px 0px 5px;
}
.post-wrapper {
  margin: 0px 0px 15px 0px;
}
.rest {
  height: 48px;
}
</style>