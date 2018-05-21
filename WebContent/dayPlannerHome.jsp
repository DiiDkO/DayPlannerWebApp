<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.timetable.renderer.PairHeadersData"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>DayPlanner</title>
<style>
table {
	width: 100%;
}

th, td {
	height: 50px;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

.dropdown-toggle {
	background-color: #4CAF50;
	color: white;
}

.btn {
	background-color: #4CAF50;
	color: white;
}

.dropdown-submenu {
	color: white;
	position: relative;
}

.dropdown-submenu .dropdown-menu {
	color: white;
	top: 0;
	left: 100%;
	margin-top: -1px;
}

body {
	background-color: lightgreen;
}

.button {
	background-color: #4CAF50;
	color: white;
	text-align: center;
	font-size: 16px;
	padding: 0.5px;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 5px;
}

</style>
</head>
<body>

	<div align="left">
		<div class="dropdown">
			<button class="btn  dropdown-toggle" type="button"
				data-toggle="dropdown">
				Options <span class="caret"></span>
			</button>
			<ul class="dropdown-menu">

				<li class="dropdown-submenu"><a class="test" tabindex="-1"
					href="#">Add Menu <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="addEvent.jsp">Add Event</a></li>
						<li><a tabindex="-1" href="addMOTD.jsp">Add MOTD</a></li>
					</ul></li>

				<li class="dropdown-submenu"><a class="test" tabindex="-1"
					href="#">Update Menu <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="changeUserPassword.jsp">Change Password</a></li>
						<li><a tabindex="-1" href="updateEventName.jsp">Update Event Name</a></li>
						<li><a tabindex="-1" href="updateStartDateTime.jsp">Update Start Date and Time</a></li>
						<li><a tabindex="-1" href="updateEndDateTime.jsp">Update End Time</a></li>
						<li><a tabindex="-1" href="updateDescription.jsp">Update Description</a></li>
						<li><a tabindex="-1" href="updateMOTD.jsp">Update MOTD</a></li>
					</ul></li>
				<li class="dropdown-submenu"><a class="test" tabindex="-1"
					href="#">Delete Menu <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="deleteUser.jsp">Delete User</a></li>
						<li><a tabindex="-1" href="deleteEvent.jsp">Delete Event</a></li>
						<li><a tabindex="-1" href="deleteAllEvents.jsp">Delete All Events</a></li>
						<li><a tabindex="-1" href="deleteEventsOnDateTime.jsp">Delete Events on Start/End Date and Time</a></li>
						<li><a tabindex="-1" href="deleteMOTD.jsp">Delete MOTD</a></li>
					</ul></li>
				<li class="dropdown-submenu"><a class="test" tabindex="-1"
					href="#">Your DayPlanner <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a tabindex="-1" href="viewDay.jsp">Events of Day</a></li>
						<li><a tabindex="-1" href="viewEventsOfMonth.jsp">Events of Month</a></li>
						<li><a tabindex="-1" href="viewEventsOfWeek.jsp">Events of Week </a></li>
 						<li><a tabindex="-1" href="viewEventsFromDateToDate.jsp">Events from Date to Date</a></li> 
					</ul>
					</li>
			</ul>
			<div class="button">
				<b><a href="index.html"
					class="button">Log out</a></b>
			</div>
		</div>
	</div>
	<h1 align="center">
		<b>Welcome to Day Planner</b>
	</h1>
	<div align="center">
		<h2 align="center">Current Week events</h2>
		<div class="message" align="center">
			<b><font color="red"><c:out value="${message}" /></font></b>
			<c:remove var="message" scope="session" />
		</div>
		<table border="1">
			<tbody>
				<tr>
					<c:forEach items="${arrays.getHeaders()}" var="arr"
						varStatus="iterator">
						<th>${arr}</th>
					</c:forEach>
				</tr>
				<c:forEach items="${arrays.getData()}" var="arr"
					varStatus="iterator">
					<tr>
						<c:forEach items="${arr}" var="element">
							<td>${element}</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.dropdown-submenu a.test').on("click", function(e) {
				$(this).next('ul').toggle();
				e.stopPropagation();
				e.preventDefault();
			});
		});
		
		 setTimeout(function(){
		       location.reload();
		   },60000);
	</script>
</body>
</html>