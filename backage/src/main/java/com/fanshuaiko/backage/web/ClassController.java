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
     *
     * @param collegeNo
     * @return
     */
    @GetMapping("/institute/{collegeNo}")
    public ResultData queryInstituteByCollegeNo(@PathVariable("collegeNo") String collegeNo) {
        try {
            ResultData resultData = classService.queryInstituteByCollegeNo(collegeNo);
            return resultData;
        } catch (Exception e) {
            log.info("--------class/queryInstituteByCollegeNo:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 根据学院编码查询专业
     *
     * @param instituteNo
     * @return
     */
    @GetMapping("/major/{instituteNo}")
    public ResultData queryMajorByInstituteNo(@PathVariable("instituteNo") String instituteNo) {
        try {
            ResultData resultData = classService.queryMajorByInstituteNo(instituteNo);
            return resultData;
        } catch (Exception e) {
            log.info("--------class/queryMajorByInstituteNo:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }
}
