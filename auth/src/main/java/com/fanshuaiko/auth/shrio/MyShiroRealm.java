package com.fanshuaiko.auth.shrio;

import com.fanshuaiko.auth.entity.Auth;
import com.fanshuaiko.auth.service.AuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName MyShiroRealm
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-20 下午4:41
 * @Version 1.0
 **/
public class MyShiroRealm extends AuthorizingRealm {

    private  static final Logger log = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private AuthService authService;

    //授权
    //我没有做角色所以用不到这个方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.从数据库查询该用户的角色
        //2.将用户角色设置
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.setRoles(roles);
//        return simpleAuthorizationInfo；
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String no = (String) authenticationToken.getPrincipal();
        Object password = authenticationToken.getCredentials();

        if(null == no){
            throw new UnknownAccountException("用户名不能为空！");
        }
        if(null == password){
            throw new UnknownAccountException("密码不能为空！");
        }
        log.info("------------用户登陆编号:"+no);
        log.info("------------用户输入的登陆密码:"+password.toString());
        Auth auth = authService.getAuthByNo(no);
        if(auth==null){
            throw new UnknownAccountException("用户不存在！");
        }
        String passwordDataSource = auth.getPassword();
        log.info("------------数据库用户密码:"+passwordDataSource);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                auth, auth.getPassword(),ByteSource.Util.bytes(auth.getSalt()),getName());
        return simpleAuthenticationInfo;
    }
}
