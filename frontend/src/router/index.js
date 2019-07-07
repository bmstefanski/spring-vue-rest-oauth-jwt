import Vue from 'vue'
import Router from 'vue-router'

import Home from '../views/Home.vue'
import OAuth2RedirectHandlerView from "../views/OAuth2RedirectHandlerView";

Vue.use(Router);

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: Home
    },
    {
      path: '/oauth2/redirect',
      component: OAuth2RedirectHandlerView
    }
  ]
});

export default router;
