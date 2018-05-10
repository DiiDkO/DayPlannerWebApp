package com.resources;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.system.LoginSystem;
import com.registration.system.RegistrationSystem;
import com.user.User;
import com.user.UserAction;
import com.user.UserController;
import com.user.validation.PasswordException;
import com.user.validation.UsernameException;

@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;

	public UserManager() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		this.userOptions(operation, request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User deleteUser = new User(request.getParameter("currUsername"), request.getParameter("currPassword"));
			UserController controller = new UserController(deleteUser);
			if (deleteUser.getUsername().equals(this.user.getUsername())
					&& deleteUser.getPassword().equals(this.user.getPassword())
					&& deleteUser.getPassword().equals(request.getParameter("confirmPassword"))) {
				if (controller.deleteUser()) {
					request.setAttribute("message", "The user account is deleted successfully!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}

			} else {
				request.setAttribute("message", "The invalid username/password!");
				request.getRequestDispatcher("deleteUser.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void changeUserPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			User user = new User(request.getParameter("username"), request.getParameter("password"));
			String newPass = request.getParameter("newPassword");
			UserController controller = new UserController(user);
			if (this.user.getUsername().equals(user.getUsername()) && this.user.getPassword().equals(user.getPassword())
					&& (!this.user.getPassword().equals(newPass))) {
				if (controller.updateUserPassword(newPass)) {
					this.user.setUsername(this.user.getUsername());
					request.getSession().setAttribute("user", this.user);
					request.getRequestDispatcher("EventsManager").forward(request, response);
				}
			} else {
				request.setAttribute("message", "Something go wrong. Try to add event again!");
				request.getRequestDispatcher("changeUserPassword.jsp").forward(request, response);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void registration(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.user = new User(request.getParameter("username"), request.getParameter("password"));
		try {
			RegistrationSystem registration = new RegistrationSystem(this.user);
			if (registration.checkUserValidation()) {
				if (registration.userExist()) {
					request.setAttribute("message", "User already exists!");
					request.getRequestDispatcher("registration.jsp").include(request, response);
				} else {
					UserController userController = new UserController(this.user);
					userController.addUser();
					request.setAttribute("message", "Registration done successfully!");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		} catch (

		UsernameException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("registration.jsp").include(request, response);
			e.printStackTrace();
		} catch (PasswordException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("registration.jsp").include(request, response);
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			request.setAttribute("message", e.getMessage());
			request.getRequestDispatcher("registration.jsp").include(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			request.setAttribute("message", "Invalid user data");
			request.getRequestDispatcher("registration.jsp").include(request, response);
			e.printStackTrace();
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.user = new User(request.getParameter("username"), request.getParameter("password"));
		try {
			LoginSystem login = new LoginSystem(user);
			if (login.Login()) {
				request.getSession().setAttribute("user", this.user);
				request.getRequestDispatcher("EventsManager").forward(request, response);
			} else {
				request.setAttribute("message", "Invalid username/password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error");
			request.setAttribute("Invalid username/password", "message");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("incomplete-switch")
	private void userOptions(String operation, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (UserAction.parseString(operation)) {
		case LOGIN:
			System.out.println();
			login(request, response);
			break;
		case REGISTRATION:
			this.registration(request, response);
			break;
		case CHANGE_USER_PASSWORD:
			this.changeUserPassword(request, response);
			break;
		case DELETE_USER:
			this.deleteUser(request, response);
			break;
		}
	}
}
