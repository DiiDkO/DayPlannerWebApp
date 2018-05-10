package com.event.reminder;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.database.manager.DatabaseManager;
import com.event.Event;
import com.user.User;

public class EventReminder {
	private LocalDateTime dateTime;
	private DatabaseManager dbManager;

	public EventReminder(User user) throws ClassNotFoundException, SQLException {
		dateTime = LocalDateTime.now();
		dbManager = new DatabaseManager(user);
	}

	public String eventReminder() throws ClassNotFoundException, SQLException {
		List<Event> eventsOfCurrentDay = new ArrayList<Event>(dbManager.getDataForDayFromCurrecntMonth());
		for (Event event : eventsOfCurrentDay) {
			if(!event.isMOTD()) {
			int differenceMinutes = 0;
			if (dateTime.getHour() < (this.toLocalDateTime(event.getStartTime()).getHour())) {
				int currentTimeMinutes = dateTime.getHour() * 60 + dateTime.getMinute();
				int eventTimeMinutes = this.toLocalDateTime(event.getStartTime()).getHour() * 60
						+ this.toLocalDateTime(event.getStartTime()).getMinute();
				differenceMinutes = eventTimeMinutes - currentTimeMinutes;
			}
			if (differenceMinutes == 60) {
				return event.getName() + " --> " + event.getDescription() + " is starting after an hour";
			} else if (differenceMinutes < 60 && differenceMinutes > 0)
				return event.getName() + " --> " + event.getDescription() + " is starting after " + differenceMinutes
						+ " minute(s)";
			else
				return null;

		}
		}	
		return null;
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		return timestamp.toLocalDateTime();
	}
}
