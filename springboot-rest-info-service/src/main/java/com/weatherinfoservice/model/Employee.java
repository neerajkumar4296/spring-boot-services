package com.weatherinfoservice.model;

import java.util.Date;

//@Entity
//@Table(name = "EMPLOYEE")
public class Employee {

	//@Id
	//@GeneratedValue
	private Long employeeId;
	
	//@Column(name = "FIRSTNAME", length = 25, nullable = false)
	private String firstName;
	
	//@Column(name = "LASTNAME", length = 25)
	private String lastName;
	
	//@Column(name = "DOB", nullable = false)
	private Date dob;
	
	//@Column(name = "QUALIFICATION", nullable = false)
	private Qualification edQualification;

	
	//@Column(name = "SALARY", nullable = false)
	private Long salary;
	
	
	private Address address;

	public Employee() {
		super();
	}

	public Employee(Long employeeId, String firstName, String lastName, Date dob, Long salary,
			Qualification edQualification, Address address) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.salary = salary;
		this.edQualification = edQualification;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Qualification getEdQualification() {
		return edQualification;
	}

	public void setEdQualification(Qualification edQualification) {
		this.edQualification = edQualification;
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
				+ dob + ", salary=" + salary + ", edQualification=" + edQualification + ", address=" + address + "]";
	}
	

	
	

}
