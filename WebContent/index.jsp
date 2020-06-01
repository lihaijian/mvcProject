<%@page import="cn.lhj.mvcproject.model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
table {
	border: 1px red solid;
	margin: 10px auto;
}

form {
	height: 200;
	border: 1px black solid;
}

#queryresult {
	height: 200;
	border: 1px red solid;
}
</style>
</head>
<body>

	<%-- 	<c:if test="${empty sessionScope.user}">
		<c:redirect url="/login.jsp" context="/mvcProject"/>
	</c:if> --%>

	<form action="<c:url value="/query.udo"/>" method="get">
		<table>
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username"></td>
			</tr>

			<tr>
				<td>电话号码:</td>
				<td><input type="text" name="phoneno"></td>
			</tr>

			<tr>
				<td>地址:</td>
				<td><input type="text" name="address"></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="用户查询" /> <a href="<c:url value="/add.jsp"/>">增加用户</a>
					<a href="<c:url value="/logout.udo"/>">注销用户</a></td>
			</tr>
		</table>

	</form>
	<div id="queryresult">
		<table>
			<tr>
				<td>用户id</td>
				<td>用户名称</td>
				<td>用户密码</td>
				<td>用户电话</td>
				<td>用户地址</td>
				<td>注册日期</td>
				<td>修改操作</td>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.username}</td>
					<td>${user.passwd}</td>
					<td>${user.address}</td>
					<td>${user.phoneNo}</td>
					<td>${user.regDate}</td>
					<td><a
						href="<c:url value="/update.udo">
						         	 <c:param name="id"  value="${user.id}"/>
						         </c:url>">修改</a>
						<a
						href="<c:url value="/delete.udo">
						         	 <c:param name="id"  value="${user.id}"/>
						         </c:url>">删除</a>
					</td>
				</tr>
			</c:forEach>

		</table>
		<br>
		<br>

		<table>
			<tr>
				<td>sessionid</td>
				<td>用户名称</td>
			</tr>
			<%
				Map<String, String> online = (Map<String, String>) application.getAttribute("online");
				if (online != null) {
					Set<String> keys = online.keySet();
					for (String key : keys) {
			%>
			<tr>
				<td><%=key%></td>
				<td><%=online.get(key)%></td>
			</tr>
			<%
				}
				}
			%>
		</table>

	</div>
</body>
</html>