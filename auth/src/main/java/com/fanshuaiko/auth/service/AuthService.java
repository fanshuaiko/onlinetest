package com.fanshuaiko.auth.service;

import com.fanshuaiko.auth.entity.Auth;

/**
 * @ClassName AuthService
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-20 下午4:09
 * @Version 1.0
 **/
public interface AuthService {
    /**
     * 根据用户名查询密码
     * @param username
     * @return
     */
    String getPassByNo(String username);

    /**
     * 根据用户名查询认证信息
     * @param username
     * @return
     */
    Auth getAuthByNo(String username);
}
