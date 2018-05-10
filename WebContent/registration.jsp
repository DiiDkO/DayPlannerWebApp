<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>dayPlanner</title>
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
</style>
</head>

<body>
	<div class="button">
		<b><a href="http://localhost:8080/MyDayPlannerWeb" class="button">Back</a></b>
	</div>
	<h2 align="center">
		<b><i>Registration</i></b>
	</h2>

	<form action="UserManager" method="post">
		<div class="message" align="center">
			<c:out value="${message}" />
			<b><font color="red"><c:out value="${message}" /></font></b>
		</div>
		<div class="container" align="center">
		<input type="hidden" name="operation" value="registration"> <br>
			<br> <label for="username"><b>Username: </b></label> <input
				type="text" placeholder="Enter Username" name="username" required><br>
			<br> <label for="password"> <b>Password: </b></label> <input
				type="password" placeholder="Enter Password" name="password"
				required><br> <br>
			<div class="submit">
				<b><button class="submit">Registration</button></b>
			</div>
			<br>
		</div>
	</form>
</body>
</html>