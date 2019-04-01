package com.fanshuaiko.backage;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.LinkedList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BakageApplicationTests {

    @Test
    public void createMD5() {
        String hashAlgorithmName = "md5";
        String credentials = "111111";//密码
        int hashIterations = 2;
        ByteSource credentialsSalt = ByteSource.Util.bytes("t10001");//账号
        System.out.println("credentialsSalt"+credentialsSalt);
        String  obj = new SimpleHash(hashAlgorithmName, credentials,credentialsSalt, hashIterations).toHex();
        System.out.println("========"+obj);
    }


    @Test
    public static void main(String[] args){
    }


}
