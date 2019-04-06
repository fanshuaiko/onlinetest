package com.fanshuaiko.front.entity.vo;


/**
 * @ClassName TestVo
 * @Description 返回考试信息vo
 * @Author fanshuaiko
 * @Date 19-4-6 上午10:10
 * @Version 1.0
 **/
public class TestVo {
    private Long id; //主键

    private String name; //考试名称

    private String courseName; //所属课程

    private String teacherName; //任课教师，也是创建考试的教师

    private String totalScore; //考试总分

    private String status; //考试状态,0.未开始，1.进行中，3.已结束

    private String startTime; //开考时间

    private String endTime; //结束时间

    private int testTime; //考试时长

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public int getTestTime() {
        return testTime;
    }

    public void setTestTime(int testTime) {

        this.testTime = testTime;
    }

    @Override
    public String toString() {
        return "TestVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseName=" + courseName +
                ", teacherName='" + teacherName + '\'' +
                ", totalScore='" + totalScore + '\'' +
                ", status='" + status + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", testTime=" + testTime +
                '}';
    }
}
