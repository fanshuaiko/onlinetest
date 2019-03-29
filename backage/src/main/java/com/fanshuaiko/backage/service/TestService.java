package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.VO.TestVO;
import com.fanshuaiko.backage.utils.ResultData;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName TestService
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-27 下午1:56
 * @Version 1.0
 **/
public interface TestService {

    /**
     * 新建考试
     *
     * @param testVo
     * @return
     */
    ResultData createTest(TestVO testVo);

    /**
     * 上传选择题，缓存到redis
     *
     * @param file
     * @param type
     * @return
     */
    ResultData uploadChoice(MultipartFile file, String type);

    /**
     * 上传主观题，缓存到redis
     *
     * @param file
     * @param type
     * @return
     */
    ResultData uploadSubjective(MultipartFile file, String type);

    /**
     * 删除考试
     *
     * @param id
     * @return
     */
    ResultData deleteTest(Long id);

    /**
     * 根据教师编号分页查询考试
     *
     * @param pageNum
     * @param pageSize
     * @param teacherNo
     * @return
     */
    ResultData pageQueryTest(int pageNum, int pageSize, String teacherNo);
}
