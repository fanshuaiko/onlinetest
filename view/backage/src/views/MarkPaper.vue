<template>
  <section>
    <!--    数据展示列表-->
    <el-table
      :data="paperStatusList"
      :v-loading="listLoading"
      stripe
      style="width: 100%">
      <el-table-column
        prop="testName"
        label="考试名称"
        width="300">
      </el-table-column>
      <el-table-column
        prop="choiceStatus"
        label="选择题批改状态"
        width="300">
        <template scope="scope">
          <label v-if="scope.row.choiceStatus=='0'">
            <el-button type="primary" @click="markChoice(scope.row.testNo)">点击批改</el-button>
          </label>
          <label v-if="scope.row.choiceStatus=='1'">
            <el-button type="info" plain disabled>已批改</el-button>
          </label>
        </template>
      </el-table-column>
      <el-table-column
        prop="subjectiveStatus"
        label="主观题批改状态">
        <template scope="scope">
          <label v-if="scope.row.subjectiveStatus=='0'">
            <el-button type="primary" @click="markSubjective(scope.row.testNo)">点击批改</el-button>
          </label>
          <label v-if="scope.row.subjectiveStatus=='1'">
            <el-button type="info" plain disabled>已批改</el-button>
          </label>
        </template>
      </el-table-column>
      <el-table-column
        label="考试分析">
        <template scope="scope">
          <label v-if="scope.row.choiceStatus=='1' && scope.row.subjectiveStatus=='1'">
            <el-button type="primary" @click="getTestAnalyzeData(scope.row.testNo)">点击查看考试分析</el-button>
          </label>
          <label v-else>
            <el-button type="info" plain disabled>点击查看考试分析</el-button>
          </label>
        </template>
      </el-table-column>
    </el-table>

    <!--    分页-->
    <el-col :span="24" class="toolbar">
      <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange" :page-size="pageSize"
                     :total="total"
                     style="float:right;">
      </el-pagination>
    </el-col>

    <!--        主观题批改界面-->
    <el-dialog title="主观题批改" :visible.sync="dialogMarkSubjectiveVisible">
      <div class="panel panel-success">
        <div class="panel-heading">[题目]:{{ScoreDetail.questionName}}</div>
        <li class="list-group-item list-group-item-info">
          [学生答案]:
          <br>
          <hr>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          {{ScoreDetail.studentAnswer}}
        </li>
        <li class="list-group-item list-group-item-warning">
          [参考答案]:
          <br>
          <hr>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          {{ScoreDetail.answer}}
        </li>
        <li class="list-group-item list-group-item-danger">
          [评分]
          <div class="block">
            <el-slider
              v-model="score"
              show-input
              :step="1"
              show-stops
              :max="ScoreDetail.questionScore"
            >
            </el-slider>
          </div>
        </li>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogMarkSubjectiveVisible = false">取消</el-button>
        <el-button type="primary" @click="nextQuestion(ScoreDetail.id)">下一题</el-button>
      </div>
    </el-dialog>

    <el-dialog title="考试分析" :visible.sync="dialogTestAnalyzeVisible">

      <!--      饼状图-->
      <el-card class="box-card" style="width: 45%;height: 50%;float: left;margin-right: 4%;margin-left: 3%">
        <div slot="header" class="clearfix">
          <span>及格与未及格人数对比图&nbsp;&nbsp;[总人数:{{testAnalyze.studentCount}}]</span>
        </div>
        <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
        <div ref="pieChart" style="width: 300px;height: 200px"></div>
          <span>及格率:{{testAnalyze.passRate}}</span>
      </el-card>

      <!--      柱状图-->
      <el-card class="box-card" style="width: 45%;height: 50%;">
        <div slot="header" class="clearfix">
          <span>各分数数据柱状图</span>
        </div>
        <div ref="columnChart" style="width: 300px;height: 200px"></div>
        <span>满分:{{testAnalyze.totalScore}}</span>
      </el-card>
    </el-dialog>
  </section>
</template>

