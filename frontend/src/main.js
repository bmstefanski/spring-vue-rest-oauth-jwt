import Vue from 'vue';
import axios from "./axios"
import {NotificationService} from "./services";

import OAuth2RedirectHandlerView from "./views/OAuth2RedirectHandlerView";
import HomeView from "./views/Home";

Vue.config.productionTip = false;
Vue.prototype.$http = axios;
Vue.prototype.$notify = NotificationService;

export const routes = {
  '/': HomeView,
  '/oauth2/redirect': OAuth2RedirectHandlerView
};

new Vue({
  data: {
    currentRoute: window.location.pathname
  },
  computed: {
    ViewComponent() {
      return routes[this.currentRoute] || HomeView
    }
  },
  render(h) {
    return h(this.ViewComponent)
  }
}).$mount('#app');

window.addEventListener('popstate', () => {
  app.currentRoute = window.location.pathname
});
