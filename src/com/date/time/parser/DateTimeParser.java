package com.date.time.parser;

import java.sql.Timestamp;
import java.time.LocalDate;

public class DateTimeParser {
	public static Timestamp parseToTimestamp(String dateTime) {
		String date = dateTime.substring(0, 10);
		String time = dateTime.substring(11, 16) + ":00";
		return Timestamp.valueOf((date + " " + time));
	}

	public static Timestamp parseToDateTime(String date) {
		String timestampDate = date.substring(0, 10);
		return Timestamp.valueOf((timestampDate + " 00:00:00"));
	}

	public static LocalDate parseToLocalYearMonth(String date) {
		int year=Integer.parseInt(date.substring(0, 4));
		int month=Integer.parseInt(date.substring(5, 7));
		return LocalDate.of(year, month, 01);
	}
	
	public static LocalDate parseToLocalDate(String date) {
		int year=Integer.parseInt(date.substring(0, 4));
		int month=Integer.parseInt(date.substring(5, 7));
		int day=Integer.parseInt(date.substring(8, 10));
		return LocalDate.of(year, month,day);
	}
	
}
