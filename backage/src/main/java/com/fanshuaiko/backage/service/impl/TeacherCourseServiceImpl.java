package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.TeacherCourseDao;
import com.fanshuaiko.backage.entity.VO.TeacherCourseVo;
import com.fanshuaiko.backage.service.TeacherCourseService;
import com.fanshuaiko.backage.utils.ResultData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        deleteCourseAuthority(teacherCourseVo);
        addCourseAuthority(teacherCourseVo);
        return ResultData.newSuccessResultData();
    }

    @Override
    public ResultData pageQueryCourseAuthority(TeacherCourseVo teacherCourseVo) {
        PageHelper.startPage(teacherCourseVo.getPageNum(),teacherCourseVo.getPageSize());
        List<TeacherCourseVo> teacherCourseVos = teacherCourseDao.selectByCondition(teacherCourseVo);
        PageInfo pageInfo = new PageInfo(teacherCourseVos);
        return ResultData.newSuccessResultData(pageInfo);
    }
}
