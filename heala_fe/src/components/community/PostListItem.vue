<template>
  <div class="post-list-item-container">
    <router-link :to="{ name: 'PostDetail',  params: { id: article.id } }" class="text-decoration-none align-self">
      <el-row>
        <div class="post-image-wrapper">
          <img :src="imageServer + article.image" alt="" class="post-image">
        </div>
        <div class="post-content">
          <div class="text-align-start text-title">{{ article.title }}</div>
          <div class="text-content"></div>
          <div class="text-align-start text-content">{{ article.content }}</div>
          <div class="display-flex justify-content-space-between post-info">
            <span class="post-time">
              <div>
                {{ article.createdAt.substring(0, 10) }}
              </div>
            </span>
            <span class="fix-width">
              <font-awesome-icon :icon="['far', 'star']" class="padding-right small-icon"/>{{ article.likes }}
            </span>
          </div>
        </div>
      </el-row>
    </router-link>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js";
export default {
  name: "PostListItem",
  props: [ 'article' ],
  data() {
    return {
      imageServer: SERVER.IMAGE_URL,
    }
  },
}
</script>

<style scoped>
.post-list-item-container {
  font-size: 11px;
  margin: 10px 0px;
}
.text-decoration-none {
  text-decoration: none;
  color: black;
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
  font-size: 14px;
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
.post-time {
  color: gray;
}
.display-flex {
  display: flex;
}
.margin-left-10 {
  margin-left: 10%;
}
.margin-right-10 {
  margin-right: 10%;
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
.post-image-wrapper {
  width: 25%;
  position: relative;
  overflow: hidden;
  float: left;
  margin: 0px 3%;
}
.post-image-wrapper::after {
  content: "";
  display: block;
  padding-bottom: 100%;
}
.post-image {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.post-content {
  width: 70%;
  padding-left: 25%;
}
.post-info {
  padding-top: 5px;
}
</style>