package com.event;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.timetable.renderer.PairHeadersData;
import com.timetable.renderer.TimetableRenderer;
import com.user.User;

public class EventsView {
	private User user;
	private LocalDate currDate;

	public EventsView(User user) {
		this.user = user;
		currDate = LocalDate.now();
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfCurrentDay() throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		PairHeadersData<List<String>, List<List<String>>> pair=timeTable.eventsOfDay(currDate);
		return pair;
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfDay(LocalDate date) throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		return timeTable.eventsOfDay(date);
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfCurrentWeekOfCurrentMonth() throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		int week=this.getNumberOfCurrentWeek(currDate.getDayOfMonth());
		return timeTable.eventsOfWeek(week, currDate);
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfWeekOfCurrentMonth(int numberOfWeek) throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		return timeTable.eventsOfWeek(numberOfWeek, currDate);
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfWeekOfMonth(int numberOfWeek, LocalDate date) throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		return timeTable.eventsOfWeek(numberOfWeek, date);
	}

	public PairHeadersData<List<String> ,List<List<String>>> eventsOfMonth(LocalDate date) throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		return timeTable.eventsOfMonth(date);
			
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfCurrentMonth() throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		return timeTable.eventsOfMonth(LocalDate.of(currDate.getYear(), currDate.getMonth(), 1));
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsFromDateToDate(LocalDate fromDate, LocalDate toDate)
			throws ClassNotFoundException, SQLException {
		TimetableRenderer timeTable = new TimetableRenderer(this.user);
		return timeTable.eventsFromDateToDate(fromDate, toDate);
	}

	private int getNumberOfCurrentWeek(int day) {
		if (day >= 1 && day <= 7)
			return 1;
		else if (day >= 8 && day <= 14)
			return 2;
		else if (day >= 15 && day <= 21)
			return 3;
		else if (day >= 22 && day <= 28)
			return 4;
		else
			return 5;
	}
}
