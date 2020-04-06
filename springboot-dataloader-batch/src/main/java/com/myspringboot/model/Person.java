package com.myspringboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PERSONS")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
	private long id;
    
	@Column(name="FIRST_NAME", precision = 25)
    private String firstName;
	
	@Column(name="LAST_NAME", precision = 25)
    private String lastName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="UPDATE_TIMESTAMP")
	@DateTimeFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date updateDate;

	
	public Person() {
		super();
	}


	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", updateDate=" + updateDate + "]";
	}
    


   
    
    

   

}