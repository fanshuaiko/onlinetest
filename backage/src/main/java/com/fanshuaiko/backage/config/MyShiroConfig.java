package com.fanshuaiko.backage.config;

import com.fanshuaiko.backage.shrio.MySessionManager;
import com.fanshuaiko.backage.shrio.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyShiroConfig
 * @Description shiro的config信息，shiro中的session结合redis
 * @Author fanshuaiko
 * @Date 19-3-20 下午4:23
 * @Version 1.0
 **/
@Configuration
public class MyShiroConfig {
    /**
     * 一、在认证中：
     *    1.1，将加密算法定义好后扔到 MyShiroRealm中 也就是自己定义的realm中
     *    1.2，将MyShiroRealm定义后扔到SecurityManager中。
     *    1.3，后期用到session什么的，都被SecurityManager管理
     *
     * @return
     */


    /**
     * 二、配置session（用Redis存储）
     * 	  2.1 需要配置session，就需要将sessionManager配置在SecurityManager中。
     *    2.2 sessionManager需要交给Redis来管理，所以定义了RedisSessionDAO
     *    2.3 RedisSessionDAO中需要配置Redis的信息，所以定义RedisManager
     *
     * @return
     */


    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    //-----------------认证------------------

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myShiroRealm());
        defaultWebSecurityManager.setSessionManager(sessionManager());
        //自定义缓存实现，使用redis
//        defaultWebSecurityManager.setCacheManager();
        return defaultWebSecurityManager;
    }

    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 加密次数
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    //-------------redis-session--------------

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO());
        return mySessionManager;
    }



    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        redisSessionDAO.setExpire(10000);
        return redisSessionDAO;
    }

    /**
     * 配置shiro redisManager
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
//        redisManager.setPassword(password);
        return redisManager;
    }

}
