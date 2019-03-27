package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.entity.VO.TestVO;
import com.fanshuaiko.backage.service.TestService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * @param testVo
     * @return
     */
    @PostMapping("/test")
    public ResultData createTest(TestVO testVo) {
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
     * 上传选择题，缓存到redis
     * @param file
     * @param type
     * @return
     */
    @PostMapping("/choice")
    public ResultData uploadChoice(MultipartFile file,String type){
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
     * @param file
     * @param type
     * @return
     */
    @PostMapping("/subjective")
    public ResultData uploadSubjective(MultipartFile file,String type){
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
