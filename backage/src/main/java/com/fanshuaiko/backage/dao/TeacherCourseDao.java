package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.TeacherCourse;

public interface TeacherCourseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);
}