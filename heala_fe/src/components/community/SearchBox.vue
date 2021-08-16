<template>
  <div class="search-container">
    <div class="search-box">
      <font-awesome-icon :icon="['fas', 'search']" class="icon" v-if="!enter" />
      <input type="text" placeholder="검색" class="search-input"
      v-model="keyword"
      @focus="enter = true"
      @blur="enter = false"
      @keyup.enter="search">
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchBox",
  data() {
    return {
      enter: false,
      keyword: "",
    }
  },
  methods: {
    search() {
      this.$emit("search", this.keyword);
    },
  },
}
</script>

<style scoped>
.search-container {
  width: 100%;
  padding: 0px 10%;
  box-sizing: border-box;
  font-size: 13px;
}
.search-box {
  background-color: #c5c5c54b;
  border-radius: 25px;
  text-align: left;
  padding: 5px 10px;
}
.icon {
  margin: auto 2px;
}
.search-input {
  background-color: initial;
  border: none;
}
.search-input:focus {
  outline: none;
}
</style>