<template>
  <section>
    <!--    -->
    <el-col :span="24" class="toolbar">
      <el-button type="primary" @click="handleAdd">新建</el-button>
    </el-col>

    <!--    数据列表-->
    <el-table
      v-loading="loading"
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
      <el-table-column label="操作" width="150">
        <template scope="scope">
          <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
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
    <el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="addForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="addForm.sex">
            <el-radio class="radio" :label="1">男</el-radio>
            <el-radio class="radio" :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="addForm.age" :min="0" :max="200"></el-input-number>
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker type="date" placeholder="选择日期" v-model="addForm.birth"></el-date-picker>
        </el-form-item>
        <el-form-item label="地址">
          <el-input type="textarea" v-model="addForm.addr"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
      </div>
    </el-dialog>

  </section>
</template>

<script>
  export default {
    name: "Tests",
    data() {
      return {
        //后台返回的封装好的分页数据
        testVoList: [],
        total: 0,
        pageNum: 1,
        pageSize: 5,

        username: 't10001',

        //页面等待效果
        loading: true,
        //列表等待效果
        listLoading: false,

        sels: [],//列表选中列

        addFormVisible: false,//新增界面是否显示

        //新增界面数据
        addForm: {
          name: '',
          sex: -1,
          age: 0,
          birth: '',
          addr: ''
        },
        //新增页面表单填写校验规则
        addFormRules: {
          name: [
            { required: true, message: '请输入姓名', trigger: 'blur' }
          ]
        },
        //提交时等待效果
        addLoading:false
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
        this.$axios.get('backage-api/test/test/' + this.pageNum + '/' + this.pageSize + '/' + this.username)
          .then(res => {
            console.log('main:loadData:axios-get:' + JSON.stringify(res))
            this.testVoList = res.data.data['list']
            this.total = parseInt(res.data.data['total'])
            this.pageNum = res.data.data['pageNum']
            this.pageSize = res.data.data['pageSize']
            console.log('Tests:pageQueryTest:testVoList:' + JSON.stringify(this.testVoList))
            this.loading = false
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
          this.$axios.delete('backage-api/test/test/' + ids).then((res) => {
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
        console.log('ids---------------:' + row.id)
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          this.listLoading = true;
          //NProgress.start();
          // let para = { id: row.id };
          this.$axios.delete('backage-api/test/test/' + row.id).then((res) => {
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
      //显示新增界面
      handleAdd() {
        this.addFormVisible = true;
        this.addForm = {
          name: '',
          sex: -1,
          age: 0,
          birth: '',
          addr: ''
        };
      },
      //提交新建的考试
      addSubmit: function () {
        this.$refs.addForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              this.addLoading = true;
              //NProgress.start();
              let para = Object.assign({}, this.addForm);
              para.birth = (!para.birth || para.birth == '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd');
              addUser(para).then((res) => {
                this.addLoading = false;
                //NProgress.done();
                this.$message({
                  message: '提交成功',
                  type: 'success'
                });
                this.$refs['addForm'].resetFields();
                this.addFormVisible = false;
                this.getUsers();
              });
            });
          }
        });
      },
    }
  }


</script>

<style scoped>

</style>
