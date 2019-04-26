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
    public ResultData pageQueryCourseAuthority(TeacherCourseVo teacherCourseVo) {
        PageHelper.startPage(teacherCourseVo.getPageNum(),teacherCourseVo.getPageSize());
        List<TeacherCourseVo> teacherCourseVos = teacherCourseDao.selectByCondition(teacherCourseVo);
        PageInfo pageInfo = new PageInfo(teacherCourseVos);
        return ResultData.newSuccessResultData(pageInfo);
    }

    @Override
    public ResultData editCourseAuthority(TeacherCourseVo teacherCourseVo) {
        //如果传过来没有课程，要么就是之前有数据，现在要删除所有数据，要么就是之前就没数据，这两种情况都可以直接调用删除方法来执行
        //如果传过来有课程，要么就是之前没课程，现在新增课程；要么就是之前有课程，但是修改了其他课程。这两种情况均可简单到先删除数据库对应数据，再插入新的数据。当然有更加好的做法，但是这里没必要
        if(teacherCourseVo.getCourseIds().size()==0){
            int i = teacherCourseDao.deleteByTeacherNo(teacherCourseVo.getTeacherNo());
            return ResultData.newSuccessResultData(i);
        }else{
            teacherCourseDao.deleteByTeacherNo(teacherCourseVo.getTeacherNo());
            teacherCourseDao.insertTeacherCourseVo(teacherCourseVo);
            return ResultData.newSuccessResultData();
        }
    }
}
