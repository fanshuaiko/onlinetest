package com.fanshuaiko.backage.entity;

import java.util.Date;
/**
 * @ClassName ScoreDetail
 * @Description 详细得分实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:40
 * @Version 1.0
 **/
public class ScoreDetail {
    private Long id;

    private String studentNo;

    private Long testNo;

    private Long questionNo;

    private int studentScore;

    private int questionScore; //题目分值

    private String questionType;

    private String flag; //0.未批改，1.已批改

    private Date createTime;

    private String studentAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo == null ? null : studentNo.trim();
    }

    public Long getTestNo() {
        return testNo;
    }

    public void setTestNo(Long testNo) {
        this.testNo = testNo;
    }

    public Long getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Long questionNo) {
        this.questionNo = questionNo;
    }

    public int getStudentScore() {
        return studentScore;
    }

    public void setStudentScore(int studentScore) {
        this.studentScore = studentScore;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer == null ? null : studentAnswer.trim();
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }

    @Override
    public String toString() {
        return "ScoreDetail{" +
                "id=" + id +
                ", studentNo='" + studentNo + '\'' +
                ", testNo=" + testNo +
                ", questionNo=" + questionNo +
                ", studentScore='" + studentScore + '\'' +
                ", questionScore=" + questionScore +
                ", questionType='" + questionType + '\'' +
                ", flag='" + flag + '\'' +
                ", createTime=" + createTime +
                ", studentAnswer='" + studentAnswer + '\'' +
                '}';
    }
}