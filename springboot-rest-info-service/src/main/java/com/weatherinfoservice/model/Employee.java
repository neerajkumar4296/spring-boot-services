package com.weatherinfoservice.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class Employee {

	@Id
	@Column(name = "EMPLOYEE_ID")
	private Long employeeId;
	
	@Column(name = "FIRSTNAME", length = 25, nullable = false)
	private String firstName;
	
	@Column(name = "LASTNAME", length = 25)
	private String lastName;
	
	@Column(name = "DOB", nullable = false)
	//@Temporal(TemporalType.DATE)-- not usefull when using with java 8 time api
	private LocalDate dob;
	
	@Column(name = "MAXM_QUALIFICATION", nullable = false)
	@Enumerated(EnumType.STRING)
	private Qualification maxEdQualification;

	
	@Column(name = "SALARY", nullable = false)
	private Long salary;
	
	@Transient
	//@Embedded
	@Column(name = "ADDRESS", nullable = false)
	@Formula(value = "concat(houseno, road, city, state, zip, country)")
	private Address address;

	public Employee() {
		super();
	}

	public Employee(Long employeeId, String firstName, String lastName, LocalDate dob, Long salary,
			Qualification edQualification, Address address) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.salary = salary;
		this.maxEdQualification = edQualification;
		this.address = address;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Qualification getEdQualification() {
		return maxEdQualification;
	}

	public void setEdQualification(Qualification edQualification) {
		this.maxEdQualification = edQualification;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob="
				+ dob + ", salary=" + salary + ", edQualification=" + maxEdQualification + ", address=" + address + "]";
	}
	

	
	

}
