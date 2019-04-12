package com.fanshuaiko.backage.entity.VO;


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

    private int totalScore; //考试总分

    private String status; //考试状态,0.未开始，1.进行中，3.已结束

    private String startTime; //开考时间

    private String endTime; //结束时间

    private int testTime;//考试时长，单位分钟

    private long singleRedisId; //保存在redis中的单选题id

    private long judgeRedisId; //保存在redis中判断题id

    private long multipleRedisId; //保存在redis中多选题id

    private long subjectiveRedisId; //保存在redis中主观题id

    private int singleScore; //单选题分值

    private int judgeScore; //判断题分值

    private int multipleScore; //多选题分值

    private int subjectiveScore; //主观题分值

    private List<String> classNoList; //参与考试的班级

    private List<Long> singleIdList; //选择题id

    private List<Long> judgeIdList; //判断题id

    private List<Long> multipleIdList; //多选题id

    private List<Long> subjectiveIdList; //主观题

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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public int getMultipleScore() {
        return multipleScore;
    }

    public void setMultipleScore(int multipleScore) {
        this.multipleScore = multipleScore;
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

    public List<Long> getSingleIdList() {
        return singleIdList;
    }

    public void setSingleIdList(List<Long> singleIdList) {
        this.singleIdList = singleIdList;
    }

    public List<Long> getJudgeIdList() {
        return judgeIdList;
    }

    public void setJudgeIdList(List<Long> judgeIdList) {
        this.judgeIdList = judgeIdList;
    }

    public List<Long> getMultipleIdList() {
        return multipleIdList;
    }

    public void setMultipleIdList(List<Long> multipleIdList) {
        this.multipleIdList = multipleIdList;
    }

    public List<Long> getSubjectiveIdList() {
        return subjectiveIdList;
    }

    public void setSubjectiveIdList(List<Long> subjectiveIdList) {
        this.subjectiveIdList = subjectiveIdList;
    }

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {
        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return "TestVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseId=" + courseId +
                ", teacherNo='" + teacherNo + '\'' +
                ", totalScore=" + totalScore +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", testTime=" + testTime +
                ", singleRedisId=" + singleRedisId +
                ", judgeRedisId=" + judgeRedisId +
                ", multipleRedisId=" + multipleRedisId +
                ", subjectiveRedisId=" + subjectiveRedisId +
                ", singleScore=" + singleScore +
                ", judgeScore=" + judgeScore +
                ", multipleScore=" + multipleScore +
                ", subjectiveScore=" + subjectiveScore +
                ", classNoList=" + classNoList +
                ", singleIdList=" + singleIdList +
                ", judgeIdList=" + judgeIdList +
                ", multipleIdList=" + multipleIdList +
                ", subjectiveIdList=" + subjectiveIdList +
                '}';
    }
}
