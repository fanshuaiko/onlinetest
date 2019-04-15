<template>
  <section>
    <!--  查询条件-->
    <el-form :inline="true" :model="QuestionQueryTerm" class="demo-form-inline">
      <el-form-item label="题目">
        <el-input v-model="QuestionQueryTerm.question"></el-input>
      </el-form-item>
      <el-form-item label="所属课程">
        <el-input v-model="QuestionQueryTerm.courseName"></el-input>
      </el-form-item>
      <el-form-item label="题目类型">
        <el-select v-model="QuestionQueryTerm.type" placeholder="题目类型">
          <el-option label="主观题" value="4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="queryByConditions">查询</el-button>
      </el-form-item>
    </el-form>

    <!--    题目列表-->
    <el-collapse accordion v-for="item in questionList">
      <el-collapse-item v-bind:title="item.question" class="panel-heading">
        <div class="panel panel-success">
          <!--        <div class="questions" style="width: 50%;margin:0 auto; " v-for="item in questionList">-->
          <div class="panel-heading" v-if="item.type == '4'">[主观题]、{{item.question}}&nbsp;&nbsp;&nbsp;&nbsp;[所属课程：{{item.courseName}}]</div>

          <div class="panel-body" v-if="item.type == '4'">
            参考答案:&nbsp;&nbsp;{{item.answer}}
          </div>
        </div>
        <!--        </div>-->

      </el-collapse-item>
    </el-collapse>

    <!--分页-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange"
                     :page-size="QuestionQueryTerm.pageSize"
                     :total="total"
                     style="float:right;">
      </el-pagination>
    </el-col>
  </section>
</template>



<script>
  import * as api from '../api/api';

  export default {
    name: "SubjectiveManager",
    mounted() {
      this.queryByConditions()
    },
    data() {
      return {
        //查询参数
        QuestionQueryTerm: {
          question: '',
          courseName: '',
          type: '',
          pageSize: 10,
          pageNum: 1
        },

        //题目集合
        questionList: '',

        //总记录数
        total: 0,

      }
    },
    methods: {
      //多条件分页查询题目
      subjectivePageQuery() {
        console.log('params::' + JSON.stringify(this.QuestionQueryTerm))
        api.subjectivePageQuery(this.QuestionQueryTerm).then(res => {
          console.log('subjectiveManager:subjectivePageQuery:res:::' + JSON.stringify(res))
          this.questionList = res.data['data']['list']
          this.QuestionQueryTerm.pageNum = res.data['data']['pageNum']
          this.QuestionQueryTerm.pageSize = res.data['data']['pageSize']
          this.total = parseInt(res.data['data']['total'])
          console.log('subjectiveManager:subjectivePageQuery:questionList:::' + JSON.stringify(this.questionList))
        })
      },

      //点击多条件查询按钮时默认查第一页
      queryByConditions() {
        console.log('params::' + JSON.stringify(this.QuestionQueryTerm))
        //默认查第一页，否则会因为没有条件查询时点到非第一页，再点条件查询会导致传的页数不是第一页而造成没有数据的情况
        this.QuestionQueryTerm.pageNum = 1
        api.subjectivePageQuery(this.QuestionQueryTerm).then(res => {
          console.log('subjectiveManager:queryByConditions:res:::' + JSON.stringify(res))
          this.questionList = res.data['data']['list']
          this.QuestionQueryTerm.pageNum = res.data['data']['pageNum']
          this.QuestionQueryTerm.pageSize = res.data['data']['pageSize']
          this.total = parseInt(res.data['data']['total'])
          console.log('subjectiveManager:queryByConditions:questionList:::' + JSON.stringify(this.questionList))
        })
      },

      //分页查询
      handleCurrentChange(val) {
        this.QuestionQueryTerm.pageNum = val;
        this.subjectivePageQuery();
      },

    }
  }
</script>

<style>
  @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";
</style>
