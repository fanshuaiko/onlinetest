package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.VO.QuestionQueryTerm;
import com.fanshuaiko.backage.entity.Subjective;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface SubjectiveDao {


    int deleteByPrimaryKey(Long id);

    int insert(Subjective record);

    int insertSelective(Subjective record);

    Subjective selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Subjective record);

    int updateByPrimaryKey(Subjective record);

    List<Subjective> queryByConditions(QuestionQueryTerm queryTerm); //多条件查询

    int batchAdd(@Param("subjectiveList") List<Subjective> subjectiveList);//批量添加题目

    List<Long> returnRandomQuestionIds(String type, int count,int courseId); //根据类型随机返回指定数量的id
}