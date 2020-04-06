package com.weatherinfoservice.exceptions;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateExceptionHandler implements ResponseErrorHandler  {

	public static final Logger logger= LoggerFactory.getLogger(RestTemplateExceptionHandler.class);
	public static final String RESPONSE_ERROR_MESSAGE= "Invalid City Name:: Please Make Sure You Spell the City Name Correctly";
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		HttpStatus status = response.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		logger.error("ResponseBody: {}", RESPONSE_ERROR_MESSAGE);
        throw new BadServiceRequestException(RESPONSE_ERROR_MESSAGE);
    }
	
		
}


