package com.user;

public enum UserAction {
	CHANGE_USER_PASSWORD("changePass"), DELETE_USER("delUser"), LOGIN("login"), REGISTRATION("registration"), UNKNOWN(null);
	private String option;

	UserAction(String option) {
		this.option = option;
	}

	public String getString() {
		return this.option;
	}

	public static UserAction parseString(String option) {
		for (UserAction action : UserAction.values())
			if (action.getString().equals(option))
				return action;
		return UNKNOWN;
	}
}
