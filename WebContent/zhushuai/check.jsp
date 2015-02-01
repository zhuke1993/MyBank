<%@page import="com.bank.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主页</title>
</head>
<body>
<%
	Object obj=(Object)request.getSession().getAttribute("loginUser");
	if (obj == null) {
		response.sendRedirect("login.jsp");
	} else {
		User user = (User) obj;
	}
%>
</body>
</html>