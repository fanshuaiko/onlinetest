package com.fanshuaiko.backage.entity.VO;


import java.util.Date;

/**
 * @ClassName Question
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-29 下午5:27
 * @Version 1.0
 **/
public class QuestionReturnVo {
    private Long id; //snowflake算法生成主键

    private String question; //题目

    private String choiceA; //选项A

    private String choiceB; //选项B

    private String choiceC; //选项C

    private String choiceD; //选项D

    private String answer; //正确答案

    private String courseName; //所属课程

    private String type; //1.单项选择题，2.判断题，3.多选题,4.主观题

    private Date createTime; //创建时间

    private Date updateTime; //修改时间

    private int score; //题目分值

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuestionReturnVo{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", choiceA='" + choiceA + '\'' +
                ", choiceB='" + choiceB + '\'' +
                ", choiceC='" + choiceC + '\'' +
                ", choiceD='" + choiceD + '\'' +
                ", answer='" + answer + '\'' +
                ", courseName='" + courseName + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", score=" + score +
                '}';
    }
}
