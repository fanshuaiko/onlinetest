package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.entity.VO.QuestionQueryTerm;
import com.fanshuaiko.backage.entity.VO.ScoreQueryTerm;
import com.fanshuaiko.backage.service.PaperService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName PaperController
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午2:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("/paper")
public class PaperController {
    private static Logger log = LoggerFactory.getLogger(PaperController.class);

    @Autowired
    private PaperService paperService;

    /**
     * 根据考试编码查询未批改的主观题
     *
     * @param testNo
     * @return
     */
    @GetMapping("/scoreDetail/{testNo}")
    public ResultData queryScoreDetail(@PathVariable("testNo") long testNo) {
        try {
            ResultData resultData = paperService.queryScoreDetail(testNo);
            return resultData;
        } catch (Exception e) {
            log.info("--------paper:queryScoreDetail--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 根据score_detail的id更新学生题目得分
     * @param id
     * @param score
     * @return
     */
    @PostMapping("/scoreDetail/{id}/{score}")
    public ResultData saveStudentScoreDetail(@PathVariable("id") long id,@PathVariable("score") int score){
        try {
            ResultData resultData = paperService.saveStudentScoreDetail(id,score);
            return resultData;
        } catch (Exception e) {
            log.info("--------paper:saveStudentScoreDetail--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.UPDATE_FAILOR, ErrorCode.UPDATE_FAILOR_MSG);
        }
    }

    /**
     * 批改该场考试的选择题
     * @param testNo
     * @return
     */
    @PostMapping("choice/{testNo}")
    public ResultData markChoice(@PathVariable("testNo") long testNo){
        try {
            ResultData resultData = paperService.markChoice(testNo);
            return resultData;
        } catch (Exception e) {
            log.info("--------paper:markChoice--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "批改出现异常！");
        }
    }

    /**
     * 分页查询PagerStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("paperStatus/{pageNum}/{pageSize}")
    public ResultData pageQueryPaperStatus(@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize){
        try {
            ResultData resultData = paperService.pageQueryPaperStatus(pageNum,pageSize);
            return resultData;
        } catch (Exception e) {
            log.info("--------paper:pageQueryPaperStatus--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 多参数分页查询学生分数
     * @param scoreQueryTerm
     * @return
     */
    @GetMapping("/score")
    public ResultData pageQueryStudentScore(ScoreQueryTerm scoreQueryTerm){
        try {
            ResultData resultData = paperService.pageQueryStudentScore(scoreQueryTerm);
            return resultData;
        } catch (Exception e) {
            log.info("--------paper:pageQueryStudentScore--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }
}
