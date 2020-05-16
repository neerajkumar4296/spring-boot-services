package com.weatherinfoservice.model;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name = "ADDRESS")
public class Address {

	
	private String houseno;
	private String road;
	private String city;
	private String zip;
	private String state;
	private String country;
	
	
	private AddressType addressType;

	public Address() {
		super();
	}

	public Address(String houseno,  String road, String city, String zip, String state,
			String country, AddressType addressType) {
		super();
		this.houseno = houseno;
		this.road = road;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.country = country;
		this.addressType = addressType;
	}

	public String getHouseno() {
		return houseno;
	}

	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}


	@Override
	public String toString() {
		return "Address [houseno=" + houseno + ", road=" + road + ", city=" + city + ", zip=" + zip + ", state=" + state
				+ ", country=" + country + "]";
	}

}
