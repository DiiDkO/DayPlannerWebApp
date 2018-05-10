package com.user.validation;

public class PasswordException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;

	public PasswordException(String message) {
		this.message = message;
	}

	public PasswordException() {
		this.message = "Password must contain  one upper ,one lower letter and one number and must be 6 signs at least\n";
	}

	public String getMessage() {
		return message;
	}

}
