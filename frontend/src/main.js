import Vue from 'vue';
import App from './App.vue';
import store from './store';
import axios from "axios";

import HomeView from "./views/Home";

Vue.config.productionTip = false;
Vue.prototype.$http = axios;

const routes = {
  '/': HomeView
};

new Vue({
  store,
  data: {
    currentRoute: window.location.pathname
  },
  computed: {
    ViewComponent() {
      return routes[this.currentRoute] || HomeView
    }
  },
  render (h) { return h(this.ViewComponent) }
}).$mount('#app');
