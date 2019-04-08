import Vue from 'vue'
import Router from 'vue-router'
// import Home from './views/Home.vue'
import ShowTests from './views/ShowTests.vue'
import Login from "@/views/Login";


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [

    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/tests',
      name: 'tests',
      component: ShowTests
    }
  ]
})
