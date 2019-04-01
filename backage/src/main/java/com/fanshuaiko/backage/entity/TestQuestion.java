package com.fanshuaiko.backage.entity;

/**
 * @ClassName TestQuestion
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 上午10:05
 * @Version 1.0
 **/
public class TestQuestion {
    private int id;
    private long testNo;
    private long questionNo;
    private int score;
    private String questionType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTestNo() {
        return testNo;
    }

    public void setTestNo(long testNo) {
        this.testNo = testNo;
    }

    public long getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(long questionNo) {
        this.questionNo = questionNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Override
    public String toString() {
        return "TestQuestion{" +
                "id=" + id +
                ", testNo=" + testNo +
                ", questionNo=" + questionNo +
                ", score=" + score +
                ", questionType='" + questionType + '\'' +
                '}';
    }
}
