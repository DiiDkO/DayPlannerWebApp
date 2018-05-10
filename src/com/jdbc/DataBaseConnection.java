package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseConnection {
	Connection getConnection() throws SQLException;
}
