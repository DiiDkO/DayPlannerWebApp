<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="myStyle.css" rel="stylesheet">
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
	<h1 align="center">Add MOTD</h1>
	<form action="EventsManager" method="get">
		<div class="container" align="center">
			<input type="hidden" name="operation" value="addMOTD"> <br>
			<label for="MOTD"><b>MOTD: </b></label> <input type="text"
				placeholder="Enter MOTD" name="MOTD" required><br> <br>
			<label for="MOTDDate"><b>Date: </b></label> <input type="date"
				name="MOTDDate"><br> <br>
				<b><button class="submit">Add MOTD</button></b>
		</div>
	</form>
</body>
</html>