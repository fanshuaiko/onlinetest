<template>
  <section>
    <!--  查询条件-->
    <el-form :inline="true" :model="ScoreQueryTerm" class="demo-form-inline">
      <el-form-item>
        <label style="text-align: right;font-size: 14px;color: #606266;line-height: 40px;padding: 0 12px 0 0;">专业</label>
        <el-autocomplete
          v-model="ScoreQueryTerm.majorName"
          :fetch-suggestions="likeQueryMajorName"
        ></el-autocomplete>
      </el-form-item>

      <el-form-item label="班级">
        <el-input v-model="ScoreQueryTerm.classCo"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="ScoreQueryTerm.studentName"></el-input>
      </el-form-item>
      <el-form-item label="学号">
        <el-input v-model="ScoreQueryTerm.studentNo"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="pageQueryStudentScore">查询</el-button>
      </el-form-item>
    </el-form>

    <!--  数据列表-->
    <el-table
      :data="ScoreList"
      stripe
      style="width: 100%">
      <el-table-column
        prop="testName"
        label="考试名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="studentNo"
        label="学号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="studentName"
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="majorName"
        label="专业">
      </el-table-column>
      <el-table-column
        prop="classCo"
        label="班级">
      </el-table-column>
      <el-table-column
        prop="totalScore"
        label="总分">
      </el-table-column>
    </el-table>

    <!--分页-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange"
                     :page-size="ScoreQueryTerm.pageSize"
                     :total="total"
                     style="float:right;">
      </el-pagination>
    </el-col>
  </section>
</template>

<script>

  import * as api from '../api/api';

  export default {
    name: "StudentScore",
    data() {
      return {
        //学生分数查询参数
        ScoreQueryTerm: {
          //专业名称
          majorName: '',
          //班级
          classCo: null,
          //学生姓名
          studentName: '',
          //学号
          studentNo: null,
          //
          pageSize: 10,
          //
          pageNum: 1,
        },

        //总记录数
        total: 0,

        //分数数据实体集合
        ScoreList: null,

        //模糊查询后天返回的专业名称集合
        majorNameList: [],

        //将专业名称加上符合联想组件规则的value字段后的集合
        list: []

      }
    },
    mounted() {
      this.pageQueryStudentScore()
    },
    methods: {
      //多条件分页查询分数
      pageQueryStudentScore() {
        api.pageQueryStudentScore(this.ScoreQueryTerm).then(res => {
          console.log('StudentScore:pageQueryStudentScore:res::' + JSON.stringify(res))
          if (res.status == 200 && res.data['code'] == '0') {
            this.ScoreList = res.data['data']['list']
          } else {
            this.$alert('查询失败')
          }
        })
      },

      //模糊查询专业名称
      likeQueryMajorName(queryString, cb) {
        var params = {
          name: queryString
        }
        api.likeQueryMajorName(params).then(res => {
          console.log('StudentScore:likeQueryMajorName:res:' + JSON.stringify(res))
          if (res.status == 200 && res.data['code'] == '0') {
            this.majorNameList = res.data['data']
            console.log('this.majorNameList++' + this.majorNameList)
            //将后端返回的专业名称组装成符合联想组件规则的数据
            this.list = this.majorNameList.map(item => {
              return {value: item, label: item}
            })
            console.log('--------' + JSON.stringify(this.list))
            cb(this.list);
          }
        })
      },

      //执行分页查询
      handleCurrentChange(val) {
        this.ScoreQueryTerm.pageNum = val
        this.pageQueryStudentScore()
      },

    }
  }
</script>

<style scoped>
  @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";
</style>
