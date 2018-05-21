package com.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.manager.EventOptionsAction;
import com.event.EventController;
import com.event.reminder.EventReminder;
import com.timetable.renderer.PairHeadersData;
import com.user.User;

@WebServlet("/EventsManager")
public class EventsManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		EventManagerController EMController = new EventManagerController(this.user);
		switch (EventOptionsAction.parseString(operation)) {
		case ADD_EVENT:
			EMController.addEvent(request, response);
			break;
		case ADD_MOTD:
			EMController.addMOTD(request, response);
			break;
		case UPDATE_EVENT_NAME:
			EMController.updateEventName(request, response);
			break;
		case UPDATE_EVENT_START_DATE_TIME:
			EMController.updateStartDateTime(request, response);
			break;
		case UPDATE_EVENT_END_DATE_TIME:
			EMController.updateEndDateTime(request, response);
			break;
		case UPDATE_EVENT_DESCRIPTION:
			EMController.updateDescription(request, response);
			break;
		case UPDATE_MOTD:
			EMController.updateMOTD(request, response);
			break;
		case DELETE_EVENT:
			EMController.deleteEvent(request, response);
			break;
		case DELETE_ALL_EVENTS:
			EMController.deleteAllEvents(request, response);
			break;
		case DELETE_EVENTS_START_END_DATE_TIME:
			EMController.deleteOnStartEndDateTime(request, response);
			break;
		case DELETE_MOTD:
			EMController.deleteMOTD(request, response);
			break;
		case VIEW_EVENTS_OF_CURRENT_DAY:
			EMController.viewCurrentDay(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_DAY:
			EMController.viewCertainDay(request, response);
			break;
		case VIEW_EVENTS_OF_CURRENT_MONTH:
			EMController.viewCurrentMonth(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_MONTH:
			EMController.viewCertainMonth(request, response);
			break;
		case VIEW_EVENTS_OF_CURRENT_WEEK:
			EMController.viewCurrentWeek(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_WEEK_OF_CURRENT_MONTH:
			EMController.viewCertaintWeek(request, response);
			break;
		case VIEW_EVENTS_OF_CERTAIN_WEEK_OF_CERTAIN_MONTH:
			EMController.viewCertainWeekOfCertainMonth(request, response);
			break;
		case VIEW_EVENTS_FROM_DATE_TO_DATE:
			EMController.viewFromDateToDate(request, response);
			break;
		}
	}

}
