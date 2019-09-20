package com.revature.model;

import java.util.ArrayList;

public class Employee implements Comparable<Employee> {

	private int id;
	private String fullName;
	private String jobTitle;
	private String address;
	private String email;
	private String userPassword;
	private boolean managerStatus;

	public Employee(int ID, String fullName, String jobTitle, String address, String email, String password,
			boolean managerStatus) {
		this.id = ID;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.address = address;
		this.email = email;
		this.userPassword = password;
		this.managerStatus = managerStatus;
	}

	public Employee(String fullName, String jobTitle, String address, String email, String password,
			boolean managerStatus) {
		this.id = -1;
		this.fullName = fullName;
		this.jobTitle = jobTitle;
		this.address = address;
		this.email = email;
		this.userPassword = password;
		this.managerStatus = managerStatus;
	}
	
	public int getID() {
		return this.id;
	}

	public void setID(int ID) {
		this.id = ID;
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

	public boolean isManager() {
		return managerStatus;
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
