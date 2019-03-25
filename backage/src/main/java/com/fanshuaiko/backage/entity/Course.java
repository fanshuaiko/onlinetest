package com.fanshuaiko.backage.entity;
/**
 * @ClassName Course
 * @Description 课程实体类
 * @Author fanshuaiko
 * @Date 19-3-20 下午2:20
 * @Version 1.0
 **/
public class Course {
    private Integer id; //课程id

    private String name; //课程

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

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}