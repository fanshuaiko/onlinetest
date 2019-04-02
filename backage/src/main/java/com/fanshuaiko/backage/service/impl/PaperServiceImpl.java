package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.dao.ScoreDao;
import com.fanshuaiko.backage.dao.ScoreDetailDao;
import com.fanshuaiko.backage.dao.TestDao;
import com.fanshuaiko.backage.entity.Score;
import com.fanshuaiko.backage.entity.ScoreDetail;
import com.fanshuaiko.backage.entity.VO.ScoreDetailReturnVo;
import com.fanshuaiko.backage.service.PaperService;
import com.fanshuaiko.backage.utils.ResultData;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PaperSreviceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午2:54
 * @Version 1.0
 **/
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private ScoreDetailDao scoreDetailDao;

    @Autowired
    private ChoiceDao choiceDao;

    @Autowired
    private TestDao testDao;

    @Autowired
    private ScoreDao scoreDao;

    @Override
    public ResultData queryScoreDetail(long testNo) {
        ScoreDetailReturnVo scoreDetailReturnVo = scoreDetailDao.queryScoreDetail(testNo);
        //返回null说明所有题目批改完成，开始统计总分
        if (null == scoreDetailReturnVo) {
            List<Score> scoreList = scoreDetailDao.sumTotalScore(testNo);
            int count = scoreDao.batchAdd(scoreList);
            return ResultData.newSuccessResultData("试卷全部批改完成，"+"共"+count+"套！");
        }
        return ResultData.newSuccessResultData(scoreDetailReturnVo);
    }

    @Override
    public ResultData saveStudentScoreDetail(long id, int score) {
        int i = scoreDetailDao.updateStudentScoreById(id, score);
        return ResultData.newSuccessResultData(i);
    }

    @Override
    public ResultData markChoice(long testNo) {
        List<Long> questionNos = testDao.queryQuestionNos(testNo);
        //题目和正确答案
        List<Map<Long, String>> mapList = choiceDao.questionAnswerMap(questionNos);
        HashMap<String, String> map = new HashMap<>();
        for (Map<Long, String> longStringMap : mapList) {
            map.put(longStringMap.get("id"), longStringMap.get("answer"));
        }
        List<ScoreDetail> scoreDetails = scoreDetailDao.queryAllChoiceByTestNo(testNo);
        for (ScoreDetail scoreDetail : scoreDetails) {
            if (map.get(scoreDetail.getQuestionNo()).equals(scoreDetail.getStudentAnswer())) {
                scoreDetail.setStudentScore(scoreDetail.getQuestionScore());
            }
        }
        int count = scoreDetailDao.batchUpdateStudentScore(scoreDetails);
        return ResultData.newSuccessResultData(count);
    }
}
