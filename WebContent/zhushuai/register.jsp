<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开户</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link href="css/site.css" rel="stylesheet">
<script src="js/jquery.min.js"></script>
<script src="js/md5.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/site.js"></script>
<!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
<script type="text/javascript">
	//CharMode函数
	//测试某个字符是属于哪一类
	function CharMode(iN) {
		if (iN >= 48 && iN <= 57) //数字
			return 1;
		if (iN >= 65 && iN <= 90) //大写字母
			return 2;
		if (iN >= 97 && iN <= 122) //小写
			return 4;
		else
			return 8; //特殊字符
	}

	//bitTotal函数
	//计算出当前密码当中一共有多少种模式
	function bitTotal(num) {
		modes = 0;
		for (i = 0; i < 4; i++) {
			if (num & 1)
				modes++;
			num >>>= 1;
		}
		return modes;
	}

	//checkStrong函数
	//返回密码的强度级别
	function checkStrong(sPW) {
		if (sPW.length <= 4)
			return 0; //密码太短
		Modes = 0;
		for (i = 0; i < sPW.length; i++) {
			//测试每一个字符的类别并统计一共有多少种模式
			Modes |= CharMode(sPW.charCodeAt(i));
		}
		return bitTotal(Modes);
	}

	function isext() {
		jQuery.ajax({
			url : "isNameExt",
			type : "post",
			dataType : "text",
			data : {
				"name" : $("#name").val()
			},
			success : function(data) {
				if (data == 1) {
					$("#lname").text("用户名已存在");
					return true;
				} else {
					$("#lname").text("可以开户");
					return false;
				}
			}
		});
	}

	$(function() {
		$("#name").blur(function() {
			if ($("#name").val() == "") {
				$("#lname").text("用户名不能为空");
				return false;
			} else {
				return isext();
				$("#lname").text("");
				return true;
			}
		});

		$("#pwd").blur(function() {
			var pwd = $.trim($("#pwd").val());
			if ($("#pwd").val() == "") {
				$("#lpwd").text("密码不能为空");
				return false;
			} else if (!/^[\w\W]{6,18}$/.test(pwd)) {
				$("#lpwd").text("输入6-18位密码");
				return false;
			} else {
				$("#lpwd").text("");
				return true;
			}
		});

		$("#pwd").keyup(function() {
			var pwd = $.trim($("#pwd").val());
			O_color = "#eeeeee";
			L_color = "#FF0000";
			M_color = "#FF9900";
			H_color = "#33CC00";
			if (pwd == null || pwd == '') {
				Lcolor = Mcolor = Hcolor = O_color;
			} else {
				S_level = checkStrong(pwd);
				switch (S_level) {
				case 0:
					Lcolor = Mcolor = Hcolor = O_color;
				case 1:
					Lcolor = L_color;
					Mcolor = Hcolor = O_color;
					break;
				case 2:
					Lcolor = Mcolor = M_color;
					Hcolor = O_color;
					break;
				default:
					Lcolor = Mcolor = Hcolor = H_color;
				}
			}
			document.getElementById("strength_L").style.background = Lcolor;
			document.getElementById("strength_M").style.background = Mcolor;
			document.getElementById("strength_H").style.background = Hcolor;
			return;
		});

		$("#repwd").blur(function() {
			if ($("#repwd").val() == "") {
				$("#lrepwd").text("请再次输入密码");
				return false;
			} else if ($("#pwd").val() != $("#repwd").val()) {
				$("#lrepwd").text("两次输入的密码不一致");
				return false;
			} else {
				$("#lrepwd").text("");
				return true;
			}
		});

		$("#phone").blur(function() {
			var phone = $.trim($("#phone").val());
			if ($("#phone").val() == "") {
				$("#lphone").text("联系电话不能为空");
				return false;
			} else {
				 //匹配格式：11位手机号码，3-4位区号，7-8位电话号码，1-4位分机号，如：12345678901、1234-12345678-1234 
				 if(!/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(phone)){
						$("#lphone").text("输入正确格式的联系电话");
						return false;
					}else{
						$("#lphone").text("");
						return true;
					}
			}
		});

		$("#email").blur(function() {
			var email = $.trim($("#email").val());
			if ($("#email").val() == "") {
				$("#lemail").text("电子邮箱不能为空");
				return false;
			} else {
				 if(!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(email)){
						$("#lemail").text("输入正确格式的电子邮箱");
						return false;
					}else{
						$("#lemail").text("");
						return true;
					}
			}
		});

	});

	//在提交前进行检查
	function check() {
		if ($("#name").val() == "") {
			$("#lname").text("用户名不能为空");
			return false;
		}
		if ($("#pwd").val() == "") {
			$("#lpwd").text("密码不能为空");
			return false;
		}
		if ($("#repwd").val() == "") {
			$("#lrepwd").text("请再次输入密码");
			return false;
		} else if ($("#pwd").val() != $("#repwd").val()) {
			$("#lrepwd").text("两次输入的密码不一致");
			return false;
		}
		if ($("#phone").val() == "") {
			$("#lphone").text("联系电话不能为空");
			return false;
		}
		if ($("#email").val() == "") {
			$("#lemail").text("电子邮件不能为空");
			return false;
		}
		if ($("#sex").val() == "") {
			$("#lsex").text("请选择性别");
			return false;
		}
		//密码用MD5加密
		$("#pwd").val(hex_md5($("#pwd").val()));
		$("#repwd").val(hex_md5($("#repwd").val()));
		
		return true;
		
	}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span9">
				<h1>开户</h1>
				<%
					if (session.getAttribute("regMsg") != null) {
						out.print("<lable style='color:red'>"
								+ session.getAttribute("regMsg") + "</lable><br>");
						session.setAttribute("regMsg", null);
					}
				%>
				<form id="edit-profile" class="form-horizontal" action="regValidate"
					method="post">
					<fieldset>
						<div class="control-group">
							<label class="control-label" for="input01">用户名</label>
							<div class="controls">
								<input type="text" id="name" name="user.name" /><label
									style="color: red" id="lname"></label>
							</div>
						</div>
						<div class="control-group" style='position:relative'>
							<label class="control-label" for="input01">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
							<div class="controls">
								<input type="password" id="pwd" name="user.pwd"/>
								<table width="200px" height="25" border="0" cellspacing="0"
									cellpadding="1" bordercolor="#cccccc" style='font-size: 12px;display: inline;position:absolute'>
									<tr align="center" bgcolor="#eeeeee">
										<td width="33%" id="strength_L">弱</td>
										<td width="33%" id="strength_M">中</td>
										<td width="33%" id="strength_H">强</td>
									</tr>
								</table>
								<label style="color: red" id="lpwd"></label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="input01">确认密码</label>
							<div class="controls">
								<input type="password" id="repwd" name="user.repwd" /><label
									style="color: red" id="lrepwd"></label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="input01">联系电话</label>
							<div class="controls">
								<input type="text" id="phone" name="user.phone" /><label
									style="color: red" id="lphone"></label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="input01">Email</label>
							<div class="controls">
								<input type="text" id="email" name="user.email" /><label
									style="color: red" id="lemail"></label>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="input01">性&nbsp;&nbsp;&nbsp;&nbsp;别</label>
							<div class="controls">
								男<input type="radio" id="sex" name="user.sex" value="男" /><br>
								女<input type="radio" id="sex" name="user.sex" value="女" /> <label
									style="color: red" id="lsex"></label>
							</div>
						</div>
						<center>
							<s:fielderror cssStyle="color:red"></s:fielderror>
						</center>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary"
								onclick="return check()">注册</button>
							<button class="btn" type="reset">重置</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>

</body>
</html>