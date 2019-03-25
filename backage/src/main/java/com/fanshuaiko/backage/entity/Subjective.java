package com.fanshuaiko.backage.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

/**
 * @ClassName Subjective
 * @Description 主观题
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:40
 * @Version 1.0
 **/
public class Subjective {
    private Long id; //题目id

    @Excel(name = "题目")
    private String question; //题目

    @Excel(name = "答案")
    private String anwser; //答案

    @Excel(name = "所属课程")
    private String courseName; //所属课程

    private String type; //题目类型

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

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser == null ? null : anwser.trim();
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

    @Override
    public String toString() {
        return "Subjective{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", anwser='" + anwser + '\'' +
                ", courseName='" + courseName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}