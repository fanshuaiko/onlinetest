package com.fanshuaiko.mail;

import com.fanshuaiko.mail.entity.ScoreParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @ClassName MailController
 * @Description 邮件发送
 * @Author fanshuaiko
 * @Date 19-4-18 下午5:11
 * @Version 1.0
 **/
@RestController
@RequestMapping("/mail")
public class MailController {
    private Logger log = LoggerFactory.getLogger(MailController.class);

    //发件账号
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    //注入模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 发送分数邮件
     * @param scoreParams
     */
    @PostMapping("/score")
    public String sendHTMLMail(@RequestBody ScoreParams scoreParams) {
        Context context = new Context();
        //设置模板页面中的参数
        context.setVariable("name",scoreParams.getName());
        context.setVariable("studentNo",scoreParams.getStudentNo());
        context.setVariable("totalScore",scoreParams.getTotalScore());
        context.setVariable("averageScore",scoreParams.getAverageScore());
        context.setVariable("maxScore",scoreParams.getMaxScore());
        context.setVariable("sort",scoreParams.getSort());
        context.setVariable("studentCount",scoreParams.getStudentNo());
        context.setVariable("passRate",scoreParams.getPassRate());
        //设置模板邮件的内容
        String emailContent = templateEngine.process("ScoreTemplate",context);

        log.info("开始发送HTML邮件：{}，{}，{}，{}",from,scoreParams.getToMailAddress(),scoreParams.getTestName(),emailContent);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);//true表示需要创建一个multipart message
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(scoreParams.getToMailAddress());
            mimeMessageHelper.setSubject(scoreParams.getTestName()+"成绩单");
            mimeMessageHelper.setText(emailContent,true);//true代表有附件
            javaMailSender.send(mimeMessage);
            log.info("邮件发送成功！");
            return "邮件发送成功！";
        } catch (MessagingException e) {
            log.error("邮件发送失败！", e);
            return "邮件发送失败！";

        }
    }


}
