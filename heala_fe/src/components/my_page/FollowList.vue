<template>
  <div>
    <div :class="{ 'follow-list-mask': !isSearchList }"></div>
    <div :class="{ 'follow-list':!isSearchList, 'user-list':isSearchList }">
      <div class="follow-list-item"
        v-for="(follow, index) in followList"
        v-bind:key="index">
        <follow-list-item v-bind="follow" @follow="$emit('follow')" @unfollow="$emit('unfollow')"></follow-list-item>
      </div>
      <infinite-loading :identifier="infiniteId" @infinite="infiniteHandler" spineer="waveDots">
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
  props: [ "followListType", "isSearchList", "keyword" ],
  data() {
    return {
      userId: 1,
      followList: [],
      page: 0,
      infiniteId: 0,
    }
  },
  created() {
    this.userId = localStorage.getItem("userId");
    if(this.isSearchList) {
      this.getInitialSearchUserList();
    }
    else {
      this.getInitialFollowList();
    }
  },
  watch: {
    keyword() {
      this.infiniteId += 1;
      this.page = 0;
      this.getInitialSearchUserList();
    }
  },
  methods: {
    async getInitialFollowList() {
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.feed + this.$route.params.id + SERVER.ROUTES.followList
        + `?myId=${this.userId}&type=${this.followListType}&page=${this.page}`);
        this.followList = response.data;
      } catch(exp) {
        console.log(exp);
      }
    },
    async getFollowList() {
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.feed + this.$route.params.id + SERVER.ROUTES.followList
        + `?myId=${this.userId}&type=${this.followListType}&page=${this.page}`);
        return response.data;
      } catch(exp) {
        console.log(exp);
      }
    },
    async getInitialSearchUserList() {
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.userSearch
        + `?userId=${this.userId}&word=${this.keyword}&page=${this.page}`);
        this.followList = response.data;
      } catch(exp) {
        console.log(exp);
      }
    },
    async getSearchUserList() {
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.userSearch
        + `?userId=${this.userId}&word=${this.keyword}&page=${this.page}`);
        return response.data;
      } catch(exp) {
        console.log(exp);
      }
    },
    async infiniteHandler($state) {
      const EACH_LEN = 15;
      this.page += 1;
      try{
        let data;
        if(this.isSearchList) data = this.getSearchUserList();
        else data = this.getFollowList();
        if(data.length) {
          this.followList = this.followList.concat(data);
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
.user-list {
  background-color: #fff;
  width: 100%;
}
.rest {
  height: 48px;
}
</style>