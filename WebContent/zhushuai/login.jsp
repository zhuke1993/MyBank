<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

  <meta charset="UTF-8">

  <title>个人银行账户管理系统</title>
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="demo/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="js/prefixfree.min.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/md5.js"></script>

	
    <style>
body{
  padding:0px;
  margin:0px;
  background:hsl(212,50%,50%);
  position: relative;
}
#form{
	position:absolute;
	left: 700px;
	top:200px;
}
.sun {
  width:40px;
  height:40px;
  border-radius:360px;
  background:white;
  right:40px;
  top:-120px;
  position:absolute;
  	animation-name: sunrise;
	  animation-duration: 1s;
	  animation-timing-function: ease;
	  animation-iteration-count: 1;	
	  animation-direction: normal;
	  animation-delay: .1;
	  animation-play-state: running;
	  animation-fill-mode: forwards;
}

@keyframes sunrise {

	0% {
		  top: -120px;
	}

25% {
  top:19px;
  right: 40px;
	}

50% {
  top:25px;
  right: 40px;
	}

100% {
  top:18px;
  right: 40px;
	}

}

.wmd1{
   -webkit-transform: scale(.6);
  position:absolute;
  top:120px;
  left:200px;
  perspective: 1000px;
}

.base{ }

.blades{
  width: 350px;
  height: 350px;
  left: 10%;
  top: 10%;
  z-index:2;
  border-radius: 50%;
  position: absolute;
  margin-top: -30px;
  margin-left: 50px;

  animation: spin 6s linear infinite;
}

.blade1 {
  		background: white;
  position:absolute;
	  width:41px;
	  height:139px;
  top:-10px;
  left:150.5px;
  transform:rotate(0deg);
  display:inline-block;
  background:
    linear-gradient(135deg, transparent 20px, white 0),
    linear-gradient(225deg, transparent 20px, white 0),
    linear-gradient(315deg, transparent 20px, white 0),
    linear-gradient(45deg, transparent  20px,  white 0);
  background-position: top left, top right, bottom right, bottom left;
  background-size: 50% 50%;
  background-repeat: no-repeat;
}

.blade2 {
  		background:white;
  position:absolute;
	  width:41px;
	  height:139px;
  top:105.5px;
  left:41px;
  transform:rotate(-90deg);
  display:inline-block;
  background:
    linear-gradient(135deg, transparent 20px, white 0),
    linear-gradient(225deg, transparent 20px, white 0),
    linear-gradient(315deg, transparent 20px, white 0),
    linear-gradient(45deg, transparent  20px,  white 0);
  background-position: top left, top right, bottom right, bottom left;
  background-size: 50% 50%;
  background-repeat: no-repeat;
}

.blade3 {
  		background:white;
  position:absolute;
	  width:41px;
	  height:139px;
  top:105.5px;
  right:41px;
  transform:rotate(-270deg);
  display:inline-block;
  background:
    linear-gradient(135deg, transparent 20px, white 0),
    linear-gradient(225deg, transparent 20px, white 0),
    linear-gradient(315deg, transparent 20px, white 0),
    linear-gradient(45deg, transparent  20px,  white 0);
  background-position: top left, top right, bottom right, bottom left;
  background-size: 50% 50%;
  background-repeat: no-repeat;
}

.blade4 {
  		background:white;
  position:absolute;
	  width:41px;
	  height:139px;
  bottom:-10px;
  left:150.5px;
  transform:rotate(180deg);
  display:inline-block;
  background:
    linear-gradient(135deg, transparent 20px, white 0),
    linear-gradient(225deg, transparent 20px, white 0),
    linear-gradient(315deg, transparent 20px, white 0),
    linear-gradient(45deg, transparent  20px,  white 0);
  background-position: top left, top right, bottom right, bottom left;
  background-size: 50% 50%;
  background-repeat: no-repeat;
}

.vane1{
  width:1px;
  height:350px;
  left:175px;
  background:white;
  position:absolute;
  transform:rotate(90deg);
}

.vane2{
  width:1px;
  height:350px;
  left:171.5px;
  background:white;
  position:absolute;
  transform:rotate(180deg);
}

.base .bottom_base{
  position:absolute;
  width:90px;
  height:100px;
  left:162px;
  border-right: 16px solid transparent;
  border-left: 16px solid transparent;
  border-bottom: 380px solid white;
  opacity:.8;
  z-index:-1;
  top:42.5px;
}

ul{
  position:absolute;
  top:180px;
  left:-30px;
}
li{
  width:10px;
  height:10px;
  background:white;
  padding:2px;
  display:block;
  margin: 30px;
  box-shadow: inset 0px -2px 0px lightgray; 
}

li:nth-child(2){
  position:absolute;
  top:-45px;
  left:20px;
}

li:nth-child(1){
  position:absolute;
  top:35px;
  left:50px;
}

li:nth-child(3){
  position:absolute;
  top:75px;
  left:50px;
}

@keyframes spin {
0% {
   	transform:rotate(0deg);
 }
 100% {
   	transform:rotate(-360deg);
 }
}

</style>

