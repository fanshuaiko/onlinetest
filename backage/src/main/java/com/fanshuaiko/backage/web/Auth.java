package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.utils.ResultData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

/**
 * @ClassName Login
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-20 上午10:54
 * @Version 1.0
 **/
@RestController
@RequestMapping("/auth")
public class Auth {

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    Logger logger = LoggerFactory.getLogger(Auth.class);

    @PostMapping("/login")
    public ResultData login(@RequestParam String username, @RequestParam String password) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        String sessionId = (String) session.getId();
        return ResultData.newResultData(sessionId);
    }

    @PostMapping("/logout")
    public ResultData logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            String sessionId = (String) session.getId();
            logger.info("sessionId{}", sessionId);
            JedisShardInfo shardInfo = new JedisShardInfo("redis://"+host+port);
            Jedis jedis = new Jedis(shardInfo);
            long jedis_key = jedis.del("shiro:session:" + sessionId);
            logger.info("jedis_key{}", jedis_key);
            logger.info("--------数据已经删除--------");
            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return ResultData.newResultData("注销失败！");
        }

    }
}
