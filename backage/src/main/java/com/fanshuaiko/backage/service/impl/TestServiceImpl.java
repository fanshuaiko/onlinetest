package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.dao.SubjectiveDao;
import com.fanshuaiko.backage.dao.TestDao;
import com.fanshuaiko.backage.dict.QuestionType;
import com.fanshuaiko.backage.dict.TestStatus;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.Subjective;
import com.fanshuaiko.backage.entity.VO.TestVO;
import com.fanshuaiko.backage.service.TestService;
import com.fanshuaiko.backage.utils.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-27 下午1:56
 * @Version 1.0
 **/
@Service
@Transactional
public class TestServiceImpl implements TestService {

    private static Logger log = LoggerFactory.getLogger(TestServiceImpl.class);
    @Autowired
    private TestDao testDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ChoiceDao choiceDao;

    @Autowired
    private SubjectiveDao subjectiveDao;

    @Autowired
    private ImportUtilService importUtil;

    @Override
    public ResultData createTest(TestVO testVO) {
        try {
            testVO.setId(SnowflakeIdWorker.nextId());
            testVO.setStatus(TestStatus.NotBegin.getCODE());
            //保存test相关信息到test表
            int i = testDao.insertTest(testVO);
            log.info("插入test成功，记录" + i + "条");
            //保存class信息到test_class表
            int i1 = testDao.insertTestClass(testVO);
            log.info("插入test_class成功，记录" + i1 + "条");
            //保存上传的题目保存到题目表
            if (testVO.getSingleRedisId() != 0) {
                List<Choice> singleList = (List<Choice>) redisTemplate.opsForValue().get(testVO.getSingleRedisId());
                if (CollectionUtils.isEmpty(singleList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "保存单选题失败，请重新上传！");
                } else {
                    //保存题目到提目表
                    choiceDao.batchAdd(singleList);
                    //保存题目与考试相关表
                    List<Long> singleIdList = returnIdFromObj(singleList);
                    testDao.insertTestQuestion(testVO.getId(), singleIdList, testVO.getSingleScore(), QuestionType.SingleChoice.getCODE());
                }
            }
            if (testVO.getJudgeRedisId() != 0) {
                List<Choice> judgeList = (List<Choice>) redisTemplate.opsForValue().get(testVO.getJudgeRedisId());
                if (CollectionUtils.isEmpty(judgeList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "保存判断题失败，请重新上传！");
                } else {
                    choiceDao.batchAdd(judgeList);
                    List<Long> judgeIdList = returnIdFromObj(judgeList);
                    testDao.insertTestQuestion(testVO.getId(), judgeIdList, testVO.getJudgeScore(), QuestionType.JudgeChoice.getCODE());
                }
            }
            if (testVO.getMultipleRedisId() != 0) {
                List<Choice> multipleList = (List<Choice>) redisTemplate.opsForValue().get(testVO.getMultipleRedisId());
                if (CollectionUtils.isEmpty(multipleList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "保存多选题失败，请重新上传！");
                } else {
                    choiceDao.batchAdd(multipleList);
                    List<Long> multipleIdList = returnIdFromObj(multipleList);
                    testDao.insertTestQuestion(testVO.getId(), multipleIdList, testVO.getMultipleScore(), QuestionType.MultipleChoice.getCODE());
                }
            }
            if (testVO.getSubjectiveRedisId() != 0) {
                List<Subjective> subjectiveList = (List<Subjective>) redisTemplate.opsForValue().get(testVO.getSubjectiveRedisId());
                if (CollectionUtils.isEmpty(subjectiveList)) {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "保存主观题失败，请重新上传！");
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
            if (testVO.getSingleRedisId() == 0 && !CollectionUtils.isEmpty(testVO.getJudgeIdList())) {
                if (!CollectionUtils.isEmpty(testVO.getSingleIdList())) {

                    testDao.insertTestQuestion(testVO.getId(), testVO.getSingleIdList(), testVO.getSingleScore(), QuestionType.SingleChoice.getCODE());
                }
            }
            if (testVO.getJudgeRedisId() == 0 && !CollectionUtils.isEmpty(testVO.getJudgeIdList())) {
                if (!CollectionUtils.isEmpty(testVO.getJudgeIdList())) {

                    testDao.insertTestQuestion(testVO.getId(), testVO.getJudgeIdList(), testVO.getJudgeScore(), QuestionType.JudgeChoice.getCODE());
                }
            }
            if (testVO.getMultipleRedisId() == 0 && !CollectionUtils.isEmpty(testVO.getMultipleIdList())) {
                if (!CollectionUtils.isEmpty(testVO.getMultipleIdList())) {

                    testDao.insertTestQuestion(testVO.getId(), testVO.getMultipleIdList(), testVO.getMultipleScore(), QuestionType.MultipleChoice.getCODE());
                }
            }
            if (testVO.getSubjectiveRedisId() == 0 && !CollectionUtils.isEmpty(testVO.getSubjectiveIdList())) {
                if (!CollectionUtils.isEmpty(testVO.getSubjectiveIdList())) {

                    testDao.insertTestQuestion(testVO.getId(), testVO.getSubjectiveIdList(), testVO.getSubjectiveScore(), QuestionType.Subjective.getCODE());
                }
            }

            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, "新建考试失败！");
        }

    }

    @Override
    public ResultData uploadChoice(MultipartFile file, String type) {
        try {
            ResultData resultData = importUtil.checkImportChoice(file, type);
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
                redisTemplate.expire(singleRedisId, 1, TimeUnit.HOURS);
                map.put(singleRedisId, choiceList.size());
            } else if (type.equals(QuestionType.JudgeChoice.getCODE())) {
                long judgeRedisId = SnowflakeIdWorker.nextId();
                redisTemplate.opsForValue().set(judgeRedisId, choiceList);
                redisTemplate.expire(judgeRedisId, 1, TimeUnit.HOURS);
                map.put(judgeRedisId, choiceList.size());
            } else if (type.equals(QuestionType.MultipleChoice.getCODE())) {
                long multipleRedisId = SnowflakeIdWorker.nextId();
                redisTemplate.opsForValue().set(multipleRedisId, choiceList);
                redisTemplate.expire(multipleRedisId, 1, TimeUnit.HOURS);
                map.put(multipleRedisId, choiceList.size());
            }
            return ResultData.newSuccessResultData(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "上传失败！");
        }
    }

    @Override
    public ResultData uploadSubjective(MultipartFile file, String type) {
        try {
            long subjectiveRedisId = SnowflakeIdWorker.nextId();
            ResultData resultData = importUtil.checkImportSubjective(file, type);
            if (resultData.getData() == null) {
                return resultData;
            }
            List<Subjective> subjectiveList = (List<Subjective>) resultData.getData();
            redisTemplate.opsForValue().set(subjectiveRedisId, subjectiveList);
            redisTemplate.expire(subjectiveRedisId, 1, TimeUnit.HOURS);
            HashMap<Long, Integer> map = new HashMap<>();
            map.put(subjectiveRedisId, subjectiveList.size());
            return ResultData.newSuccessResultData(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "上传失败！");
        }
    }

    @Override
    public ResultData deleteTest(Long id) {
        int i = testDao.deleteByPrimaryKey(id);
        return ResultData.newSuccessResultData(i);
    }

    public List<Long> returnIdFromObj(List<Choice> list) {
        LinkedList<Long> ids = new LinkedList<>();
        for (Choice choice : list) {
            ids.add(choice.getId());
        }
        return ids;
    }
}