<script type="text/javascript">

	function validate(){
		document.getElementById("myForm").submit();
	}
	
	$(function() {
		$("input",$("#name").next("span")).blur(function() {
			if ($("#name").val() == "") {
				$("#lname").text("用户名不能为空");
			} else {
				$("#lname").text("");
			}

		});
		$("input",$("#pwd").next("span")).blur(function() {
			if ($("#pwd").val() == "") {
				$("#lpwd").text("密码不能为空");
			} else {
				$("#lpwd").text("");
			}

		});

		$("input",$("#validate").next("span")).blur(function() {
			jQuery.ajax({
				url : "checkValidate",
				type : "post",
				dataType : "text",
				data : {
					"validate" : $("#validate").val()
				},
				success : function(data) {
					if (data == 0) {
						$("#lvalidate").text("验证码错误");
					} else {
						$("#lvalidate").text("");
					}
				}
			});
		});

	});

	function check() {	
		if ($("input",$("#name").next("span")).val() == "") {
			$("#lname").text("用户名不能为空");
			return false;
		} else if ($("input",$("#pwd").next("span")).val() == "") {
			$("#lpwd").text("密码不能为空");
			return false;
		} else {
			//密码用MD5加密
			$("input",$("#pwd").next("span")).val(hex_md5($("input",$("#pwd").next("span")).val()));
			jQuery.ajax({
				url : "checkValidate",
				type : "post",
				dataType : "text",
				data : {
					"validate" : $("#validate").val()
				},
				success : function(data) {
					if (data == 0) {
						$("#lvalidate").text("验证码错误");
						return false;
					} else {
						return true;
					}
				}
			});
		}
	}
	//重新获取验证码
	function myReload() {
		document.getElementById("createCheckCode").src = document
				.getElementById("createCheckCode").src
				+ "?nocache=" + new Date().getTime();//防止浏览器缓存而得不到实时最新的结果
	}
</script>

</head>

<body>
	<div class="easyui-panel" title="银行管理系统" style="width: 400px" id="form">
		<div style="padding: 10px 60px 20px 60px">
			<form id="myForm" method="post" action="loginValidate">
				<%
					if (session.getAttribute("loginMsg") != null) {
						out.print("<lable style='color:red'>"
								+ session.getAttribute("loginMsg") + "</lable><br>");
						session.setAttribute("loginMsg", null);
					}
					if (session.getAttribute("regMsg") != null) {
						out.print("<lable style='color:red'>"
								+ session.getAttribute("regMsg") + "</lable><br>");
						session.setAttribute("regMsg", null);
					}
			%>
				<center>
					<s:fielderror cssStyle="color:red"></s:fielderror>
				</center>
				<table cellpadding="5">
					<tr>
						<td>用户名</td>
						<td><input class="easyui-textbox" type="text" name="user.name"
							id="name" placeholder="Name" data-options="required:true"></input><br>
							<label style="color: red" id="lname"></label></td>
					</tr>
					<tr>
						<td>密&nbsp;&nbsp;&nbsp;码</td>
						<td><input class="easyui-textbox" type="password" name="user.pwd"
							id="pwd" placeholder="Password" data-options="required:true"></input><br>
							<label id="lpwd" style="color: red"></label></td>
					</tr>
					<tr>
						<td>身&nbsp;&nbsp;&nbsp;份</td>
						<td><select class="" name="">
								<option value="1" name="user.groupId" id="user">普通用户</option>
								<option value="0" name="user.groupId" id="admin">管理员</option>
						</select></td>
					</tr>
					<tr>
						<td>验证码</td>
						<td style='position: relative'><input class="easyui-textbox" name="validate" type="text" id="validate" maxlength="4"
							style="width: 60px" data-options="required:true"></input>
							&nbsp;&nbsp;&nbsp;<img id="createCheckCode" src="pngValidate"
							style='position: absolute'><br> <lable id="lvalidate"
								style="color:red"></lable>
						<td><a href="#" style='position: absolute'
							onClick="myReload()">换一个</a></td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:validate()" class="easyui-linkbutton"
					onclick="return check();">登录</a> <a href="register.jsp"
					class="easyui-linkbutton">注册</a>
			</div>
		</div>
	</div>
	<div style="text-align: center; clear: both">
		<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>
		<script src="/follow.js" type="text/javascript"></script>
	</div>


	<div class="wmd1">
		<div class="blades">
			<div class="blade2"></div>
			<div class="blade1"></div>
			<div class="vane1"></div>
			<div class="blade3"></div>
			<div class="blade4"></div>
			<div class="vane2"></div>
		</div>
		<div class="base">
			<div class="bottom_base">
				<ul>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>

		<div class="wmd1">
			<div class="blades">
				<div class="blade2"></div>
				<div class="blade1"></div>
				<div class="vane1"></div>
				<div class="blade3"></div>
				<div class="blade4"></div>
				<div class="vane2"></div>
			</div>
			<div class="base">
				<div class="bottom_base">
					<ul>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
						<li></li>
					</ul>
				</div>
			</div>

			<div class="wmd1">
				<div class="blades">
					<div class="blade2"></div>
					<div class="blade1"></div>
					<div class="vane1"></div>
					<div class="blade3"></div>
					<div class="blade4"></div>
					<div class="vane2"></div>
				</div>
				<div class="base">
					<div class="bottom_base">
						<ul>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
							<li></li>
						</ul>
					</div>
				</div>
</body>
</html>