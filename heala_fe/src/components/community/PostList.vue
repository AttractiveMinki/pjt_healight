<template>
  <div class="article-bottom-padding">
		<div v-if="!isScrapList" v-show="!searchAllPost && !searchPostByCategory" class="post-list-title">
      {{ subCategory[selectedSubCategory] }} 게시글
    </div>
    <div v-show="searchAllPost || searchPostByCategory" class="post-list-title">
      검색결과
    </div>
		<div v-for="(article, idx) in communityArticles" :key="idx" class="post-list-item">
			<post-list-item :article="article"></post-list-item>
		</div>
		<infinite-loading :identifier="infiniteId" @infinite="infiniteHandler" spineer="waveDots">
			<div slot="no-more"></div>
			<div slot="no-results"></div>
		</infinite-loading>
	</div>
</template>

<script>
import PostListItem from "@/components/community/PostListItem";
import InfiniteLoading from "vue-infinite-loading";
import SERVER from "@/api/drf.js";
import axios from "axios";
import { mapState } from "vuex";

export default {
  name: "PostList",
  props: [ 'category', 'keyword', 'isScrapList' ],
	data() {
		return {
      userId: 1,
			communityArticles: [],
      limit: 0,
      subCategory: [ "일반", "정보", "질문", "BEST" ],
      infiniteId: 0,
      searchAllPost: false,
      searchPostByCategory: false,
    }
	},
	created() {
    this.$store.commit("GET_USERID")
    this.userId = this.$store.state.userId
    if(this.isScrapList) {
      this.getInitialScrapList();
    }
    else {
      this.$store.state.selectedSubCategory = 0;
      this.getInitialCommunityInfo();
    }
	},
  computed: {
    ...mapState([
      "selectedSubCategory",
    ]),
  },
  watch: {
    category() {
      this.initPage();
      if(this.category == 3) {
        this.$store.state.selectedSubCategory = 0;
        this.getInitialCommunityInfo();
      }
      else {
        this.getInitialPostListByCategory();
      }
      this.$emit("changeCategory");
    },
    selectedSubCategory() {
      this.initPage();
      this.$emit("changeSubCategory");
      if(this.category == 3) {
        this.getInitialCommunityInfo();
      }
      else {
        this.getInitialPostListByCategory();
      }
    },
    keyword() {
      this.initPage();
      if(!this.keyword.length) {
        this.searchPostByCategory = false;
        this.searchAllPost = false;
        if(this.category == 3) {
          this.$store.state.selectedSubCategory = 0;
          this.getInitialCommunityInfo();
        }
        else {
          this.getInitialPostListByCategory();
        }
      }
      else if(this.category == 3) {
        this.searchPostByCategory = false;
        this.searchAllPost = true;
        this.getInitialSearchPostList();
      }
      else {
        this.searchAllPost = false;
        this.searchPostByCategory = true;
        this.getInitialSearchPostListByCategory();
      }
    },
  },
	methods: {
    initPage() {
      this.limit = 0;
      this.infiniteId++;
    },
    async getInitialCommunityInfo() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}?page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
        this.communityArticles = response.data
      } catch(err) {
        alert('초기 값을 불러들일 때 오류가 발생했습니다.')
      }
    },
		async getCommunityInfo() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}?page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
        return response.data
      } catch(err) {
        alert('정보를 불러올 때 오류가 발생했습니다.')
      }
    },
    async getInitialPostListByCategory() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}category?category=${this.category}&page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
        this.communityArticles = response.data
      } catch(err) {
        alert('세부 카테고리를 불러올 때 오류가 발생했습니다.')
      }
    },
    async getPostListByCategory() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}category?category=${this.category}&page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
        return response.data
      } catch(err) {
        alert('세부 카테고리를 불러올 때 오류가 발생했습니다.')
      }
    },
    async getInitialSearchPostList() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.postSearch}?page=${this.limit}&word=${this.keyword}`)
        this.communityArticles = response.data;
        } catch(err) {
        alert('검색 결과를 가져올 때 오류가 발생했습니다.')
      }
    },
    async getInitialScrapList() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.feed}${this.userId}${SERVER.ROUTES.scrap}?page=${this.limit}`)
        this.communityArticles = response.data
      } catch(err) {
        alert('스크랩 목록을 가져올 때 오류가 발생했습니다.')
      }
    },
    async getSearchPostList() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.postSearch}?page=${this.limit}&word=${this.keyword}`)
        return response.data
      } catch(err) {
        alert('검색 결과를 가져올 때 오류가 발생했습니다.')
      }
    },
    async getScrapList() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.feed}${this.userId}${SERVER.ROUTES.scrap}?page=${this.limit}`)
        return response.data
      } catch(err) {
        alert('스크랩 목록을 가져올 때 오류가 발생했습니다.')
      }
    },
    async getInitialSearchPostListByCategory() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.postSearchCategory}?page=${this.limit}&word=${this.keyword}&category=${this.category}&subCategory=${this.$store.state.selectedSubCategory}`)
        this.communityArticles = response.data;
      } catch(err) {
        alert('검색 결과를 가져올 때 오류가 발생했습니다.')
      }
    },
    async getSearchPostListByCategory() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.postSearchCategory}?page=${this.limit}&word=${this.keyword}&category=${this.category}&subCategory=${this.$store.state.selectedSubCategory}`)
        return response.data;
      } catch(err) {
        alert('검색 결과를 가져올 때 오류가 발생했습니다.')
      }
    },
    async infiniteHandler($state) {
      const EACH_LEN = 10;
      
      this.limit += 1;
      let data;
      if(this.isScrapList) {
        data = await this.getScrapList();
      }
      else if(this.searchAllPost) {
        data = await this.getSearchPostList();
      }
      else if(this.searchPostByCategory) {
        data = await this.getSearchPostListByCategory();
      }
      else if(this.category == 3){
        data = await this.getCommunityInfo();
      }
      else {
        data = await this.getPostListByCategory();
      }
      setTimeout(() => {
        if (data.length) {
          this.communityArticles = this.communityArticles.concat(data)
          $state.loaded()

          if (data.length < EACH_LEN) {
            $state.complete()
          }
        }
        else {
          $state.complete()
        }
      }, 300)
    },
	},
	components: { PostListItem, InfiniteLoading, },
}
</script>

<style scoped>
.article-bottom-padding {
	padding-bottom: 3rem;
}
.post-list-title {
	text-align: left;
	padding: 10px 3%;
	margin-bottom: 5px;
	font-size: 15px;
}
.post-list-item {
	margin: auto;
}
hr {
	margin: 5px 0px 15px 0px;
	border-top: 1px solid #bbb;
  border-right: 0px;
  border-bottom: 0px;
  border-left: 0px;
}
</style>