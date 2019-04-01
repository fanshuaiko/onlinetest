package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.service.ClassService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Class
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 上午11:39
 * @Version 1.0
 **/
@RestController
@RequestMapping("/class")
public class ClassController {
    private static Logger log = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private ClassService classService;

    /**
     * 根据学校编码查询学院
     * @param college_no
     * @return
     */
    @GetMapping("/college/{college_no}")
    public ResultData queryInstituteByCollegeNo(@PathVariable("college_no") String college_no) {
        try {
            ResultData resultData = classService.queryInstituteByCollegeNo(college_no);
            return resultData;
        } catch (Exception e) {
            log.info("--------test/createTest:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
    }
}
