package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.service.ChoiceService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName QuestionController
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-21 下午1:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("question")
public class QuestionController {

    private static Logger log = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    ChoiceService choiceService;

    @PostMapping("/choice")
    public ResultData insertChoice(Choice choice) {
        try {
            ResultData resultData = choiceService.insert(choice);
            return resultData;
        } catch (Exception e) {
            log.info("--------insertChoice:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR, ErrorCode.ADD_FAILOR_MSG);
        }
    }
}
