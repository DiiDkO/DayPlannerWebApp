<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.timetable.renderer.PairHeadersData"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
table {
	width: 100%;
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	height: 50px;
	border: 1px solid black;
	border-collapse: collapse;
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
</style>
</head>
<body>
<body>
	<form action="EventsManager" method="post">
		<b><button class="back">Back</button></b>
	</form>
	<h1 align="center">Events of Week</h1>
	<div class="message" align="center">
		<input type="hidden" name="operation" value="viewCurrWeek"> <br>
		<b><font color="red"><c:out value="${message}" /></font></b>
		<c:remove var="message" scope="session" />
	</div>
	<table>
		<tr>
			<th>Events of current week</th>
			<th>Events of certain week of current month</th>
			<th>Events of certain week of certain month</th>
		</tr>
		<tr>
			<td align="center">
				<form action="EventsManager" method="get">
					<div align="center">
						<input type="hidden" name="operation" value="viewCurrWeek">
						<br>
						<b><button class="button" OnClick='javascript:showHideTable();'>View events</button></b>
						</div>
				</form>
			</td>
			<td align="center">
			<form action="EventsManager" method="get">
					<div align="center">
						<input type="hidden" name="operation"
							value="viewCertainWeekCurrMonth"> 
							<select name="week">
							<option value="#">Choose week</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
							<b><button class="button" OnClick='javascript:showTable();'>View events</button></b>
					</div>
				</form></td>
				<td>
				<form action="EventsManager" method="get">
					<div align="center">
						<input type="hidden" name="operation"
							value="viewCertainWeekCertainMonth"> 
							<input type="month" name="weekMonth">
							<select name="week">
							<option value="#">Choose week</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
						</select>
							<b><button class="button" OnClick='javascript:showTable();'>View events</button></b>
					</div>
				</form>
				</td>
		</tr>
	</table><br>


	<div align="center">
		<div align="center" id="weekEvents">
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
	</div>
	<script>
		function showTable() {
			document.getElementById('weekEvents').style.visibility = "visible";
		}
		
		setTimeout(function(){
		       location.reload();
		   },60000);
	</script>
</body>
</html>