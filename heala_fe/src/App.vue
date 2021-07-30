<template>
  <div id="app">
    <router-view></router-view><br><br>
    <router-link to="/profile">Profile</router-link>&nbsp;
    <router-link to="/writing">Writing</router-link>&nbsp;
    <router-link to="/healHome">Challenge</router-link>
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
html, body {
  padding: 0;
  margin: 0;
}
</style>
