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
	border-radius: 5px;
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
	border-radius: 5px;
}

form {
  padding: 20px 0;
  position: relative;
  z-index: 2;
}
form input {
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  outline: 0;
  border: 1px solid rgba(255, 255, 255, 0.4);
  background-color: rgba(255, 255, 255, 0.2);
  width: 250px;
  border-radius: 3px;
  padding: 10px 15px;
  margin: 0 auto 10px auto;
  display: block;
  text-align: center;
  font-size: 18px;
  color: white;
  transition-duration: 0.25s;
  font-weight: 300;
}
form input:hover {
  background-color: rgba(255, 255, 255, 0.4);
}
form input:focus {
  background-color: white;
  width: 300px;
  color: #53e3a6;
}
form button {
  -webkit-appearance: none;
     -moz-appearance: none;
          appearance: none;
  outline: 0;
  background-color: white;
  border: 0;
  padding: 10px 15px;
  color: #53e3a6;
  border-radius: 3px;
  width: 250px;
  cursor: pointer;
  font-size: 18px;
  transition-duration: 0.25s;
}
form button:hover {
  background-color: #f5f7f9;
}
#myForm {
  position: fixed;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
</head>

<body>
	<div class="button">
		<b><a href="index.html" class="button">Back</a></b>
	</div>
	

	<form id="myForm" action="UserManager" method="post">
	<h2 align="center">	<b><i>Registration</i></b></h2>
		<div class="message" align="center">
			<c:out value="${message}" />
			<b><font color="red"><c:out value="${message}" /></font></b>
		</div>
		<div class="container" align="center">
		<input type="hidden" name="operation" value="registration"> <br>
		 <input type="text" placeholder="Enter Username" name="username" required><br>
		 <input	type="password" placeholder="Enter Password" name="password" required><br> 
				<b><button class="submit">Registration</button></b>
			<br>
		</div>
	</form>
</body>
</html>