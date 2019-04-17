package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.*;
import com.fanshuaiko.backage.entity.PaperStatus;
import com.fanshuaiko.backage.entity.Score;
import com.fanshuaiko.backage.entity.ScoreDetail;
import com.fanshuaiko.backage.entity.VO.ScoreDetailReturnVo;
import com.fanshuaiko.backage.entity.VO.ScoreQueryTerm;
import com.fanshuaiko.backage.entity.VO.ScoreReturnVo;
import com.fanshuaiko.backage.service.PaperService;
import com.fanshuaiko.backage.utils.ResultData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private PaperStatusDao paperStatusDao;

    @Override
    public ResultData queryScoreDetail(long testNo) {
        ScoreDetailReturnVo scoreDetailReturnVo = scoreDetailDao.queryScoreDetail(testNo);
        //返回null说明所有题目批改完成，开始统计总分
        if (null == scoreDetailReturnVo) {
            List<Score> scoreList = scoreDetailDao.sumTotalScore(testNo);
            int count = scoreDao.batchAdd(scoreList);
            int i = paperStatusDao.updateSubjectiveStatusByTestNo(testNo);
            return ResultData.newResultData("0", "试卷全部批改完成");
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
        int i = paperStatusDao.updateChoiceStatusByTestNo(testNo);
        return ResultData.newSuccessResultData(i);
    }

    @Override
    public ResultData pageQueryPaperStatus(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PaperStatus> paperStatuses = paperStatusDao.selectAll();
        PageInfo<PaperStatus> paperStatusPageInfo = new PageInfo<>(paperStatuses);
        return ResultData.newSuccessResultData(paperStatusPageInfo);
    }

    @Override
    public ResultData pageQueryStudentScore(ScoreQueryTerm scoreQueryTerm) {
        PageHelper.startPage(scoreQueryTerm.getPageNum(),scoreQueryTerm.getPageSize());
        List<ScoreReturnVo> scoreReturnVoList =scoreDao.queryByConditions(scoreQueryTerm);
        PageInfo<ScoreReturnVo> scoreReturnVoPageInfo = new PageInfo<>(scoreReturnVoList);
        return ResultData.newSuccessResultData(scoreReturnVoPageInfo);
    }
}
