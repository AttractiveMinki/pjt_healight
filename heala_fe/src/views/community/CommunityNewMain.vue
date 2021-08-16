<template>
  <div class="community-main-container">
    <el-row class="border-bottom">
      <el-col :span="6">
        <div @click="category = 3" class="category-name">전체</div>
        <div v-show="category == 3" class="square red-bar"></div>
      </el-col>
      <el-col :span="6">
        <div @click="category = 0" class="category-name">운동</div>
        <div v-show="category == 0" class="square yellow-bar"></div>
      </el-col>
      <el-col :span="6">
        <div @click="category = 1" class="category-name">식단</div>
        <div v-show="category == 1" class="square orange-bar"></div>
      </el-col>
      <el-col :span="6">
        <div @click="category = 2" class="category-name">마음</div>
        <div v-show="category == 2" class="square green-bar"></div>
      </el-col>
    </el-row>
    <community-category-bar
      v-if="category != 3"
      :category="category"
      class="border-bottom">
    </community-category-bar>
    <div v-else class="total-rest"></div>
    <div class="search-box">
      <search-box></search-box>
    </div>
    <post-list :category="category"></post-list>

    <Footer />
  </div>
</template>

<script>
import CommunityCategoryBar from "@/components/community/CommunityCategoryBar"
import SearchBox from "@/components/community/SearchBox";
import PostList from "@/components/community/PostList";
import Footer from "@/components/home/Footer";

export default {
  name: "CommunityNewMain",
  data() {
    return {
      category: 3,
      subCategory: 0,
    }
  },
  components: { CommunityCategoryBar, SearchBox, PostList, Footer, },
}
</script>

<style scoped>
.community-main-container {
  font-size: 14px;
}
.border-bottom {
  border-bottom: 1px solid #b3b3b3;
}
.category-name {
  margin-bottom: 10px;
}
.square {
  width: 100%;
  height: 4px;
}
.red-bar {
  background: #F85050;
}
.yellow-bar {
  background: #FFE600;
}
.orange-bar {
  background: #FF7A00;
}
.green-bar {
  background: #94EC3C;
}
.total-rest {
  height: 36px;
}
.search-box {
  margin-top: 20px;
  margin-bottom: 10px;
}
</style>