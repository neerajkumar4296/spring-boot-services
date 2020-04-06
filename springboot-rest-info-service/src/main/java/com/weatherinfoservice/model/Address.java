package com.weatherinfoservice.model;




public class Address {

	private int houseno;
	private String road;
	private String city;
	private String zip;
	private String state;
	private String country;
	
	private AddressType addressType;

	public Address() {
		super();
	}

	public Address(int houseno,  String road, String city, String zip, String state,
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

	public int getHouseno() {
		return houseno;
	}

	public void setHouseno(int houseno) {
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
