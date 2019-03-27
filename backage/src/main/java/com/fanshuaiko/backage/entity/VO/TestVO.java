package com.fanshuaiko.backage.entity.VO;

import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.Subjective;

import java.util.Date;
import java.util.List;

/**
 * @ClassName TestVO
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-27 下午5:18
 * @Version 1.0
 **/
public class TestVO {
    private Long id; //test主键

    private String name; //考试名称

    private Integer courseId; //所属课程id

    private String teacherNo; //任课教师id，也是创建考试的教师

    private String totalScore; //考试总分

    private String status; //考试状态,0.未开始，1.进行中，3.已结束

    private Date startTime; //开考时间

    private Date endTime; //结束时间

    private long singleRedisId; //保存在redis中的单选题id

    private long judgeRedisId; //保存在redis中判断题id

    private long multipleRedisId; //保存在redis中多选题id

    private long subjectiveRedisId; //保存在redis中主观题id

    private int singleScore; //单选题分值

    private int judgeScore; //判断题分值

    private int mutipleScore; //多选题分值

    private int subjectiveScore; //主观题分值

    private List<String> classNoList; //参与考试的班级

    private List<Choice> choiceList; //选择题

    private List<Subjective> subjectiveList; //主观题

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public long getSingleRedisId() {
        return singleRedisId;
    }

    public void setSingleRedisId(long singleRedisId) {
        this.singleRedisId = singleRedisId;
    }

    public long getJudgeRedisId() {
        return judgeRedisId;
    }

    public void setJudgeRedisId(long judgeRedisId) {
        this.judgeRedisId = judgeRedisId;
    }

    public long getMultipleRedisId() {
        return multipleRedisId;
    }

    public void setMultipleRedisId(long multipleRedisId) {
        this.multipleRedisId = multipleRedisId;
    }

    public long getSubjectiveRedisId() {
        return subjectiveRedisId;
    }

    public void setSubjectiveRedisId(long subjectiveRedisId) {
        this.subjectiveRedisId = subjectiveRedisId;
    }

    public int getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(int singleScore) {
        this.singleScore = singleScore;
    }

    public int getJudgeScore() {
        return judgeScore;
    }

    public void setJudgeScore(int judgeScore) {
        this.judgeScore = judgeScore;
    }

    public int getMutipleScore() {
        return mutipleScore;
    }

    public void setMutipleScore(int mutipleScore) {
        this.mutipleScore = mutipleScore;
    }

    public int getSubjectiveScore() {
        return subjectiveScore;
    }

    public void setSubjectiveScore(int subjectiveScore) {
        this.subjectiveScore = subjectiveScore;
    }

    public List<String> getClassNoList() {
        return classNoList;
    }

    public void setClassNoList(List<String> classNoList) {
        this.classNoList = classNoList;
    }

    public List<Choice> getChoiceList() {
        return choiceList;
    }

    public void setChoiceList(List<Choice> choiceList) {
        this.choiceList = choiceList;
    }

    public List<Subjective> getSubjectiveList() {
        return subjectiveList;
    }

    public void setSubjectiveList(List<Subjective> subjectiveList) {
        this.subjectiveList = subjectiveList;
    }

    @Override
    public String toString() {
        return "TestVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                ", teacherNo='" + teacherNo + '\'' +
                ", totalScore='" + totalScore + '\'' +
                ", status='" + status + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", singleRedisId=" + singleRedisId +
                ", judgeRedisId=" + judgeRedisId +
                ", multipleRedisId=" + multipleRedisId +
                ", subjectiveRedisId=" + subjectiveRedisId +
                ", singleScore=" + singleScore +
                ", judgeScore=" + judgeScore +
                ", mutipleScore=" + mutipleScore +
                ", subjectiveScore=" + subjectiveScore +
                ", classNoList=" + classNoList +
                ", choiceList=" + choiceList +
                ", subjectiveList=" + subjectiveList +
                '}';
    }
}
