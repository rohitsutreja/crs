package com.crs.models;

public class User {
	private int id;
	private String username;
	private String email;
	private String usertype;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public User(int id, String username, String email, String usertype) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.usertype = usertype;
	}

}
