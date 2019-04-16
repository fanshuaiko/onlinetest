<template>
  <div class="app" style="position: absolute;width:100%;height: 100%;" @mouseleave="catchMouseOut">
    <div class="question-main">
      <div class="top">
        <div class="process">
          <div class="progress-bar-background">
            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="20" aria-valuemin="0"
                 aria-valuemax="100" v-bind:style="{width: progressWidth + '%'}">
              完成{{progressWidth}}%
            </div>
          </div>
        </div>
        <div class="current-question-no">当前第{{currentQuestionNo}}题/共{{questionCount}}题</div>
        <div class="time">
          距离考试结束:{{ `${hr}: ${min}: ${sec}` }}
        </div>
      </div>
      <div class="question">
        <div class="question-type" v-if="question.type == '1'">[单选题|{{question.questionScore}}分]</div>
        <div class="question-type" v-if="question.type == '2'">[判断题|{{question.questionScore}}分]</div>
        <div class="question-type" v-if="question.type == '3'">[多选题|{{question.questionScore}}分]</div>
        <div class="question-type" v-if="question.type == '4'">[主观题|{{question.questionScore}}分]</div>
        <div class="question-content" v-if="question.type == '1'">
          <div class="question-name">{{question.question}}</div>
          <div class="choiceA">
            <input type="radio" name="SingleOptionsRadios" value="A" v-model="checkedValue">
            A.&nbsp&nbsp{{question.choiceA}}
          </div>
          <div class="choiceB">
            <input type="radio" name="SingleOptionsRadios" value="B" v-model="checkedValue">
            B.&nbsp&nbsp{{question.choiceB}}
          </div>
          <div class="choiceC">
            <input type="radio" name="SingleOptionsRadios" value="C" v-model="checkedValue">
            C.&nbsp&nbsp{{question.choiceC}}
          </div>
          <div class="choiceD">
            <input type="radio" name="SingleOptionsRadios" value="D" v-model="checkedValue">
            D.&nbsp&nbsp{{question.choiceD}}
          </div>
        </div>
        <div class="question-content" v-if="question.type == '2'">
          <div class="question-name">{{question.question}}</div>
          <div class="choiceA">
            <input type="radio" name="JudgeOptionsRadios" value="A" v-model="checkedValue">
            A.&nbsp&nbsp{{question.choiceA}}
          </div>
          <div class="choiceB">
            <input type="radio" name="JudgeOptionsRadios" value="B" v-model="checkedValue">
            B.&nbsp&nbsp{{question.choiceB}}
          </div>

        </div>
        <div class="question-content" v-if="question.type == '3'">
          <div class="question-name">{{question.question}}</div>
          <div class="choiceA">
            <input type="checkbox" name="MultipleOptionsRadios" value="A" v-model="multipleValue">
            A.&nbsp&nbsp{{question.choiceA}}
          </div>
          <div class="choiceB">
            <input type="checkbox" name="MultipleOptionsRadios" value="B" v-model="multipleValue">
            B.&nbsp&nbsp{{question.choiceB}}
          </div>
          <div class="choiceC">
            <input type="checkbox" name="MultipleOptionsRadios" value="C" v-model="multipleValue">
            C.&nbsp&nbsp{{question.choiceC}}
          </div>
          <div class="choiceD">
            <input type="checkbox" name="MultipleOptionsRadios" value="D" v-model="multipleValue">
            D.&nbsp&nbsp{{question.choiceD}}
          </div>
        </div>
        <div class="question-content" v-if="question.type == '4'">
          <div class="question-name">{{question.question}}</div>
          <div class="subjective">
            <textarea v-model="subjectiveValue" class="form-control" rows="8" placeholder="请输入答案"
                      id="subjective-answer"></textarea>
          </div>
        </div>
      </div>
      <div class="bottom">
        <div class="next-question" v-if="disableFlag==''">
          <button type="button" class="btn btn-info" v-on:click="nextQuestion()">下一题</button>
        </div>
        <div class="next-question" v-else>
          <button type="button" class="btn btn-info" v-bind:disabled="disableFlag">下一题</button>
        </div>
        <div class="submit-paper">
          <button type="button" class="btn btn-success" v-on:click="submitPaper()">提交试卷</button>
        </div>
      </div>
    </div>

    <!--弹出提示鼠标离开页面信息    -->
    <el-dialog title="鼠标监测" :visible.sync="mouseOutVisible">
      离开页面{{mouseleaveCount}}次，两次将自动提交试卷
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "Main",
    data() {
      return {
        //学号
        username: '',
        //token
        AUTHORIZATION: '',
        //倒计时时间
        day: '', hr: '', min: '', sec: '',
        //上个页面传过来的题目集合
        questionList: '',
        //考试信息实体
        test: '',
        //当前页面展示的题目
        question: '',
        //随机选取题目辅助数组
        arr: '',
        //下一题禁用标记
        disableFlag: '',
        //题目数量
        questionCount: 0,
        //当前第几题
        currentQuestionNo: 0,
        //进度条
        progressWidth: 0,
        //答题情况集合
        scoreDetailList: [],
        //选择题选中的值
        checkedValue: '',
        //多选题选中的值
        multipleValue: [],
        //主观题的答案
        subjectiveValue: '',
        //鼠标离开页面
        mouseleaveCount: 0,
        //鼠标离开页面提示信息是否展示
        mouseOutVisible:false

      }
    },
    mounted() {
      this.loadData()
      this.countdown()
      this.nextQuestion()
    },
    methods: {
      loadData() {

        var AUTHORIZATION = this.$route.query.AUTHORIZATION
        var test = this.$route.query.test
        var dataList = this.$route.query.questionList
        var xqo = JSON.stringify(dataList)
        var questions = eval('(' + xqo + ')');
        this.username = this.$route.query.username
        this.questionList = questions
        this.test = test
        this.AUTHORIZATION = AUTHORIZATION

        //题目随机辅助数组初始化
        var len = this.getJsonLength(this.questionList)//题目数量
        var array = new Array(len)
        for (let i = 0; i < len; i++) {
          array[i] = i;
        }
        this.arr = array

        this.questionCount = len

        console.log('main:AUTHORIZATION:' + JSON.stringify(AUTHORIZATION))
        console.log('main:test:' + JSON.stringify(test))
        console.log('main:dataList:' + JSON.stringify(dataList))
        console.log('main:题目数量:' + len)
      }
      ,
      countdown() {
        const end = Date.parse(new Date(this.test.endTime))
        const now = Date.parse(new Date())
        const msec = -(now - end)
        let day = parseInt(msec / 1000 / 60 / 60 / 24)
        let hr = parseInt(msec / 1000 / 60 / 60 % 24)
        let min = parseInt(msec / 1000 / 60 % 60)
        let sec = parseInt(msec / 1000 % 60)
        this.day = day
        this.hr = hr > 9 ? hr : '0' + hr
        this.min = min > 9 ? min : '0' + min
        this.sec = sec > 9 ? sec : '0' + sec
        const that = this
        setTimeout(function () {
          that.countdown()
        }, 1000)
      },
      nextQuestion() {

        this.saveAnswer(this.question)//保存上一道题的值

        var randomNum = this.getRandom(this.arr);
        if (randomNum != -1) {
          this.question = ''
          this.question = this.questionList[randomNum]
          this.currentQuestionNo++
          this.progressWidth += 100 / this.questionCount
          console.log('进度条每次增加：' + this.progressWidth)
          console.log('预期题目：：' + JSON.stringify(this.questionList[randomNum]))
          console.log('当前的题目：：' + JSON.stringify(this.question))
        } else if (randomNum == -1) {
          this.disableFlag = 'disabled'
        }
      },
      //获取json中对象的个数
      getJsonLength(json) {
        var jsonLength = 0;
        for (var i in json) {
          jsonLength++;
        }
        return jsonLength;
      },
      getRandom(arr) {
        if (arr == '') {
          return -1
        } else {
          var randomNum = Math.floor(Math.random() * arr.length);
          console.log('randomNum::' + randomNum)
          console.log('进来this.arr:::::' + arr)
          var dd = arr[randomNum]
          arr.splice(randomNum, 1)
          console.log('删除一个元素后this.arr:::::' + arr)
          console.log('随机第几题:' + dd)
          return dd
        }
      },
      submitPaper() {
        console.log('去除第一个空元素之前submitPaper:this.scoreDetailList' + JSON.stringify(this.scoreDetailList))
        this.scoreDetailList.shift()
        console.log('去除第一个空元素之后submitPaper:this.scoreDetailList' + JSON.stringify(this.scoreDetailList))
        var config = {
          headers: {'AUTHORIZATION': this.AUTHORIZATION}
        }
        this.$axios.post('/front-api/test/commit', this.scoreDetailList, config)
          .then(res => {
            if (res.status == 200 && res.data['code'] == '0') {
              this.$router.push({
                path: '/submit'
              })
            }
          }).catch(err => {
          console.log(err)
          alert("提交失败！")
        })
      }
      ,
      saveAnswer(question) {
        console.log('saveAnswer:question:' + JSON.stringify(question))
        if (question.type == '3') {
          var arr = this.multipleValue
          studentAnswer = arr.join(',')
        } else if (question.type == '4') {
          var studentAnswer = this.subjectiveValue
        } else {
          var studentAnswer = this.checkedValue
        }
        var scoredetail = new this.ScoreDetail(this.username, this.test.id, question.id, question.type, studentAnswer, question.questionScore);
        console.log('scoredetail:::' + JSON.stringify(scoredetail))
        this.scoreDetailList.push(scoredetail)
        console.log('scoreDetailList:::' + JSON.stringify(this.scoreDetailList))

      },

      ScoreDetail(studentNo, testNo, questionNo, questionType, studentAnswer, questionScore) {
        this.studentNo = studentNo
        this.testNo = testNo
        this.questionNo = questionNo
        this.questionType = questionType
        this.studentAnswer = studentAnswer
        this.questionScore = questionScore
      },

      catchMouseOut() {
        this.mouseleaveCount++
        this.mouseOutVisible = true
        //离开次数达到2次自动提交试卷
        if(this.mouseleaveCount == 2){
          this.submitPaper()
          this.$router.push({
            path: '/submit'
          })
        }
      }
    }

  }
