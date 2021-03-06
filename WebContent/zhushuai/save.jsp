<%@page import="com.bank.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/site.js"></script>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="css/QQShop.css">
<script type="text/javascript" src="js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="js/QQShop.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存款</title>
</head>
<body>
	<div class="container">
			<div class="navbar">
			<div class="navbar-inner">
				<div class="container">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> 
					<div class="brand" >银行管理系统
					</div>
					<ul class="nav" style="float: right;">
						<li class="nav-header">
						</li>
						<li >
							<a href="set.jsp"><i class="icon-user"></i>&nbsp; <%=((User)session.getAttribute("loginUser")).getName() %>&nbsp;&nbsp;你好</a>
						</li>
					</ul>				
				</div>
			</div>
		</div>
			<div class="row">
				<div class="span3">
				<div class="well" style="padding: 8px 0;">
					<ul class="nav nav-list">
						<li class="nav-header">功能</li>
						<li><a href="index.jsp"><i class="icon-home"></i>主页</a></li>
						<li class="divider"></li>
						<li class="active"><a href="save.jsp"><i class="icon-check"></i>存款</a></li>
						<li><a href="fetch.jsp"><i class="icon-check"></i>取款</a></li>
						<li><a href="transform.jsp"><i class="icon-check"></i>转账</a></li>
						<li><a href="selectTradeInfo.action"><i class="icon-check"></i>交易记录</a></li>
						<li><a href="set.jsp"><i class="icon-edit"></i>用户设置</a></li>
						<li class="divider"></li>
						<li><a href="about.jsp"><i class="icon-user"></i>关于我们</a></li>
							<li><a href="logout.action"><i class="icon-remove"></i>退出</a></li>
					</ul>
				</div>
			</div>
				<div class="span9">
					<h1>
						存款
					</h1>
					<a class="toggle-link" href="#new-project"><i class="icon-plus"></i>存款</a>
					<center>
						<s:fielderror cssStyle="color:red"></s:fielderror>
					</center>
					<form id="new-project" class="form-horizontal hidden" action="smoneyValidate.action">
						<fieldset>
							<legend></legend>
							<div class="control-group">
								<label class="control-label" for="input01">金额</label>
								<div class="controls">
									<input type="text" name="money" class="input-xlarge" id="input01" />&nbsp;&nbsp;元
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="input01">输入密码</label>
								<div class="controls">
									<input type="password" name="pwd" class="input-xlarge" id="input01" />
								</div>
							</div>
							<div class="form-actions">
								<button type="submit" class="btn btn-primary">提交</button> <button class="btn" type="submit">取消</button>	
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
</body>
</html>