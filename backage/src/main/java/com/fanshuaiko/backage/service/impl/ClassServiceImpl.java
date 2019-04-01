package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ClassDao;
import com.fanshuaiko.backage.dao.InstituteDao;
import com.fanshuaiko.backage.dao.MajorDao;
import com.fanshuaiko.backage.entity.Class;
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

    @Autowired
    private ClassDao classDao;

    @Override
    public ResultData queryInstituteByCollegeNo(String collegeNo) {
        List<Institute> instituteList = instituteDao.queryInstituteByCollegeNo(collegeNo);
        return ResultData.newSuccessResultData(instituteList);
    }

    @Override
    public ResultData queryMajorByInstituteNo(String instituteNo) {
        List<Major> majorList = majorDao.queryMajorByInstituteNo(instituteNo);
        return ResultData.newSuccessResultData(majorList);
    }

    @Override
    public ResultData queryClassByMajorNo(String majorNo) {
        List<Class> classList = classDao.queryClassByMajorNo(majorNo);
        return ResultData.newSuccessResultData(classList);
    }
}
