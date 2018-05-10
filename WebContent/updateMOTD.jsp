<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<b>Update MOTD</b>
	</h1>
	<form action="EventsManager" method="get">
		<div class="container" align="center">
			<input type="hidden" name="operation" value="updateMOTD"> <br>
			<label for="MOTDDate"><b>Date: </b></label>
			<input type="date" name="MOTDDate"><br> <br>
			<label for="MOTD"><b>UpdateMOTD: </b></label>
			<input type="text" placeholder="Enter New MOTD" name="newMOTD" required><br> <br>
				<b><button class="submit">Update</button></b>
		</div>
	</form>

</body>
</html>