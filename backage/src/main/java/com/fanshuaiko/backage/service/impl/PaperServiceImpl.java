package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ScoreDetailDao;
import com.fanshuaiko.backage.entity.VO.ScoreDetailReturnVo;
import com.fanshuaiko.backage.service.PaperService;
import com.fanshuaiko.backage.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResultData queryScoreDetail(long testNo) {
        ScoreDetailReturnVo scoreDetailReturnVo = scoreDetailDao.queryScoreDetail(testNo);
        if( null == scoreDetailReturnVo){
            return ResultData.newSuccessResultData("已经是最后一题！");
        }
        return ResultData.newSuccessResultData(scoreDetailReturnVo);
    }

    @Override
    public ResultData saveStudentScoreDetail(long id,int score) {
        int i = scoreDetailDao.updateStudentScoreById(id,score);
        return ResultData.newSuccessResultData(i);
    }
}
