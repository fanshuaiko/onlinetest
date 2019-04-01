package com.fanshuaiko.backage.web;

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
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
    }

}
