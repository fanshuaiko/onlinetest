<template>
  <div>
    <div class="page-header" style="text-align: center;color: white">
      <h1>{{testName}}考试试题</h1>
    </div>
    <div class="content" style="width: 70%;margin:0 auto;background-color: white;border-radius: 20px;
    box-shadow: gainsboro 0px 0px 30px 5px;">
      <div class="questions" style="width: 50%;margin:0 auto; " v-for="item in questionList">
        <div class="panel panel-success"  >
          <div class="panel-heading" v-if="item.type == '1'">[单选题 {{item.score}}分]、{{item.question}}&nbsp;&nbsp;[参考答案]:{{item.answer}}</div>
          <div class="panel-heading" v-if="item.type == '2'">[判断题 {{item.score}}分]、{{item.question}}&nbsp;&nbsp;[参考答案]:{{item.answer}}</div>
          <div class="panel-heading" v-if="item.type == '3'">[多选题 {{item.score}}分]、{{item.question}}&nbsp;&nbsp;[参考答案]:{{item.answer}}</div>
          <div class="panel-heading" v-if="item.type == '4'">[主观题 {{item.score}}分]、{{item.question}}</div>
          <div class="panel-body" v-if="item.type != '4'">
            A.&nbsp;&nbsp;{{item.choiceA}}
          </div>
          <div class="panel-body" v-if="item.type != '4'">
            B.&nbsp;&nbsp;{{item.choiceB}}
          </div>
          <div class="panel-body" v-if="item.type == '1' || item.type == '3'">
            C.&nbsp;&nbsp;{{item.choiceC}}
          </div>
          <div class="panel-body" v-if="item.type == '1' || item.type == '3'">
            D.&nbsp;&nbsp;{{item.choiceD}}
          </div>
          <div class="panel-body" v-if="item.type == '4'">
            参考答案:&nbsp;&nbsp;{{item.answer}}
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Paper",
    data() {
      return {
        questionList: '',
        questionNum:0,
        testName:''
      }
    },

    mounted() {
      this.loadData()
    },
    methods: {
      loadData() {
        this.questionList = this.$route.query.questions
        this.testName = this.$route.query.testName
        console.log('Paper:questions::' + JSON.stringify(this.questionList))
      }
    }
  }
</script>

<style>

  @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";

  body {
    background-color: darkturquoise;
  }
</style>
