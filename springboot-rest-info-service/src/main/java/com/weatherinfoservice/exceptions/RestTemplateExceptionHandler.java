package com.weatherinfoservice.exceptions;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTemplateExceptionHandler implements ResponseErrorHandler  {

	public static final Logger logger= LoggerFactory.getLogger(RestTemplateExceptionHandler.class);
	

	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		HttpStatus status = response.getStatusCode();
        return status.is4xxClientError() || status.is5xxServerError();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		/*
		 * String result = new BufferedReader(new InputStreamReader(response.getBody()))
		 * .lines().collect(Collectors.joining("\n"));
		 */
		ObjectMapper errorResponseMapper= new ObjectMapper();
		Map<String, String> responseErrorMap=  errorResponseMapper.readValue(response.getBody(), Map.class);
		String responseMessage= responseErrorMap.get("message");
		
		if(response.getStatusCode()==HttpStatus.BAD_REQUEST) {
			logger.error("ResponseBody: {}", responseMessage);
	        throw new BadServiceRequestException(responseMessage);
		}
		else
		{
			logger.error("ResponseBody: {}", responseMessage);
	        throw new HttpServerErrorException(response.getStatusCode(),  responseMessage);
		}
    }
	
	

	
		
}


