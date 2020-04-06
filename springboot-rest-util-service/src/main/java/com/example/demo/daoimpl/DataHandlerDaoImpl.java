package com.example.demo.daoimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.customexception.CustomAppException;
import com.example.demo.dao.DataHandlerDao;
import com.example.demo.delegate.ApplicationDelegate;
import com.example.demo.model.Person;
import com.example.demo.rowmapper.PersonMapper;

@Repository
public class DataHandlerDaoImpl implements  DataHandlerDao {
	
	@Autowired
	ApplicationDelegate applicationDelegate;

	
	private static final String INSERT_FILE_TO_DB_QUERY = "INSERT INTO FILE_UPLOAD (FILE_NAME, FILE_CONTENT, FILE_CONTENTTYPE, UPLOAD_TIMESTAMP)" + 
			"VALUES (?, ?, ?, sysdate);";
	private static final String SELELCT_PERSON_QUERY = "SELECT *FROM PERSONS WHERE ID=?;";
	private static final String SELELCT_ALL_PERSON_QUERY = "SELECT *FROM PERSONS;";
	private static final String INSERT_RECORDS_QUERY = "INSERT INTO PERSONS VALUES(?, ?, ?, ?, ?);";
	private static final String COUNT_RECORDS_QUERY = "SELECT COUNT(*) FROM";
	
	@Autowired
	@Qualifier("dbfile1")
    JdbcTemplate jdbcTemplate;
	
	
	@Override
	public void saveTheFileToDataBase(MultipartFile fileToSave) throws DataAccessException, IOException {
		jdbcTemplate.update(INSERT_FILE_TO_DB_QUERY, fileToSave.getOriginalFilename(), fileToSave.getBytes().toString(),fileToSave.getContentType());
	}
	@Override
	public List<Person> getAllUsers() throws SQLException {

		return jdbcTemplate.query(SELELCT_ALL_PERSON_QUERY, new PersonMapper());
		
	}

	@Override
	public void insertRecords() throws SQLException {
		//Coverts List of string into an object[] of strings using java 8 stream
				List<Object[]> stringArgs= Arrays.asList("123,JOHN,DOW," +getCurrentTime()+"," +applicationDelegate.getRandomPass(), "144,JOHNSON,TITLER," +getCurrentTime()+"," +applicationDelegate.getRandomPass(), "154,JOHNSON,DREW," +getCurrentTime()+"," +applicationDelegate.getRandomPass() )
											.stream().map(name-> name.split(","))
											.collect(Collectors.toList());
				jdbcTemplate.batchUpdate(INSERT_RECORDS_QUERY, stringArgs);
		
	}
	@Override
	public Person searchUser(int id) throws SQLException, CustomAppException {
		Person person = null;
		try {
		person= jdbcTemplate.queryForObject(SELELCT_PERSON_QUERY, new Object[] { id }, new PersonMapper());
		}
		catch(EmptyResultDataAccessException ex)
		{
			throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "No User found with the ID:: "+id+"...Please check");
		}
		
		return person;
				}
	
	@Override
	public int getRecordsCount(String tableName) throws SQLException, CustomAppException {
		return jdbcTemplate.queryForObject(COUNT_RECORDS_QUERY+ " " +tableName.toUpperCase(), Integer.class);
	}
	
	private LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}
	
	
	
}
