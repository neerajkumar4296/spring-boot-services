package com.weatherinfoservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class SpringBootRestInfoApplication {

	// run with default configuration
	  //public static void main(String[] args) {
	  //SpringApplication.run(SpringBootRestInfoApplication.class, args); }
	 

    // for building the Spring boot application with custom configuration properties file
	public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(SpringBootRestInfoApplication.class)
                .properties("spring.config.name:application")
                .build()
                .run(args);
    }
}
