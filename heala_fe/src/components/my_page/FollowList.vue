<template>
  <div class="follow-list">dd</div>
</template>

<script>
export default {
    name: "FollowList",
    data() {
        return {
            userId: 1,
            followList: [],
        }
    },
    created() {
      this.userId = localStorage.getItem("userId");  
    },
    methods: {
        
    },
}
</script>

<style scoped>
.follow-list {
    position: absolute;
    top: 0px;
    z-index: 1;
    background-color: #fff;
}
</style>