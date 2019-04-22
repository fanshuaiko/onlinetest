<template>
  <section>
    <!-- 新建按钮   -->
    <el-col :span="24" class="toolbar">
      <el-button type="primary" @click="clickNewCreateButton">新建</el-button>
    </el-col>

    <!--    数据列表-->
    <el-table
      v-loading="listLoading"
      :data="testVoList"
      height="500"
      style="width: 100%"
      @selection-change="selsChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column
        prop="name"
        label="考试名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="courseName"
        label="课程名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="teacherName"
        label="教师">
      </el-table-column>
      <el-table-column
        prop="startTime"
        label="开考时间">
      </el-table-column>
      <el-table-column
        prop="testTime"
        label="考试时长">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态">
        <template scope="scope">
          <label v-if="scope.row.status=='0'">未开始</label>
          <label v-if="scope.row.status=='1'">进行中</label>
          <label v-if="scope.row.status=='2'">已结束</label>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="250">
        <template scope="scope">
          <el-button size="small" @click="getQuestions(scope.row)">预览试卷</el-button>
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)" disabled>编辑</el-button>
          <el-button type="danger" size="small" v-if="scope.row.status=='2'" disabled>删除</el-button>
          <el-button type="danger" size="small" v-else @click="handleDel(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除{{this.sels.length}}
      </el-button>
      <el-pagination layout="total, prev, pager, next" @current-change="handleCurrentChange" :page-size="this.pageSize"
                     :total="total"
                     style="float:right;">
      </el-pagination>
    </el-col>


    <!--新增页面-->
    <el-dialog title="新增" :visible.sync="dialogAddFormVisible" width="80%">

      <!--    折叠面板  -->
      <el-collapse v-model="activeName" accordion>

        <el-form :model="addForm" label-width="100px" :rules="addFormRules" ref="addForm">

          <!--          基础信息-->
          <el-collapse-item title="基础信息" name="1">
            <el-form-item label="考试名称" prop="name">
              <el-input v-model="addForm.name" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="所属课程" prop="courseId">
              <el-select v-model="courseName" placeholder="请选择" @change="setCourseId">
                <el-option
                  v-for="item in allCourse"
                  :key="item.id"
                  :value="item.name"
                >
                </el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="参考班级" prop="classNoList">
              <el-tree
                :data="schoolTree"
                show-checkbox
                node-key="classNo"
                ref="tree"
                @check-change="getTreeCheckedKeys"
              >
              </el-tree>
            </el-form-item>

            <el-form-item label="任课教师" prop="teacherNo">
              <el-input v-model="addForm.teacherNo" auto-complete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="考试总分" prop="totalScore">
              <el-input-number v-model="addForm.totalScore" :min="0" :max="200"></el-input-number>
            </el-form-item>
            <el-form-item label="开考时间" prop="startTime">
              <div class="block">
                <el-date-picker
                  v-model="addForm.startTime"
                  type="datetime"
                  placeholder="选择开考日期时间"
                  default-time="09:00:00">
                </el-date-picker>
              </div>
            </el-form-item>
            <el-form-item label="考试时长" prop="testTime">
              <el-input-number v-model="addForm.testTime" :min="0" :max="200"></el-input-number>&nbsp;&nbsp;&nbsp;分钟
            </el-form-item>

          </el-collapse-item>

          <!--          试题信息-->
          <el-collapse-item title="试题信息" name="2">

            <!--            单选题卡片-->
            <el-card class="box-card" style="width: 20%;float: left;margin-right: 6%;">
              <div slot="header" class="clearfix">
                <span>单选题</span>
              </div>
              <el-upload
                class="upload-demo"
                action="backage-api/test/choice"
                :on-remove="handleRemoveUploadSingle"
                :before-remove="beforeRemove"
                :data="uploadQuestionType.single"
                :on-success="uploadSingleSuccess"
                :on-error="uploadFail"

              >
                <el-button size="primary" type="primary">点击上传<i class="el-icon-upload el-icon--right"></i></el-button>
              </el-upload>
              <div>
                <i class="el-icon-circle-check el-icon--right" style="color: deepskyblue"></i>
                已上传{{completeUploadCount.single}}道题目
              </div>
              选择题库随机出题数量<br>
              <!--              <el-form-item label="题库随机出题" prop="">-->
              <el-input-number v-model="addForm.singleRandomCount" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <br>
              设置题目分值<br>
              <el-input-number v-model="addForm.singleScore" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <br>
              <!--              </el-form-item>-->
              <el-button type="primary">
                <a href="/backage-api/file/single" style="color: white;text-decoration: none">
                  下载模板
                </a>
                <i class="el-icon-download el-icon--right"></i>
              </el-button>
            </el-card>

            <!--            判断题卡片-->
            <el-card class="box-card" style="width: 20%;float: left;margin-right: 6%;">
              <div slot="header" class="clearfix">
                <span>判断题</span>
              </div>
              <el-upload
                class="upload-demo"
                action="backage-api/test/choice"
                :on-remove="handleRemoveUploadjudge"
                :before-remove="beforeRemove"
                :data="uploadQuestionType.judge"
                :on-success="uploadJudgeSuccess"
                :on-error="uploadFail"
              >
                <el-button size="primary" type="primary">点击上传<i class="el-icon-upload el-icon--right"></i></el-button>
              </el-upload>
              <div>
                <i class="el-icon-circle-check el-icon--right" style="color: deepskyblue"></i>
                已上传{{completeUploadCount.judge}}道题目
              </div>
              选择题库随机出题数量
              <!--              <el-form-item label="题库随机出题" prop="">-->
              <el-input-number v-model="addForm.judgeRandomCount" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <br>
              设置题目分值<br>
              <el-input-number v-model="addForm.judgeScore" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <!--              </el-form-item>-->
              <el-button type="primary">
                <a href="/backage-api/file/judge" style="color: white;text-decoration: none">
                  下载模板
                </a>
                <i class="el-icon-download el-icon--right"></i>
              </el-button>
            </el-card>

            <!--            多选题卡片-->
            <el-card class="box-card" style="width: 20%;float: left;margin-right: 6%;">
              <div slot="header" class="clearfix">
                <span>多选题</span>
              </div>
              <el-upload
                class="upload-demo"
                action="backage-api/test/choice"
                :on-remove="handleRemoveUploadMultiple"
                :before-remove="beforeRemove"
                :data="uploadQuestionType.multiple"
                :on-success="uploadMutipleSuccess"
                :on-error="uploadFail"
              >
                <el-button size="primary" type="primary">点击上传<i class="el-icon-upload el-icon--right"></i></el-button>
              </el-upload>
              <div>
                <i class="el-icon-circle-check el-icon--right" style="color: deepskyblue"></i>
                已上传{{completeUploadCount.multiple}}道题目
              </div>
              选择题库随机出题数量
              <!--              <el-form-item label="题库随机出题" prop="">-->
              <el-input-number v-model="addForm.multipleRandomCount" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <br>
              设置题目分值<br>
              <el-input-number v-model="addForm.multipleScore" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <!--              </el-form-item>-->
              <el-button type="primary">
                <a href="/backage-api/file/multiple" style="color: white;text-decoration: none">
                  下载模板
                </a>
                <i class="el-icon-download el-icon--right"></i>
              </el-button>
            </el-card>

            <!--            主观题卡片-->
            <el-card class="box-card" style="width: 20%;float: left;">
              <div slot="header" class="clearfix">
                <span>主观题</span>
              </div>
              <el-upload
                class="upload-demo"
                action="backage-api/test/subjective"
                :on-remove="handleRemoveUploadSubjective"
                :before-remove="beforeRemove"
                :data="uploadQuestionType.subjective"
                :on-success="uploadSubjectiveSuccess"
                :on-error="uploadFail"
              >
                <el-button size="primary" type="primary">点击上传<i class="el-icon-upload el-icon--right"></i></el-button>
              </el-upload>
              <div>
                <i class="el-icon-circle-check el-icon--right" style="color: deepskyblue"></i>
                已上传{{completeUploadCount.subjective}}道题目
              </div>
              选择题库随机出题数量
              <!--              <el-form-item label="题库随机出题" prop="">-->
              <el-input-number v-model="addForm.subjectiveRandomCount" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <br>
              设置题目分值<br>
              <el-input-number v-model="addForm.subjectiveScore" :min="0" :max="50"
                               @change="countCurrentTotalScore"></el-input-number>
              <!--              </el-form-item>-->
              <el-button type="primary">
                <a href="/backage-api/file/subjective" style="color: white;text-decoration: none">
                  下载模板
                </a>
                <i class="el-icon-download el-icon--right"></i>
              </el-button>
            </el-card>
          </el-collapse-item>
        </el-form>
      </el-collapse>

      <div slot="footer" class="dialog-footer">
        当前分值：{{currentTotalScore}} 预设总分{{addForm.totalScore}}
        <el-button @click="dialogAddFormVisible = false">取消</el-button>
        <el-button type="primary" @click="checkQuestion('addForm')" :loading="addLoading">提交</el-button>
      </div>

    </el-dialog>


  </section>
