package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.service.CourseService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CourseController
 * @Description 课程Controller
 * @Author fanshuaiko
 * @Date 19-3-25 下午5:43
 * @Version 1.0
 **/
@RestController
@RequestMapping("/course")
public class CourseController {
    private static Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping("/course")
    public ResultData queryAll() {
        try {
            ResultData resultData= courseService.queryAll();
            return resultData;
        } catch (Exception e) {
            log.info("--------course/queryAll:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.DEL_FAILOR, ErrorCode.DEL_FAILOR_MSG);
        }
    }
}
