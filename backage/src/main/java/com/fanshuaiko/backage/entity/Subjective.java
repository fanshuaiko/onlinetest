package com.fanshuaiko.backage.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;
/**
 * @ClassName Subjective
 * @Description 主观题
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:40
 * @Version 1.0
 **/
public class Subjective {
    private Long id; //snowflake算法生成主键

    @Excel(name = "题目")
    private String question; //题目

    @Excel(name = "答案")
    private String answer; //答案

    @Excel(name = "所属课程")
    private String courseName; //所属课程

    private String type; //题目类型

    private Date createTime; //创建时间

    private Date updateTime; //修改时间

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
        this.question = question == null ? null : question.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    @Override
    public String toString() {
        return "Subjective{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", anwser='" + answer + '\'' +
                ", courseName='" + courseName + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}