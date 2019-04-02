package com.fanshuaiko.front.dao;

import com.fanshuaiko.front.entity.Question;
import com.fanshuaiko.front.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TestDao {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    List<Test> queryTestByStudentNo(String studentNo);

    List<Question> getSubjective(long testNo);

    List<Question> getChoice(long testNo);
}