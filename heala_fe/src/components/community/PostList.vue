<template>
  <div class="article-bottom-padding">
    {{ infiniteId }}
		<!-- <el-col :span="12" class="text-align-start padding-1">
			<span>BEST 게시글</span>
		</el-col> -->
		<div class="post-list-title">{{ subCategory[selectedSubCategory] }} 게시글</div>
		<!-- <el-col :span="12" class="text-align-end padding-1 community-title">
			<i class="margin-left-10 el-icon-arrow-right"></i>
			<span>최신 글 보기</span>
		</el-col> -->
			<!-- <hr> -->
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
  props: [ 'category' ],
	data() {
		return {
			communityArticles: [],
      limit: 0,
      subCategory: [ "BEST", "일반", "정보", "질문", "익명" ],
      infiniteId: 0,
    }
	},
	created() {
		this.getInitialCommunityInfo();
	},
  computed: {
    ...mapState([
      "selectedSubCategory",
    ]),
  },
  watch: {
    category() {
      this.limit = 0;
      this.infiniteId++;
      if(this.category == 3) {
        this.$store.state.selectedSubCategory = 0;
        this.getInitialCommunityInfo();
      }
      else {
        this.getInitialPostListByCategory();
      }
    },
    selectedSubCategory() {
      this.limit = 0;
      this.infiniteId++;
      this.getInitialPostListByCategory();
    }
  },
	methods: {
    async getInitialCommunityInfo() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}?page=${this.limit}`)
        this.communityArticles = response.data
      } catch(err) {
        console.log(err)
      }
    },
		async getCommunityInfo() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}?page=${this.limit}`)
        return response.data
      } catch(err) {
        console.log(err)
      }
    },
    async getInitialPostListByCategory() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}category?category=${this.category}&page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
        this.communityArticles = response.data
      } catch(err) {
        console.log(err)
      }
    },
    async getPostListByCategory() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}category?category=${this.category}&page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
        return response.data
      } catch(err) {
        console.log(err)
      }
    },
    async infiniteHandler($state) {
      const EACH_LEN = 10;
      
      this.limit += 1;
      let data;
      if(this.category == 3){
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