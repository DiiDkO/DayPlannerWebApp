package com.user;

public class User {

	private String username;
	private String password;

	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);

	}

	public User() {
		this.username = null;
		this.password = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
