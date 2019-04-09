<template>
  <div class="content">
    <table class="am-table am-table-striped am-table-hover">
      <tr>
        <th>考试id</th>
        <th>考试</th>
        <th>所属课程</th>
        <th>任课教师</th>
        <th>总分</th>
        <th>开考时间</th>
        <th>考试时长</th>
        <th>考试状态</th>
      </tr>
      <tr v-for="item in dataList">
        <td>{{item.id}}</td>
        <td>{{item.name}}</td>
        <td>{{item.courseName}}</td>
        <td>{{item.teacherName}}</td>
        <td>{{item.totalScore}}</td>
        <td>{{item.startTime}}</td>
        <td>{{item.testTime}}</td>
        <td v-if="item.status === '0'">
          <button type="button" class="am-btn am-btn-default am-round" disabled="disabled">未开始</button>
        </td>
        <td v-else-if="item.status === '1'">
          <button type="button" class="am-btn am-btn-success am-round"
                  v-on:click="getQuestion(item.id)">进行中
          </button>
        </td>
        <td v-else-if="item.status === '2'">
          <button type="button" class="am-btn am-btn-warning am-round" disabled="disabled">已结束</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
  export default {
    name: "ShowTests",

    data() {
      return {
        dataList: '',
      }
    },

    mounted: function () {
      this.loadData();//拿到上一个页面传的值
    },

    methods: {

      loadData() {
        var testVoList = this.$route.query.testVoList

        this.dataList = testVoList
        console.log('here is Tests:' + testVoList)
      },

      getQuestion(testNo) {
        console.log('getQuestion::' + testNo)
        var AUTHORIZATION = this.$route.query.AUTHORIZATION
        if (AUTHORIZATION == '') {
          // alert("未登录或session过期，即将返回登录页面！")
          this.$router.push({
            path: '/'
          })
        } else {
          console.log('AUTHORIZATION::' + AUTHORIZATION)
          this.$axios.get('/front-api/test/' + testNo, {headers: {'AUTHORIZATION': AUTHORIZATION}})
            .then(res => {
            console.log(res)
            if (res.status == 200 && res.data['code'] == '0') {
              this.$router.push({
                path: '/main',
                query: {
                  questionList: res.data['data'],
                  AUTHORIZATION: AUTHORIZATION
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
  @import "http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css";

  body {
    /*background-color: seagreen;*/
  }

  .content {
    /*background: seagreen;*/
    position: fixed;
    width: 60%;
    height: 40%;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    border-radius: 20px;
    box-shadow: #42b983 0px 0px 30px 5px;
    text-align: center;
  }
</style>
