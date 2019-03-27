package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.TestDao;
import com.fanshuaiko.backage.dict.QuestionType;
import com.fanshuaiko.backage.dict.TestStatus;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.Subjective;
import com.fanshuaiko.backage.entity.Test;
import com.fanshuaiko.backage.entity.VO.TestVO;
import com.fanshuaiko.backage.service.TestService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ImportUtil;
import com.fanshuaiko.backage.utils.ResultData;
import com.fanshuaiko.backage.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TestServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-27 下午1:56
 * @Version 1.0
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public ResultData createTest(TestVO testVO) {
        testVO.setId(SnowflakeIdWorker.nextId());
        testVO.setStatus(TestStatus.NotBegin.getCODE());
        //保存test相关信息到test表
        testDao.insertTest(testVO);
        //保存class信息到test_class表


        //保存题目到题目表

        //保存题目到test_question表

        return null;
    }

    @Override
    public ResultData uploadChoice(MultipartFile file, String type) {
        try {
            ResultData resultData = ImportUtil.checkImportChoice(file, type);
            if (resultData.getData() == null) {
                return resultData;
            }
            List<Choice> choiceList = (List<Choice>) resultData.getData();
            HashMap<Long, Integer> map = new HashMap<>();
            if (type.equals(QuestionType.SingleChoice.getCODE())) {
                long singleRedisId = SnowflakeIdWorker.nextId();
                redisTemplate.opsForValue().set(singleRedisId, choiceList);
                map.put(singleRedisId, choiceList.size());
            } else if (type.equals(QuestionType.JudgeChoice.getCODE())) {
                long judgeRedisId = SnowflakeIdWorker.nextId();
                redisTemplate.opsForValue().set(judgeRedisId, choiceList);
                map.put(judgeRedisId, choiceList.size());
            } else if (type.equals(QuestionType.MultipleChoice.getCODE())) {
                long multipleRedisId = SnowflakeIdWorker.nextId();
                redisTemplate.opsForValue().set(multipleRedisId, choiceList);
                map.put(multipleRedisId, choiceList.size());
            }
            return ResultData.newSuccessResultData(map);
        } catch (Exception e) {
            return ResultData.newResultData(ErrorCode.FAILOR, "上传失败！");
        }
    }

    @Override
    public ResultData uploadSubjective(MultipartFile file, String type) {
        try {
            long subjectiveRedisId = SnowflakeIdWorker.nextId();
            ResultData resultData = ImportUtil.checkImportSubjective(file, type);
            if (resultData.getData() == null) {
                return resultData;
            }
            List<Subjective> subjectiveList = (List<Subjective>) resultData.getData();
            redisTemplate.opsForValue().set(subjectiveRedisId, subjectiveList);
            HashMap<Long, Integer> map = new HashMap<>();
            map.put(subjectiveRedisId, subjectiveList.size());
            return ResultData.newSuccessResultData(map);
        } catch (Exception e) {
            return ResultData.newResultData(ErrorCode.FAILOR, "上传失败！");
        }
    }

}
