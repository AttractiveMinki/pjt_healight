<template>
  <div class="header-container">
      <font-awesome-icon icon="arrow-left" />
      <div class="title">
        <slot name="title">마음</slot>
      </div>
      <font-awesome-icon icon="ellipsis-v" />
      <hr>
  </div>
</template>

<script>
export default {
    name: "PostHeader",
    data() {
        return {
            
        }
    },
}
</script>

<style scoped>
.header-container {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  padding: 15px 0px;
  margin: 0px;
  box-sizing: border-box;
}
.title {
  width: 80%;
  font-weight: bold;
  display: inline-block;
}
hr {
  margin: 18px 18px 0px 18px;
}
</style>