package com.weatherinfoservice.repositories;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.weatherinfoservice.model.WeatherReport;

@Repository
public class OpenWeatherRestRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(OpenWeatherRestRepository.class);

		
	@Value("${openweatherapi.basicuri: }")
	private String weatherServiceBasicUri;

	@Value("${openweatherapi.responseunitmetric}")
	private String responseUnit;

	@Value("${openweatherapi.responsemodehtml}")
	private String responseMode;

	@Value("${openweatherapi.apiparam}")
	private String apiParam;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	public String getWeatherServicApiUrl(String location) {
		StringBuilder apiUrlBuilder = new StringBuilder();
		logger.info("getWeatherServicApiUrl called for location:: " + location);
		return apiUrlBuilder.append(weatherServiceBasicUri).append(location).append(responseUnit).append(apiParam)
				.toString();

	}
	
	public WeatherReport getWeatherReport(String location) {
		logger.info("making api request to service endpoint url for weather report::");
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(headers);
		ResponseEntity<WeatherReport> responseEntity = restTemplate.exchange(getWeatherServicApiUrl(location),
				HttpMethod.GET, httpEntity, WeatherReport.class);
		return responseEntity.getBody();
	}

}
