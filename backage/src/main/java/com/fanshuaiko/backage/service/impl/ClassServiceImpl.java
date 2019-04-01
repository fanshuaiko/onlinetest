package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.InstituteDao;
import com.fanshuaiko.backage.dao.MajorDao;
import com.fanshuaiko.backage.entity.Institute;
import com.fanshuaiko.backage.entity.Major;
import com.fanshuaiko.backage.service.ClassService;
import com.fanshuaiko.backage.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ClassServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-1 下午1:29
 * @Version 1.0
 **/
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private InstituteDao instituteDao;
    @Autowired
    private MajorDao majorDao;

    @Override
    public ResultData queryInstituteByCollegeNo(String collegeNo) {
        List<Institute> instituteList = instituteDao.queryInstituteByCollegeNo(collegeNo);
        return ResultData.newSuccessResultData(instituteList);
    }

    @Override
    public ResultData queryMajorByInstituteNo(String instituteNo) {
        List<Major> instituteList = majorDao.queryMajorByInstituteNo(instituteNo);
        return ResultData.newSuccessResultData(instituteList);
    }
}
