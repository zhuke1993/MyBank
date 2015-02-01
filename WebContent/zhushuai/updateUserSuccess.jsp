<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息成功</title>
</head>
<body>
<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<center>
			修改信息成功，具体内容如下：
			<table>
				<tr>
					<td>
						密码：
					</td>
					<td>
						<s:property value="user.pwd" />
					</td>
				</tr>
				<tr>
					<td>
						联系电话：
					</td>
					<td>
						<s:property value="user.phone" />
					</td>
				</tr>
				<tr>
					<td>
						email：
					</td>
					<td>
						<s:property value="user.email" />
					</td>
				</tr>
			</table>
		</center>
</body>
</html>