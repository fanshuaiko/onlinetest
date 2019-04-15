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
          <el-option label="单选题" value="1"></el-option>
          <el-option label="判断题" value="2"></el-option>
          <el-option label="多选题" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="queryByConditions">查询</el-button>
      </el-form-item>
    </el-form>

    <!--    题目列表-->
    <el-collapse v-for="item in questionList" accordion>
      <el-collapse-item v-bind:title="item.question" class="panel-heading">
        <div class="panel panel-success">
          <!--        <div class="questions" style="width: 50%;margin:0 auto; " v-for="item in questionList">-->
          <div class="panel-heading" v-if="item.type == '1'">
            [单选题]、{{item.question}}&nbsp;&nbsp;[参考答案]:
            <el-select v-model="item.answer" placeholder="请选择" :disabled="disable">
              <el-option
                v-for="item in singleOptions"
                :key="item.value"
                :label="item.value"
                :value="item.value">
              </el-option>
            </el-select>
            &nbsp;&nbsp;&nbsp;&nbsp;[所属课程：{{item.courseName}}]
            <el-button type="success" size="mini" icon="el-icon-check" circle style="float: right" :disabled="disable"
                       @click="saveEdit(item)"></el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" circle style="float: right"
                       @click="deleteChoice(item.id)"></el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit" circle style="float: right"
                       @click="edit"></el-button>
          </div>
          <div class="panel-heading" v-if="item.type == '2'">
            [判断题]、{{item.question}}&nbsp;&nbsp;[参考答案]:
            <el-select v-model="item.answer" placeholder="请选择" :disabled="disable">
              <el-option
                v-for="item in judgeOptions"
                :key="item.value"
                :label="item.value"
                :value="item.value">
              </el-option>
            </el-select>
            &nbsp;&nbsp;&nbsp;&nbsp;[所属课程：{{item.courseName}}]
            <el-button type="success" size="mini" icon="el-icon-check" circle style="float: right" :disabled="disable"
                       @click="saveEdit(item)"></el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete" circle style="float: right"
                       @click="deleteChoice(item.id)"></el-button>
            <el-button type="primary" size="mini" icon="el-icon-edit" circle style="float: right"
                       @click="edit"></el-button>
          </div>
          <div class="panel-heading" v-if="item.type == '3'">
            [多选题]、{{item.question}}&nbsp;&nbsp;[参考答案]:{{item.answer}}&nbsp;&nbsp;&nbsp;&nbsp;[所属课程：{{item.courseName}}]
          </div>
          <div class="panel-heading" v-if="item.type == '4'">
            [主观题]、{{item.question}}&nbsp;&nbsp;&nbsp;&nbsp;[所属课程：{{item.courseName}}]
          </div>
          <div class="panel-body" v-if="item.type != '4'">
            <el-input placeholder="请输入内容" v-model="item.choiceA" :disabled="disable">
              <template slot="prepend">A.</template>
            </el-input>
          </div>
          <div class="panel-body" v-if="item.type != '4'">
            <el-input placeholder="请输入内容" v-model="item.choiceB" :disabled="disable">
              <template slot="prepend">B.</template>
            </el-input>
          </div>
          <div class="panel-body" v-if="item.type == '1' || item.type == '3'">
            <el-input placeholder="请输入内容" v-model="item.choiceC" :disabled="disable">
              <template slot="prepend">C.</template>
            </el-input>
          </div>
          <div class="panel-body" v-if="item.type == '1' || item.type == '3'">
            <el-input placeholder="请输入内容" v-model="item.choiceD" :disabled="disable">
              <template slot="prepend">D.</template>
            </el-input>
          </div>
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
    name: "ChoiceManager",
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

        //选项是否可编辑
        disable: true,

        //选项
        singleOptions: [{
          value: 'A',
        }, {
          value: 'B',
        }, {
          value: 'C',
        }, {
          value: 'D',
        }],

        judgeOptions: [{
          value: 'A',
        }, {
          value: 'B',
        }]

      }
    },
    methods: {
      //多条件分页查询题目
      choicePageQuery() {
        console.log('params::' + JSON.stringify(this.QuestionQueryTerm))
        api.choicePageQuery(this.QuestionQueryTerm).then(res => {
          console.log('choiceManager:choicePageQuery:res:::' + JSON.stringify(res))
          this.questionList = res.data['data']['list']
          this.QuestionQueryTerm.pageNum = res.data['data']['pageNum']
          this.QuestionQueryTerm.pageSize = res.data['data']['pageSize']
          this.total = parseInt(res.data['data']['total'])
          console.log('choiceManager:choicePageQuery:questionList:::' + JSON.stringify(this.questionList))
        })
      },

      //点击多条件查询按钮时默认查第一页
      queryByConditions() {
        console.log('params::' + JSON.stringify(this.QuestionQueryTerm))
        //默认查第一页，否则会因为没有条件查询时点到非第一页，再点条件查询会导致传的页数不是第一页而造成没有数据的情况
        this.QuestionQueryTerm.pageNum = 1
        api.choicePageQuery(this.QuestionQueryTerm).then(res => {
          console.log('choiceManager:choicePageQuery:res:::' + JSON.stringify(res))
          this.questionList = res.data['data']['list']
          this.QuestionQueryTerm.pageNum = res.data['data']['pageNum']
          this.QuestionQueryTerm.pageSize = res.data['data']['pageSize']
          this.total = parseInt(res.data['data']['total'])
          console.log('choiceManager:choicePageQuery:questionList:::' + JSON.stringify(this.questionList))
        })
      },

      //分页查询
      handleCurrentChange(val) {
        this.QuestionQueryTerm.pageNum = val;
        this.choicePageQuery();
      },

      //设置当前题目为可编辑
      edit() {
        this.disable = false
      },

      //保存题目修改
      saveEdit(item) {
        this.disable = true
        console.log('item::::' + JSON.stringify(item))
        api.updateChoice(item).then(res => {
          console.log(JSON.stringify(res))
          if (res.data['code'] == '0') {
            //刷新列表数据
            this.choicePageQuery()
            this.$message({
              message: '修改成功',
              type: 'success'
            })
          } else {
            this.$message.error('修改失败');
          }
        })
      },

      //删除当前题目
      deleteChoice(id) {
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          api.deleteChoiceById(id).then(res=>{
            if(res.data['code'] == '0'){
              this.$message({
                message: '删除成功',
                type: 'success'
              })
              //  更新列表数据
              this.choicePageQuery()
            }else{
              this.$message.error('删除失败');
            }
          })
        })
      }
    }
  }
</script>

<style>
  @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";
</style>
