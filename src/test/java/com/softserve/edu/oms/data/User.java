package com.softserve.edu.oms.data;

import ru.yandex.qatools.allure.annotations.Step;

public class User implements IUser {

	private String loginname;
	private String firstname;
	private String lastname;	
	private String password;
	private String email;
	private String region;
	private String role;

	public User (){

	}

	public User(String loginname, String firstname,
			String lastname, String password,
			String email, String region, String role) {
		this.loginname = loginname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.region = region;
		this.role = role;
	}

	// setters
	
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// getters
	
	public String getLoginname() {
		return loginname;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getRegion() {
		return region;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "\nloginname= " + loginname
				+"\tfirstname= " + firstname
				+"\tlastname= " + lastname
				+"\tpassword= " + password
				+"\temail= " + email
				+"\tregion= " + region
				+"\trole= " + role;
	}

	//Compare two users
	@Step("Compare two users")
	public boolean CompareTo (User user){

	return this.getLoginname().equals(user.getLoginname())
			&& this.getFirstname().equals(user.getFirstname())
			&& this.getLastname().equals(user.getLastname())
			&& this.getRegion().equalsIgnoreCase(user.getRegion().toString())
			&& this.getRole().equalsIgnoreCase(user.getRole().toString());
	}
}
