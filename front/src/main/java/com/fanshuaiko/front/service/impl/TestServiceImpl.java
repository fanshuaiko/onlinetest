package com.fanshuaiko.front.service.impl;

import com.fanshuaiko.front.dao.TestDao;
import com.fanshuaiko.front.entity.Test;
import com.fanshuaiko.front.service.TestService;
import com.fanshuaiko.front.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TestServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午5:43
 * @Version 1.0
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public ResultData queryTest(String studentNo) {
        List<Test> testList = testDao.queryTestByStudentNo(studentNo);
        return ResultData.newSuccessResultData(testList);
    }
}
