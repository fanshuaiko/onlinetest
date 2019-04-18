package com.fanshuaiko.backage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.fanshuaiko.backage.dao")
public class BackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackageApplication.class, args);
    }

}
