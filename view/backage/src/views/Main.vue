<template>
  <el-container style=" border: 1px solid #eee;">
    <el-aside width="200px">

      <el-header style="text-align: center; font-size: 20px;">
        ONLINETEST
      </el-header>

      <el-menu :default-openeds="['1']" @select="handleSelect">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>考试</template>
          <el-menu-item-group>
            <el-menu-item index="1-1">所有考试</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>题目管理</template>
          <el-menu-item-group>
            <el-menu-item index="2-1">选择题管理</el-menu-item>
            <el-menu-item index="2-2">主观题管理</el-menu-item>
            <el-menu-item index="2-3">题目上传</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-message"></i>阅卷</template>
          <el-menu-item-group>
            <el-menu-item index="3-1">批改试卷</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-setting"></i>考试分析</template>
          <el-menu-item-group>
            <el-menu-item index="4-1">分数查询</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
<!--        <el-dropdown>-->
<!--          <i class="el-icon-setting" style="margin-right: 15px"></i>-->
<!--          <el-dropdown-menu slot="dropdown">-->
<!--            <el-dropdown-item>查看</el-dropdown-item>-->
<!--            <el-dropdown-item>新增</el-dropdown-item>-->
<!--            <el-dropdown-item>删除</el-dropdown-item>-->
<!--          </el-dropdown-menu>-->
<!--        </el-dropdown>-->
        <span>{{username}}</span>
      </el-header>

      <el-main>
        <el-col :span="24" class="content-wrapper">
          <transition name="fade" mode="out-in">
            <router-view></router-view>
          </transition>
        </el-col>
      </el-main>


    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: "Main",
    data() {
      return {
        username: sessionStorage.getItem('username'),
      }
    },

    mounted() {
      this.loadData()
    },

    methods: {
      loadData() {
        // var AUTHORIZATION = this.$route.query.AUTHORIZATION

      },
      handleSelect(key, keyPath) {
        switch (key) {
          case '1-1':
            this.$router.push('/tests');
            break;
          case '2-1':
            this.$router.push('/choiceManager');
            break;
          case '2-2':
            this.$router.push('/subjectiveManager');
            break;
          case '2-3':
            this.$router.push('/uploadQuestion');
            break;
          case '3-1':
            this.$router.push('/markPaper');
            break;
          case '4-1':
            this.$router.push('/studentScore');
            break;
        }
      }
    }
  }
</script>

<style scoped>
  .el-container{
    height: 800px;
  }
  .el-header {
    background-color: #20a0ff;
    color: white;
    line-height: 60px;
  }

  .el-aside {
    color: #eef1f6;
    background-color: #eef1f6;
  }

  .el-menu-item-group {
    background-color: #eef1f6;
  }
</style>
