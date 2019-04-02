package com.fanshuaiko.front.web;

import com.fanshuaiko.front.entity.ScoreDetail;
import com.fanshuaiko.front.service.TestService;
import com.fanshuaiko.front.utils.ErrorCode;
import com.fanshuaiko.front.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName testController
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午5:38
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    /**
     * 根据学号查询考试信息
     *
     * @param studentNo
     * @return
     */
    @GetMapping("/student/{studentNo}")
    public ResultData queryTest(@PathVariable("studentNo") String studentNo) {
        try {
            ResultData resultData = testService.queryTest(studentNo);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/queryTest:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 根据考试编码查询该场考试的所有试题
     *
     * @param testNo
     * @return
     */
    @GetMapping("/{testNo}")
    public ResultData getQuestion(@PathVariable("testNo") long testNo) {
        try {
            ResultData resultData = testService.getQuestion(testNo);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/getQuestion:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 提交考试，保存学生答题情况
     *
     * @param scoreDetailList
     * @return
     */
    @PostMapping("/commit")
    public ResultData commitTest(@RequestBody List<ScoreDetail> scoreDetailList) {
        try {
            ResultData resultData = testService.commitTest(scoreDetailList);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/commitTest:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, "提交失败！");
        }
    }
}
