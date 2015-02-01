<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="check.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">

<link rel="stylesheet" type="text/css" href="css/QQShop.css">
<script type="text/javascript" src="js/jquery-1.5.2.js"></script>
<script type="text/javascript" src="js/QQShop.js"></script>
<title>我的银行</title>
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
						<li class="active"><a href="index.jsp"><i class="icon-home"></i>主页</a></li>
							<li class="divider"></li>
						<li><a href="save.jsp"><i class="icon-check"></i>存款</a></li>
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
				<div class="wrapper" >      
						  <div id="focus" style="background:black;height:280px;">
							  <ul >
								  <li style="margin-left:-25px;width:800px;padding-right:25px;"><a href="#" ><img src="img/001.jpg" alt="" /></a></li>
								  <li style="margin-left:-25px;width:800px;padding-right:25px;"><a href="#"><img src="img/002.jpg" alt="" /></a></li>
								  <li style="margin-left:-25px;width:800px;padding-right:25px;"><a href="#"><img src="img/003.jpg" alt="" /></a></li>
							  </ul>
						  </div>
                      </div>
				<div class="well summary">
					<ul>
						<li><span class="count">用户名</span><br><%=((User)session.getAttribute("loginUser")).getName() %></li>
						<li><span class="count">账号</span><br><%=((User)session.getAttribute("loginUser")).getId() %></li>
						<li><span class="count">余额</span><br><%=((User)session.getAttribute("loginUser")).getBalance() %></li>

					</ul>
				</div>
				<h2>历史记录</h2>
				<table class="table table-bordered table-striped">
					<thead>
						<tr >
							<th>交易时间</th>
							<th>交易类型</th>
							<th>操作账户</th>
							<th>被操作账户</th>
							<th>交易金额</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="list" id="id">
							<tr>
							 	<td><s:property value="br_dateTime" /></td>
							 	<td><s:property value="br_action" /></td>
								<td><s:property value="br_fromId" /></td>
								<td><s:property value="br_toId" /></td>
								<td><s:property value="br_money" /></td>
							</tr>
						</s:iterator>

					</tbody>
				</table>
				<ul class="pager">
					<li class="next"><a href="selectTradeInfo.action">More &rarr;</a></li>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>