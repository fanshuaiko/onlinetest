package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.VO.QuestionQueryTerm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ChoiceDao {
    int deleteByPrimaryKey(Long id);

    int insert(Choice record);

    int insertSelective(Choice record);

    Choice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Choice record);

    int updateByPrimaryKey(Choice record);

    List<Choice> queryByConditions(QuestionQueryTerm queryTerm); //多条件查询

    int batchAdd(@Param("choiceList") List<Choice> choiceList);//批量添加题目

    List<Map<Long,String>> questionAnswerMap(@Param("ids") List<Long> ids); //根据题目id查询题目答案
}