<template>
  <div>
    <div class="post-container">
      <div class="post-title">
        {{ title }}
      </div>
      <div class="post-time">
        {{ createdAt.split(" ")[0] }}
      </div>
      <div class="post-image-wrapper">
        <img :src="require(`@/assets/image/${postImage}`)" alt="" class="post-image">
      </div>
      <div class="icon-wrapper">
        <star :like="postLike" @cancelStar="cancelStar" @star="star"></star>
        <font-awesome-icon :icon="['far', 'comment']" @click="newComment" class="icon" />
        <font-awesome-icon :icon="['fas', 'bookmark']" v-if="postScrap" @click="cancelScrap" class="icon icon-bookmark" />
        <font-awesome-icon :icon="['far', 'bookmark']" v-else @click="scrap" class="icon icon-bookmark" />
      </div>
      <div>
        <div class="post-user">
          <user-image :image="userImage"></user-image>
        </div>
        <div @click="elipsis ^= 1" :class="{ 'post-content': !elipsis, 'post-content-elipsis': elipsis }">
          <span class="post-user-name">{{ userName }}</span>
          {{ content }}
        </div>
        <div class="comment-open">댓글 {{ commentCount }}개 더보기</div>
      </div>
    </div>
  </div>
</template>

<script>
import Star from "@/components/Star";
import UserImage from "@/components/UserImage";

export default {
    name: "Post",
    data() {
        return {
            postId: 1,
            title: "임시 제목",
            createdAt: "21/08/08 21:19",
            postImage: "cat.jpg",
            likes: 0,
            anonymouse: 0,
            category: 1,
            subCategory: 2,
            access: 0,
            content: "기본 데이터입니다. 기본 데이터입니다. 기본 데이터입니다. 기본 데이터입니다. 기본 데이터입니다. 기본 데이터입니다.",
            userId: 2,
            userImage: "blue.jpg",
            userName: "유저 1",
            commentCount: 1,
            postLike: true,
            postScrap: true,

            elipsis: true,
        }
    },
    methods: {
        star() {

        },
        cancelStar() {

        },
        scrap() {

        },
        cancelScrap() {

        },
        newComment() {

        },
    },
    components: {
        Star,
        UserImage,
    }
}
</script>

<style scoped>
.post-container {
    font-size: 13px;
    margin: 0px 10px;
    text-align: left;
}
.post-title {
    font-size: 14px;
    font-weight: bold;
    margin: 10px 0px 10px 5px;
    width: calc(100% - 60px);
    text-align: left;
    display: inline-block;
}
.post-time {
    font-size: 11px;
    color: #999999;
    display: inline-block;
    vertical-align: bottom;
    text-align: right;
    margin: 0px 0px 10px 0px;
}
.post-image-wrapper {
    width: 100%;
    position: relative;
    overflow: hidden;
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
.icon-wrapper {
    font-size: 20px;
    height: 20px;
    margin: 5px 0px;
}
.icon {
    color: #94EC3C;
    margin: 0px 0px 0px 5px;
    float: left;
}
.icon:hover {
    cursor: pointer;
}
.icon-bookmark {
    float: right;
}
.post-user {
    width: fit-content;
    overflow: hidden;
    float: left;
}
.post-user-name {
    font-weight: bold;
}
.post-content {
    width: calc(100% - 40px);
    margin: 0px 0px 0px 5px;
    display: inline-block;
}
.post-content-elipsis {
    width: calc(100% - 40px);
    margin: 0px 0px 0px 5px;
    padding: 0px 0px 0px 5px;
    /* display: inline-block; */
    white-space: normal;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
}
.comment-open {
    width: 100%;
    margin: 4px 0px 0px 5px;
    padding: 0px 0px 0px 35px;
    box-sizing: border-box;
    font-size: 11px;
    color: #999999;
}
</style>