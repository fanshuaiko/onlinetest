package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.mail.ScoreParams;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName MailService
 * @Description 邮件服务接口类
 * @Author fanshuaiko
 * @Date 19-4-18 下午6:38
 * @Version 1.0
 **/
@Service
//要调用的服务名
@FeignClient(value = "mail")
public interface MailService {

    @PostMapping("/score")
    String sendHTMLMail(ScoreParams scoreParams);
}
