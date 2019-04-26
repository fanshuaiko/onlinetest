package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.entity.VO.TeacherCourseVo;
import com.fanshuaiko.backage.service.TeacherCourseService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CourseLimitController
 * @Description 权限管理Controller
 * @Author fanshuaiko
 * @Date 19-4-26 上午10:04
 * @Version 1.0
 **/
@RestController
@RequestMapping("/authority")
public class CourseAuthorityController {

    private static Logger log = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private TeacherCourseService teacherCourseService;

    /**
     * 多添件分页查询教师及权限
     * @param teacherCourseVo
     * @return
     */
    @GetMapping("/course")
    public ResultData pageQueryCourseAuthority(TeacherCourseVo teacherCourseVo) {
        try {
            ResultData resultData = teacherCourseService.pageQueryCourseAuthority(teacherCourseVo);
            return resultData;
        } catch (Exception e) {
            log.info("--------CourseAuthorityController/pageQueryCourseAuthority:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, ErrorCode.QUERY_FAILOR_MSG);
        }
    }

    /**
     * 修改教师课程权限
     * @param teacherCourseVo
     * @return
     */
    @PostMapping("/course")
    public ResultData editCourseAuthority(@RequestBody TeacherCourseVo teacherCourseVo) {
        try {
            ResultData resultData = teacherCourseService.editCourseAuthority(teacherCourseVo);
            return resultData;
        } catch (Exception e) {
            log.info("--------CourseAuthorityController/editCourseAuthority:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, ErrorCode.FAILOR_MSG);
        }
    }
}
