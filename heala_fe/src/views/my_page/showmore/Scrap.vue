<template>
  <div>
    <Navbar :userIdentity="myIdentity" />
    <div class="scrap-list-title">저장한 게시글</div>
    <post-list :isScrapList=true></post-list>
  </div>
</template>

<script>
import Navbar from "@/components/my_page/Navbar";
import PostList from "@/components/community/PostList";

export default {
  name: "Scrap",
  data() {
    return {
      myIdentity: localStorage.getItem("userIdentity"),
    }
  },
  methods: {
    
  },
  components: {
    Navbar,
    PostList,
  },
}
</script>

<style scoped>
.scrap-list-title {
  font-size: 16px;
  text-align: left;
  font-weight: bold;
  padding-left: 20px;
}
</style>