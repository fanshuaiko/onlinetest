package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.entity.VO.TestVO;
import com.fanshuaiko.backage.service.TestService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName TestController
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-27 下午1:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    private static Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    /**
     * 新建考试
     *
     * @param testVo
     * @return
     */
    @PostMapping("/test")
    public ResultData createTest(@RequestBody TestVO testVo) {
        try {
            ResultData resultData = testService.createTest(testVo);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/createTest:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
    }

    /**
     * 删除考试
     *
     * @param id
     * @return
     */
    @DeleteMapping("/test/{id}")
    public ResultData deleteTest(@PathVariable("id") Long id) {
        try {
            ResultData resultData = testService.deleteTest(id);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/deleteTest:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
    }

    /**
     * 根据教师编号分页查询考试
     *
     * @param pageNum
     * @param pageSize
     * @param teacherNo
     * @return
     */
    @GetMapping("/test/{pageNum}/{pageSize}/{teacherNo}")
    public ResultData pageQueryTest(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize,
                                    @PathVariable("teacherNo") String teacherNo) {
        try {
            ResultData resultData = testService.pageQueryTest(pageNum, pageSize, teacherNo);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/pageQueryTest:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 上传选择题，缓存到redis
     *
     * @param file
     * @param type
     * @return
     */
    @PostMapping("/choice")
    public ResultData uploadChoice(MultipartFile file, String type) {
        try {
            ResultData resultData = testService.uploadChoice(file, type);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/uploadChioce:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "上传失败！");
        }
    }

    /**
     * 上传主观题，缓存到redis
     *
     * @param file
     * @param type
     * @return
     */
    @PostMapping("/subjective")
    public ResultData uploadSubjective(MultipartFile file, String type) {
        try {
            ResultData resultData = testService.uploadSubjective(file, type);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/uploadSubjective:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "上传失败！");
        }
    }
}
