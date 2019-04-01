package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.ScoreDetail;
import com.fanshuaiko.backage.entity.VO.ScoreDetailReturnVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScoreDetailDao {
    int insert(ScoreDetail record);

    int insertSelective(ScoreDetail record);

    ScoreDetailReturnVo queryScoreDetail(long testNo); // 根据考试编码查询未批改的主观题

    int updateStudentScoreById(@Param("id") long id, @Param("score") int score); //根据score_detail的id更新学生题目得分
}