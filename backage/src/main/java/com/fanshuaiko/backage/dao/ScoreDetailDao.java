package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.ScoreDetail;
import com.fanshuaiko.backage.entity.VO.ScoreDetailReturnVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ScoreDetailDao {
    int insert(ScoreDetail record);

    int insertSelective(ScoreDetail record);

    ScoreDetailReturnVo queryScoreDetail(long testNo); // 根据考试编码查询未批改的主观题

    int updateStudentScoreById(@Param("id") long id, @Param("score") int score); //根据score_detail的id更新学生题目得分

    List<ScoreDetail> queryAllChoiceByTestNo(Long testNo); //根据考试编号查询所有选择题的得分信息

    int batchUpdateStudentScore(@Param("scoreDetails") List<ScoreDetail> scoreDetails); //批量更新学生得分
}