package com.fanshuaiko.front.service.impl;

import com.fanshuaiko.front.dao.TestDao;
import com.fanshuaiko.front.entity.Question;
import com.fanshuaiko.front.entity.Test;
import com.fanshuaiko.front.service.TestService;
import com.fanshuaiko.front.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultData queryTest(String studentNo) {
        List<Test> testList = testDao.queryTestByStudentNo(studentNo);
        return ResultData.newSuccessResultData(testList);
    }

    @Override
    public ResultData getQuestion(long testNo) {
        List<Question> questionList = (List<Question>) redisTemplate.opsForValue().get(testNo);
        if(CollectionUtils.isEmpty(questionList)){
            List<Question> choiceList = testDao.getChoice(testNo);
            List<Question> subjectiveList = testDao.getSubjective(testNo);
            questionList.addAll(choiceList);
            questionList.addAll(subjectiveList);
            redisTemplate.opsForValue().set(testNo,questionList);
            redisTemplate.expire(testNo,1, TimeUnit.HOURS);

        }
        return ResultData.newSuccessResultData(questionList);
    }
}
