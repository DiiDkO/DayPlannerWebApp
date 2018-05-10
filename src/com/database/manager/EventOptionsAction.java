package com.database.manager;


public enum EventOptionsAction {
	ADD_EVENT("addEvent"), ADD_MOTD("addMOTD"),
	UPDATE_EVENT_NAME("updateName"), UPDATE_EVENT_START_DATE_TIME("updateStDT"), UPDATE_EVENT_END_DATE_TIME("updateEndDT"), 
	UPDATE_EVENT_DESCRIPTION("updateDescription"), UPDATE_MOTD("updateMOTD"), 
	DELETE_EVENT("delEvent"), DELETE_ALL_EVENTS("delAllEvents"), DELETE_EVENTS_START_END_DATE_TIME("delOnStEndDT"), 
	DELETE_EVENTS_END_DATE_TIME("delOnEndDT"), DELETE_MOTD("delMOTD"),
	VIEW_EVENTS_OF_CURRENT_DAY("viewCurrDay"), VIEW_EVENTS_OF_CERTAIN_DAY("viewCertainDay"), VIEW_EVENTS_OF_CURRENT_MONTH("viewCurrMonth"), 
	VIEW_EVENTS_OF_CERTAIN_MONTH("viewCertainMonth"),VIEW_EVENTS_OF_CURRENT_WEEK("viewCurrWeek"),VIEW_EVENTS_OF_CERTAIN_WEEK_OF_CURRENT_MONTH("viewCertainWeekCurrMonth"),
	VIEW_EVENTS_OF_CERTAIN_WEEK_OF_CERTAIN_MONTH("viewCertainWeekCertainMonth"),VIEW_EVENTS_FROM_DATE_TO_DATE("viewFromDateToDate"), UNKNOWN(null);
	
	private String option;

	EventOptionsAction(String option) {
		this.option = option;
	}

	public String getString() {
		return this.option;
	}

	public static EventOptionsAction parseString(String option) {
		for (EventOptionsAction action : EventOptionsAction.values())
			if (action.getString().equals(option))
				return action;
		return UNKNOWN;
	}
}
