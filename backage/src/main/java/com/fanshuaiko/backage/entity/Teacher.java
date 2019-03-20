package com.fanshuaiko.backage.entity;

import java.util.Date;

/**
 * @ClassName Teacher
 * @Description 教师实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:57
 * @Version 1.0
 **/
public class Teacher {

    private int id; //主键

    private String teacherNo; //教师编号

    private String name; //教师姓名

    private String sex; //性别

    private String collegeNo; //学校编码

    private String collegeName; //学校名称

    private String instituteNo; //学院编码

    private String instituteName; //学院名称

    private String majorNo; //专业编码

    private String majorName; //专业名称

    private String phone; //电话号码

    private String email; //邮箱

    private Date enter_time; //入职日期

    private Date leave_time; //离职日期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherNo() {
        return teacherNo;
    }

    public void setTeacherNo(String teacherNo) {
        this.teacherNo = teacherNo;
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

    public Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Date leave_time) {
        this.leave_time = leave_time;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherNo='" + teacherNo + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", collegeNo='" + collegeNo + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", instituteNo='" + instituteNo + '\'' +
                ", instituteName='" + instituteName + '\'' +
                ", majorNo='" + majorNo + '\'' +
                ", majorName='" + majorName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", enter_time=" + enter_time +
                ", leave_time=" + leave_time +
                '}';
    }
}
