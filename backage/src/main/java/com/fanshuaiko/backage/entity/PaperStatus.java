package com.fanshuaiko.backage.entity;

/**
 * @ClassName PaperStatus
 * @Description 题目批改状态
 * @Author fanshuaiko
 * @Date 19-4-16 上午10:40
 * @Version 1.0
 **/
public class PaperStatus {
    private Integer id;

    private Long testNo;

    private String choiceStatus;

    private String subjectiveStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTestNo() {
        return testNo;
    }

    public void setTestNo(Long testNo) {
        this.testNo = testNo;
    }

    public String getChoiceStatus() {
        return choiceStatus;
    }

    public void setChoiceStatus(String choiceStatus) {
        this.choiceStatus = choiceStatus == null ? null : choiceStatus.trim();
    }

    public String getSubjectiveStatus() {
        return subjectiveStatus;
    }

    public void setSubjectiveStatus(String subjectiveStatus) {
        this.subjectiveStatus = subjectiveStatus == null ? null : subjectiveStatus.trim();
    }
}