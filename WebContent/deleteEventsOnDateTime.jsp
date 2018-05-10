<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="jquery.datetimepicker.css" rel="stylesheet" />
<style>
body {
	background-color: lightgreen;
}

.button {
	background-color: #4CAF50;
	color: white;
	text-align: center;
	font-size: 16px;
	padding: 1px;
	text-decoration: none;
	display: inline-block;
	margin: 4px 2px;
	cursor: pointer;
}

.submit {
	background-color: #4CAF50;
	color: white;
	text-align: center;
	font-size: 16px;
	padding: 1px;
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
		<b>Delete Event/s Date and Time</b>
	</h1>
	<form action="EventsManager" method="get">
		<div class="container" align="center">
		<div class="message" align="center">
		<b><font color="red"><c:out value="${message}" /></font></b>
		<c:remove var="message" scope="session" />
		</div>
			<input type="hidden" name="operation" value="delOnStEndDT">
			<br> <label for="eventStartDateTime"><b>Start Date and Time: </b>
						<!-- input id="datetime"> -->
			<input type="datetime-local" name="eventStartDateTime"> </label><br>
			<br> <label for="eventEndDateTime"><b>End Date and Time: </b>
			<input type="datetime-local" name="eventEndDateTime"> </label><br>
				<b><button class="submit">Delete</button></b>
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