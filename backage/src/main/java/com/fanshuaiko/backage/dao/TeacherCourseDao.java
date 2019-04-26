package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.TeacherCourse;
import com.fanshuaiko.backage.entity.VO.TeacherCourseVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    int deleteByTeacherNo(String teacherNo); //根据教师编号删除记录

    List<TeacherCourseVo> selectByCondition(TeacherCourseVo teacherCourseVo); //根据条件查询所有TeacherCourseVo
}