package com.myspringboot.model;

import org.apache.commons.lang3.builder.ToStringExclude;

import com.fasterxml.jackson.annotation.JsonView;
import com.myspringboot.jsonviewer.JsonViewer;

public class ModelApp {

	@JsonView(JsonViewer.Public.class)
	private String userID;
	//@ToStringExclude
	@JsonView(JsonViewer.Internal.class)
	private String password;
	
	@JsonView
	private String empId;
	
	//@ToStringExclude
	@JsonView(JsonViewer.Public.class)
	private String firstName;
	
	@JsonView(JsonViewer.Public.class)
	private String lastName;

	public ModelApp(String userID, String password, String empId, String firstName, String lastName) {
		super();
		this.userID = userID;
		this.password = password;
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
