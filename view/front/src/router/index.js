import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Tests from "../views/Tests";
import Main from "../views/Main";
import Submit from "../views/Submit";

Vue.use(Router)


const router = new Router({
  routes: [
    {
      path: '/',
      name: 'login',

      component: Login
    },
    {
      path: '/tests',
      name: 'tests',
      meta:{
        requiresAuth: true
      },
      component: Tests
    },
    {
      path: '/main',
      name: 'main',
      meta:{
        requiresAuth: true
      },
      component: Main
    },
    {
      path: '/submit',
      name: 'submit',
      component: Submit
    }
  ]
});


// 导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  let token = sessionStorage.getItem('AUTHORIZATION')
  if (to.matched.some(record => record.meta.requiresAuth) && (!token || token === null)) {
    next({
      path: '/',
    })
  } else {
    next()
  }
});

export default router;
