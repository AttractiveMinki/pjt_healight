<template>
  <div>
    <div class="input-message-container">
      <slot name="image">
        <user-image :image="image"></user-image>
      </slot>
      <div class="input-wrapper">
      <slot name="input">
        <textarea id="textarea" v-model="message" type="text" :placeholder="placeholderMsg" @input="resize"></textarea>
      </slot>
      </div>
      <button :class="{ disabled:!message }" @click="writeComment">
        <slot name="button">작성</slot>
      </button>
    </div>
  </div>
</template>

<script>
import SERVER from "@/api/drf.js";
import UserImage from "@/components/UserImage.vue";
export default {
  name: "InputMessage",
  props: [ 'placeholderMsg' ],
  data() {
    return {
      imageServer: SERVER.IMAGE_URL,
      message: "",
      image: "admin/default",
    }
  },
  created() {
    this.image = localStorage.getItem("userImage");
  },
  mounted() {
    this.$nextTick(() => {
      this.$el.setAttribute("style", "height",
      `${this.$el.scrollHeight}px`);
    });
  },
  methods: {
    writeComment() {
      this.$emit('write', this.message);
      this.message = "";
    },
    resize(event) {
      event.target.style.height = "16px";
      event.target.style.height = `${event.target.scrollHeight}px`;
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
#textarea {
  border: none;
  resize: none;
  width: 100%;
  height: 16px;
  overflow: hidden;
}
#textarea:focus {
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