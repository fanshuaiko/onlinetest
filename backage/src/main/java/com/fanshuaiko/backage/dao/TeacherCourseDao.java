package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.TeacherCourse;
import com.fanshuaiko.backage.entity.VO.TeacherCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherCourseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherCourse record);

    int insertSelective(TeacherCourse record);

    TeacherCourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherCourse record);

    int updateByPrimaryKey(TeacherCourse record);

    int insertTeacherCourseVo(TeacherCourseVo teacherCourseVo);//添加教师对应的课程
}