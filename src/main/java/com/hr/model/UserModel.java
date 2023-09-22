package com.hr.model;


public class UserModel {
	
	private String email;
	private Long phoneNumber;
	private String locationName;
	private String country;
	private String state;
	private String district;
	private String mandal;
	private int pincode;
	public UserModel() {
	}
	public UserModel(String email, Long phoneNumber, String locationName,
			String country, String state, String district, String mandal, int pincode) {
		
		
		
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.locationName = locationName;
		this.country = country;
		this.state = state;
		this.district = district;
		this.mandal = mandal;
		this.pincode = pincode;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "UserModel [email=" + email + ", phoneNumber=" + phoneNumber + ", locationName=" + locationName
				+ ", country=" + country + ", state=" + state + ", district=" + district + ", mandal=" + mandal
				+ ", pincode=" + pincode + "]";
	}
	
	
}
