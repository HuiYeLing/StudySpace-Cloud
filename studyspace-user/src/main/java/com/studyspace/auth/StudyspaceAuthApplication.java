package com.studyspace.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudyspaceAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyspaceAuthApplication.class, args);
	}

}
