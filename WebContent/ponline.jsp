<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ponline</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>ssid</td>
			<td>username</td>
			<td>page</td>
			<td>ip</td>
			<td>time</td>
		</tr>
		<c:forEach var="ponline" items="${ponline}">
			<tr>
				<td>${ponline.ssid }</td>
				<td>${ponline.username }</td>
				<td>${ponline.page }</td>
				<td>${ponline.ip }</td>
				<td>${ponline.time }</td>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>