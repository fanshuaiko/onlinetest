import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Tests from "../views/Tests";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/tests',
      name: 'tests',
      component: Tests
    }
  ]
})
