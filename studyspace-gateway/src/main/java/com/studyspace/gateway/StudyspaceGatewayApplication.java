package com.studyspace.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//启用服务注册与发现
public class StudyspaceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyspaceGatewayApplication.class, args);
    }

}
