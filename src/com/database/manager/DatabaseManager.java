package com.database.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.event.Event;
import com.jdbc.JDBConnection;
import com.user.User;

public class DatabaseManager {
	private User user;
	private Event event;
	private final String databaseStm = "Use dayplannerevents ;";
	private LocalDate currentDate;

	public DatabaseManager(User user, Event event) throws ClassNotFoundException, SQLException {
		this.user = user;
		this.event = event;
		currentDate = LocalDate.now();
	}

	public DatabaseManager(User user) throws ClassNotFoundException, SQLException {
		this.user = user;
		currentDate = LocalDate.now();
	}

	// Insert database data
	public boolean addUser() throws SQLException, ClassNotFoundException {
		String sqlStm = "INSERT INTO users(username,password) VALUES(?,?);";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setString(1, user.getUsername());
			preStm.setString(2, user.getPassword());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	public boolean addEvent() throws SQLException, ClassNotFoundException {
		String sqlStm = "INSERT INTO events(name,startTime,endTime,MOTD,description,user_id) VALUES(?, ?, ?, ?, ?, ?);";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm, Statement.RETURN_GENERATED_KEYS);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setString(1, this.event.getName());
			preStm.setTimestamp(2, this.event.getStartTime());
			preStm.setTimestamp(3, this.event.getEndTime());
			preStm.setBoolean(4, this.event.isMOTD());
			preStm.setString(5, this.event.getDescription());
			preStm.setInt(6, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1) {
				try (ResultSet generateKeys = preStm.getGeneratedKeys()) {
					while (generateKeys.next()) {
						this.event.setId(generateKeys.getInt(1));
					}
					this.event.setUser_id(this.getUserIdFromDataBase());
					return true;
				}
			}
			return false;
		}
	}

	// Update database data
	public boolean updateUserPassword(String password) throws SQLException, ClassNotFoundException {
		String sqlStm = "UPDATE users SET password = '" + password + "' WHERE users.id = ? ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;

			return false;
		}
	}

	public boolean updateEventName(String eventName) throws SQLException, ClassNotFoundException {
		String sqlStm = " UPDATE events SET name= ? WHERE events.id= ? AND events.user_id = ? ; ";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setString(1, eventName);
			preStm.setInt(2, this.getEventIdFromDataBase());
			preStm.setInt(3, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	public boolean updateEventStartTime(Timestamp startTime) throws SQLException, ClassNotFoundException {
		String sqlStm = " UPDATE events SET startTime= ? " + " WHERE events.id= ? AND events.user_id = ? ; ";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setTimestamp(1, startTime);
			preStm.setInt(2, this.getEventIdFromDataBase());
			preStm.setInt(3, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	public boolean updateEventEndTime(Timestamp endTime) throws SQLException, ClassNotFoundException {
		String sqlStm = " UPDATE events SET endTime= ? " + " WHERE events.id= ? AND events.user_id = ? ; ";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setTimestamp(1, endTime);
			preStm.setInt(2, this.getEventIdFromDataBase());
			preStm.setInt(3, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	public boolean updateEventDescription(String descrip) throws SQLException, ClassNotFoundException {
		String sqlStm = " UPDATE events SET description='" + descrip + "'"
				+ " WHERE events.id= ? AND events.user_id = ? ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getEventIdFromDataBase());
			preStm.setInt(2, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	// Delete database data
	public boolean deleteUser() throws SQLException, ClassNotFoundException {
		String sqlStm = "DELETE FROM users WHERE users.id= ? ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	public boolean deleteAllEvent() throws SQLException, ClassNotFoundException {
		String sqlStm = "DELETE FROM events WHERE events.user_id = ? ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	public boolean deleteEvent() throws SQLException, ClassNotFoundException {
		String sqlStm = "DELETE FROM events WHERE events.id= ?  AND events.user_id = ? ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getEventIdFromDataBase());
			preStm.setInt(2, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	public boolean deleteOnDateTime(Timestamp eventStartTime, Timestamp eventEndTime)
			throws SQLException, ClassNotFoundException {
		String sqlStm = "DELETE FROM events WHERE events.startTime= ? AND events.endTime= ?  AND events.user_id = ? ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setTimestamp(1, eventStartTime);
			preStm.setTimestamp(2, eventEndTime);
			preStm.setInt(3, this.getUserIdFromDataBase());
			if (preStm.executeUpdate() == 1)
				return true;
			return false;
		}
	}

	// Select database data
	public int getEventIdFromDataBase() throws SQLException, ClassNotFoundException {
		String selectEventId = "SELECT id FROM events WHERE events.name= ?  AND events.startTime= ? AND events.endTime= ? ;";
		int event_id = 0;
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(selectEventId);
				Statement stm = conn.createStatement()) {
			stm.execute(this.databaseStm);
			preStm.setString(1, this.event.getName());
			preStm.setTimestamp(2, this.event.getStartTime());
			preStm.setTimestamp(3, this.event.getEndTime());
			try (ResultSet res = preStm.executeQuery()) {
				while (res.next()) {
					event_id = res.getInt(1);
				}
			}
			return event_id;
		}
	}

	public int getUserIdFromDataBase() throws SQLException, ClassNotFoundException {
		String selectUserId = "SELECT id FROM users WHERE users.username= ?  ;";
		int user_id = 0;
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(selectUserId);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setString(1, this.user.getUsername());
			try (ResultSet res = preStm.executeQuery()) {
				while (res.next()) {
					user_id = res.getInt(1);
				}
			}
			return user_id;
		}
	}

	public List<Event> getDataFromDateToDate(LocalDate fromDate, LocalDate toDate)
			throws SQLException, ClassNotFoundException {
		List<Event> list = new ArrayList<Event>();
		String sqlStm = "SELECT events.name, events.startTime, events.endTime, events.MOTD ,events.description FROM events"
				+ " WHERE events.user_id= ?  AND DATE (startTime)>= ?  AND DATE(startTime)<= ? ORDER BY startTime ASC;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setString(2, fromDate.toString());
			preStm.setString(3, toDate.toString());
			try (ResultSet res = preStm.executeQuery()) {
				while (res.next()) {
					Event event = new Event();
					event.setName(res.getString(1));
					event.setStartTime(res.getTimestamp(2));
					event.setEndTime(res.getTimestamp(3));
					event.setMOTD(res.getBoolean(4));
					event.setDescription(res.getString(5));
					list.add(event);
				}
			}
			return list;
		}
	}

	public List<Event> getDataForDayFromCurrecntMonth() throws SQLException, ClassNotFoundException {
		List<Event> list = new ArrayList<Event>();
		String sqlStm = "SELECT events.name, events.startTime, events.endTime, events.MOTD, events.description FROM events  WHERE  events.user_id= ? "
				+ " AND DAY (startTime)= ?  AND MONTH(startTime)= ? AND  YEAR(startTime)= ? ORDER BY startTime ASC ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setInt(2, currentDate.getDayOfMonth());
			preStm.setInt(3, currentDate.getMonthValue());
			preStm.setInt(4, currentDate.getYear());
			try (ResultSet res = preStm.executeQuery()) {
				while (res.next()) {

					Event event = new Event();
					event.setName(res.getString(1));
					event.setStartTime(res.getTimestamp(2));
					event.setEndTime(res.getTimestamp(3));
					event.setMOTD(res.getBoolean(4));
					event.setDescription(res.getString(5));
					list.add(event);
				}
			}
			return list;
		}
	}

	public List<Event> getDataForDayFromMonth(LocalDate date) throws SQLException, ClassNotFoundException {
		List<Event> list = new ArrayList<Event>();
		String sqlStm = "SELECT events.name, events.startTime, events.endTime, events.MOTD,events.description FROM events  WHERE  events.user_id= ? "
				+ " AND DAY (startTime)= ?  AND MONTH(startTime)= ? AND  YEAR(startTime)= ? ORDER BY startTime ASC;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setInt(2, date.getDayOfMonth());
			preStm.setInt(3, date.getMonthValue());
			preStm.setInt(4, date.getYear());
			try (ResultSet res = preStm.executeQuery()) {
				Event event = null;
				while (res.next()) {
					event = new Event();
					event.setName(res.getString(1));
					event.setStartTime(res.getTimestamp(2));
					event.setEndTime(res.getTimestamp(3));
					event.setMOTD(res.getBoolean(4));
					event.setDescription(res.getString(5));
					list.add(event);
				}
				return list;
			}

		}

	}

	public List<Event> getDataFortWeekOfMonth(LocalDate date, int week) throws SQLException, ClassNotFoundException {
		List<Event> list = new ArrayList<Event>();
		String sqlStm = weekOfMonth(week);
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setInt(2, date.getMonthValue());
			preStm.setInt(3, date.getYear());
			try (ResultSet res = preStm.executeQuery()) {
				while (res.next()) {
					Event event = new Event();
					event.setName(res.getString(1));
					event.setStartTime(res.getTimestamp(2));
					event.setEndTime(res.getTimestamp(3));
					event.setMOTD(res.getBoolean(4));
					event.setDescription(res.getString(5));
					list.add(event);
				}
			}
			return list;
		}
	}

	public List<Event> getDataFortWeekOfCurrentMonth(int week) throws SQLException, ClassNotFoundException {
		List<Event> list = new ArrayList<Event>();
		String sqlStm = weekOfMonth(week);
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setInt(2, currentDate.getMonthValue());
			preStm.setInt(3, currentDate.getYear());
			try (ResultSet res = preStm.executeQuery()) {
				while (res.next()) {
					Event event = new Event();
					event.setName(res.getString(1));
					event.setStartTime(res.getTimestamp(2));
					event.setEndTime(res.getTimestamp(3));
					event.setMOTD(res.getBoolean(4));
					event.setDescription(res.getString(5));
					list.add(event);
				}
			}
			return list;
		}
	}

	private String weekOfMonth(int week) throws SQLException {
		String sqlStm = null;
		switch (EventsSelectorAction.parseInt(week)) {
		case FIRSTWEEK:
			sqlStm = "SELECT events.name,events.startTime,events.endTime, events.MOTD, events.description FROM events"
					+ " WHERE events.user_id= ?" + " AND MONTH (startTime)= ? "
					+ " AND  YEAR(startTime)= ? AND DAY(startTime) >=1 AND DAY(startTime)<=7 ORDER BY startTime ASC ;";
			break;
		case SECONDWEEK:
			sqlStm = "SELECT events.name, events.startTime,events.endTime, events.MOTD ,events.description FROM events WHERE events.user_id=? "
					+ " AND MONTH (startTime)=? AND  YEAR(startTime)= ? AND DAY(startTime) >=8 AND DAY(startTime)<=14 ORDER BY startTime ASC;";
			break;

		case THIRDWEEK:
			sqlStm = "SELECT events.name, events.startTime,events.endTime, events.MOTD,events.description FROM events WHERE events.user_id=? "
					+ " AND MONTH (startTime)= ? AND  YEAR(startTime)= ? AND DAY(startTime) >=15 AND DAY(startTime)<=21 ORDER BY startTime ASC;";
			break;
		case FORTHWEEK:
			sqlStm = "SELECT events.name, events.startTime, events.endTime, events.MOTD,events.description FROM events WHERE events.user_id= ? "
					+ " AND MONTH (startTime)= ? AND  YEAR(startTime)= ? AND DAY(startTime) >=22  AND  DAY(startTime) <=28  ORDER BY startTime ASC;";
			break;

		case FIFTHWEEK:
			sqlStm = "SELECT events.name, events.startTime, events.endTime, events.MOTD ,events.description FROM events WHERE events.user_id= ? "
					+ " AND MONTH (startTime)= ?  AND  YEAR(startTime)= ?  AND DAY(startTime)>=29 AND DAY(startTime)<=31 ORDER BY startTime ASC;";
			break;

		default:
			System.out.println("Ivalid week of a month!");
			break;
		}
		return sqlStm;
	}

	public List<Event> getAllDataFromCurrentMonth() throws SQLException, ClassNotFoundException {
		List<Event> list = new ArrayList<Event>();
		String sqlStm = "SELECT name, startTime,endTime, MOTD, description FROM events"
				+ " WHERE events.user_id= ? AND MONTH (startTime)= ? ORDER BY startTime ASC ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setInt(2, this.currentDate.getMonthValue());
			try (ResultSet res = preStm.executeQuery()) {
				while (res.next()) {
					Event event = new Event();
					event.setName(res.getString(1));
					event.setStartTime(res.getTimestamp(2));
					event.setEndTime(res.getTimestamp(3));
					event.setMOTD(res.getBoolean(4));
					event.setDescription(res.getString(5));
					list.add(event);
				}
			}

			return list;
		}
	}

	public List<Event> getAllDataFromMonth(LocalDate date) throws SQLException, ClassNotFoundException {
		List<Event> list = new ArrayList<Event>();
		String sqlStm = "SELECT events.name, events.startTime, events.endTime, events.MOTD,events.description FROM events  WHERE  events.user_id= ? "
				+ "  AND MONTH(startTime)= ? AND  YEAR(startTime)= ? ORDER BY startTime ASC;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {

			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setInt(2, date.getMonthValue());
			preStm.setInt(3, date.getYear());
			try (ResultSet res = preStm.executeQuery()) {
				Event event = null;
				while (res.next()) {
					event = new Event();
					event.setName(res.getString(1));
					event.setStartTime(res.getTimestamp(2));
					event.setEndTime(res.getTimestamp(3));
					event.setMOTD(res.getBoolean(4));
					event.setDescription(res.getString(5));
					list.add(event);
				}
				
			}
			return list;
		}
	}

	public Event getMOTD(Timestamp dateTime) throws SQLException, ClassNotFoundException {
		Event event = new Event();
		String sqlStm = "SELECT name, startTime, endTime, description from events"
				+ " WHERE events.user_id= ? AND startTime= ? AND endTime= ? AND MOTD= ? ORDER BY startTime ASC ;";
		try (Connection conn = new JDBConnection().getConnection();
				PreparedStatement preStm = conn.prepareStatement(sqlStm);
				Statement stm = conn.createStatement()) {
			stm.execute(databaseStm);
			preStm.setInt(1, this.getUserIdFromDataBase());
			preStm.setTimestamp(2, dateTime);
			preStm.setTimestamp(3, dateTime);
			preStm.setBoolean(4, true);
			try (ResultSet res = preStm.executeQuery()) {
				if (!res.next()) {
					return null;
				}
				event.setName(res.getString(1));
				event.setStartTime(res.getTimestamp(2));
				event.setEndTime(res.getTimestamp(3));
				event.setDescription(res.getString(4));
				return event;
			}
		}
	}

}
