<template>
  <div>
    <div :class="{ 'follow-list-mask': !isSearchList }"></div>
    <div :class="{ 'follow-list':!isSearchList, 'user-list':isSearchList }">
      <div class="follow-list-item"
        v-for="(follow, index) in followList"
        v-bind:key="index"
        @click="goToFeed(follow.id)"
        >
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
    this.$store.commit("GET_USERID")
    this.userId = this.$store.state.userId
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
        + `?myId=${this.userId}&follow=${this.followListType}&page=${this.page}`);
        this.followList = response.data;
      } catch(exp) {
        alert('팔로우 정보를 가져오던 중 오류가 발생했습니다.')
      }
    },
    async getFollowList() {
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.feed + this.$route.params.id + SERVER.ROUTES.followList
        + `?myId=${this.userId}&follow=${this.followListType}&page=${this.page}`);
        return response.data;
      } catch(exp) {
        alert('팔로우 정보를 가져오던 중 오류가 발생했습니다.')
      }
    },
    async getInitialSearchUserList() {
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.userSearch
        + `?userId=${this.userId}&word=${this.keyword}&page=${this.page}`);
        this.followList = response.data;
      } catch(exp) {
        alert('사용자 정보를 가져오던 중 오류가 발생했습니다.')
      }
    },
    async getSearchUserList() {
      try{
        const response = await axios.get(SERVER.URL + SERVER.ROUTES.userSearch
        + `?userId=${this.userId}&word=${this.keyword}&page=${this.page}`);
        return response.data;
      } catch(exp) {
        alert('사용자 정보를 가져오던 중 오류가 발생했습니다.')
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
        alert('추가 정보를 가져오던 중 오류가 발생했습니다.')
      }
    },
    goToFeed(followId) {
      this.$router.push({ name: "Profile", params: { id: followId }});
      // this.$router.go({ name: "Profile", params: { id: followId }});
    },
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