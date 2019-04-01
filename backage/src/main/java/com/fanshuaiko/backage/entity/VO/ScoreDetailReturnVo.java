package com.fanshuaiko.backage.entity.VO;

/**
 * @ClassName ScoreDetailReturnVo
 * @Description 返回主观题学生答案和题目
 * @Author fanshuaiko
 * @Date 19-4-1 下午3:26
 * @Version 1.0
 **/
public class ScoreDetailReturnVo {
    private long id;
    private String testName; //考试名称
    private String questionName; //题目名称
    private String studentAnswer; //学生答案
    private String answer; //正确答案
    private int questionScore; //题目分值
    private String questionType; //题目类型
    private String flag; //0.未批改，1.已批改

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "ScoreDetailReturnVo{" +
                "id=" + id +
                ", testName='" + testName + '\'' +
                ", questionName='" + questionName + '\'' +
                ", studentAnswer='" + studentAnswer + '\'' +
                ", answer='" + answer + '\'' +
                ", questionScore='" + questionScore + '\'' +
                ", questionType='" + questionType + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}

