package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.dict.QuestionType;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.QuestionQueryTerm;
import com.fanshuaiko.backage.service.ChoiceService;
import com.fanshuaiko.backage.utils.ResultData;
import com.fanshuaiko.backage.utils.SnowflakeIdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ChoiceServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-22 上午10:08
 * @Version 1.0
 **/
@Service
public class ChoiceServiceImpl implements ChoiceService {

    @Autowired
    private ChoiceDao choiceDao;

    @Override
    public ResultData insert(Choice choice) {
        choice.setId(SnowflakeIdWorker.nextId());//生成id
        int count = choiceDao.insert(choice);
        return ResultData.newSuccessResultData(count);
    }

    @Override
    public ResultData deleteByPrimaryKey(long id) {
        int count = choiceDao.deleteByPrimaryKey(id);
        return ResultData.newSuccessResultData(count);
    }

    public ResultData updateByPrimaryKey(Choice choice){
        int count = choiceDao.updateByPrimaryKey(choice);
        return ResultData.newSuccessResultData(count);
    }

    public ResultData<PageInfo<Choice>> choicePageQuery(QuestionQueryTerm queryTerm){
        PageHelper.startPage(queryTerm.getPageNum(),queryTerm.getPageSize());
        List<Choice> choiceList = choiceDao.queryByConditions(queryTerm);
        PageInfo<Choice> pageInfo = new PageInfo<>(choiceList);
        return ResultData.newSuccessResultData(pageInfo);
    }
}
