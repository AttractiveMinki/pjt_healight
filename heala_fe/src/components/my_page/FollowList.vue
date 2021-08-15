<template>
  <div>
    <div class="follow-list-mask"></div>
    <div class="follow-list">
      <div class="follow-list-item"
        v-for="(follow, index) in followList"
        v-bind:key="index">
        <follow-list-item v-bind="follow"></follow-list-item>
      </div>
      <infinite-loading @infinite="infiniteHandler" spineer="waveDots">
        <div slot="no-more"></div>
        <div slot="no-results"></div>
      </infinite-loading>
      <div class="rest"></div>
    </div>
  </div>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import FollowListItem from "@/components/my_page/FollowListItem";

import SERVER from "@/api/drf.js";
import axios from "axios";

export default {
    name: "FollowList",
    props: [ "followListType" ],
    data() {
        return {
            userId: 1,
            followList: [],
            page: 0,
            follow: "기본입니다.",
            end: false,
        }
    },
    created() {
      this.userId = localStorage.getItem("userId");
      this.getFollowList();
    },
    methods: {
      async getFollowList() {
        try{
          const response = await axios.get(SERVER.URL + SERVER.ROUTES.feed + this.$route.params.id + SERVER.ROUTES.followList
          + `?myId=${this.userId}&type=${this.followListType}&page=${this.page}`);
          this.followList = response.data;
        } catch(exp) {
          console.log(exp);
        }
      },
      async infiniteHandler($state) {
        const EACH_LEN = 15;
        this.page += 1;
        try{
          const response = await axios.get(SERVER.URL + SERVER.ROUTES.feed + this.$route.params.id + SERVER.ROUTES.followList
            + `?myId=${this.userId}&type=${this.followListType}&page=${this.page}`);
          // setTimeout(() => {
          if(response.data.length) {
            this.followList = this.followList.concat(response.data);
            $state.loaded();

            if(response.data.length < EACH_LEN) {
              $state.complete();
            }
          }
          else {
            $state.complete();
          }
        } catch(exp) {
          console.log(exp);
        }
        // }, 300);
      }
    },
    components: {
      InfiniteLoading,
      FollowListItem,
    }
}
</script>

<style scoped>
.follow-list-mask {
  position: absolute;
  top: 0px;
  z-index: 1;
  background-color: #fff;
  width: 100%;
  height: 80vh;
}
.follow-list {
    position: absolute;
    top: 0px;
    z-index: 2;
    background-color: #fff;
    width: 100%;
}
.rest {
  /* background-color: rgba(0, 0, 0, .1);
  z-index: -1;
  display: table;
  transition: opacity .3s ease; */
  height: 48px;
}
</style>