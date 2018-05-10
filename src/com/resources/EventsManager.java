package com.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.manager.EventOptionsAction;
import com.date.time.parser.DateTimeParser;
import com.event.Event;
import com.event.EventController;
import com.event.reminder.EventReminder;
import com.timetable.renderer.PairHeadersData;
import com.user.User;

@WebServlet("/EventsManager")
public class EventsManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;
	private Event event;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.event = null;
		String operation = request.getParameter("operation");
		this.eventsOptions(operation, request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.user = (User) request.getSession().getAttribute("user");

		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfCurrentWeek();

			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("dayPlannerHome.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("dayPlannerHome.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void eventsOptions(String operation, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (EventOptionsAction.parseString(operation)) {
		case ADD_EVENT:
			this.addEvent(request, response);
			break;
		case ADD_MOTD:
			this.addMOTD(request, response);
			break;
		case UPDATE_EVENT_NAME:

			break;
		case UPDATE_EVENT_START_DATE_TIME:
			this.updateStartDateTime(request, response);
			break;
		case UPDATE_EVENT_END_DATE_TIME:
			this.updateEndDateTime(request, response);
			break;
		case UPDATE_EVENT_DESCRIPTION:
			this.updateDescription(request, response);
			break;
		case UPDATE_MOTD:
			this.updateMOTD(request, response);
			break;
		case DELETE_EVENT:
			this.deleteEvent(request, response);
			break;
		case DELETE_ALL_EVENTS:
			this.deleteAllEvents(request, response);
			break;
		case DELETE_EVENTS_START_END_DATE_TIME:
			this.deleteOnStartEndDateTime(request, response);
			break;
		case DELETE_MOTD:
			this.deleteMOTD(request, response);
			break;
		case VIEW_EVENTS_OF_CURRENT_DAY:
			this.viewCurrentDay(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_DAY:
			this.viewCertainDay(request, response);
			break;
		case VIEW_EVENTS_OF_CURRENT_MONTH:
			this.viewCurrentMonth(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_MONTH:
			this.viewCertainMonth(request, response);
			break;
		case VIEW_EVENTS_OF_CURRENT_WEEK:
			this.viewCurrentWeek(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_WEEK_OF_CURRENT_MONTH:
			this.viewCertaintWeek(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_WEEK_OF_CERTAIN_MONTH:
			this.viewCertainWeekOfCertainMonth(request, response);
			break;
		case VIEW_EVENTS_FROM_DATE_TO_DATE:
			this.viewFromDateToDate(request, response);
			break;
		}
	}
	private void viewFromDateToDate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfDateToDate(
					DateTimeParser.parseToLocalDate(fromDate),DateTimeParser.parseToLocalDate(toDate));
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsFromDateToDate.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsFromDateToDate.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("message", "Something went wrong!");
			request.getRequestDispatcher("viewEventsFromDateToDate.jsp").forward(request, response);
		}

	}
	private void viewCertainWeekOfCertainMonth(HttpServletRequest request, HttpServletResponse response)
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("message", "The month has only 4 weeks !");
			request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
		}

	}

	private void viewCertaintWeek(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			int week = Integer.parseInt(request.getParameter("week"));
			System.out.println(week);
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfWeekOfCurrentMonth(week);
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			request.setAttribute("message", "The month has only 4 weeks !");
			request.getRequestDispatcher("viewEventsOfWeek.jsp").forward(request, response);
		}

	}

	private void viewCurrentWeek(HttpServletRequest request, HttpServletResponse response)
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void viewCertainMonth(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			String date=request.getParameter("viewMonth");
			System.out.println(date);
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfMonth(DateTimeParser.parseToLocalYearMonth(date));
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewEventsOfMonth.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void viewCurrentMonth(HttpServletRequest request, HttpServletResponse response)
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void viewCertainDay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController eventController = new EventController(this.user);
			EventReminder reminder = new EventReminder(this.user);
			String remindMessage = reminder.eventReminder();
			String date=request.getParameter("viewDay");
			PairHeadersData<List<String>, List<List<String>>> pair = eventController.viewOfDay(DateTimeParser.parseToLocalDate(date));
			if (remindMessage != null) {
				request.setAttribute("message", remindMessage);
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			} else {
				request.setAttribute("arrays", pair);
				request.getRequestDispatcher("viewDay.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void viewCurrentDay(HttpServletRequest request, HttpServletResponse response)
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

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void deleteMOTD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp MOTDTimestamp = DateTimeParser.parseToDateTime(request.getParameter("MOTDDate"));
			this.event = new Event("MOTD", MOTDTimestamp, MOTDTimestamp, true);
			EventController controller = new EventController(this.user, this.event);
			if (controller.deleteMOTD()) {
				request.setAttribute("message", "MOTD is deleted!");
				request.getRequestDispatcher("deleteMOTD.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add MOTD again!");
				request.getRequestDispatcher("deleteMOTD.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("deleteMOTD.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	private void deleteOnStartEndDateTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EventController controller = new EventController(this.user, this.event);
			if (controller.deleteOnDateTime(DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime")),
					DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime")))) {
				request.setAttribute("message", "The events on that starting/end date time are deleted!");
				request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add event again!");
				request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("deleteEventsOnDateTime.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	private void deleteAllEvents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User confirmUser=new User(request.getParameter("currUsername"),request.getParameter("currPassword"));
			EventController controller = new EventController(this.user);
				if (confirmUser.getUsername().equals(this.user.getUsername())
						&& confirmUser.getPassword().equals(this.user.getPassword())
						&& confirmUser.getPassword().equals(request.getParameter("confirmPassword"))) {
					if (controller.deleteAllEvents()) {
						request.setAttribute("message", "The all events are deleted successfully!");
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
		}catch(Exception e) {
			request.setAttribute("message", "There are no events to delete !");
			request.getRequestDispatcher("deleteAllEvents.jsp").forward(request, response);
		}
	}

	private void deleteEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.event = new Event(request.getParameter("eventName"),
					DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime")),
					DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime")), false);
			EventController controller = new EventController(this.user, this.event);
			if (controller.deleteEvent()) {
				request.setAttribute("message", "The  events is deleted successfully!");
				request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to delete event again!");
				request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try again!");
			request.getRequestDispatcher("deleteEvent.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	private void updateMOTD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp MOTDTimestamp = DateTimeParser.parseToDateTime(request.getParameter("MOTDDate"));
			this.event = new Event("MOTD", MOTDTimestamp, MOTDTimestamp, true);
			EventController controller = new EventController(this.user, this.event);
			if (controller.upadateMOTD(request.getParameter("newMOTD"))) {
				this.doPost(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add MOTD again!");
				request.getRequestDispatcher("updateMOTD.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void updateEndDateTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.event = new Event(request.getParameter("eventName"),
					DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime")),
					DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime")), false);
			EventController controller = new EventController(this.user, this.event);
			if (controller
					.updateEventEndTime(DateTimeParser.parseToTimestamp(request.getParameter("eventNewEndDateTime")))) {
				this.doPost(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add event again!");
				request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try to add event again!");
			request.getRequestDispatcher("updateEndDateTime.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	private void updateStartDateTime(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.event = new Event(request.getParameter("eventName"),
					DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime")),
					DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime")), false);
			EventController controller = new EventController(this.user, this.event);
			if (controller.updateEventStartDateTime(
					DateTimeParser.parseToTimestamp(request.getParameter("eventNewStartDateTime")))) {
				this.doPost(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add event again!");
				request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("message", "Something go wrong. Try to add event again!");
			request.getRequestDispatcher("updateStartDateTime.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	private void updateDescription(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.event = new Event(request.getParameter("eventName"),
					DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime")),
					DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime")), false);
			EventController controller = new EventController(this.user, this.event);
			if (controller.upadateMOTD(request.getParameter("eventNewDescription"))) {
				this.doPost(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add event again!");
				request.getRequestDispatcher("updateDescription.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void addEvent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.event = new Event(request.getParameter("eventName"),
					DateTimeParser.parseToTimestamp(request.getParameter("eventStartDateTime")),
					DateTimeParser.parseToTimestamp(request.getParameter("eventEndDateTime")), false,
					request.getParameter("eventDescription"));
			EventController controller = new EventController(this.user, this.event);
			if (controller.addEvent()) {
				this.doPost(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add event again!");
				request.getRequestDispatcher("addEvent.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void addMOTD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Timestamp MOTDTimestamp = DateTimeParser.parseToDateTime(request.getParameter("MOTDDate"));
			this.event = new Event("MOTD", MOTDTimestamp, MOTDTimestamp, true, request.getParameter("MOTD"));
			EventController controller = new EventController(this.user, this.event);
			if (controller.addMOTD()) {
				this.doPost(request, response);
			} else {
				request.setAttribute("message", "Something go wrong. Try to add MOTD again!");
				request.getRequestDispatcher("addMOTD.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
