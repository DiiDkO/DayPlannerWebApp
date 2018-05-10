<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<b>Change User's password</b>
	</h1>
	<form action="UserManager" method="post">
		<div class="message" align="center">
			<b><font color="red"><c:out value="${message}" /></font></b>
			<c:remove var="message" scope="session" />
		</div>
		 <input type="hidden" name="operation" value="changePass">
		<div class="container" align="center">
			<br> <label for="username"><b>Username: </b></label> <input
				type="text" placeholder="Enter Username" name="username" required><br>
			<br> <label for="password"> <b>Password: </b></label> <input
				type="password" placeholder="Enter Password" name="password"
				required><br> <br> 
				 <label for="newPassword"><b>New Password: </b>
			</label> <input type="password" placeholder="Enter New Password"
				name="newPassword" required><br> <br>
				<b>
					<button class="submit">Submit</button>
				</b>
			<br>
		</div>
	</form>
</body>
</html>