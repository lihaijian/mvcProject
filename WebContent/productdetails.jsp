<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String pname = (String)request.getAttribute("p");
		out.println(request.getAttribute("p"));
	%>
	
	<form action="<%=request.getContextPath()%>/addcar.pdo">
		<input type="hidden" name="pname" value="<%=pname %>"> 
		<input type="submit" value="加入购物车">
	</form>
</body>
</html>