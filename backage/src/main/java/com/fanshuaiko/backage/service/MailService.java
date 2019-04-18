package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.mail.ScoreParams;
import com.fanshuaiko.backage.service.impl.MailServiceFail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ClassName MailService
 * @Description 邮件服务接口类
 * @Author fanshuaiko
 * @Date 19-4-18 下午6:38
 * @Version 1.0
 **/
@Service
//要调用的服务名
@FeignClient(value = "mail",fallback = MailServiceFail.class)
public interface MailService {

    @PostMapping(value = "/mail/score",consumes = "application/json")
    String sendHTMLMail(List<ScoreParams> paramsList);
}
