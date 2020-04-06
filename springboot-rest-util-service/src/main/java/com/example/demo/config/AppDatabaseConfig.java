package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppDatabaseConfig {

	/*
	 * @PropertySource(name = "datasource2.oracledb") Properties props;
	 */
	
	
	@Bean(name = "inmemoryfile1")
	@Primary
	@ConfigurationProperties(prefix = "datasource1.h2")
	public DataSource getInMemoryDb() {
	   return DataSourceBuilder.create().build();


	}

	@Bean(name = "inmemoryfile2")
	@ConfigurationProperties(prefix = "datasource2.h2")
	public DataSource getOracleDb() {		
		//System.out.println(props);
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name= "dbfile1")
	public JdbcTemplate getInMemoryJdbcTemplate() {
		return new JdbcTemplate(getInMemoryDb());
		
	}
	
	@Bean(name= "dbfile2")
	public JdbcTemplate getOracleJdbcTemplate() {
		return new JdbcTemplate(getOracleDb());
	}

}
