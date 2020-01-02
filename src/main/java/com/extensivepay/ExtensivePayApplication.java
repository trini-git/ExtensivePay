package com.extensivepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ExtensivePayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtensivePayApplication.class, args);
	}

}
