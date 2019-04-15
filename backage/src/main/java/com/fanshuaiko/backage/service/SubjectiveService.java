package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.VO.QuestionQueryTerm;
import com.fanshuaiko.backage.entity.Subjective;
import com.fanshuaiko.backage.utils.ResultData;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName SubjectiveService
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-25 下午4:35
 * @Version 1.0
 **/
public interface SubjectiveService {
    /**
     * 插入一道题目
     * @param subjective
     * @return
     */
    ResultData insert(Subjective subjective);
    /**
     * 根据id删除题目
     * @param id
     * @return
     */
    ResultData deleteByPrimaryKey(long id);
    /**
     * 修改题目
     * @param subjective
     * @return
     */
    ResultData updateByPrimaryKey(Subjective subjective);
    /**
     * 多条件分页查询题目
     * @param queryTerm
     * @return
     */
    ResultData<PageInfo<Subjective>> subjectivePageQuery(QuestionQueryTerm queryTerm);
    /**
     * 选择题导入
     * @param file
     * @param type
     * @return
     */
    ResultData importSubjective(MultipartFile file, String type);
}
