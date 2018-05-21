package com.resources;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.User;
import com.user.UserAction;

@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		this.userOptions(operation, request, response);
	}

	@SuppressWarnings("incomplete-switch")
	private void userOptions(String operation, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserManagerController UMController = new UserManagerController(this.user);
		switch (UserAction.parseString(operation)) {
		case LOGIN:
			UMController.login(request, response);
			break;
		case REGISTRATION:
			UMController.registration(request, response);
			break;
		case CHANGE_USER_PASSWORD:
			UMController.changeUserPassword(request, response);
			break;
		case DELETE_USER:
			UMController.deleteUser(request, response);
			break;
		}
	}
}
