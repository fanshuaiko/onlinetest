package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.dao.SubjectiveDao;
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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.LinkedList;
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
    private RedisTemplate redisTemplate;

    @Autowired
    private ChoiceDao choiceDao;

    @Autowired
    private SubjectiveDao subjectiveDao;

    @Override
    @Transactional
    public ResultData createTest(TestVO testVO) {
        try{
            testVO.setId(SnowflakeIdWorker.nextId());
            testVO.setStatus(TestStatus.NotBegin.getCODE());
            //保存test相关信息到test表
            testDao.insertTest(testVO);
            //保存class信息到test_class表
            testDao.insertTestClass(testVO);
            //保存上传的题目保存到题目表
            if (!StringUtils.isEmpty(testVO.getSingleRedisId())) {
                List<Choice> singleList = (List<Choice>) redisTemplate.opsForValue().get(testVO.getSingleRedisId());
                if (CollectionUtils.isEmpty(singleList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, "保存单选题失败，请重新上传！");
                } else {
                    //保存题目到提目表
                    choiceDao.batchAdd(singleList);
                    //保存题目与考试相关表
                    List<Long> singleIdList = returnIdFromObj(singleList);
                    testDao.insertTestQuestion(testVO.getId(), singleIdList, testVO.getSingleScore(), QuestionType.SingleChoice.getCODE());
                }
            }
            if (!StringUtils.isEmpty(testVO.getJudgeRedisId())) {
                List<Choice> judgeList = (List<Choice>) redisTemplate.opsForValue().get(testVO.getJudgeRedisId());
                if (CollectionUtils.isEmpty(judgeList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, "保存判断题失败，请重新上传！");
                } else {
                    choiceDao.batchAdd(judgeList);
                    List<Long> judgeIdList = returnIdFromObj(judgeList);
                    testDao.insertTestQuestion(testVO.getId(), judgeIdList, testVO.getJudgeScore(), QuestionType.JudgeChoice.getCODE());
                }
            }
            if (!StringUtils.isEmpty(testVO.getMultipleRedisId())) {
                List<Choice> multipleList = (List<Choice>) redisTemplate.opsForValue().get(testVO.getMultipleRedisId());
                if (CollectionUtils.isEmpty(multipleList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, "保存多选题失败，请重新上传！");
                } else {
                    choiceDao.batchAdd(multipleList);
                    List<Long> multipleIdList = returnIdFromObj(multipleList);
                    testDao.insertTestQuestion(testVO.getId(), multipleIdList, testVO.getMultipleScore(), QuestionType.MultipleChoice.getCODE());
                }
            }
            if (!StringUtils.isEmpty(testVO.getSubjectiveRedisId())) {
                List<Subjective> subjectiveList = (List<Subjective>) redisTemplate.opsForValue().get(testVO.getSubjectiveRedisId());
                if (CollectionUtils.isEmpty(subjectiveList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, "保存主观题失败，请重新上传！");
                } else {
                    subjectiveDao.batchAdd(subjectiveList);
                    LinkedList<Long> subjectiveIdList = new LinkedList<>();
                    for (Subjective subjective : subjectiveList) {
                        subjectiveIdList.add(subjective.getId());
                    }
                    testDao.insertTestQuestion(testVO.getId(), subjectiveIdList, testVO.getSubjectiveScore(), QuestionType.Subjective.getCODE());
                }
            }

            //保存题目到test_question表
            if (StringUtils.isEmpty(testVO.getSingleRedisId())) {
                testDao.insertTestQuestion(testVO.getId(), testVO.getSingleIdList(), testVO.getSingleScore(), QuestionType.SingleChoice.getCODE());
            } else if (StringUtils.isEmpty(testVO.getJudgeRedisId())) {
                testDao.insertTestQuestion(testVO.getId(), testVO.getJudgeIdList(), testVO.getJudgeScore(), QuestionType.JudgeChoice.getCODE());
            } else if (StringUtils.isEmpty(testVO.getMultipleRedisId())) {
                testDao.insertTestQuestion(testVO.getId(), testVO.getMultipleIdList(), testVO.getMultipleScore(), QuestionType.MultipleChoice.getCODE());
            } else if (StringUtils.isEmpty(testVO.getSubjectiveRedisId())) {
                testDao.insertTestQuestion(testVO.getId(), testVO.getSubjectiveIdList(), testVO.getSubjectiveScore(), QuestionType.Subjective.getCODE());
            }

            return null;
        }catch (Exception e){
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG,"新建考试失败！");
        }

    }

    @Override
    public ResultData uploadChoice(MultipartFile file, String type) {
        try {
            ResultData resultData = ImportUtil.checkImportChoice(file, type);
            if (resultData.getData() == null) {
                return resultData;
            }
            List<Choice> choiceList = (List<Choice>) resultData.getData();
            for (Choice choice : choiceList) {
                choice.setId(SnowflakeIdWorker.nextId());
                choice.setType(type);
            }
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

    public List<Long> returnIdFromObj(List<Choice> list) {
        LinkedList<Long> ids = new LinkedList<>();
        for (Choice choice : list) {
            ids.add(choice.getId());
        }
        return ids;
    }
}
