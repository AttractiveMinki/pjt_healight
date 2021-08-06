<template>
  <div>
    <div class="input-message-container">
      <slot name="image">
        <user-image :image="loginUser.image"></user-image>
      </slot>
      <div class="input-wrapper">
      <slot name="input">
        <input v-model="message" type="text" :placeholder="placeholderMsg">
      </slot>
      </div>
      <button :class="{ disabled:!message }" @click="writeComment">
        <slot name="button">작성</slot>
      </button>
    </div>
  </div>
</template>

<script>
import UserImage from "@/components/UserImage.vue";
export default {
    name: "InputMessage",
    props: [ 'placeholderMsg' ],
    data() {
        return {
            message: "",
        }
    },
    computed: {
      loginUser() {
        return this.$store.state.loginUser;
      }
    },
    methods: {
      writeComment() {
        this.$emit('write', this.message);
        this.message = "";
      },
    },
    components: { UserImage },
}
</script>

<style scoped>
.input-message-container {
    left: 0px;
    bottom: 0px;
    right: 0px;
    position: fixed;
    font-size: 13px;
    border: 1px solid;
    padding: 10px;
    background-color: #fff;
}
.input-wrapper {
  float: left;
  margin: 5px auto auto 10px;
  width: calc(100% - 90px);
}
input {
  border: none;
  width: 100%;
  word-break: break-all;
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