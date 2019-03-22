package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.QuestionQueryTerm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}