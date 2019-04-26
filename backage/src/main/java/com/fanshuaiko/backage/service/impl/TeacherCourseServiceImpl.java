package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.TeacherCourseDao;
import com.fanshuaiko.backage.entity.VO.TeacherCourseVo;
import com.fanshuaiko.backage.service.TeacherCourseService;
import com.fanshuaiko.backage.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName TeacherCourseServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-26 上午10:12
 * @Version 1.0
 **/
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    private TeacherCourseDao teacherCourseDao;

    @Override
    public ResultData addCourseAuthority(TeacherCourseVo teacherCourseVo) {
        int i = teacherCourseDao.insertTeacherCourseVo(teacherCourseVo);
        return ResultData.newSuccessResultData(i);
    }

    @Override
    public ResultData deleteCourseAuthority(TeacherCourseVo teacherCourseVo) {
        int i = teacherCourseDao.deleteByTeacherNo(teacherCourseVo.getTeacherNo());
        return ResultData.newSuccessResultData(i);
    }

    @Override
    public ResultData updateCourseAuthority(TeacherCourseVo teacherCourseVo) {
        return null;
    }

    @Override
    public ResultData pageQueryCourseAuthority(TeacherCourseVo teacherCourseVo) {
        return null;
    }
}
