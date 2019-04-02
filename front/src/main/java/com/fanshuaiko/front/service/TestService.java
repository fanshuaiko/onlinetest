package com.fanshuaiko.front.service;

import com.fanshuaiko.front.utils.ResultData;

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

}
