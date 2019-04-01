package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.ScoreDetail;
import com.fanshuaiko.backage.entity.VO.ScoreDetailReturnVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScoreDetailDao {
    int insert(ScoreDetail record);

    int insertSelective(ScoreDetail record);

    ScoreDetailReturnVo queryScoreDetail(long testNo);
}