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

    private List<Integer> courseIds; //课程id

    private int pageSize;//

    private int pageNum;//

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

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return "TeacherCourseVo{" +
                "teacherNo='" + teacherNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseNames=" + courseNames +
                ", courseIds=" + courseIds +
                ", pageSize=" + pageSize +
                ", pageNum=" + pageNum +
                '}';
    }
}
