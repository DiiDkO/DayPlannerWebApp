<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.timetable.renderer.PairHeadersData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
table {
	width: 150%;
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	height: 50px;
	border: 1px solid black;
	border-collapse: collapse;
}


.btn {
	background-color: #4CAF50;
	color: white;
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
}
</style>
</head>
<body>
	<form action="EventsManager" method="post">
		<b><button class="back">Back</button></b>
	</form>


	<form action="EventsManager" method="get">
		<div align="center">
			<input type="hidden" name="operation" value="viewFromDateToDate"><br>
			<b>From Date: </b><input type="date" name="fromDate"><br><br>
			 <b> To Date : </b><input type="date" name="toDate"> <br><br>
			<b><button class="button" OnClick='javascript:showTable();'>View events</button></b>
		</div>
	</form>


	<br>
	<div align="center">
		<h2 align="center">Events from date to date</h2>
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
	<script>
		function showTable() {
			document.getElementById('weekEvents').style.visibility = "visible";
		}
	</script>
</body>
</html>