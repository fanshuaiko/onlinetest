package com.fanshuaiko.backage.entity;
/**
 * @ClassName Class
 * @Description 班级实体类
 * @Author fanshuaiko
 * @Date 19-3-20 下午2:20
 * @Version 1.0
 **/
public class Class {
    private Integer id;

    private String classCo; //班号

    private String classNo; //编辑编码

    private String majorNo; //专业编码

    private String instituteNo; //学院编码

    private String collegeNo; //学校编码

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCo() {
        return classCo;
    }

    public void setClassCo(String classCo) {
        this.classCo = classCo == null ? null : classCo.trim();
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo == null ? null : classNo.trim();
    }

    public String getMajorNo() {
        return majorNo;
    }

    public void setMajorNo(String majorNo) {
        this.majorNo = majorNo == null ? null : majorNo.trim();
    }

    public String getInstituteNo() {
        return instituteNo;
    }

    public void setInstituteNo(String instituteNo) {
        this.instituteNo = instituteNo == null ? null : instituteNo.trim();
    }

    public String getCollegeNo() {
        return collegeNo;
    }

    public void setCollegeNo(String collegeNo) {
        this.collegeNo = collegeNo == null ? null : collegeNo.trim();
    }
}