</script>

<style>
  /*@import "http://cdn.amazeui.org/amazeui/2.7.2/css/amazeui.min.css";*/
  @import "https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css";

  .question-main {
    /*background-color: #42b983;*/
    position: absolute;
    left: 50%;
    top: 35%;
    transform: translate(-50%, -50%);
    width: 70%;
    height: 60%;
    border: 1px solid darkgrey;
  }

  .top {
    height: 50px;
    width: 100%;
    top: 0%;
    background-color: darkgray;
  }

  .process {
    position: relative;
    height: 100%;
    width: 70%;
    /*background-color: brown;*/
    float: left;
  }

  .progress-bar-background {
    position: relative;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    border-radius: 20px;
    width: 90%;
    height: 60%;
    background-color: white;
  }

  .progress-bar {

    border-radius: 20px;
    /*width: 70%;*/
    height: 100%;
    text-align: center;
  }

  .current-question-no {
    position: relative;
    height: 100%;
    width: 15%;
    /*background-color: aqua;*/
    float: left;
  }

  .time {
    position: relative;
    height: 100%;
    width: 15%;
    /*background-color: blue;*/
    float: right;
    border-left: 1px solid red;
    line-height: 100%;
  }

  .question {
    width: 100%;
    height: 610px;
    border: 1px solid darkgrey;
  }

  .question-type {
    height: 60px;
    width: 100%;
    top: 110px;
    /*background-color: darkgrey;*/
    border: 1px solid darkgrey;
    line-height: 60px;
  }

  .question-content {
    height: 550px;
    width: 100%;
    /*top: 510px;*/
    /*background-color: darkslategrey;*/
  }

  .question-name {
    height: 110px;
    width: 100%;
    /*top: 630px;*/
    /*background-color: red;*/
    line-height: 110px;
  }

  .choiceA {
    height: 110px;
    width: 100%;
    /*top: 700px;*/
    /*background-color: blueviolet;*/
    border: 1px solid darkgrey;
    line-height: 110px;
  }

  .choiceB {
    height: 110px;
    width: 100%;
    /*top: 700px;*/
    /*background-color: red;*/
    border: 1px solid darkgrey;
    line-height: 110px;

  }

  .choiceC {
    height: 110px;
    width: 100%;
    /*top: 700px;*/
    /*background-color: blueviolet;*/
    border: 1px solid darkgrey;
    line-height: 110px;

  }

  .choiceD {
    height: 110px;
    width: 100%;
    /*top: 700px;*/
    /*background-color: red;*/
    border: 1px solid darkgrey;
    line-height: 110px;

  }

  .subjective {
    height: 440px;
    width: 100%;
    /*top: 700px;*/
    /*background-color: blanchedalmond;*/
    line-height: 440px;
  }

  .bottom {
    width: 100%;
    height: 60px;
    /*background-color: black;*/
    border: 1px solid darkgrey;
  }

  .next-question {
    position: relative;
    height: 100%;
    width: 15%;
    left: 35%;
    /*background-color: blue;*/
    float: left;
    text-align: center;
    line-height: 100%;
  }

  .submit-paper {
    position: relative;
    height: 100%;
    width: 15%;
    right: 35%;
    /*background-color: darkgrey;*/
    float: right;
    text-align: center;
    line-height: 100%;
  }


</style>
