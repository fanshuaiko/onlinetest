package com.fanshuaiko.backage.entity;


/**
 * @ClassName QuestionQueryTerm
 * @Description 问题查询条件
 * @Author fanshuaiko
 * @Date 19-3-22 下午4:43
 * @Version 1.0
 **/
public class QuestionQueryTerm {

    private int pageNum; //第几页

    private int pageSize; //每页多少条

    private String question; //题目

    private String courseName; //所属课程

    private String type; //题目类型

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    @Override
    public String toString() {
        return "QuestionQueryTerm{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", question='" + question + '\'' +
                ", courseName='" + courseName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
