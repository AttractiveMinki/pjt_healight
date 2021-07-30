<template>
  <div>
    <font-awesome-icon :icon="['fas', 'star']" class="icon" v-if="like" @click="$emit('cancelStar')" />
    <font-awesome-icon :icon="['far', 'star']" class="icon" v-else @click="$emit('star')" />
  </div>
</template>

<script>
export default {
    name: "Star",
    props: [ 'like' ],
}
</script>

<style scoped>
.icon {
    color: #94EC3C;
    float: left;
}
</style>