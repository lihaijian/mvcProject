<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		/* function getCookie(c_name){
			if(document.cookie.length>0){
				var start = document.cookie.indexOf(c_name+'='); // abc=111;bca=222
				start = start + c_name.length + 1;
				var end = document.cookie.indexOf(";",start);
				//console.log(end);
				if(end!=-1){
					return unescape(document.cookie.substring(start,end));
				}else{
					return unescape(document.cookie.substring(start));
				}
			}
		}
		
		window.onload = function(){
			userform = document.getElementById("userform");
			username = document.getElementById("username");
			userkey = getCookie("userKey");
			ssid = getCookie("ssid");
			if(userkey!=null&&userkey!=""&&ssid!=null&&ssid!=""){
				username.value = userkey;
				userform.submit();
			}
		} */
	</script>
	
	<c:if test="${not empty requestScope.error}">
		<span style="color: red;font-weight:bolder;">${requestScope.error }</span>
	</c:if>
	
	

	<form id="userform" action="<%=response.encodeUrl(request.getContextPath()+"/login.udo")%>">
		用户名：<input id="username" type="text" name="username"><br>
		密码：<input type="text" name="passwd"><br>
		<input type="submit" value="提交"><br>
		记住我一周<input type="radio" value="7" name="expiredays"><br>
		记住我一个月<input type="radio" value="30" name="expiredays"><br>
		永远记住我<input type="radio" value="100" name="expiredays"><br>
	</form>

</body>
</html>