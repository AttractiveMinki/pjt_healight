<template>
  <el-row class="comment-wrapper">
    <div v-if="comment_id" class="comment-reply">
    <div class="comment-user">
        <user-image :image="commentUserImage"></user-image>
    </div>
    <div class="comment-content">
        <span class="comment-user-name">{{ commentUserName }}</span>
        {{ text }}
    </div>
    <star :like="likeUI" @cancelStar="cancelStar" @star="star" class="star"></star>
    </div>
  </el-row>
</template>

<script>
import UserImage from "@/components/UserImage";
import Star from "@/components/Star";

export default {
    name: "Comment",
    // props: [ 'id', 'text', 'likes', 'created_at', 'comment_id', 'user_id', 'post_id', 'commentUserName', 'commentUserImage' ],
    props: [ 'id', 'text', 'likes', 'created_at', 'comment_id', 'user_id', 'post_id', ],
    data() {
        return {
            // dummy
            // id: 1,
            // text: "오늘도 치즈 귀여움에 힐링 받고 갑니다.. 치즈는 사랑..♥",
            // likes: 2,
            // created_at: "07/17 13:56",
            // comment_id: "",
            // user_id: 1,
            // post_id: 1,
            commentUserName: "원웨이",
            commentUserImage: "blue.jpg",

            likeUI: 1,
        }
    },
    methods: {
        cancelStar() {
            this.likeUI = 0;
            // vuex 에게 통신 ㄱㄱ
        },
        star() {
            this.likeUI = 1;
            // vuex 에게 통신 ㄱㄱ
        },
    },
    components: { UserImage, Star, },
}
</script>

<style scoped>
.comment-wrapper {
    margin: 5px 0px;
    position: relative;
}
.comment-tab {
    width: 30px;
    height: 30px;
    float: left;
}
.comment-user {
    width: fit-content;
    overflow: hidden;
    float: left;
}
.comment-user-name {
    font-weight: bold;
}
.comment-content {
    width: calc(100% - 60px);
    margin: 0px 5px 0px 5px;
    display: inline-block;
    word-break: break-all;
    word-wrap: break-word;
}
.star {
    display: inline-block;
    position: absolute;
    top: 50%;
    transform: translate(0%, -50%);
}
</style>