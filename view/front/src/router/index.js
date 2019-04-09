import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Tests from "../views/Tests";
import Main from "../views/Main";
import Submit from "../views/Submit";

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
    },
    {
      path: '/main',
      name: 'main',
      component: Main
    },
    {
      path: '/submit',
      name: 'submit',
      component: Submit
    }
  ]
})
