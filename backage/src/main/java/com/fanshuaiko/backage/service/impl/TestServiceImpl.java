package com.fanshuaiko.backage.service.impl;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.dao.ScoreDao;
import com.fanshuaiko.backage.dao.SubjectiveDao;
import com.fanshuaiko.backage.dao.TestDao;
import com.fanshuaiko.backage.dict.QuestionType;
import com.fanshuaiko.backage.dict.TestStatus;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.Subjective;
import com.fanshuaiko.backage.entity.TestQuestion;
import com.fanshuaiko.backage.entity.VO.QuestionReturnVo;
import com.fanshuaiko.backage.entity.VO.TestAnalyze;
import com.fanshuaiko.backage.entity.VO.TestReturnVo;
import com.fanshuaiko.backage.entity.VO.TestVO;
import com.fanshuaiko.backage.entity.mail.ScoreParams;
import com.fanshuaiko.backage.service.MailService;
import com.fanshuaiko.backage.service.TestService;
import com.fanshuaiko.backage.utils.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private MailService mailService;

    @Override
    public ResultData createTest(TestVO testVO) {
        try {
            testVO.setId(SnowflakeIdWorker.nextId());
            testVO.setStatus(TestStatus.NotBegin.getCODE());

            //设置考试结束时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = sdf.parse(testVO.getStartTime());
            Date endTime = new Date(startTime.getTime() + testVO.getTestTime() * 60 * 1000);
            String endTime2 = sdf.format(endTime);
            testVO.setEndTime(endTime2);


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
            if (testVO.getSingleRandomCount() != 0) {
                //根据课程和题目类型随机返回指定数量的题目id
                List<Long> ids = returnRandomQuestionIds(QuestionType.SingleChoice.getCODE(), testVO.getSingleRandomCount(), testVO.getCourseId());
                if (ids.size() != 0) {
                    testDao.insertTestQuestion(testVO.getId(), ids, testVO.getSingleScore(), QuestionType.SingleChoice.getCODE());
                } else {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "题库中没有该课程的单选题！");
                }
            }
            if (testVO.getJudgeRandomCount() != 0) {
                //根据课程和题目类型随机返回指定数量的题目id
                List<Long> ids = returnRandomQuestionIds(QuestionType.JudgeChoice.getCODE(), testVO.getJudgeRandomCount(), testVO.getCourseId());
                if (ids.size() != 0) {
                    testDao.insertTestQuestion(testVO.getId(), ids, testVO.getJudgeScore(), QuestionType.JudgeChoice.getCODE());
                } else {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "题库中没有该课程的判断题！");
                }
            }
            if (testVO.getMultipleRandomCount() != 0) {
                //根据课程和题目类型随机返回指定数量的题目id
                List<Long> ids = returnRandomQuestionIds(QuestionType.MultipleChoice.getCODE(), testVO.getMultipleRandomCount(), testVO.getCourseId());
                if (ids.size() != 0) {
                    testDao.insertTestQuestion(testVO.getId(), ids, testVO.getMultipleScore(), QuestionType.MultipleChoice.getCODE());
                } else {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "题库中没有该课程的多选题！");
                }
            }
            if (testVO.getSubjectiveRandomCount() != 0) {
                //根据课程和题目类型随机返回指定数量的题目id
                List<Long> ids = returnRandomQuestionIds(QuestionType.Subjective.getCODE(), testVO.getSubjectiveRandomCount(), testVO.getCourseId());
                if (ids.size() != 0) {
                    testDao.insertTestQuestion(testVO.getId(), ids, testVO.getSubjectiveScore(), QuestionType.Subjective.getCODE());
                } else {
                    return ResultData.newResultData(ErrorCode.ADD_FAILOR, "题库中没有该课程的主观题！");
                }
            }

            //保存test相关信息到test表
            int i = testDao.insertTest(testVO);
            log.info("插入test成功，记录" + i + "条");
            //保存class信息到test_class表
            int i1 = testDao.insertTestClass(testVO);
            log.info("插入test_class成功，记录" + i1 + "条");

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
    public ResultData deleteTest(List<Long> ids) {
        try {
            if (ids.size() > 0) {
                int i = testDao.batchDeleteByIds(ids);
                return ResultData.newSuccessResultData(i);
            }
            return ResultData.newResultData(ErrorCode.DEL_ID_FAILOR, ErrorCode.DEL_ID_FAILOR_MSG);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.DEL_ID_FAILOR, ErrorCode.DEL_FAILOR_MSG);
        }
    }

    @Override
    public ResultData pageQueryTest(int pageNum, int pageSize, String teacherNo) {
        PageHelper.startPage(pageNum, pageSize);
        List<TestReturnVo> testVOList = testDao.queryByTeacherNo(teacherNo);
        PageInfo<TestReturnVo> info = new PageInfo<>(testVOList);
        return ResultData.newSuccessResultData(info);
    }

    @Override
    public ResultData queryQuestionDetail(long testNo) {
        LinkedList<QuestionReturnVo> questionReturnVos = new LinkedList<>();
        List<TestQuestion> testQuestions = testDao.queryTestQuestion(testNo);
        QuestionReturnVo questionReturnVo;
        for (TestQuestion testQuestion : testQuestions) {
            if (testQuestion.getQuestionType().equals(QuestionType.Subjective.getCODE())) {
                questionReturnVo = new QuestionReturnVo();
                Subjective subjective = subjectiveDao.selectByPrimaryKey(testQuestion.getQuestionNo());
                questionReturnVo.setId(subjective.getId());
                questionReturnVo.setQuestion(subjective.getQuestion());
                questionReturnVo.setAnswer(subjective.getAnswer());
                questionReturnVo.setCourseName(subjective.getCourseName());
                questionReturnVo.setScore(testQuestion.getScore());
                questionReturnVo.setType(testQuestion.getQuestionType());
                questionReturnVo.setCreateTime(subjective.getCreateTime());
                questionReturnVo.setUpdateTime(subjective.getUpdateTime());
                questionReturnVos.add(questionReturnVo);
            } else {
                questionReturnVo = new QuestionReturnVo();
                Choice choice = choiceDao.selectByPrimaryKey(testQuestion.getQuestionNo());
                questionReturnVo.setId(choice.getId());
                questionReturnVo.setQuestion(choice.getQuestion());
                questionReturnVo.setChoiceA(choice.getChoiceA());
                questionReturnVo.setChoiceB(choice.getChoiceB());
                if (!StringUtils.isEmpty(choice.getChoiceC())) {
                    questionReturnVo.setChoiceC(choice.getChoiceC());
                }
                if (!StringUtils.isEmpty(choice.getChoiceD())) {
                    questionReturnVo.setChoiceD(choice.getChoiceD());
                }
                questionReturnVo.setAnswer(choice.getAnswer());
                questionReturnVo.setCourseName(choice.getCourseName());
                questionReturnVo.setCreateTime(choice.getCreateTime());
                questionReturnVo.setUpdateTime(choice.getUpdateTime());
                questionReturnVo.setScore(testQuestion.getScore());
                questionReturnVo.setType(testQuestion.getQuestionType());
                questionReturnVos.add(questionReturnVo);
            }
        }
        return ResultData.newSuccessResultData(questionReturnVos);
    }

    @Override
    public ResultData getTestAnalyzeData(Long testNo) {
        TestAnalyze testAnalyze = new TestAnalyze();
        testAnalyze.setTestNo(testNo);
        //计算参考人数和考试总分
        TestAnalyze testAnalyze2 = testDao.sumStudentCount(testNo);
        int studentCount = testAnalyze2.getStudentCount();
        testAnalyze.setTotalScore(testAnalyze2.getTotalScore());
        testAnalyze.setStudentCount(studentCount);
        //计算最高分、最低分、平均分
        TestAnalyze testAnalyze1 = scoreDao.selectMaxAndMinAndAvgScore(testNo);
        testAnalyze.setMaxScore(testAnalyze1.getMaxScore());
        testAnalyze.setMinScore(testAnalyze1.getMinScore());
        testAnalyze.setAverageScore(testAnalyze1.getAverageScore());
        //及格人数
        int passCount = scoreDao.countPassStudent(testNo);
        testAnalyze.setPassCount(passCount);
        //不及格人数
        testAnalyze.setUnPassCount(studentCount - passCount);
        //及格率
        testAnalyze.setPassRate((double) passCount / studentCount);

        return ResultData.newSuccessResultData(testAnalyze);
    }

    @Override
    public ResultData sendTestScoreMail(Long testNo) {
        try {

            TestAnalyze testAnalyze = (TestAnalyze) getTestAnalyzeData(testNo).getData();
            List<ScoreParams> paramsList = testDao.queryParamsForMail(testNo);
            for (ScoreParams scoreParams : paramsList) {
                scoreParams.setStudentCount(testAnalyze.getStudentCount());
                scoreParams.setAverageScore(testAnalyze.getAverageScore());
                scoreParams.setMaxScore(testAnalyze.getMaxScore());
                scoreParams.setPassRate(testAnalyze.getPassRate());
            }
                String s = mailService.sendHTMLMail(paramsList);
            return ResultData.newSuccessResultData(s);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "邮件发送失败");
        }

    }


    public List<Long> returnIdFromObj(List<Choice> list) {
        LinkedList<Long> ids = new LinkedList<>();
        for (Choice choice : list) {
            ids.add(choice.getId());
        }
        return ids;
    }

    public List<Long> returnRandomQuestionIds(String type, int count, int courseId) {
        if (type.equals(QuestionType.SingleChoice.getCODE())) {
            List<Long> ids = choiceDao.returnRandomQuestionIds(type, count, courseId);
            return ids;
        } else if (type.equals(QuestionType.JudgeChoice.getCODE())) {
            List<Long> ids = choiceDao.returnRandomQuestionIds(type, count, courseId);
            return ids;
        } else if (type.equals(QuestionType.MultipleChoice.getCODE())) {
            List<Long> ids = choiceDao.returnRandomQuestionIds(type, count, courseId);
            return ids;
        } else if (type.equals(QuestionType.Subjective.getCODE())) {
            List<Long> ids = subjectiveDao.returnRandomQuestionIds(type, count, courseId);
            return ids;
        }
        return null;
    }
}
