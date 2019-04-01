package com.fanshuaiko.backage.entity;
/**
 * @ClassName Institute
 * @Description 学院实体类
 * @Author fanshuaiko
 * @Date 19-3-15 上午10:40
 * @Version 1.0
 **/
public class Institute {
    private Integer id;

    private String name;

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