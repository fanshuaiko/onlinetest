package com.fanshuaiko.backage.entity;

import java.util.Date;
/**
 * @ClassName Score
 * @Description 得分实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:40
 * @Version 1.0
 **/
public class Score {
    private Long id;

    private String studentNo;

    private Long testNo;

    private Integer choice;

    private Integer subjective;

    private Integer total;

    private Date createTime;

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

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    public Integer getSubjective() {
        return subjective;
    }

    public void setSubjective(Integer subjective) {
        this.subjective = subjective;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}