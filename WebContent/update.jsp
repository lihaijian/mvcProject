<%@page import="cn.lhj.mvcproject.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	width: 800px;
	height: 200px;
	border: 1px black solid;
}

#queryresult {
	width: 800px;
	height: 200px;
	border: 1px red solid;
}
</style>
</head>
<body>
	
	<form action="<%=request.getContextPath()%>/updateDo.udo" method="get">
		<table>
			<% Object error = request.getAttribute("error");
				if(error!=null){
					String err = (String)error;
			%>
					<tr>
					<td colspan="2"><input type="text" name="error" value=<%=err%>></td>
					</tr>
			<% 
				}
			%>
			
			<tr hidden>
				<td>用户id:</td>
				<td><input type="text" name="id" value=${requestScope.user.id}></td>
			</tr>
			<tr>
				<td>用户名:</td>
				<td><input type="text" name="username" value=${requestScope.user.username} ></td>
			</tr>
			
			<tr>
				<td>用户密码:</td>
				<td><input type="text" name="passwd" value=${requestScope.user.passwd}></td>
			</tr>
			
			<tr>
				<td>电话号码:</td>
				<td><input type="text" name="phoneno" value=${requestScope.user.phoneNo}></td>
			</tr>

			<tr>
				<td>地址:</td>
				<td><input type="text" name="address" value=${requestScope.user.address}></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;"><input
					type="submit" value="提交" /></td>
			</tr>
		</table>

	</form>
</body>
</html>