package com.user.validation;

public class UsernameException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;

	public UsernameException(String message) {
		this.message = message;
	}

	public UsernameException() {
		this.message = "Username must contain  one upper ,one lower letter and one number and must be 6 signs at least\n";
	}

	public String getMessage() {
		return message;
	}

}
