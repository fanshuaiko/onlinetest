package com.fanshuaiko.backage.entity;
/**
 * @ClassName Major
 * @Description 专业实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:40
 * @Version 1.0
 **/
public class Major {
    private Integer id;

    private String name;

    private String majorNo;

    private String instituteNo;

    private String collegeNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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