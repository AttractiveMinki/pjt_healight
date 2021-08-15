<template>
  <div>
    <el-row class="justify-content-space-between">
      <el-col :span="6">
        <router-link :to="{ name: 'CommunityMain' }" class="text-decoration-none">전체</router-link> 
      </el-col>
      <el-col :span="6">
        <router-link :to="{ name: 'Health' }" class="text-decoration-none">운동</router-link> 
      </el-col>
      <el-col :span="6">
        <router-link :to="{ name: 'Diet' }" class="text-decoration-none selected-category">식단</router-link> 
        <div id="square"></div>
      </el-col>
      <el-col :span="6">
        <router-link :to="{ name: 'Heart' }" class="text-decoration-none">마음</router-link>
      </el-col>
    </el-row>
    <CommunityCategoryBar 
      :category="1"
    />
    <h1>식단</h1>
    <el-row>
      <el-col :span="24">hashtag</el-col>
    </el-row>
    <el-row>
      <el-col :span="24"><div class="introduce-text-align-start">
        <i class="el-icon-search"></i>
        <input type="text" class="get-input bg-gray" id="identity" maxlength="20" placeholder="    검색" required>
      </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" class="community article-bottom-padding">
        <el-col :span="12" class="text-align-start padding-1">
          <span v-if="selectedSubCategory == 0">BEST 게시글</span>
        </el-col>
        <el-col :span="12" class="text-align-end padding-1 community-title">
          <i class="margin-left-10 el-icon-arrow-right"></i>
          <span>최신 글 보기</span>
        </el-col>
        <el-col :span="24">
          <hr>
          <el-col :span="22" v-for="(article, idx) in communityArticles" :key="idx" >
            <div v-if="article.category == 1">
              <div v-if="article.subCategory == selectedSubCategory">
                <router-link :to="{ name: 'PostDetail',  id: article.id }" class="text-decoration-none align-self">
                  <el-col :span="6">
                    {{ article.image }}
                    <el-image class="margin-left-10"
                      style="width: 100%; height: 80px"
                      :src="article.image" 
                      >
                    </el-image>
                  </el-col>
                  <el-col :span="15" class="margin-left-10">
                    <div class="text-align-start text-title">{{ article.title }}</div>
                    <div class="text-align-start text-content"><i class="el-icon-user-solid"></i>{{ article.userId }}</div>
                    <div class="text-align-start text-content">{{ article.content }}</div>
                    <el-col class="display-flex justify-content-space-between">
                      <span class="community-title">
                        <div>
                          {{ article.createdAt.split('T')[0].replace('-', '/').replace('-', '/') }}
                        </div>
                        <div>
                          {{ article.createdAt.split('T')[1].split('.')[0].split(':')[0] }} : {{ article.createdAt.split('T')[1].split('.')[0].split(':')[1] }}
                        </div>
                      </span> 
                      <span class="fix-width">
                        <font-awesome-icon :icon="['far', 'star']" class="padding-right"/>{{ article.likes }}
                      </span>
                    </el-col>
                  </el-col>
                </router-link>
              </div>
            </div>
          </el-col>
        <infinite-loading @infinite="infiniteHandler" spineer="waveDots">
          <div slot="no-more"></div>
          <div slot="no-results"></div>
        </infinite-loading>
        </el-col>
      </el-col>
    </el-row> 
    <Footer />
  </div>
</template>

<script>
import InfiniteLoading from "vue-infinite-loading";
import Footer from "@/components/home/Footer"
import CommunityCategoryBar from "@/components/community/CommunityCategoryBar"
import SERVER from "@/api/drf.js"
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  name: 'diet',
  data: function () {
    return {
      communityArticles: [],
      limit: 0,
    }
  },
  components: {
    Footer,
    CommunityCategoryBar,
    InfiniteLoading,
  },
  methods: {
    async getCommunityDietInfo() {
      try {
        const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}category?category=${1}&page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
        this.communityArticles = response.data
      } catch(err) {
        console.log(err)
      }
    },
    async infiniteHandler($state) {
      const EACH_LEN = 10;
      this.limit += 1
      const response = await axios.get(`${SERVER.URL}${SERVER.ROUTES.community}category?category=${1}&page=${this.limit}&subCategory=${this.$store.state.selectedSubCategory}`)
      setTimeout(() => {
        if (response.data.length) {
          this.communityArticles = this.communityArticles.concat(response.data)
          $state.loaded() // 데이터 로딩

          if (response.data.length < EACH_LEN) {
            $state.complete()
          }
        }
        else {
          $state.complete()
        }
      }, 300)
    },
  },
  mounted: function () {
    this.getCommunityDietInfo()
    this.$store.state.selectedSubCategory = 0
  },
  computed: {
    ...mapState([
      "selectedSubCategory",
    ])
  },
  watch: {
    selectedSubCategory: function() {
      this.limit = 0
      this.getCommunityDietInfo()
      this.infiniteHandler()
    },
  },

}
</script>

<style scoped>  
  #square {
    width: 100%;
    height: 5px;
    background: #FFE600;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .community {
    border-radius: 5px;
    width: 100%;
    height: calc(58vh);
    border: 1px gray solid;
    overflow : scroll;
  }
  .justify-content-space-between {
    justify-content: space-between;
  }
  .text-align-start {
    text-align: start;
  }
  .text-align-end {
    text-align: end;
  }
  .text-title {
    font-weight: bold; 
    font-size: 13px;
    margin-bottom: 5px;
    white-space : nowrap; /* 한 줄 제한*/
    overflow : hidden; /* 넘어가는 글자 숨기기 */
    text-overflow: ellipsis; /* 말 줄임표 추가 */
  }
  .text-content {
    font-size: small;
    margin-bottom: 3px;
    overflow : hidden; 
    text-overflow: ellipsis; 
    word-wrap: break-word; /* 단어 단위로 줄 바꿈*/
    display: -webkit-box; /* 유연하게 height 증감시킬 수 있는 flex-box 형태로 변환*/
    -webkit-line-clamp: 2; /* 보여줄 줄 수 */
    -webkit-box-orient: vertical; /* 플렉스 박스의 방향 설정(가로) */
  }
  .community-title {
    font-size: 11px;
    color: gray;
  }
  .display-flex {
    display: flex;
  }
  .margin-left-10 {
    margin-left: 10%;
  }
  .padding-1 {
    padding: 1%;
  }
  .introduce-text-align-start {
    display: block;
    color: black;
    margin-left: 10%;
    text-align: start;
  }
  .get-input {
    border-width: 0px;
    border-radius: 5px;
    width: 80%;
    height: 40px;
    margin-bottom: 20px;
    padding: 0px 2px;
  }
  .bg-gray {
    background: #F0F0F0;
  }
  .fix-width {
    width: 65px;
    text-align: start;
  }
  .padding-right {
    padding-right: 5px;
  }
  .article-bottom-padding {
    padding-bottom: 3rem;
  }
</style>