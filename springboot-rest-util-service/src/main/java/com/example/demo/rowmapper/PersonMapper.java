package com.example.demo.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.model.Person;

public class PersonMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person p= new Person();
		p.setId(rs.getInt("ID"));
		p.setFirstName(rs.getString("FIRST_NAME"));
		p.setLastName(rs.getString("LAST_NAME"));
		return p;
	}



}
