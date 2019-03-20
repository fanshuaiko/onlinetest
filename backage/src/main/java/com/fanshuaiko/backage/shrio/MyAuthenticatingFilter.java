package com.fanshuaiko.backage.shrio;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @ClassName MyAuthenticatingFilter
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-20 下午5:32
 * @Version 1.0
 **/
public class MyAuthenticatingFilter extends AuthenticatingFilter {



    private static final Logger log = LoggerFactory.getLogger(MyAuthenticatingFilter.class);

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        log.info("--------executeLogin---------");
//        MyAuthenticatingFilter formAuthen = new MyAuthenticatingFilter();
        AuthenticationToken token = this.createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            System.out.println("*******"+msg);
            throw new IllegalStateException(msg);
        }else{
            try {
                log.info("----------我进来进行核对了信息----------------");

                Subject subject = getSubject(request, response);

                subject.login(token);

                return this.onLoginSuccess(token, subject, request, response);
            } catch (AuthenticationException e) {
                System.out.println("-----onLoginFailure;---------");
                return this.onLoginFailure(token, e, request, response);
            }
        }

    }


    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        log.info("AuthenticatingFilterOverride--------onLoginSuccess------");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        return true;
    }


    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.info("AuthenticatingFilterOverride--------onLoginFailure------");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        JSONObject json = new JSONObject();
        String exc = e.getClass().getName();
        if(exc.equals(UnknownAccountException.class.getName())){
            json.put("fail", "账户不存在");
        }
        if(exc.equals(IncorrectCredentialsException.class.getName())){
            System.out.println("=========");
            json.put("fail", "密码不正确");
        }
        out.println(json);
        out.flush();
        out.close();
        return false;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return new MyFormAuthenticationFilter().createToken(servletRequest,servletResponse);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return new MyFormAuthenticationFilter().onAccessDenied(servletRequest,servletResponse);
    }
}
