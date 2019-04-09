<template>
  <div>
    <div class="question-main">
      <div class="top">
        <div class="process"></div>
        <div class="current-question-no"></div>
        <div class="time"></div>
      </div>
      <div class="question-type"></div>
      <div class="question">
        <div class="question-no"></div>
        <div class="question-content">
          <div class="choiceA"></div>
          <div class="choiceB"></div>
          <div class="choiceC"></div>
          <div class="choiceD"></div>
          <div class="subjective"></div>
        </div>
      </div>
      <div class="bottom">
        <div class="next-question">
          <button type="button" class="btn btn-info">下一题</button>
        </div>
        <div class="submit-paper">
          <button type="button" class="btn btn-success">提交试卷</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Main"
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
  }

  .top {
    height: 50px;
    width: 100%;
    top: 0%;
    background-color: blueviolet;
  }

  .process {
    position: relative;
    height: 100%;
    width: 70%;
    background-color: brown;
    float: left;
  }

  .current-question-no {
    position: relative;
    height: 100%;
    width: 15%;
    background-color: aqua;
    float: left;
  }

  .time {
    position: relative;
    height: 100%;
    width: 15%;
    background-color: blue;
    float: right;
  }

  .question-type {
    height: 60px;
    width: 100%;
    top: 110px;
    background-color: darkgrey;
  }

  .question {
    height: 400px;
    width: 100%;
    top: 510px;
    background-color: darkslategrey;
  }

  .bottom{
    width: 100%;
    height: 60px;
    background-color: blueviolet;
  }
  .next-question{
    position: relative;
    height: 100%;
    width: 15%;
    left: 35%;
    background-color: blue;
    float: left;
    text-align: center;
  }
  .submit-paper{
    position: relative;
    height: 100%;
    width: 15%;
    right: 35%;
    background-color: darkgrey;
    float: right;
    text-align: center;
  }


</style>
