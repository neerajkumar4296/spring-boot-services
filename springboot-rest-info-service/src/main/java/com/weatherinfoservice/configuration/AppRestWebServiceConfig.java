package com.weatherinfoservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.weatherinfoservice.exceptions.RestTemplateExceptionHandler;

@Configuration
public class AppRestWebServiceConfig {

	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate= new RestTemplate();
		restTemplate.setErrorHandler(new RestTemplateExceptionHandler());
		return restTemplate;
	}
}
