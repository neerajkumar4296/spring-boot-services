package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@EnableEurekaClient
public class SpringBootRestUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestUtilApplication.class, args);
		
	}
	
    @Bean
    public RestTemplate getRestTemplate() {
    	return new RestTemplate();
    }
    


}
