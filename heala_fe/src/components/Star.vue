<template>
  <div>
    <font-awesome-icon :icon="['fas', 'star']" class="icon" v-if="likeUI" @click="cancelStar" />
    <font-awesome-icon :icon="['far', 'star']" class="icon" v-else @click="star" />
  </div>
</template>

<script>
export default {
    name: "Star",
    props: [ 'like' ],
    data() {
      return {
        likeUI: this.like,
      }
    },
    methods: {
      star() {
        this.likeUI = true;
        this.$emit('star');
      },
      cancelStar() {
        this.likeUI = false;
        this.$emit('cancelStar');
      }
    },
}
</script>

<style scoped>
.icon {
    color: #94EC3C;
    float: left;
}
.icon:hover {
  cursor: pointer;
}
</style>