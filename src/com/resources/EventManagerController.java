package com.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.date.time.parser.DateTimeParser;
import com.event.Event;
import com.event.EventController;
import com.event.reminder.EventReminder;
import com.timetable.renderer.PairHeadersData;
import com.user.User;

public class EventManagerController {
	private User user;

	public EventManagerController(User user) {
		this.user = user;
	}

	protected void addMOTD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp MOTDTimestamp = DateTimeParser.parseToDateTime(request.getParameter("MOTDDate"));
			Event event = new Event("MOTD", MOTDTimestamp, MOTDTimestamp, true, request.getParameter("MOTD"));
			EventController controller = new EventController(this.user, event);
			if (controller.addMOTD()) {
				request.setAttribute("message", "MOTD is added successfully!");
				request.getRequestDispatcher("addMOTD.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "This day have MOTD yet!");
				request.getRequestDispatcher("addMOTD.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try to again!");
			request.getRequestDispatcher("addMOTD.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void viewFromDateToDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfDateToDate(
					DateTimeParser.parseToLocalDate(fromDate), DateTimeParser.parseToLocalDate(toDate));
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsFromDateToDate.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsFromDateToDate.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something went wrong!");
			request.getRequestDispatcher("viewEventsFromDateToDate.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("message", "Something went wrong!");
			request.getRequestDispatcher("viewEventsFromDateToDate.jsp").forward(request, response);
		}

	}

	protected void viewCertainWeekOfCertainMonth(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			int week = Integer.parseInt(request.getParameter("week"));
			String date = request.getParameter("weekMonth");
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfWeekOfMonth(week,
					DateTimeParser.parseToLocalYearMonth(date));
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("message", "The month has only 4 weeks !");
			request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
		}

	}

