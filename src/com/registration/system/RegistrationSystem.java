package com.registration.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.JDBConnection;
import com.user.User;
import com.user.validation.PasswordException;
import com.user.validation.PasswordValidation;
import com.user.validation.UsernameException;
import com.user.validation.UsernameValidation;
import com.user.validation.Validator;

public class RegistrationSystem {
	private User user;

	public RegistrationSystem(User user) throws UsernameException, PasswordException {

		this.user = user;
	}

	public boolean checkUserValidation() throws UsernameException, PasswordException {
		if (this.checkUsernameValidation() && this.checkPasswordValidation())
			return true;
		return false;
	}

	private boolean checkPasswordValidation() throws UsernameException, PasswordException {
		boolean valid = false;
		Validator<String> validatePassword = new PasswordValidation();
		if (!validatePassword.validate(this.user.getPassword()))
			throw new PasswordException();
		else
			valid = true;
		return valid;
	}

	private boolean checkUsernameValidation() throws UsernameException {
		boolean valid = false;
		Validator<String> validateUsername = new UsernameValidation();
		if (!validateUsername.validate(this.user.getUsername()))
			throw new UsernameException();
		else
			valid = true;
		return valid;
	}

	public boolean userExist() throws ClassNotFoundException, SQLException {

		try (Connection conn = new JDBConnection().getConnection();
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("SELECT username FROM dayplannerevents.users ;")) {
			while (res.next())
				if (res.getString(1).equals(user.getUsername()))
					return true;
			return false;
		}
	}
}
