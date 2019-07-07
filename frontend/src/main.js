import Vue from 'vue';
import App from "./App";
import axios from "axios"
import router from "./router";
import {NotificationService} from "./services";

Vue.config.productionTip = false;
Vue.prototype.$http = axios;
Vue.prototype.$notify = NotificationService;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
