package com.fanshuaiko.backage.entity;

/**
 * @ClassName Teacher
 * @Description 教师课程对应实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:57
 * @Version 1.0
 **/
public class TeacherCourse {
    private Integer id;//主键

    private String teacherNo; //教师编号

    private String courseNo;//课程编号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo == null ? null : teacherNo.trim();
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo == null ? null : courseNo.trim();
    }

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "id=" + id +
                ", teacherNo='" + teacherNo + '\'' +
                ", courseNo='" + courseNo + '\'' +
                '}';
    }
}