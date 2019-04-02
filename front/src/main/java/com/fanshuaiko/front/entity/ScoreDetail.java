package com.fanshuaiko.front.entity;

/**
 * @ClassName ScoreDetail
 * @Description 题目得分
 * @Author fanshuaiko
 * @Date 19-4-2 上午10:00
 * @Version 1.0
 **/
public class ScoreDetail {
    private Integer id;

    private String studentNo;

    private Long testNo;

    private Long questionNo;

    private int studentScore;

    private String questionType;

    private String flag; //0.未批改，1.已批改

    private String createTime;

    private String studentAnswer;

    private int questionScore; //题目分值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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
                ", studentScore=" + studentScore +
                ", questionType='" + questionType + '\'' +
                ", flag='" + flag + '\'' +
                ", createTime='" + createTime + '\'' +
                ", studentAnswer='" + studentAnswer + '\'' +
                ", questionScore=" + questionScore +
                '}';
    }
}