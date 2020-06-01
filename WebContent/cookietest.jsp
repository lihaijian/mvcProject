<%@ page language="java"  contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length!=0){
			for(Cookie cookie:cookies){
				out.println("cookie name:"+cookie.getName());
				out.println("cookie value:"+cookie.getValue());
			}
		}else{
			Cookie cookie = new Cookie("uuid1","uuidvalue1");
			//cookie.setMaxAge(5);
			 cookie.setSecure(true);
			response.addCookie(cookie);
		}
	%>
</body>
</html>