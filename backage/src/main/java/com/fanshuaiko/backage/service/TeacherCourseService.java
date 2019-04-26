package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.VO.TeacherCourseVo;
import com.fanshuaiko.backage.utils.ResultData;

/**
 * @ClassName TeacherCourseService
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-26 上午10:12
 * @Version 1.0
 **/
public interface TeacherCourseService {

    /**
     * 多添件分页查询教师及权限
     * @param teacherCourseVo
     * @return
     */
    ResultData pageQueryCourseAuthority(TeacherCourseVo teacherCourseVo);

    /**
     * 修改教师课程权限
     * @param teacherCourseVo
     * @return
     */
    ResultData editCourseAuthority(TeacherCourseVo teacherCourseVo);
}
