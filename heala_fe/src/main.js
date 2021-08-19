import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import Element from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "./plugins/element.js";
import "@/fontAwesomeIcon.js";
import '@/assets/css/main.css'
import VueSimpleAlert from "vue-simple-alert";

Vue.config.productionTip = false;
Vue.use(Element);
Vue.use(VueSimpleAlert);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
