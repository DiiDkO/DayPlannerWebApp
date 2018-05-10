package com.user;

import java.sql.SQLException;

import com.database.manager.DatabaseManager;

public class UserController {
	private DatabaseManager dbManager;

	public UserController(User user) throws ClassNotFoundException, SQLException {
		this.dbManager = new DatabaseManager(user);
	}

	public boolean addUser() throws ClassNotFoundException, SQLException {
		if(this.dbManager.addUser())
			return true;
		return false;
	}

	public boolean deleteUser() throws ClassNotFoundException, SQLException {
		if(this.dbManager.deleteUser())
			return true;
		return false;
	}

	public boolean updateUserPassword(String password) throws ClassNotFoundException, SQLException {
		if(this.dbManager.updateUserPassword(password))
			return true;
		return false;
	}
}
