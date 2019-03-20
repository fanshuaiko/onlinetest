package com.fanshuaiko.backage.service;

import com.fanshuaiko.backage.entity.Auth;
import org.springframework.stereotype.Service;

/**
 * @ClassName AuthService
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-20 下午4:09
 * @Version 1.0
 **/
public interface AuthService {

    String getPassByNo(String no);

    Auth getAuthByNo(String no);
}
