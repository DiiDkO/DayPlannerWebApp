package com.login.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.JDBConnection;
import com.user.User;

public class LoginSystem {
	private User user;

	public LoginSystem(User user) throws ClassNotFoundException, SQLException {
		this.user = user;
	}

	public boolean Login() throws SQLException, ClassNotFoundException {
		if (loginCheck()) {
			return true;
		}
		return false;
	}

	private boolean loginCheck() throws SQLException, ClassNotFoundException {
		String sqlStm = "SELECT username, password FROM dayplannerevents.users ;";
		try (Connection conn = new JDBConnection().getConnection();
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery(sqlStm)) {
			while (res.next())
				if (res.getString(1).equals(this.user.getUsername())&& (res.getString(2)).equals(this.user.getPassword()))
					return true;
			return false;
		}
	}
}
