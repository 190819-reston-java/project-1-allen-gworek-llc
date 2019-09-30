package com.revature.model;

import java.util.ArrayList;

public class Employee implements Comparable<Employee>, AppUsable {

	private int id;
	private String fullName;
	private String jobTitle;
	private String address;
	private String email;
	private String userPassword;
	private boolean managerStatus;
	private String addressTwo;
	private String city;
	private String state;
	private String zip;

	public Employee() {
		this(-1, "", "", "", "", "", false, "", "", "", "");
	}
	
	public Employee(int ID, String fullName, String jobTitle, String address, String email, String password,
			boolean managerStatus, String addressTwo, String city, String state, String zip) {
		this.id = ID;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.address = address;
		this.email = email;
		this.userPassword = password;
		this.managerStatus = managerStatus;
		this.addressTwo = addressTwo;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Employee(String fullName, String jobTitle, String address, String email, String password,
			boolean managerStatus, String addressTwo, String city, String state, String zip) {
		
		this(-1, fullName, jobTitle, address, email, password, managerStatus, addressTwo, city, state, zip);
	}
	
	public Employee(String fullName) {
		this(fullName, "", "", "", "", false, "", "", "", "");
	}
	
	public Employee(String something, String other, String another, String yetAnother, String oneLast, boolean boole) {
		this();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return userPassword;
	}

	public void setPassword(String password) {
		this.userPassword = password;
	}

	public boolean isManagerStatus() {
		return managerStatus;
	}
	
	public void setManagerStatus(boolean managerStatus) {
		this.managerStatus = managerStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddressTwo() {
		return addressTwo;
	}

	public void setAddressTwo(String addressTwo) {
		this.addressTwo = addressTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int compareTo(Employee other) {
		if (this.id == other.id) {
			return 0;
		}
		if (this.id > other.id) {
			return 1;
		} else {
			return -1;
		}
	}


}
