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
     * 添加教师课程权限
     * @param teacherCourseVo
     * @return
     */
    @PostMapping("/course")
    public ResultData addCourseAuthority(@RequestBody TeacherCourseVo teacherCourseVo) {
        try {
            ResultData resultData = teacherCourseService.addCourseAuthority(teacherCourseVo);
            return resultData;
        } catch (Exception e) {
            log.info("--------CourseAuthorityController/addCourseAuthority:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
    }

    /**
     * 删除教师课程权限
     * @param teacherCourseVo
     * @return
     */
    @DeleteMapping("/course")
    public ResultData deleteCourseAuthority(@RequestBody TeacherCourseVo teacherCourseVo) {
        try {
            ResultData resultData = teacherCourseService.deleteCourseAuthority(teacherCourseVo);
            return resultData;
        } catch (Exception e) {
            log.info("--------CourseAuthorityController/deleteCourseAuthority:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.DEL_FAILOR, ErrorCode.DEL_FAILOR_MSG);
        }
    }

    /**
     * 修改教师课程权限
     * @param teacherCourseVo
     * @return
     */
    @PutMapping("/course")
    public ResultData updateCourseAuthority(@RequestBody TeacherCourseVo teacherCourseVo) {
        try {
            ResultData resultData = teacherCourseService.updateCourseAuthority(teacherCourseVo);
            return resultData;
        } catch (Exception e) {
            log.info("--------CourseAuthorityController/updateCourseAuthority:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.UPDATE_FAILOR, ErrorCode.UPDATE_FAILOR_MSG);
        }
    }

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
}
