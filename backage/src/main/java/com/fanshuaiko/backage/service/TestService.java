package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.VO.TestVO;
import com.fanshuaiko.backage.utils.ResultData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * @param ids
     * @return
     */
    ResultData deleteTest(List<Long> ids);

    /**
     * 根据教师编号分页查询考试
     *
     * @param pageNum
     * @param pageSize
     * @param teacherNo
     * @return
     */
    ResultData pageQueryTest(int pageNum, int pageSize, String teacherNo);

    /**
     * 根据题目id查询题目细节
     *
     * @param testNo
     * @return
     */
    ResultData queryQuestionDetail(long testNo);

    /**
     * 获取考试分析数据
     *
     * @param testNo
     * @return
     */
    ResultData getTestAnalyzeData(Long testNo);
}