<script>
  //  自定义api接口
  import * as api from '../api/api';
  //echarts图表组件
  import echarts from 'echarts';


  export default {
    name: "MarkPaper",
    data() {
      return {
        //页面加载效果
        listLoading: false,

        //数据
        paperStatusList: [],

        //分页参数
        pageNum: 1,
        pageSize: 10,
        total: 0,

        //主观题批改页面是否显示
        dialogMarkSubjectiveVisible: false,

        //当前批改的主观题信息实体
        ScoreDetail: '',
        //当前题目学生得分
        score: 0,
        //正在批改的题目所属的考试编号
        currentTestNo: 0,

        //考试分析数据实体
        testAnalyze: '',
        //考试分析界面是否显示
        dialogTestAnalyzeVisible: false,

      }
    },
    mounted() {
      this.loadData()
    },
    methods: {
      loadData() {
        this.pageQueryPaperStatus()
      },

      //如果删除了数据将触发这个方法更新列表数据
      handleCurrentChange(val) {
        this.pageNum = val;
        this.pageQueryPaperStatus();
      },

      //分页查询PaperStatus
      pageQueryPaperStatus() {
        this.listLoading = true
        var params = {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
        api.pageQueryPaperStatus(params).then(res => {
            console.log('MarkPaper:pageQueryPaperStatus:res::' + JSON.stringify(res))
            if (res.status == 200 && res.data['code'] == '0') {
              this.paperStatusList = res.data['data']['list']
            } else {
              this.$alert(res.data['message'])
            }
          }
        )
        this.listLoading = false
      },

      //点击批改主观题
      markSubjective(testNo) {
        //打开批改界面
        this.dialogMarkSubjectiveVisible = true
        //当前题目所属考试
        this.currentTestNo = testNo
        //打开界面后显示题目
        this.queryScoreDetail()
      },

      //批改选择题
      markChoice(testNo) {
        api.markChoice(testNo).then(res => {
          console.log('MarkPaper:markChoice:res::' + JSON.stringify(res))
          if (res.status == 200 && res.data['code'] == '0') {
            this.$alert('批改成功')
            //刷新列表
            this.pageQueryPaperStatus()
          } else {
            this.$alert(res.data['message'])
          }
        })
      },

      //点击下一题
      nextQuestion(id) {
        this.saveStudentScoreDetail(id)
        this.queryScoreDetail()
        this.score = 0
      },

      //保存当前批改的题目
      saveStudentScoreDetail(id) {
        var params = {
          id: id,
          score: this.score
        }
        api.saveStudentScoreDetail(params).then(res => {
          if (res.status == 200 && res.data['code'] == '0') {
            this.$message.success('当前题目批改成功')
          } else {
            this.$alert(res.data['message'])
          }
        })
      },

      //查询未批改的一道主观题
      queryScoreDetail() {
        api.queryScoreDetail(this.currentTestNo).then(res => {
          console.log('MarkPaper:queryScoreDetail:res::' + JSON.stringify(res))
          if (res.status == 200 && res.data['code'] == '0') {
            //如果为空说明已全部批改
            if (res.data['data'] == null) {
              this.$alert(res.data['message'])
              //刷新列表
              this.pageQueryPaperStatus()
              //关闭批改主观题页面
              this.dialogMarkSubjectiveVisible = false
            } else {
              this.ScoreDetail = res.data['data']
            }
            console.log('this.ScoreDetail::' + JSON.stringify(this.ScoreDetail))
          } else {
            this.$alert(res.data['message'])
          }
        })
      },

      //获取考试分析数据
      getTestAnalyzeData(testNo) {
        api.getTestAnalyzeData(testNo).then(res => {
          console.log('MarkPaper:getTestAnalyzeData:res::' + JSON.stringify(res))
          if (res.status == 200 && res.data['code'] == '0') {
            this.testAnalyze = res.data['data']
            this.dialogTestAnalyzeVisible = true
            this.drawPieChart()
            this.drawColumnChart()
          } else if (res.data['code'] != '0') {
            this.$alert(res.data['message'])
          }
        })
      },

      //圆饼图
      drawPieChart() {
        console.log('++' + JSON.stringify(this.testAnalyze))
        // 基于准备好的dom，初始化echarts实例
        var myPieChart = echarts.init(this.$refs.pieChart);
        //绘制图表
        myPieChart.setOption({
          tooltip: {},
          series: [
            {
              name: '及格与不及格分布图',
              type: 'pie',
              radius: '40%',
              data: [
                {value: this.testAnalyze.passCount, name: '及格人数'},
                {value: this.testAnalyze.unPassCount, name: '不及格人数'},
              ]
            }
          ]
        })
      },

      //柱状图
      drawColumnChart() {
        var columnChart = echarts.init(this.$refs.columnChart);
        columnChart.setOption({
          tooltip: {},
          xAxis: {
            data: ["总分", "最高分", "平均分", "最低分"]
          },
          yAxis: {},
          series: [{
            name: '分数',
            type: 'bar',
            data: [this.testAnalyze.totalScore, this.testAnalyze.maxScore,
              this.testAnalyze.averageScore, this.testAnalyze.minScore]
          }]
        });
      },
    }
  }
</script>

<style scoped>
  @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";

</style>
