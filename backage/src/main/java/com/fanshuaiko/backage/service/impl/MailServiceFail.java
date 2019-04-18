package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.entity.mail.ScoreParams;
import com.fanshuaiko.backage.service.MailService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MailServiceFail
 * @Description
 * @Author fanshuaiko
 * @Date 19-4-18 下午7:30
 * @Version 1.0
 **/
@Component
@RestController
public class MailServiceFail implements MailService {

    @Override
    public String sendHTMLMail(List<ScoreParams> paramsList) {
        return "调用失败，当前邮件服务不可用";
    }
}
