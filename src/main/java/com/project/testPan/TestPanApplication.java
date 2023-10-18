package com.project.testPan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableAutoConfiguration
@EnableFeignClients
@SpringBootApplication
@EnableCaching
public class TestPanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestPanApplication.class, args);
	}

}
