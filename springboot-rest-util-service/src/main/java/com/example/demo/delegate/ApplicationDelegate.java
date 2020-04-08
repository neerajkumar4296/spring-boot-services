package com.example.demo.delegate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.customexception.BadServiceRequestException;
import com.example.demo.customexception.CustomAppException;
import com.example.demo.dao.DataHandlerDao;
import com.example.demo.model.Person;
import com.example.demo.util.UtilityClass;


@Component
public class ApplicationDelegate {
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationDelegate.class);
	
	@Value("${otpserviceprovider.basicuri}")
	private String passwordProviderServiceUri;

	@Value("${otpserviceprovider.deafultpasslength}")
	private String lengthofpass;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	DataHandlerDao dataHandlerDao;
	
	
	
	public String getLocalisedResponse(String attributeName, Optional<String> languageCode) {
		
		/*
		 * return StringUtils.isEmpty(languageCode)? "Attribute:: " +attributeName+
		 * " in language code:: en is->> " +attributeName: "Attribute:: "
		 * +attributeName+ " in language code:: " +languageCode+ " is->> "
		 * +UtilityClass.getLocalisedMapValues().get(attributeName);
		 */
		String result = null;
		
		if (languageCode.isPresent()) {
			switch (languageCode.get().toUpperCase()) {
			case "FR":

				Map<String, String> localeValuesMap = UtilityClass.getLocalisedMapValues();
				if (localeValuesMap.containsKey(attributeName.toUpperCase())) {
					result = "Attribute:: " + attributeName + " in language code:: " + languageCode.get() + " is->> "
							+ localeValuesMap.get(attributeName.toUpperCase());
				}

				else {
					logger.error("Unknown Attribute name....couldn't get french value");
					throw new BadServiceRequestException("Unknown Attribute name....couldn't get french value");
				}
					
				break;

			case "EN":	
				
				result = "Attribute:: " + attributeName + " in language code " + languageCode.get() + " is->> "
						+ attributeName + " Only";
				break;

			default:
				logger.error(
						"Language Code other than FR and EN or unknown Attribute name was provided or both");
				throw new BadServiceRequestException(
						"Language Code other than FR and EN or unknown Attribute name was provided or both");
			}
			
		}
		return result;
		
	}

	public Person searchUser(int id) throws SQLException, CustomAppException {
		return dataHandlerDao.searchUser(id);
	}

	public List<Person> getAllUsers() throws SQLException {
		return dataHandlerDao.getAllUsers();
	}

	public void insertRecords() throws SQLException {
	dataHandlerDao.insertRecords();	
	}

	public int getCount(String tableName) throws SQLException, CustomAppException {
		return dataHandlerDao.getRecordsCount(tableName);
	}
	
	public String getRandomPass() {
		logger.info("calling rest service for password: " +passwordProviderServiceUri);
		UriComponentsBuilder builder = UriComponentsBuilder
	            .fromHttpUrl(passwordProviderServiceUri)
	            .queryParam("lengthofpass", 8);
		return restTemplate.getForObject(builder.toUriString(), String.class);	
		
	}
	
	
}
