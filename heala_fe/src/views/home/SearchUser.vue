<template>
  <div class="search-user-container">
    <kiwi-header>
      <template v-slot:title>유저 검색</template>
    </kiwi-header>
    <search-box @search="search"></search-box>
    <div class="result-title">검색결과</div>
    <hr>
    <follow-list v-if="keyword" :isSearchList=true :keyword="keyword"></follow-list>
  </div>
</template>

<script>
import KiwiHeader from "@/components/KiwiHeader";
import SearchBox from "@/components/community/SearchBox";
import FollowList from "@/components/my_page/FollowList";

export default {
  name: "SearchUser",
  data() {
    return {
      keyword: "",
    }
  },
  methods: {
    search(keyword) {
      this.keyword = keyword;
    }
  },
  components: {
    KiwiHeader,
    SearchBox,
    FollowList,
  }
}
</script>

<style scoped>
.result-title {
  font-weight: bold;
  font-size: 16px;
  text-align: left;
  padding: 20px 15px 5px 15px;
}
hr {
  margin: 5px 15px 5px 15px;
  border-top: 1px solid #bbb;
  border-right: 0px;
  border-bottom: 0px;
  border-left: 0px;
}
</style>