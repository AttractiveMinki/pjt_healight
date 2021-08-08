<template>
  <div class="header-container">
      <font-awesome-icon icon="arrow-left" />
      <div class="title">
        <slot name="title">마음</slot>
      </div>
      <div class="ellipsis">
        <slot name="ellipsis"></slot>
      </div>
      <hr>
  </div>
</template>

<script>
export default {
    name: "KiwiHeader",
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
  /* border-bottom-color: darkgray; */
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
.ellipsis {
  width: 5px;
  display: inline-block;
}
hr {
  margin: 18px 18px 0px 18px;
  border-top: 1px solid #bbb;
  border-right: 0px;
  border-bottom: 0px;
  border-left: 0px;
}
</style>