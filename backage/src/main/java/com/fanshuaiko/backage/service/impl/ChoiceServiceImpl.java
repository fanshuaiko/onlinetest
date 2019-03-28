package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.dao.CourseDao;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.VO.QuestionQueryTerm;
import com.fanshuaiko.backage.service.ChoiceService;
import com.fanshuaiko.backage.utils.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


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

    @Autowired
    CourseDao courseDao;

    @Autowired
    private ImportUtilService importUtil;


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

    public ResultData updateByPrimaryKey(Choice choice) {
        int count = choiceDao.updateByPrimaryKeySelective(choice);
        return ResultData.newSuccessResultData(count);
    }

    public ResultData<PageInfo<Choice>> choicePageQuery(QuestionQueryTerm queryTerm) {
        PageHelper.startPage(queryTerm.getPageNum(), queryTerm.getPageSize());
        List<Choice> choiceList = choiceDao.queryByConditions(queryTerm);
        PageInfo<Choice> pageInfo = new PageInfo<>(choiceList);
        return ResultData.newSuccessResultData(pageInfo);
    }

    public ResultData importChoice(MultipartFile file, String type) {
        try {
            ResultData resultData = importUtil.checkImportChoice(file, type);
            if (resultData.getData() == null) {
                return resultData;
            }
            int count = choiceDao.batchAdd((List<Choice>) resultData.getData());
            return ResultData.newSuccessResultData(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, ErrorCode.ADD_FAILOR_MSG);
        }

    }


}