</template>

<script>

  import * as api from '../api/api';

  let moment = require("moment");

  export default {
    name: "Tests",
    data() {
      return {

        //后台返回的封装好的分页数据
        testVoList: [],
        total: 0,
        pageNum: 1,
        pageSize: 10,

        username: sessionStorage.getItem('username'),

        courseName: '', //所属课程名称

        //页面等待效果
        // loading: true,
        //列表等待效果
        listLoading: true,

        sels: [],//列表选中列

        //是否显示新增页面
        dialogAddFormVisible: false,
        //新增界面数据
        addForm: {
          name: '', //考试名称
          courseId: 0, //所属课程id
          teacherNo: sessionStorage.getItem('username'), //任课教师id，也是创建考试的教师
          // teacherNo: 't10001', //任课教师id，也是创建考试的教师
          totalScore: 0, //考试总分
          status: '', //考试状态,0.未开始，1.进行中，3.已结束
          startTime: '', //开考时间
          endTime: '', //结束时间
          testTime: 0,//考试时长，单位分钟
          singleRedisId: 0, //保存在redis中的单选题id
          judgeRedisId: 0,//保存在redis中判断题id
          multipleRedisId: 0, //保存在redis中多选题id
          subjectiveRedisId: 0, //保存在redis中主观题id
          singleScore: 0, //单选题分值
          judgeScore: 0, //判断题分值
          multipleScore: 0, //多选题分值
          subjectiveScore: 0, //主观题分值
          classNoList: [], //参与考试的班级
          singleRandomCount: 0, //单选题随机出题数量
          judgeRandomCount: 0, //判断题随机出题数量
          multipleRandomCount: 0, //多选题随机出题数量
          subjectiveRandomCount: 0, //主观题题随机出题数量
        },
        //新增页面表单填写校验规则
        addFormRules: {
          name: [
            {required: true, message: '请输入考试名称', trigger: 'blur'}
          ],
          courseId: [
            {required: true, message: '请选择所属课程', trigger: 'change'}
          ],
          classNoList: [
            {required: true, message: '请选择参考班级', trigger: 'change'}
          ],
          teacherNo: [
            {required: true, message: '请选择任课教师', trigger: 'blur'}
          ],
          totalScore: [
            {required: true, message: '请输入考试总分', trigger: 'change'}
          ],
          startTime: [
            {required: true, message: '请选择开考时间', trigger: 'change'}
          ],
          testTime: [
            {required: true, message: '请输入考试时长', trigger: 'change'}
          ],
        },
        //提交时等待效果
        addLoading: false,

        //弹出新建表单手风琴效果展示第一个模块
        activeName: '1',

        uploadQuestionType: {
          single: {type: '1'},
          judge: {type: '2'},
          multiple: {type: '3'},
          subjective: {type: '4'}
        },//上传题目类型

        //用于显示已上传题目数量
        completeUploadCount: {
          single: 0,
          judge: 0,
          multiple: 0,
          subjective: 0
        },

        //所有课程
        allCourse: [],

        //当前总分
        currentTotalScore: 0,

        //学校班级层级树
        schoolTree: [
          {
            "collegeNo": 10622,
            "label": "学校",
            "children": [
              {
                "instituteNo": "i10001",
                "label": "数学与统计学院",
                "children": [{
                  "majorNo": "m10001",
                  "label": "基础数学" + "专业",
                  "children": [{
                    "classNo": "c10001",
                    "label": "1" + "班"
                  }, {
                    "classNo": "c10002",
                    "label": "2" + "班"
                  }, {
                    "classNo": "c10003",
                    "label": "3" + "班"
                  }, {
                    "classNo": "c10004",
                    "label": "4" + "班"
                  }]
                },
                  {
                    "majorNo": "m10002",
                    "label": "应用数学" + "专业",
                    "children": [{
                      "classNo": "c10005",
                      "label": "1" + "班"
                    }, {
                      "classNo": "c10006",
                      "label": "2" + "班"
                    }, {
                      "classNo": "c10007",
                      "label": "3" + "班"
                    }, {
                      "classNo": "c10008",
                      "label": "4" + "班"
                    }]
                  },
                  {
                    "majorNo": "m10003",
                    "label": "计算数学" + "专业"
                  }]
              },
              {
                "instituteNo": "i10002",
                "label": "物理与电子工程学院",
                "children": [{
                  "majorNo": "m10005",
                  "label": "物理" + "专业"
                },
                  {
                    "majorNo": "m10006",
                    "label": "电子信息工程" + "专业"
                  },
                  {
                    "majorNo": "m10007",
                    "label": "电子信息科学与技术" + "专业"
                  }]
              },
              {
                "instituteNo": "i10003",
                "label": "计算机学院"
              },
              {
                "instituteNo": "i10004",
                "label": "土木工程学院"
              }
            ]
          }
        ]

      }
    },
    mounted() {
      this.pageQueryTest()
    }
    ,
    methods: {
      //获取考试数据
      pageQueryTest() {
        console.log('Tests:pageQueryTest:AUTHORIZATION:' + sessionStorage.getItem('AUTHORIZATION'))
        console.log('Tests:pageQueryTest:username:' + sessionStorage.getItem('username'))
        var params = {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username
        }
        api.pageQueryTest(params)
          .then(res => {
            console.log('main:loadData:axios-get:' + JSON.stringify(res))
            this.testVoList = res.data.data['list']
            this.total = parseInt(res.data.data['total'])
            this.pageNum = res.data.data['pageNum']
            this.pageSize = res.data.data['pageSize']
            console.log('Tests:pageQueryTest:testVoList:' + JSON.stringify(this.testVoList))
            this.listLoading = false
          })
      },
      //批量删除选中的列
      batchRemove() {
        var ids = this.sels.map(item => item.id).toString();
        console.log('ids---------------:' + ids)
        this.$confirm('确认删除选中记录吗？', '提示', {
          type: 'warning'
        }).then(() => {
          this.listLoading = true;
          //NProgress.start();
          // let para = { ids: ids };
          api.deleteTestByIds(ids).then((res) => {
            this.listLoading = false;
            //NProgress.done();
            this.$message({
              message: '删除成功',
              type: 'success'
            });
            this.getUsers();
          });
        }).catch(() => {

        });
      },
      //删除单条
      handleDel(index, row) {
        console.log('id---------------:' + row.id)
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          this.listLoading = true;
          //NProgress.start();
          // let para = { id: row.id };
          api.deleteTestByIds(row.id).then((res) => {
            if (res.status == 200 && res.data['code'] == '0') {
              this.listLoading = false;
              //NProgress.done();
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.getUsers();
            } else {
              this.$message({
                message: res.data['message'],
                type: 'warning'
              });
            }
          });
        }).catch(() => {

        });
      },
      //如果删除了数据将触发这个方法更新列表数据
      handleCurrentChange(val) {
        this.pageNum = val;
        this.getUsers();
      },
      //获取用户列表
      getUsers() {
        this.listLoading = true
        this.pageQueryTest()
        this.listLoading = false
      },
      //监控选中列的数量
      selsChange(sels) {
        this.sels = sels;
      },

      //移除上传的题目
      handleRemoveUploadSingle() {
        this.addForm.singleRedisId = 0
        this.completeUploadCount.single = 0
        console.log('Test:handleRemoveUploadQuestion():singleRedisId::' + this.addForm.singleRedisId)
      },
      handleRemoveUploadjudge() {
        this.addForm.judgeRedisId = 0
        this.completeUploadCount.judge = 0
        console.log('Test:handleRemoveUploadQuestion():singleRedisId::' + this.addForm.judgeRedisId)
      },
      handleRemoveUploadMultiple() {
        this.addForm.multipleRedisId = 0
        this.completeUploadCount.multiple = 0
        console.log('Test:handleRemoveUploadQuestion():singleRedisId::' + this.addForm.multipleRedisId)
      },
      handleRemoveUploadSubjective() {
        this.addForm.subjectiveRedisId = 0
        this.completeUploadCount.subjective = 0
        console.log('Test:handleRemoveUploadQuestion():singleRedisId::' + this.addForm.subjectiveRedisId)
      },

      //这样写会出现异常，页面会无故多次调用这个方法，所以向上面那样分开写
      //
      // handleRemoveUploadQuestion(type) {
      //   if (type == '1') {
      //     //移除上传的单选题
      //     this.addForm.singleRedisId = 0
      //     this.completeUploadCount.single = 0
      //     console.log('Test:handleRemoveUploadQuestion():singleRedisId' + this.addForm.singleRedisId)
      //   } else if (type == '2') {
      //     //移除上传的判断题
      //     this.addForm.judgeRedisId = ''
      //   } else if (type == '3') {
      //     //移除上传的多选题
      //     this.addForm.multipleRedisId = ''
      //   } else if (type == '4') {
      //     //移除上传的主观题
      //     this.addForm.subjectiveRedisId = ''
      //   }
      // },

      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
      },

      //单选题题目上传成功
      uploadSingleSuccess(res, file) {
        if (res['code'] == '0') {
          var result = res['data']
          for (var key in result) {
            console.log('key:::' + key)
            console.log('result[key]:::' + result[key])
            this.addForm.singleRedisId = key
            this.completeUploadCount.single = result[key]
          }
        } else {
          this.uploadFail(res, file)
        }
      },
      //判断题题目上传成功
      uploadJudgeSuccess(res, file) {
        if (res['code'] == '0') {
          var result = res['data']
          for (var key in result) {
            console.log('key:::' + key)
            console.log('result[key]:::' + result[key])
            this.addForm.judgeRedisId = key
            this.completeUploadCount.judge = result[key]
          }
        } else {
          this.uploadFail(res, file)
        }
      },
      //多选题题目上传成功
      uploadMutipleSuccess(res, file) {
        if (res['code'] == '0') {
          var result = res['data']
          for (var key in result) {
            console.log('key:::' + key)
            console.log('result[key]:::' + result[key])
            this.addForm.multipleRedisId = key
            this.completeUploadCount.multiple = result[key]
          }
        } else {
          this.uploadFail(res, file)
        }
      },
      //主观题题目上传成功
      uploadSubjectiveSuccess(res, file) {
        if (res['code'] == '0') {
          var result = res['data']
          for (var key in result) {
            console.log('key:::' + key)
            console.log('result[key]:::' + result[key])
            this.addForm.subjectiveRedisId = key
            this.completeUploadCount.subjective = result[key]
          }
        } else {
          this.uploadFail(res, file)
        }
      },

      //题目上传失败
      uploadFail(res, file) {
        this.$alert(res['message'])
      },
      //点击新建按钮时，弹出新建页面并查询赋值所有课程
      clickNewCreateButton() {
        this.dialogAddFormVisible = true
        this.getAllCourse()
      },
      //获取所有课程
      getAllCourse() {
        api.getAllCourse().then(res => {
          this.allCourse = res.data['data']
          console.log('所有课程：：' + JSON.stringify(this.allCourse))
        })
      },

      //计算当前页面上的总分值
      countCurrentTotalScore() {
        this.currentTotalScore =
          (this.completeUploadCount.single + this.addForm.singleRandomCount) * this.addForm.singleScore
          + (this.completeUploadCount.judge + this.addForm.judgeRandomCount) * this.addForm.judgeScore
          + (this.completeUploadCount.multiple + this.addForm.multipleRandomCount) * this.addForm.multipleScore
          + (this.completeUploadCount.subjective + this.addForm.subjectiveRandomCount) * this.addForm.subjectiveScore
      },

      //检查当前是否有题目和相应分值
      checkQuestion(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.currentTotalScore == 0) {
              this.$notify.warning({
                title: 'Info',
                message: '题目数量或分值不能为空！',
                showClose: false
              });
            } else {
              this.submitAddForm()
            }
          }
        })
      },

      //提交新建考试表单
      submitAddForm() {
        this.addLoading = true
        //格式化日期
        this.addForm.startTime = moment(this.addForm.startTime).format('YYYY-MM-DD HH:mm:ss')
        console.log('submitAddForm:表单数据：：' + JSON.stringify(this.addForm))
        api.createTest(this.addForm).then(res => {
          console.log('submitAddForm:res::' + JSON.stringify(res))
          if (res.status == 200 && res.data['code'] == '0') {
            //关闭等待效果
            this.addLoading = false
            //成功后关闭新建页面
            this.dialogAddFormVisible = false
            this.$notify({
              title: 'Info',
              message: '成功创建一场考试！',
              type: 'success'
            })
            //更新列表
            this.getUsers()
          } else {
            this.$alert('新建考试失败')
            this.addLoading = false
          }
        }).catch(err => {
          this.$alert(err.toString())
        })
      },

      //设置班级tree选中的classNo到classNoList
      getTreeCheckedKeys() {
        var arr = this.$refs.tree.getCheckedKeys()
        arr = arr.filter(function (e) {
          return e
        })
        console.log('tree选中的值去空后：：：' + arr);
        this.addForm.classNoList = arr
      },

      //设置下拉框选中的课程的id
      setCourseId(val) {
        let obj = {}
        obj = this.allCourse.find((item) => {
          return item.name === val
        })
        this.addForm.courseId = obj.id
        console.log('this.addForm.courseId ::' + obj.id)
      },

      //跳转到试题展示页面
      getQuestions(row) {
        api.getQuestionByTestNo(row.id).then(res => {
          console.log('getQuestionByTestNo:' + JSON.stringify(res))
          if (res.status == 200 && res.data['code'] == '0') {
            var questions = res.data['data']
            this.$router.push({
              path: '/paper',
              query: {
                questions: questions,
                testName: row.name
              }
            })
          } else {
            this.$alert(res.data['message'])
          }
        })
      }
    }
  }


</script>

<style scoped>

</style>
