package com.event;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.database.manager.DatabaseManager;
import com.timetable.renderer.PairHeadersData;
import com.user.User;

public class EventController {
	private DatabaseManager dbManager;
	private EventsView eventsView;
	private User user;

	public EventController(User user, Event event) throws ClassNotFoundException, SQLException {
		this.user = user;
		dbManager = new DatabaseManager(this.user, event);
		eventsView = new EventsView(this.user);
	}

	public EventController(User user) throws ClassNotFoundException, SQLException {
		this.user = user;
		dbManager = new DatabaseManager(this.user);
		eventsView = new EventsView(this.user);
	}

	// Add event and MOTD
	public boolean addEvent() throws ClassNotFoundException, SQLException {
		if(this.dbManager.addEvent())
			return true;
		return false;
	}

	public boolean addMOTD() throws ClassNotFoundException, SQLException {
		if(this.dbManager.addEvent())
			return true;
		return false;
	}

	// Delete event's options
	public boolean deleteEvent() throws ClassNotFoundException, SQLException {
		if(this.dbManager.deleteEvent())
			return true;
		return false;
	}

	public boolean deleteMOTD() throws ClassNotFoundException, SQLException {
		if(this.dbManager.deleteEvent())
			return true;
		return false;
	}

	public boolean deleteAllEvents() throws ClassNotFoundException, SQLException {
		if(this.dbManager.deleteAllEvent())
			return true;
		return false;
	}

	public boolean deleteOnDateTime(Timestamp eventStartTime, Timestamp eventEndTime)
			throws ClassNotFoundException, SQLException {
		if(this.dbManager.deleteOnDateTime(eventStartTime, eventEndTime))
			return true;
		return false;
	}

	// Update event's options
	public boolean updateEventName(String eventName) throws ClassNotFoundException, SQLException {
		if(dbManager.updateEventName(eventName))
			return true;
		return false;
	}

	public boolean updateEventDescription(String descrip) throws ClassNotFoundException, SQLException {
		if(dbManager.updateEventDescription(descrip))
			return true;
		return false;
	}

	public boolean upadateMOTD(String descrip) throws ClassNotFoundException, SQLException {
		if(dbManager.updateEventDescription(descrip))
			return true;
		return false;
	}

	public boolean updateEventStartDateTime(Timestamp startTime) throws ClassNotFoundException, SQLException {
		if(dbManager.updateEventStartTime(startTime))
			return true;
		return false;
	}

	public boolean updateEventEndTime(Timestamp endTime) throws ClassNotFoundException, SQLException {
		if(dbManager.updateEventEndTime(endTime))
			return true;
		return false;
	}

	// Get events of the day/week/month/date to date
	public PairHeadersData<List<String>, List<List<String>>> viewOfCurrentDay() throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsOfCurrentDay();
	}

	public PairHeadersData<List<String>, List<List<String>>> viewOfDay(LocalDate date) throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsOfDay(date);
	}

	public PairHeadersData<List<String>, List<List<String>>> viewOfCurrentMonth() throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsOfCurrentMonth();
	}

	public PairHeadersData<List<String>, List<List<String>>> viewOfMonth(LocalDate date)
			throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsOfMonth(date);

	}

	public PairHeadersData<List<String>, List<List<String>>> viewOfCurrentWeek() throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsOfCurrentWeekOfCurrentMonth();
	}

	public PairHeadersData<List<String>, List<List<String>>> viewOfWeekOfCurrentMonth(int numberOfWeek)
			throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsOfWeekOfCurrentMonth(numberOfWeek);
	}

	public PairHeadersData<List<String>, List<List<String>>> viewOfWeekOfMonth(int numberOfWeek, LocalDate date)
			throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsOfWeekOfMonth(numberOfWeek, date);
	}

	public PairHeadersData<List<String>, List<List<String>>> viewOfDateToDate(LocalDate fromDate, LocalDate toDate)
			throws ClassNotFoundException, SQLException {
		return this.eventsView.eventsFromDateToDate(fromDate, toDate);
	}
}
