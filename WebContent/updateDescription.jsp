<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="myStyle.css" rel="stylesheet" />
<title>Insert title here</title>
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
		<b>Update Description</b>
	</h1>
	<form action="EventsManager" method="get">
		<div class="container" align="center">
			<input type="hidden" name="operation" value="updateDescription">
			<br> <label for="eventName"><b>Name: </b></label> <input
				type="text" placeholder="Enter EventName" name="eventName" required><br>
			<br> <label for="eventStartDateTime"><b>Start date
					and time: </b> <!-- input id="datetime"> --> <input
				type="datetime-local" name="eventStartDateTime"> </label><br> <br>
			<label for="eventEndDateTime"><b>End date and Time: </b> <!-- input id="datetime"> -->
				<input type="datetime-local" name="eventEndDateTime"></label><br><br>
			<label for="eventNewDescription"><b>Update Description: </b></label>
			<input type="text" placeholder="Enter New Description"
				name="eventNewDescription" required><br> <br> <br>
				<b><button class="submit">Update</button></b>
		</div>
	</form>
</body>
</html>