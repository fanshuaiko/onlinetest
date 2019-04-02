package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScoreDao {
    int deleteByPrimaryKey(Long id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

    int batchAdd(@Param("scoreList") List<Score> scoreList); //批量保存总分
}