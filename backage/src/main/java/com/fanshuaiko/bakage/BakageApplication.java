package com.fanshuaiko.bakage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BakageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakageApplication.class, args);
    }

}
