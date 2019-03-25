package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.CourseDao;
import com.fanshuaiko.backage.entity.Course;
import com.fanshuaiko.backage.service.CourseService;
import com.fanshuaiko.backage.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName CourseServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-25 上午10:28
 * @Version 1.0
 **/
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseDao courseDao;

    @Override
    public ResultData queryAll() {
        List<Course> courseList= courseDao.queryAll();
        return ResultData.newSuccessResultData(courseList);
    }
}
