package com.fanshuaiko.backage.service;

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
}
