<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注销失败</title>
</head>
<body>
	<center>
			<br>
			<br>
			<br>
			<br>
			<br>
			您的帐户还有<s:property value="balance"/>元，请将剩余金额全部取出！
			<br>
            <a href="fetch.jsp">进入取款页面</a>
        </center>
</body>
</html>