package com.example.demo.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.customexception.CustomAppException;
import com.example.demo.model.Person;


public interface DataHandlerDao {

	
	 void saveTheFileToDataBase(MultipartFile fileToSave) throws DataAccessException, IOException;
	 List<Person>getAllUsers() throws SQLException;
	 void insertRecords() throws SQLException;
	 Person searchUser(int id) throws SQLException, CustomAppException;
	 int getRecordsCount(String tableName) throws SQLException, CustomAppException;
	 
}
