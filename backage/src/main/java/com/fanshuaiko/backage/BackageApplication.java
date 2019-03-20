package com.fanshuaiko.backage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackageApplication.class, args);
    }

}
