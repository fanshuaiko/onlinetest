package com.fanshuaiko.backage.entity;

import java.util.Date;

/**
 * @ClassName Student
 * @Description 学生实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:40
 * @Version 1.0
 **/
public class Student {
    private int id; //主键

    private String studentNo; //学号

    private String name; //姓名

    private String sex; //性别

    private String collegeNo; //学校编码

    private String collegeName; //学校名称

    private String instituteNo; //学院编码

    private String instituteName; //学院名称

    private String majorNo; //专业编码

    private String majorName; //专业名称

    private String classNo; //班级编码

    private String className; //班级

    private String phone; //电话号码

    private String email; //邮箱

    private Date enter_time; //入学日期

    private Date graduate_time; //毕业日期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollegeNo() {
        return collegeNo;
    }

    public void setCollegeNo(String collegeNo) {
        this.collegeNo = collegeNo;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getInstituteNo() {
        return instituteNo;
    }

    public void setInstituteNo(String instituteNo) {
        this.instituteNo = instituteNo;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnter_time() {
        return enter_time;
    }

    public void setEnter_time(Date enter_time) {
        this.enter_time = enter_time;
    }

    public Date getGraduate_time() {
        return graduate_time;
    }

    public void setGraduate_time(Date graduate_time) {
        this.graduate_time = graduate_time;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", collegeNo='" + collegeNo + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", instituteNo='" + instituteNo + '\'' +
                ", instituteName='" + instituteName + '\'' +
                ", majorNo='" + majorNo + '\'' +
                ", majorName='" + majorName + '\'' +
                ", classNo='" + classNo + '\'' +
                ", className='" + className + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", enter_time=" + enter_time +
                ", graduate_time=" + graduate_time +
                '}';
    }
}
