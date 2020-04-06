package com.example.demo.delegate;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	
	
	public String getLocalisedResponse(String attributeName, @Nullable String languageCode) {
		
		return StringUtils.isEmpty(languageCode)? "Attribute:: "  +attributeName+ " in language code:: en is->> " +attributeName:
			"Attribute:: "  +attributeName+ " in language code:: " +languageCode+ " is->> " +UtilityClass.getLocalisedMapValues().get(attributeName);
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
