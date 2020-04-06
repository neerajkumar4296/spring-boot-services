package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

public class AppRestWebServiceConfig {

	
	@Bean
	@Scope(scopeName = "prototype")
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
