package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.utils.ResultData;

/**
 * @ClassName ChoiceServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-22 上午10:08
 * @Version 1.0
 **/
public interface ChoiceService {
    ResultData insert(Choice choice);
}
