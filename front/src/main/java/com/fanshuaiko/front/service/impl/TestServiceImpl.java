package com.fanshuaiko.front.service.impl;

import com.fanshuaiko.front.dao.ScoreDetailDao;
import com.fanshuaiko.front.dao.TestDao;
import com.fanshuaiko.front.entity.Question;
import com.fanshuaiko.front.entity.ScoreDetail;
import com.fanshuaiko.front.entity.Test;
import com.fanshuaiko.front.entity.vo.TestVo;
import com.fanshuaiko.front.service.TestService;
import com.fanshuaiko.front.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private ScoreDetailDao scoreDetailDao;

    @Override
    public ResultData queryTest(String studentNo) {
        List<TestVo> testList = testDao.queryTestVoByStudentNo(studentNo);
        return ResultData.newSuccessResultData(testList);
    }

    @Override
    public ResultData getQuestion(long testNo) {
        //从缓存获取题目
        List<Question> questionList = (List<Question>) redisTemplate.opsForValue().get(testNo);
        //如果缓存中没有就冲数据库查询再放入缓存中
        if (CollectionUtils.isEmpty(questionList)) {
            questionList = new LinkedList<>();
            List<Question> choiceList = testDao.getChoice(testNo);
            List<Question> subjectiveList = testDao.getSubjective(testNo);
            if (!CollectionUtils.isEmpty(choiceList)) {
                questionList.addAll(choiceList);
            }
            if (!CollectionUtils.isEmpty(subjectiveList)) {
                questionList.addAll(subjectiveList);
            }
            redisTemplate.opsForValue().set(testNo, questionList);
            redisTemplate.expire(testNo, 1, TimeUnit.HOURS);

        }
        return ResultData.newSuccessResultData(questionList);
    }

    @Override
    public ResultData commitTest(List<ScoreDetail> scoreDetailList) {
        if(scoreDetailList.size()==0){
            return ResultData.newSuccessResultData(0);
        }
        int i = scoreDetailDao.batchAdd(scoreDetailList);
        return ResultData.newSuccessResultData(i);
    }
}
