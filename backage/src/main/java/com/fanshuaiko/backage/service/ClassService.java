package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.utils.ResultData;

/**
 * @ClassName ClassService
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午1:29
 * @Version 1.0
 **/
public interface ClassService {
    /**
     * 根据学校编码查询学院
     *
     * @param college_no
     * @return
     */
    ResultData queryInstituteByCollegeNo(String college_no);

    /**
     * 根据学院编码查询专业
     *
     * @param instituteNo
     * @return
     */
    ResultData queryMajorByInstituteNo(String instituteNo);

    /**
     * 根据专业编码询班级
     *
     * @param majorNo
     * @return
     */
    ResultData queryClassByMajorNo(String majorNo);
}
