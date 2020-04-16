package com.weatherinfoservice.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.weatherinfoservice.exceptions.BadServiceRequestException;

@Component
public class ApplicationDelegate {
	
	@Value(value = "${service.localeService.basicUri}")
	private String localeServiceBasicUri;

	@Autowired
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restUtilServiceTemplate;

	public String getLocalisedResponse(String attributeName, Optional<String> languageCode) {

		String result = null;
		Map<String, String> pathParams = new HashMap<>();
		pathParams.put("attributename", attributeName);
		//pathParams.put("languagecode", languageCode.get());
		UriComponentsBuilder uriBuilder = UriComponentsBuilder
				.fromUriString(localeServiceBasicUri);
		
		uriBuilder= languageCode.isPresent() ? 
				uriBuilder.queryParam("languageCode", languageCode.get()):
			    uriBuilder.queryParam("languageCode", StringUtils.EMPTY); 
		
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
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputFile.getInputStream()));
		//Optional<String> largestLine= reader.lines().sorted((str1, str2)-> str1.length()-str2.length()).findFirst();
		//List<String> filteredLines= reader.lines().filter( str1-> str1.startsWith("select")).collect(Collectors.toList());
		
		return reader.lines().max(Comparator.comparingInt(String::length)).get();

	}
}
