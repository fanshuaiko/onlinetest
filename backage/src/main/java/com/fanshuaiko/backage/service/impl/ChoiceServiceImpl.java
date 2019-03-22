package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.service.ChoiceService;
import com.fanshuaiko.backage.utils.ResultData;
import com.fanshuaiko.backage.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        choice.setId(SnowflakeIdWorker.nextId());
        int insert = choiceDao.insert(choice);
        return ResultData.newSuccessResultData(insert);
    }
}
