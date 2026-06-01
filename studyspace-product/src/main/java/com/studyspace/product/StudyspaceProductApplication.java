package com.studyspace.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现，使得该服务可以被注册到服务注册中心，并且能够发现其他服务
@EnableFeignClients//启用Feign客户端功能，使得该服务可以通过声明式的方式调用其他服务的接口
public class StudyspaceProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyspaceProductApplication.class, args);
    }
}