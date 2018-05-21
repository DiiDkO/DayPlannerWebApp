package com.main.test;

import java.sql.SQLException;
import java.time.LocalDate;

import com.timetable.renderer.TimetableRenderer;
import com.user.User;

public class Test {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		TimetableRenderer tb= new TimetableRenderer(new User("Dan123","Dan123"));
		tb.eventsFromDateToDate(LocalDate.now(), LocalDate.of(2018, 05, 12));
		
	}
}
