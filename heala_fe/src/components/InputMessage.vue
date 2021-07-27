<template>
  <div>
    <div class="input-container">
      <div class="image-wrapper">
        <slot name="image">
          <img src="../assets/image/blue.jpg" alt="../assets/image/user.png">
        </slot>
      </div>
      <div class="input-wrapper">
      <slot name="input">
        <input v-model="message" type="text" placeholder="댓글 달기..">
      </slot>
      </div>
      <button :class="{ disabled:!message }" @click="$emit('write')">
        <slot name="button">작성</slot>
      </button>
    </div>
  </div>
</template>

<script>
export default {
    name: "InputMessage",
    data() {
        return {
            message: "",
        }
    },
}
</script>

<style scoped>
.input-container {
    left: 0px;
    bottom: 0px;
    right: 0px;
    position: fixed;
    font-size: 13px;
    border: 1px solid;
    padding: 10px;
}
.image-wrapper {
    width: 30px;
    height: 30px;
    border-radius: 70%;
    overflow: hidden;
    float: left;
}
.input-wrapper {
    float: left;
    margin: 5px auto auto 10px;
}
img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
input {
  border: none;
}
input:focus {
    outline: none;
}
button {
    border: none;
    background: inherit;
    float: right;
    margin: 5px auto auto auto;
    font-weight: bold;
    color: #1a8efa;
}
button.disabled {
    color: #a9d6ff;
}
button:hover {
    cursor: pointer;
}
button.disabled:hover {
    cursor: default;
}
</style>