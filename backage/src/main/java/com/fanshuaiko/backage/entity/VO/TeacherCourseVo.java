package com.fanshuaiko.backage.entity.VO;

import java.util.List;

/**
 * @ClassName TeacherCourseVo
 * @Description 教师对应课程返回实体类
 * @Author fanshuaiko
 * @Date 19-4-26 上午9:59
 * @Version 1.0
 **/
public class TeacherCourseVo {
    private String teacherNo; //教师编号

    private String teacherName; //教师姓名

    private List<String> courseNames;//课程

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

    @Override
    public String toString() {
        return "TeacherCourseVo{" +
                "teacherNo='" + teacherNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseNames=" + courseNames +
                '}';
    }
}
