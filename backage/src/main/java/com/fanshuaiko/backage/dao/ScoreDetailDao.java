package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.ScoreDetail;

public interface ScoreDetailDao {
    int insert(ScoreDetail record);

    int insertSelective(ScoreDetail record);
}