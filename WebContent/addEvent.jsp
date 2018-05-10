<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Day Planner</title>
<link href="jquery.datetimepicker.css" rel="stylesheet" />
<link href="myStyle.css" rel="stylesheet" />
<style>
body {
	background-color: lightgreen;
}

.button {
		background-color: #4CAF50; 
	color: white;
	text-align: center;
	font-size: 14px;
	padding: 0px;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
	cursor: pointer;
}

.submit {
		background-color: #4CAF50;
	color: white;
	text-align: center;
	font-size: 14px;
	padding: 0px;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
	cursor: pointer;
}
.back {
	background-color: #4CAF50;
	color: white;
	text-align: center;
	font-size: 16px;
	padding: 0.5px;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
	cursor: pointer;
}
</style>
</head>
<body>
	<form action="EventsManager" method="post">
		<b><button class="back">Back</button></b>
	</form>
	<h1 align="center">
		<b>Add new Event</b>
	</h1>
	<form action="EventsManager" method="get">
		<div class="container" align="center">
			<div class="message" align="center">
				<b><font color="red"><c:out value="${message}" /></font></b>
				<c:remove var="message" scope="session" />
			</div>
			 <input type="hidden" name="operation" value="addEvent">
			<br> <label for="eventName"><b>Name: </b></label> <input
				type="text" placeholder="Enter EventName" name="eventName" required><br><br>
			<br> <label for="eventStartDateTime"><b>Start date
					and time: </b>
			<!-- 	<input id="datetime"> --> <input type="datetime-local"
				name="eventStartDateTime"> <br>
			<br></label> <br> <br> <label for="eventEndDateTime"><b>End
					date and Time: </b>
			<!-- <input id="datetime">--> <input type="datetime-local"
				name="eventEndDateTime"><br>
			<br> </label><br> <br> <label for="eventDescription"><b>Description:
			</b></label> <input type="text" placeholder="Enter Event Description"
				name="eventDescription" required><br> <br> <br>
				<b><button class="button">Add Event</button></b>
		</div>
	</form>
</body>
<script src="jquery.datetimepicker.full.js">
	
</script>
<script src="jquery.js"></script>
<script>
	$("#datetime").datetimepicker();
</script>
</html>