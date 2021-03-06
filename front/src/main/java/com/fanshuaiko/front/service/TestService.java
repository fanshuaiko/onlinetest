package com.fanshuaiko.front.service;

import com.fanshuaiko.front.entity.ScoreDetail;
import com.fanshuaiko.front.utils.ResultData;

import java.util.List;

/**
 * @ClassName TestService
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午5:43
 * @Version 1.0
 **/
public interface TestService {
    /**
     * 根据学号查询考试信息
     *
     * @param studentNo
     * @return
     */
    ResultData queryTest(String studentNo);

    /**
     * 根据考试编码查询该场考试的所有试题
     *
     * @param testNo
     * @return
     */
    ResultData getQuestion(long testNo);

    /**
     * 提交考试，保存学生答题情况
     *
     * @param scoreDetailList
     * @return
     */
    ResultData commitTest(List<ScoreDetail> scoreDetailList);
}
