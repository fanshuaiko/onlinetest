<template>
  <div class="content">
    <el-table
      :data="dataList"
      height="350"
      stripe
      style="width: 100%">
      <el-table-column
        prop="name"
        label="考试名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="courseName"
        label="所属课程"
        width="180">
      </el-table-column>
      <el-table-column
        prop="teacherName"
        label="任课教师">
      </el-table-column>
      <el-table-column
        prop="totalScore"
        label="总分">
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
        label="考试状态">
        <template scope="scope">
          <el-button type="primary" disabled="disabled" v-if="scope.row.status === '0'">
            未开始
          </el-button>
          <el-button type="primary"
                     v-on:click="getQuestion(scope.row)" v-else-if="scope.row.status === '1'">进行中
          </el-button>
          <el-button type="primary" disabled="disabled"
                     v-else-if="scope.row.status === '2'">已结束
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "ShowTests",

    data() {
      return {
        dataList: [],
        username: ''
      }
    },

    mounted: function () {
      this.loadData();//拿到上一个页面传的值
    },

    methods: {

      loadData() {
        var testVoList = this.$route.query.testVoList
        var username = this.$route.query.username
        this.username = username
        this.dataList = JSON.parse(JSON.stringify(testVoList))
        console.log('here is Tests:' + this.dataList)
      },

      getQuestion(test) {
        console.log('getQuestion::' + test)
        var AUTHORIZATION = this.$route.query.AUTHORIZATION
        if (AUTHORIZATION == '') {
          // alert("未登录或session过期，即将返回登录页面！")
          this.$router.push({
            path: '/'
          })
        } else {
          console.log('AUTHORIZATION::' + AUTHORIZATION)
          this.$axios.get('/front-api/test/' + test.id, {headers: {'AUTHORIZATION': AUTHORIZATION}})
            .then(res => {
              console.log(res)
              if (res.status == 200 && res.data['code'] == '0') {
                this.$router.push({
                  path: '/main',
                  query: {
                    questionList: res.data['data'],
                    test: test,
                    AUTHORIZATION: AUTHORIZATION,
                    username: this.username
                  }
                })
              } else {
                alert(res.data['message'])
              }
            })
            .catch(err => {
              console.log(err)
            })
        }
      },
    }
  }
</script>

<style scoped>


  .content {
    position: fixed;
    width: 70%;
    height: 40%;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    border-radius: 20px;
    box-shadow: darkgrey 0px 0px 30px 5px;
    text-align: center;
  }
</style>
