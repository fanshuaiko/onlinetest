package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.VO.ScoreQueryTerm;
import com.fanshuaiko.backage.utils.ResultData;

/**
 * @ClassName PaperService
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午2:54
 * @Version 1.0
 **/
public interface PaperService {
    /**
     * 根据考试编码查询未批改的主观题
     *
     * @param testNo
     * @return
     */
    ResultData queryScoreDetail(long testNo);

    ResultData saveStudentScoreDetail(long id,int score);

    ResultData markChoice(long testNo);

    ResultData pageQueryPaperStatus(int pageNum, int pageSize);

    ResultData pageQueryStudentScore(ScoreQueryTerm scoreQueryTerm);
}
