package com.springboot.springbootcloudconfigserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableConfigServer
@EnableEncryptableProperties
public class SpringbootCloudConfigServerApplication {

	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(SpringbootCloudConfigServerApplication.class, args); }
	 */
	
	 // for building the Spring boot application with custom configuration properties file
		public static void main(String[] args) throws Exception {
	        new SpringApplicationBuilder(SpringbootCloudConfigServerApplication.class)
	                // for providing the secret pass to jasypt at application start up for decryption of properties
	                .properties("jasypt.encryptor.password:topsecret")
	                .build()
	                .run(args);
	    }

}
