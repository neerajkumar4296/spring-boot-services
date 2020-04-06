package com.example.demo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.customexception.CustomAppException;
import com.example.demo.delegate.ApplicationDelegate;
import com.example.demo.model.Person;
import com.example.demo.util.UtilityClass;

@RestController
@RequestMapping(value="main")
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	

	
	@Autowired
	ApplicationDelegate applicationDelegate;

	

	@RequestMapping(value = "/searchuser/{id}", method = RequestMethod.GET)
	public Person searchUser(@PathVariable int id) throws SQLException, CustomAppException {
		return applicationDelegate.searchUser(id);
	}

	@RequestMapping(value = "/getallusers", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Person> getAllUsers() throws SQLException {
		return applicationDelegate.getAllUsers();
	}
	
	@RequestMapping(value = "/insertrecords", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void insertRecords() throws SQLException {
		applicationDelegate.insertRecords();
	}
	
	@RequestMapping(value = "/getrecordscount/{tablename}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public int getPersonsCount(@PathVariable(name = "tablename") String tableName) throws SQLException, CustomAppException {
		return applicationDelegate.getCount(tableName);
		
	}
	
	 @RequestMapping(value = "system/getproperties", method = RequestMethod.GET)
	 public Map<String, String> getSystemProps() {
	    	return UtilityClass.getSystemProperties(System.getProperties());
	    }

}
