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
        <router-link :to="{ name: 'Diet' }" class="text-decoration-none">식단</router-link> 
      </el-col>
      <el-col :span="6">
        <router-link :to="{ name: 'Heart' }" class="text-decoration-none selected-category">마음</router-link>
        <div id="square"></div>
      </el-col>
    </el-row>
    <h1>마음</h1>
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
      <el-col :span="24" class="community">
        <el-col :span="12" class="text-align-start padding-1">
          <span>BEST 게시글</span>
        </el-col>
        <el-col :span="12" class="text-align-end padding-1 community-title">
          <i class="margin-left-10 el-icon-arrow-right"></i>
          <span>최신 글 보기</span>
        </el-col>
        <el-col :span="24">
          <hr>
          <el-col :span="22" v-for="(feed, idx) in feeds" :key="idx" >
            <div v-if="feed.category == 2">
              <router-link :to="{ name: 'PostDetail',  id: feed.id }" class="text-decoration-none align-self">
                <el-col :span="6">
                  <!-- {{ feed.image }} -->
                  <el-image class="margin-left-10"
                    style="width: 100%; height: 80px"
                    :src="feed.image" 
                    >
                  </el-image>
                </el-col>
                <el-col :span="15" class="margin-left-10">
                  
                  <div class="text-align-start text-title">{{ feed.title }}</div>
                  <div class="text-align-start text-content"><i class="el-icon-user-solid"></i>{{ feed.user_id }}</div>
                  <div class="text-align-start text-content">{{ feed.content }}</div>
                  <el-col class="display-flex justify-content-space-between"><span class="community-title">{{ feed.created_at }}</span> <span class="fix-width"><font-awesome-icon :icon="['far', 'star']" class="padding-right"/>{{ feed.likes }}</span></el-col>
                </el-col>
              </router-link>
            </div>
          </el-col>
        </el-col>
      </el-col>
    </el-row> 
    <Footer />
  </div>
</template>

<script>
import Footer from "@/components/home/Footer"
import { mapState } from "vuex"

export default {
  name: 'heart',
  components: {
    Footer,
  },
  computed: {
    ...mapState([
      "feeds",
    ])
  },  
}
</script>

<style scoped>  
  #square {
    width: 100%;
    height: 5px;
    background: #94EC3C;
  }
  .text-decoration-none {
    text-decoration: none;
    color: black;
  }
  .justify-content-space-between {
    justify-content: space-between;
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
  .community {
    margin-left: 10%;
    border-radius: 5px;
    /* border-width: 0px; */
    width: 80%;
    height: 1000px;
    border: 1px gray solid;
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
  .padding-1 {
    padding: 1%;
  }
  .community-title {
    font-size: 11px;
    color: gray;
  }
  .margin-left-10 {
    margin-left: 10%;
  }
  .display-flex {
    display: flex;
  }
  .fix-width {
    width: 65px;
    text-align: start;
  }
  .padding-right {
    padding-right: 5px;
  }
</style>