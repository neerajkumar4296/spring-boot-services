package com.weatherinfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SpringBootRestInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestInfoApplication.class, args);
	}

}
