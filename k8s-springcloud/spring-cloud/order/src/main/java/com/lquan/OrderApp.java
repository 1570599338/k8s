package com.lquan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: spring-cloud
 * @description:
 * @author: lquan
 * @create: 2023-04-06 13:51
 **/
@SpringBootApplication
@EnableEurekaClient
public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class);
    }
}
