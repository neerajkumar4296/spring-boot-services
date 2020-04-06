package com.example.demo.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppSwaggerConfig 
{
	private static final Logger LOGGER = LoggerFactory.getLogger( AppSwaggerConfig.class );
	
	private static final String SPRING_BOOT_SERVICE_TITLE = "Spring Boot Rest WebService";

	private static final String SPRING_BOOT_SERVICE_DESCRIPTION = "spring boot rest webService for some utitlity function";

	private static final String RESOURCES_BASE_PACKAGE = "com.example.demo";

	private static final String AUTHORIZATION = "Authorization";

	@Bean
	public Docket apiDocklet()
	{
		LOGGER.info( "Configuring Swagger into the app" );
		return new Docket( DocumentationType.SWAGGER_2 )
						.directModelSubstitute( LocalDate.class, java.sql.Date.class )
						.directModelSubstitute( LocalDateTime.class, java.util.Date.class )
						.apiInfo( apiInfo() )
						.select()
						.apis( RequestHandlerSelectors.basePackage( RESOURCES_BASE_PACKAGE ) )
						.paths( PathSelectors.any() )
						.build().securitySchemes( Arrays.asList( apiKey() ) );
	}

	private ApiKey apiKey()
	{
		return new ApiKey( AUTHORIZATION, AUTHORIZATION, "header" );
	}

	private ApiInfo apiInfo()
	{
		return new ApiInfoBuilder()
						.title( SPRING_BOOT_SERVICE_TITLE )
						.description( SPRING_BOOT_SERVICE_DESCRIPTION )
						.contact( new Contact( "Neeraj Kumar", "", "123@example.com" ) )
						.version( "2.0" )
						.build();
	}
}
