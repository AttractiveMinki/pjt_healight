<template>
  <div>
    <post-header></post-header>
    <div class="post-container">
        <div class="post-title">
            {{ post.title }}
        </div>
        <div class="post-time">
            {{ post.createdAt }}
        </div>
        <div class="post-image-wrapper">
            <img :src="require(`@/assets/image/${post.image}`)" alt="" class="post-image">
        </div>
        <div class="icon-wrapper">
            <star :like="likeUI" @cancelStar="cancelStar" @star="star"></star>
            <font-awesome-icon :icon="['far', 'comment']" class="icon" />
            <font-awesome-icon :icon="['fas', 'bookmark']" v-if="scrapUI" @click="cancelScrap" class="icon icon-bookmark" />
            <font-awesome-icon :icon="['far', 'bookmark']" v-else @click="scrap" class="icon icon-bookmark" />
        </div>
        <el-row>
            <div class="post-user">
                <user-image :image="postUser.image"></user-image>
            </div>
            <div class="post-content">
                <span class="post-user-name">{{ postUser.name }}</span>
                {{ post.content }}
            </div>
        </el-row>
        <div class="comment-container">
            <comment
              v-for="comment in comments"
              v-bind="comment"
              v-bind:key="comment.id"
            ></comment>
        </div>
        <div class="rest"></div>
    </div>
    <el-row>
        <input-message @write="createComment"></input-message>
    </el-row>
  </div>
</template>

<script>
import InputMessage from "@/components/InputMessage.vue";
import PostHeader from "@/components/PostHeader.vue";
import Star from "@/components/Star.vue";
import UserImage from "@/components/UserImage.vue";
import Comment from "@/components/Comment.vue";

export default {
    name: "PostDetail",
    data() {
        return {
            likeUI: this.$store.state.postLike.like,
            scrapUI: this.$store.state.postScrap.scrap,
        }
    },
    computed: {
        post() {
            return this.$store.state.postDetail;
        },
        comments() {
            return this.$store.state.comments;
        },
        postUser() {
            return this.$store.state.postUser;
        },
    },
    created() {
        let postId = this.$route.params.id;
        this.$store.dispatch("setPostDetail", { postId });
        this.$store.dispatch("setPostComments", { postId });
        this.$store.dispatch("setPostScrap", { postId });
        this.$store.dispatch("setPostLike", { postId });
    },
    methods: {
        cancelStar() {
            this.likeUI = 0;
            this.$store.dispatch("cancelLikePost", { postId: this.post.id });
        },
        star() {
            this.likeUI = 1;
            this.$store.dispatch("likePost", { postId: this.post.id });
        },
        cancelScrap() {
            this.scrapUI = 0;
            this.$store.dispatch("cancelScrapPost", { postId: this.post.id });
        },
        scrap() {
            this.scrapUI = 1;
            this.$store.dispatch("scrapPost", { postId: this.post.id });
        },
        createComment(message) {
            this.$store.dispatch("createComment", { message });
        },
    },
    components: { InputMessage, PostHeader, Star, UserImage, Comment },
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
    width: calc(100% - 40px);
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
.comment-container {
    font-size: 13px;
    margin: 15px 0px 0px 0px;
}
.rest {
    height: 60px;
}
</style>