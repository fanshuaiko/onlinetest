import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Main from "../views/Main";
import Tests from "../views/Tests";
import Paper from "../views/Paper";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/main',
      name: 'main',
      component: Main,
      iconCls: 'el-icon-message',//图标样式class
      children:[
        {path:'/tests',component:Tests,name:'所有考试'}
        ]
    },
    {
      path: '/paper',
      name: 'paper',
      component: Paper
    },
  ]
})
