package com.hr.model;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
	private String userId;
	@NotNull(message="name field shoud not be null")
	@Size(min = 3, max = 20)
	private String username;
	@NotNull(message="name field shoud not be null")
	@Size(max = 50)
	@Email
	private String email;
	private Set<String> roles;
	@NotNull(message="name field shoud not be null")
	@Size(min = 6, max = 40)
	private String password;

	private Long phoneNumber;
	private String locationName;
	private String country;
	private String state;
	private String district;
	private String mandal;
	private int pincode;
	public SignupRequest() {
		
	}
	public SignupRequest(String userId, @NotNull @Size(min = 3, max = 20) String username,
			@NotNull @Size(max = 50) @Email String email, Set<String> roles,
			@NotNull @Size(min = 6, max = 40) String password, Long phoneNumber, String locationName, String country,
			String state, String district, String mandal, int pincode) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.locationName = locationName;
		this.country = country;
		this.state = state;
		this.district = district;
		this.mandal = mandal;
		this.pincode = pincode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
		return "SignupRequest [userId=" + userId + ", username=" + username + ", email=" + email + ", roles=" + roles
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", locationName=" + locationName
				+ ", country=" + country + ", state=" + state + ", district=" + district + ", mandal=" + mandal
				+ ", pincode=" + pincode + "]";
	}
	
}
