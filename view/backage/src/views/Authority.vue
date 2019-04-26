<template>
  <el-container style=" border: 1px solid #eee;">
    <el-aside width="200px">

      <el-header style="text-align: center; font-size: 20px;">
        ONLINETEST
      </el-header>

      <el-menu :default-openeds="['1']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>权限管理</template>
          <el-menu-item-group>
            <el-menu-item index="1-1">课程权限</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <span>{{username}}</span>
      </el-header>

      <el-main>
        <!--  查询条件-->
        <el-form :inline="true" :model="QuestionQueryTerm" class="demo-form-inline">
          <el-form-item label="教师编号">
            <el-input v-model="QuestionQueryTerm.teacherNo"></el-input>
          </el-form-item>
          <el-form-item label="教师姓名">
            <el-input v-model="QuestionQueryTerm.teacherName"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="queryByConditions">查询</el-button>
          </el-form-item>
        </el-form>

        <!--        数据列表-->
        <el-table
          :data="TeacherCourseVoList"
          style="width: 100%"
          v-loading="listLoading">
          <el-table-column
            prop="teacherNo"
            label="教师编号"
            width="300">
          </el-table-column>
          <el-table-column
            prop="teacherName"
            label="教师姓名"
            width="300">
          </el-table-column>
          <el-table-column
            label="课程权限"
            width="300">
            <template scope="scope">
              <el-select v-model="scope.row.courseIds" multiple placeholder="请选择">
                <el-option
                  v-for="item in allCourse"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column
            label="操作">
            <template scope="scope">
              <el-button type="danger" size="small" @click="saveOption(scope.row)">确认</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!--分页-->
        <el-col :span="24" class="toolbar">
          <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange"
                         :page-size="QuestionQueryTerm.pageSize"
                         :total="total"
                         style="float:right;">
          </el-pagination>
        </el-col>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
  import * as api from '../api/api';

  export default {
    name: "Main",
    data() {
      return {
        username: sessionStorage.getItem('username'),

        TeacherCourseVoList: [],//列表数据

        QuestionQueryTerm: {//查询参数
          teacherNo: '',//教师编号
          teacherName: '',//教师姓名
          pageSize: 10,//分页参数-每页显示条数
          pageNum: 1,//分页参数-显示第几页
        },

        total: 0,//总条数，

        dialogFormVisible: false,//编辑权限页面是否可见

        allCourse: [],//所有课程

        listLoading: true,//页面加载效果

      }
    },

    mounted() {
      this.loadData()
    },

    methods: {
      loadData() {
        this.queryByConditions()
        this.getAllCourse()
      },

      //多条件分页查询
      queryByConditions() {
        this.listLoading = true
        api.pageQueryCourseAuthority(this.QuestionQueryTerm).then(res => {
          if (res.status == 200 && res.data['code'] == '0') {
            this.TeacherCourseVoList = res.data['data']['list']
            this.total = parseInt(res.data['data']['total'])
          } else {
            this.$message.warning('查询操作失败')
          }
          this.listLoading = false
        })
      },

      //分页查询
      handleCurrentChange(val) {
        this.QuestionQueryTerm.pageNum = val;
        this.queryByConditions();
      },

      //获取所有课程
      getAllCourse() {
        api.getAllCourse().then(res => {
          this.allCourse = res.data['data']
          console.log('所有课程：：' + JSON.stringify(this.allCourse))
        })
      },

      //执行修改权限
      saveOption(teacherCourseVo) {
        console.log('---' + JSON.stringify(teacherCourseVo))
        api.editCourseAuthority(teacherCourseVo).then(res => {
          if (res.status == 200 && res.data['code'] == '0') {
            this.queryByConditions()//刷新列表
            this.$message.success('操作成功')
          } else {
            this.$message.error('操作失败')
          }
        })
      },

    }
  }
</script>

<style scoped>
  .el-container {
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
