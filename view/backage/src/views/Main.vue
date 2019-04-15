<template>
  <el-container style=" border: 1px solid #eee;height: 100%">
    <el-aside width="200px" style="background-color: white">

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
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-message"></i>阅卷</template>
          <el-menu-item-group>
            <el-menu-item index="3-1">选项</el-menu-item>
            <el-menu-item index="3-1">选项</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-setting"></i>考试分析</template>
          <el-menu-item-group>
            <template slot="title">分组一</template>
            <el-menu-item index="4-1">选项1</el-menu-item>
            <el-menu-item index="4-2">选项2</el-menu-item>
          </el-menu-item-group>
          <el-menu-item-group title="分组2">
            <el-menu-item index="4-3">选项3</el-menu-item>
          </el-menu-item-group>
          <el-submenu index="4-4">
            <template slot="title">选项4</template>
            <el-menu-item index="4-4-1">选项4-1</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
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

      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄',
      };
      return {
        tableData: Array(20).fill(item),
        username: 't10001',
        pageNum: 1,
        pageSize: 5
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
        }
      }
    }
  }
</script>

<style>
  .el-header {
    background-color: #20a0ff;
    color: white;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
  }

  .el-menu-item-group {
    background-color: #E8E8E8;
  }
</style>
