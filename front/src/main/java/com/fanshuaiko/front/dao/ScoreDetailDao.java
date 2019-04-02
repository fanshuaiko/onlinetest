package com.fanshuaiko.front.dao;

import com.fanshuaiko.front.entity.ScoreDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScoreDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreDetail record);

    int insertSelective(ScoreDetail record);

    ScoreDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreDetail record);

    int updateByPrimaryKeyWithBLOBs(ScoreDetail record);

    int updateByPrimaryKey(ScoreDetail record);

    int batchAdd(@Param("scoreDetailList") List<ScoreDetail> scoreDetailList);
}