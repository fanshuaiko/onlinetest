package com.fanshuaiko.backage.service.impl;

import com.fanshuaiko.backage.dao.AuthDao;
import com.fanshuaiko.backage.entity.Auth;
import com.fanshuaiko.backage.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName AuthServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-20 下午4:13
 * @Version 1.0
 **/
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Override
    public String getPassByNo(String username) {
        return authDao.getPassByNo(username);
    }

    @Override
    public Auth getAuthByNo(String username) {
        return authDao.getAuthByNo(username);
    }
}
