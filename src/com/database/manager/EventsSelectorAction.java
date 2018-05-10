package com.database.manager;

public enum EventsSelectorAction {
	FIRSTWEEK(1), SECONDWEEK(2), THIRDWEEK(3), FORTHWEEK(4), FIFTHWEEK(5), UNKNOWN(0);

	private int id;

	EventsSelectorAction(int id) {
		this.id = id;
	}

	private int getId() {
		return this.id;
	}

	public static EventsSelectorAction parseInt(int choice) {
		for (EventsSelectorAction action : EventsSelectorAction.values()) {
			if (action.getId() == choice)
				return action;
		}
		return UNKNOWN;
	}
}