	protected void viewCertaintWeek(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			int week = Integer.parseInt(request.getParameter("week"));
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfWeekOfCurrentMonth(week);
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("message", "The month has only 4 weeks !");
			request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
		}

	}

	protected void viewCurrentWeek(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfCurrentWeek();
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	protected void viewCertainMonth(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			String date = request.getParameter("viewMonth");
			System.out.println(date);
			PairHeadersData<List<String>, List<List<String>>> pair = eventController
					.viewOfMonth(DateTimeParser.parseToLocalYearMonth(date));
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	protected void viewCurrentMonth(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfCurrentMonth();

			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	protected void viewCertainDay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			String date = request.getParameter("viewDay");
			PairHeadersData<List<String>, List<List<String>>> pair = eventController
					.viewOfDay(DateTimeParser.parseToLocalDate(date));
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	protected void viewCurrentDay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfCurrentDay();

			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			e.printStackTrace();
		}

	}

	protected void deleteMOTD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp MOTDTimestamp = DateTimeParser.parseToDateTime(request.getParameter("MOTDDate"));
			Event event = new Event("MOTD", MOTDTimestamp, MOTDTimestamp, true);
			EventController controller = new EventController(this.user, event);
			if (controller.deleteMOTD()) {
				request.setAttribute("message", "MOTD is deleted!");
				request.getRequestDispatcher("deleteMOTD.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "This day have no MOTD !");
				request.getRequestDispatcher("deleteMOTD.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("deleteMOTD.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void deleteOnStartEndDateTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController controller = new EventController(this.user);
			Timestamp strDT = DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime"));
			Timestamp endDT = DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime"));
			if (strDT.toLocalDateTime().getHour() < endDT.toLocalDateTime().getHour()
					&& strDT.toLocalDateTime().getMinute() == endDT.toLocalDateTime().getMinute()) {
				if (controller.deleteOnDateTime(strDT, endDT)) {
					request.setAttribute("message", "The events on that starting/end date time are deleted!");
					request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "There are no events !");
					request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
				}

			} else if (!strDT.toLocalDateTime().toLocalDate().equals(endDT.toLocalDateTime().toLocalDate())) {
				request.setAttribute("message", "Events could be only in one day !");
				request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
			} else if (strDT.toLocalDateTime().getMinute() != endDT.toLocalDateTime().getMinute()) {
				request.setAttribute("message", "The hour could be only round !");
				request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid end time!");
				request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void deleteAllEvents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User confirmUser = new User(request.getParameter("currUsername"), request.getParameter("currPassword"));
			EventController controller = new EventController(this.user);
			if (confirmUser.getUsername().equals(this.user.getUsername())
					&& confirmUser.getPassword().equals(this.user.getPassword())
					&& confirmUser.getPassword().equals(request.getParameter("confirmPassword"))) {
				if (controller.deleteAllEvents()) {
					request.setAttribute("message", "The all events are deleted successfully!");
					request.getRequestDispatcher("deleteAllEvents.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "There are no events!");
					request.getRequestDispatcher("deleteAllEvents.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("message", "Invalid username/password !");
				request.getRequestDispatcher("deleteAllEvents.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("deleteAllEvents.jsp").forward(request, response);
			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("message", "There are no events to delete !");
			request.getRequestDispatcher("deleteAllEvents.jsp").forward(request, response);
		}
	}

	protected void deleteEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp strDT = DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime"));
			Timestamp endDT = DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime"));
			Event event = new Event(request.getParameter("eventName"), strDT, endDT, false);
			if (strDT.toLocalDateTime().getHour() < endDT.toLocalDateTime().getHour()
					&& strDT.toLocalDateTime().getMinute() == endDT.toLocalDateTime().getMinute()) {
				EventController controller = new EventController(this.user, event);
				if (controller.deleteEvent()) {
					request.setAttribute("message", "The  events is deleted successfully!");
					request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Such event dosen't exist!");
					request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
				}
			} else if (!strDT.toLocalDateTime().toLocalDate().equals(endDT.toLocalDateTime().toLocalDate())) {
				request.setAttribute("message", "Events could be only in one day !");
				request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
			} else if (strDT.toLocalDateTime().getMinute() != endDT.toLocalDateTime().getMinute()) {
				request.setAttribute("message", "The hour could be only round !");
				request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid end time!");
				request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void updateMOTD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp MOTDTimestamp = DateTimeParser.parseToDateTime(request.getParameter("MOTDDate"));
			Event event = new Event("MOTD", MOTDTimestamp, MOTDTimestamp, true);
			EventController controller = new EventController(this.user, event);
			if (controller.upadateMOTD(request.getParameter("newMOTD"))) {
				request.setAttribute("message", "MOTD is updated successfully!");
				request.getRequestDispatcher("updateMOTD.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "This day have no MOTD !");
				request.getRequestDispatcher("updateMOTD.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something went wrong! Try again!");
			request.getRequestDispatcher("updateMOTD.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void updateEventName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp strDT = DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime"));
			Timestamp endDT = DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime"));
			Event event = new Event(request.getParameter("eventName"), strDT, endDT, false,
					request.getParameter("eventDescription"));
			if (strDT.toLocalDateTime().getHour() < endDT.toLocalDateTime().getHour()
					&& strDT.toLocalDateTime().getMinute() == endDT.toLocalDateTime().getMinute()) {
				EventController controller = new EventController(this.user, event);
				if (controller.updateEventName(request.getParameter("newEventName"))) {
					request.setAttribute("message", "The event name is updated successfully !");
					request.getRequestDispatcher("updateEventName.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Such event dosen't exist!");
					request.getRequestDispatcher("updateEventName.jsp").forward(request, response);
				}
			} else if (!strDT.toLocalDateTime().toLocalDate().equals(endDT.toLocalDateTime().toLocalDate())) {
				request.setAttribute("message", "Events could be only in one day !");
				request.getRequestDispatcher("updateEventName.jsp").forward(request, response);
			} else if (strDT.toLocalDateTime().getMinute() != endDT.toLocalDateTime().getMinute()) {
				request.setAttribute("message", "The hour could be only round !");
				request.getRequestDispatcher("updateEventName.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid end time!");
				request.getRequestDispatcher("updateEventName.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("updateEventName.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void updateEndDateTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp strDT = DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime"));
			Timestamp endDT = DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime"));
			Event event = new Event(request.getParameter("eventName"), strDT, endDT, false);
			if (strDT.toLocalDateTime().getHour() < endDT.toLocalDateTime().getHour()
					&& strDT.toLocalDateTime().getMinute() == endDT.toLocalDateTime().getMinute()) {
				EventController controller = new EventController(this.user, event);
				if (controller.updateEventEndTime(
						DateTimeParser.parseToTimestamp(request.getParameter("eventNewEndDateTime")))) {
					request.setAttribute("message", "End date and time are updated successfully !");
					request.getRequestDispatcher("updateEndDateTime.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Such event dosen't exist !");
					request.getRequestDispatcher("updateEndDateTime.jsp").forward(request, response);
				}
			} else if (!strDT.toLocalDateTime().toLocalDate().equals(endDT.toLocalDateTime().toLocalDate())) {
				request.setAttribute("message", "Events could be only in one day !");
				request.getRequestDispatcher("updateEndDateTime.jsp").forward(request, response);
			} else if (strDT.toLocalDateTime().getMinute() != endDT.toLocalDateTime().getMinute()) {
				request.setAttribute("message", "The hour could be only round !");
				request.getRequestDispatcher("updateEndDateTime.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid end time!");
				request.getRequestDispatcher("updateEndDateTime.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("updateEndDateTime.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void updateStartDateTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp strDT = DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime"));
			Timestamp endDT = DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime"));
			Event event = new Event(request.getParameter("eventName"), strDT, endDT, false);
			if (strDT.toLocalDateTime().getHour() < endDT.toLocalDateTime().getHour()
					&& strDT.toLocalDateTime().getMinute() == endDT.toLocalDateTime().getMinute()) {
				EventController controller = new EventController(this.user, event);
				if (controller.updateEventStartDateTime(
						DateTimeParser.parseToTimestamp(request.getParameter("eventNewStartDateTime")))) {
					request.setAttribute("message", "Start date and time are updated successfully !");
					request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Such event dosen't exist !");
					request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
				}
			} else if (!strDT.toLocalDateTime().toLocalDate().equals(endDT.toLocalDateTime().toLocalDate())) {
				request.setAttribute("message", "Events could be only in one day !");
				request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
			} else if (strDT.toLocalDateTime().getMinute() != endDT.toLocalDateTime().getMinute()) {
				request.setAttribute("message", "The hour could be only round !");
				request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid end time!");
				request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void updateDescription(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp strDT = DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime"));
			Timestamp endDT = DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime"));
			Event event = new Event(request.getParameter("eventName"), strDT, endDT, false);
			if (strDT.toLocalDateTime().getHour() < endDT.toLocalDateTime().getHour()
					&& strDT.toLocalDateTime().getMinute() == endDT.toLocalDateTime().getMinute()) {
				EventController controller = new EventController(this.user, event);
				if (controller.updateEventDescription(request.getParameter("eventNewDescription"))) {
					request.setAttribute("message", "Description is updated successfully !");
					request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "Such event dosen't exist !");
					request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
				}
			} else if (!strDT.toLocalDateTime().toLocalDate().equals(endDT.toLocalDateTime().toLocalDate())) {
				request.setAttribute("message", "Events could be only in one day !");
				request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
			} else if (strDT.toLocalDateTime().getMinute() != endDT.toLocalDateTime().getMinute()) {
				request.setAttribute("message", "The hour could be only round !");
				request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid end time!");
				request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	protected void addEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp strDT = DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime"));
			Timestamp endDT = DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime"));
			Event event = new Event(request.getParameter("eventName"), strDT, endDT, false,
					request.getParameter("eventDescription"));
			if (strDT.toLocalDateTime().getHour() < endDT.toLocalDateTime().getHour()
					&& strDT.toLocalDateTime().getMinute() == endDT.toLocalDateTime().getMinute()) {
				EventController controller = new EventController(this.user, event);
				if (controller.addEvent()) {
					request.setAttribute("message", "The event is added successfully !");
					request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "There is such event on this start date time!");
					request.getRequestDispatcher("addEvent.jsp").forward(request, response);
				}
			} else if (!strDT.toLocalDateTime().toLocalDate().equals(endDT.toLocalDateTime().toLocalDate())) {
				request.setAttribute("message", "Events could be only in one day !");
				request.getRequestDispatcher("addEvent.jsp").forward(request, response);
			} else if (strDT.toLocalDateTime().getMinute() != endDT.toLocalDateTime().getMinute()) {
				request.setAttribute("message", "The hour could be only round !");
				request.getRequestDispatcher("addEvent.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid end time!");
				request.getRequestDispatcher("addEvent.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try to add event again!");
			request.getRequestDispatcher("addEvent.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
}
