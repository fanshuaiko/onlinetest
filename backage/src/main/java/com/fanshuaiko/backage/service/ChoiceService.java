package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.QuestionQueryTerm;
import com.fanshuaiko.backage.utils.ResultData;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ChoiceServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-22 上午10:08
 * @Version 1.0
 **/
public interface ChoiceService {
    /**
     * 插入一道题目
     * @param choice
     * @return
     */
    ResultData insert(Choice choice);

    /**
     * 根据id删除题目
     * @param id
     * @return
     */
    ResultData deleteByPrimaryKey(long id);

    /**
     * 修改题目
     * @param choice
     * @return
     */
    ResultData updateByPrimaryKey(Choice choice);

    /**
     * 多条件分页查询题目
     * @param queryTerm
     * @return
     */
    ResultData<PageInfo<Choice>> choicePageQuery(QuestionQueryTerm queryTerm);

    /**
     * 选择题导入
     * @param file
     * @return
     */
    ResultData importChoice(MultipartFile file,String type);
}
