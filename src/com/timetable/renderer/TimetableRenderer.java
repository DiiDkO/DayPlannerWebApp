package com.timetable.renderer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.database.manager.DatabaseManager;
import com.date.time.parser.DateTimeParser;
import com.event.Event;
import com.user.User;

public class TimetableRenderer {
	private DatabaseManager dbManager;

	public TimetableRenderer(User user) throws ClassNotFoundException, SQLException {
		dbManager = new DatabaseManager(user);
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfDay(LocalDate date)
			throws ClassNotFoundException, SQLException {
		List<Event> events = new ArrayList<>(dbManager.getDataForDayFromMonth(date));
		//Timestamp dateTimeMOTD = Timestamp.valueOf(date.toString() + " 00:00:00");
		Event MOTD = dbManager.getMOTD(DateTimeParser.parseToDateTime(date.toString()));
		String dateOfPrint = date.toString() + " MOTD: " + ((MOTD == null) ? "None" : MOTD.getDescription());
		List<String> headers = new ArrayList<>(Arrays.asList("HOUR", dateOfPrint));
		List<List<String>> rows = new ArrayList<>();

		for (int i = 0; i < 24; i++) {
			boolean flag = false;
			List<String> currRow = new ArrayList<>();
			int startTime = 0;
			int endTime = 0;
			int currTime = i;
			for (Event e : events) {
				startTime = e.getStartTime().toLocalDateTime().getHour();
				endTime = e.getEndTime().toLocalDateTime().getHour();
				if (currTime == startTime || currTime == endTime || (currTime > startTime && currTime < endTime)) {
					if (!e.isMOTD()) {
						currRow.addAll(Arrays.asList((currTime < 10 ? ("0" + currTime) : currTime) + ":00" + " ",
								(e.getName() + "--> " + e.getDescription() + " ")));
						flag = true;
					} 
				}
			}
			if (!flag)
				currRow.addAll(Arrays.asList((currTime < 10 ? ("0" + currTime) : currTime) + ":00" + " ", ""));
			rows.addAll(Arrays.asList(currRow));
		}
		return new PairHeadersData<List<String>, List<List<String>>>(headers, rows);
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfMonth(LocalDate date)
			throws ClassNotFoundException, SQLException {
		List<Event> events = new ArrayList<>(dbManager.getAllDataFromMonth(date));
		List<String> headers = new ArrayList<>(Arrays.asList("HOUR"));
		List<List<String>> rows = new ArrayList<>();
		int numberOfDays = date.lengthOfMonth();
		headers.addAll(insertDaysOfMonthIntoList(date, numberOfDays));
		HashMap<Integer, HashMap<Integer, Event>> eventsByDays = new HashMap<>();
		int iterator = 1;
		eventsByDays = this.inputEventsByHoursIntoTable(
				LocalDate.of(date.getYear(), date.getMonthValue(), numberOfDays), events, iterator);
		rows = this.inputEventsDescriptionOnRows(LocalDate.of(date.getYear(), date.getMonthValue(), 1),
				LocalDate.of(date.getYear(), date.getMonthValue(), numberOfDays), eventsByDays, iterator);
		return new PairHeadersData<List<String>, List<List<String>>>(headers, rows);	}

	public PairHeadersData<List<String>, List<List<String>>> eventsOfWeek(int numberOfWeek, LocalDate date)
			throws ClassNotFoundException, SQLException {
		List<Event> events = new ArrayList<>(dbManager.getDataFortWeekOfMonth(date, numberOfWeek));
		List<String> headers = new ArrayList<>(Arrays.asList("HOUR"));
		int currentYear = date.getYear();
		int currentMonth = date.getMonthValue();
		String fromDayToDay = this.getDatesOfWeeks(numberOfWeek, date.lengthOfMonth());
		int fromDay = Integer.parseInt(fromDayToDay.substring(0, 2));
		int toDay = Integer.parseInt(fromDayToDay.substring(3, 5));
		headers.addAll(this.inputEventsFromDateToDateIntoList(LocalDate.of(currentYear, currentMonth, fromDay),
				LocalDate.of(currentYear, currentMonth, toDay)));
		HashMap<Integer, HashMap<Integer, Event>> eventsByDays = new HashMap<>();
		List<List<String>> rows = new ArrayList<>();
		int iterator = fromDay;
		eventsByDays = this.inputEventsByHoursIntoTable(LocalDate.of(currentYear, currentMonth, toDay), events,
				iterator);
		rows = this.inputEventsDescriptionOnRows(LocalDate.of(currentYear, currentMonth, fromDay),
				LocalDate.of(currentYear, currentMonth, toDay), eventsByDays, iterator);
		return new PairHeadersData<List<String>, List<List<String>>>(headers, rows);
	}

	public PairHeadersData<List<String>, List<List<String>>> eventsFromDateToDate(LocalDate fromDate, LocalDate toDate)
			throws ClassNotFoundException, SQLException {
		List<Event> events = new ArrayList<>(dbManager.getDataFromDateToDate(fromDate, toDate));
		List<String> headers = new ArrayList<>(Arrays.asList("HOUR"));
		headers.addAll(this.inputEventsFromDateToDateIntoList(fromDate, toDate));
		HashMap<Integer, HashMap<Integer, Event>> eventsByDays = new HashMap<>();
		List<List<String>> rows = new ArrayList<>();
		int iterator = fromDate.getDayOfMonth();
		eventsByDays = inputEventsByHoursIntoTable(toDate, events, iterator);
		rows = inputEventsDescriptionOnRows(fromDate, toDate, eventsByDays, iterator);
		return new PairHeadersData<List<String>, List<List<String>>>(headers, rows);
	}

	private List<List<String>> inputEventsDescriptionOnRows(LocalDate fromDate, LocalDate toDate,
			HashMap<Integer, HashMap<Integer, Event>> eventsByDays, int iterator) {
		List<List<String>> rows = new ArrayList<>();
		for (int i = 0; i < 24; i++) {
			ArrayList<String> row = new ArrayList<>();
			row.add((((i < 10) ? "0" + i : i) + ":00 "));
			while (iterator != toDate.getDayOfMonth() + 1) {
				Event eventByHour = eventsByDays.get(iterator).get(i);
				row.add((eventByHour == null || eventByHour.isMOTD()) ? ""
						: (eventByHour.getName() + "--> " + eventByHour.getDescription() + " "));
				iterator++;
			}
			iterator = fromDate.getDayOfMonth();
			rows.add(row);
		}

		return rows;
	}

	private HashMap<Integer, HashMap<Integer, Event>> inputEventsByHoursIntoTable(LocalDate toDate, List<Event> events,
			int iterator) {
		HashMap<Integer, HashMap<Integer, Event>> eventsByDays = new HashMap<>();
		while (iterator != toDate.getDayOfMonth() + 1) {
			HashMap<Integer, Event> eventsByHours = new HashMap<>();
			int startHour = 0;
			int endHour = 0;
			for (Event e : events) {
				int day = e.getStartTime().toLocalDateTime().toLocalDate().getDayOfMonth();
				if (day == iterator) {
					startHour = e.getStartTime().toLocalDateTime().toLocalTime().getHour();
					endHour = e.getEndTime().toLocalDateTime().toLocalTime().getHour();
					int duration = endHour - startHour;
					eventsByHours.put(startHour, e);
					while (duration != 0) {
						eventsByHours.put(++startHour, e);
						duration--;
					}
					eventsByHours.put(endHour, e);
				}
			}
			eventsByDays.put(iterator, eventsByHours);
			iterator++;
		}
		return eventsByDays;
	}

	private String getDatesOfWeeks(int numberOfWeek, int lastDay) {
		switch (WeekDaysAction.parseInt(numberOfWeek)) {
		case FIRSTWEEK:
			return "01-07";
		case SECONDWEEK:
			return "08-14";
		case THIRDWEEK:
			return "15-21";
		case FORTHWEEK:
			return "22-28";
		case FIFTWEEK:
			return ("29-" + lastDay).toString();
		default:
			return null;
		}
	}

	private List<String> inputEventsFromDateToDateIntoList(LocalDate fromDate, LocalDate toDate)
			throws ClassNotFoundException, SQLException {
		List<String> headers = new ArrayList<>();
		toDate = toDate.plusDays(1);
		while (!fromDate.equals(toDate)) {
			Timestamp dateTime = Timestamp.valueOf(fromDate.toString() + " 00:00:00");
			Event MOTD = dbManager.getMOTD(dateTime);
			String dateOfPrint = fromDate.toString() + " MOTD: " + (MOTD == null ? "None" : MOTD.getDescription());
			headers.add(dateOfPrint);
			fromDate = fromDate.plusDays(1);
		}
		return headers;
	}

	private List<String> insertDaysOfMonthIntoList(LocalDate date, int numberOfDays)
			throws ClassNotFoundException, SQLException {
		List<String> headers = new ArrayList<>();
		for (int i = 1; i <= numberOfDays; i++) {
			Timestamp dateTime = Timestamp.valueOf(date.toString() + " 00:00:00");
			Event MOTD = dbManager.getMOTD(dateTime);
			String dateOfPrint = date.toString() + " MOTD: " + (MOTD == null ? "None" : MOTD.getDescription());
			headers.add(dateOfPrint);
			date = date.plusDays(1);
		}
		return headers;
	}

//	private String[][] convert2DArray(List<List<String>> rows) {
//		String[][] result = new String[rows.size()][];
//		for (int i = 0; i < rows.size(); i++) {
//			List<String> currentList = rows.get(i);
//			result[i] = currentList.toArray(new String[currentList.size()]);
//		}
//		return result;
//	}
}
