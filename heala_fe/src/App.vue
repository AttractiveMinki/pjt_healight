<template>
  <div id="app">
    <router-link :to="{ name: 'signup' }">회원가입</router-link>
    <br>
    <router-link :to="{ name: 'login' }">로그인</router-link>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: "app",
  components: {
  },
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
