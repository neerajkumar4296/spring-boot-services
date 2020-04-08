package com.weatherinfoservice.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.weatherinfoservice.exceptions.BadServiceRequestException;

@Component
public class ApplicationDelegate {

	@Autowired
	private RestTemplate restUtilServiceTemplate;

	public String getLocalisedResponse(String attributeName, Optional<String> languageCode) {

		String result = null;
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("attributename", attributeName);
		//pathParams.put("languagecode", languageCode.get());
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromUriString("http://REST-UTIL-SERVICE/application/getLocale/{attributename}");
		
		uriBuilder= languageCode.isPresent() ? uriBuilder.queryParam("languageCode", languageCode.get()): uriBuilder.queryParam("languageCode", StringUtils.EMPTY); 
		
		URI uri= uriBuilder.buildAndExpand(pathParams).toUri();
		System.out.println("calling util service uri:: " +uri.toString());
		result = restUtilServiceTemplate.getForObject(uri, String.class);
		System.out.println("result returned:: " + result);
		return result;

	}

	public String analyzeContentInTheFile(MultipartFile inputFile) throws IOException {
		if (inputFile.isEmpty()) {
			throw new BadServiceRequestException("Input Text File Provided is Blank...Kindly Check");
		}
		// return
		// restUtilServiceTemplate.postForObject("http://rest-util-service/file/uploadfile",
		// inputFile, String.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.getInputStream()));
		return reader.lines().max(Comparator.comparingInt(String::length)).get();

	}
}
