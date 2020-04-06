package com.weatherinfoservice.delegate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.weatherinfoservice.exceptions.BadServiceRequestException;
import com.weatherinfoservice.util.ApplicationUtil;

@Component
public class ApplicationDelegate {

	public String getLocalisedResponse(String attributeName, Optional<String> languageCode) {

		String result = null;

		if (languageCode.isPresent())
		{
			switch (languageCode.get().toUpperCase()) {
			
			case "FR":
				
				Map<String, String> localeValuesMap = ApplicationUtil.getLocalisedMapValues();
				if(localeValuesMap.containsKey(attributeName.toUpperCase())) {
					result = "Attribute:: " + attributeName + " in language code:: " + languageCode.get() + " is->> "
							+ localeValuesMap.get(attributeName.toUpperCase());
				}
				
				else
					throw new BadServiceRequestException("Unknown Attribute name....couldn't get french value");
					break;	

			case "EN":
				result = "Attribute:: " + attributeName + " in language code " + languageCode.get() + " is->> "
						+ attributeName+ " Only";
               break;
               
			default:
				throw new BadServiceRequestException("Language Code other than FR and EN or unknown Attribute name was provided or both");
			}
			return result;
		}
		return "Attribute:: " + attributeName + " in language code EN is->> "
					+ attributeName+ " Only";
		 


	}

	public String analyzeContentInTheFile(MultipartFile inputFile) throws IOException {
		if(inputFile.isEmpty()) {
			throw new BadServiceRequestException("Input File Provide is Blank...Kindly Check");
		}
		BufferedReader reader= new BufferedReader(new InputStreamReader(inputFile.getInputStream()));
		reader.lines().mapToInt(String::length).max().getAsInt();
			return reader.lines()
					.max(Comparator.comparingInt(String::length))
					.get();
		}
		

	

}
