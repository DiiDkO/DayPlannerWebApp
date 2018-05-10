package com.dayplanner.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.jdbc.JDBConnection;

public class DayPlannerDatabase {
	final String databaseName = "dayplannerevents";

	public DayPlannerDatabase() {
	}

	public boolean checkDatabaseExist() throws SQLException, ClassNotFoundException {
		if (this.createDataBase() == 1) {
			System.out.println("Database not exist.\nCreating database ...\n");
			return false;
		} else
			return true;
	}

	private int createDataBase() throws SQLException, ClassNotFoundException {
		try (Connection conn = new JDBConnection().getConnection(); Statement stm = conn.createStatement()) {
			return stm.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName + " ;");
		}

	}
}
