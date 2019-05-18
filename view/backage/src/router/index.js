import Vue from 'vue'
import Router from 'vue-router'
import Login from "../views/Login";
import Main from "../views/Main";
import Tests from "../views/Tests";
import Paper from "../views/Paper";
import ChoiceManager from "../views/ChoiceManager";
import SubjectiveManager from "../views/SubjectiveManager";
import UploadQuestion from "../views/UploadQuestion";
import MarkPaper from "../views/MarkPaper";
import StudentScore from "../views/StudentScore";
import Authority from "../views/Authority";

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/main',
      name: 'main',
      meta:{
        requiresAuth: true
      },
      component: Main,
      iconCls: 'el-icon-message',//图标样式class
      children: [
        {path: '/tests', component: Tests, name: '所有考试'},
        {path: '/choiceManager', component: ChoiceManager, name: '选择题管理'},
        {path: '/subjectiveManager', component: SubjectiveManager, name: '主观题管理'},
        {path: '/uploadQuestion', component: UploadQuestion, name: '题目上传'},
        {path: '/markPaper', component: MarkPaper, name: '试卷批改'},
        {path: '/studentScore', component: StudentScore, name: '分数查询'},
      ]
    },
    {
      path: '/paper',
      name: 'paper',
      meta:{
        requiresAuth: true
      },
      component: Paper
    },
    {
      path: '/authority',
      name: '权限管理',
      component: Authority
    },
  ]
})


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
