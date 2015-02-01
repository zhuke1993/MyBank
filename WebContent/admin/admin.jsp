<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Custom DataGrid Pager - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.4.1/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../jquery-easyui-1.4.1/demo.css">
<script type="text/javascript"
	src="../jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/md5.js"></script>

<script type="text/javascript" src="../jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<table id="dg" title="所有用户" style="width: 700px; height: 350px"
		data-options="rownumbers:true,singleSelect:true,pagination:true,url:'../getPage.do',method:'get',
					  onSelect: function(rowIndex,rowData){
					  						$('#id').val(rowData.id);
					  						$('#id1').val(rowData.id);
					  						$('#pwd').val(rowData.pwd);
											$('#name').val(rowData.name);
											$('#phone').val(rowData.phone);
											$('#email').val(rowData.email);
											$('#sex').val(rowData.sex);
											$('#balance').val(rowData.balance);
											$('#groupId').val(rowData.groupId);
											$('#dlg').dialog('open');
					  					}">
		<thead>
			<tr>
				<th data-options="field:'id',width:50, align:'center'">用户ID</th>
				<th data-options="field:'name',width:150, align:'center'">用户名</th>
				<th data-options="field:'phone',width:150,align:'center'">电话</th>
				<th data-options="field:'email',width:150,align:'center'">邮箱</th>
				<th data-options="field:'sex',width:50, align:'center'">性别</th>
				<th data-options="field:'balance',width:100,align:'center'">余额</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		encodeURIComponent("UTF-8");
		function modifyAjax() {
			jQuery.ajax({
				url : '../modifyUser',
				type : 'post',
				dataType : 'text',
				data : {
					"id" : $("#id").val(),
					"name" : $("#name").val(),
					"pwd" : $('#pwd').val(),
					"phone" : $('#phone').val(),
					"email" : $('#email').val(),
					"sex" : $('#sex').val(),
					"balance" : $('#balance').val(),
					"groupId" : $('#groupId').val()
				},
				success : function(data) {
					alert(data);
					$('#dlg').dialog('close');
					window.location.reload();
				}
			});
		}
		
		function modifyAjax1() {
			jQuery.ajax({
				url : '../modifyUser',
				type : 'post',
				dataType : 'text',
				data : {
					"id" : $("#id3").val(),
					"name" : $("#name3").val(),
					"pwd" : $('#pwd3').val(),
					"phone" : $('#phone3').val(),
					"email" : $('#email3').val(),
					"sex" : $('#sex3').val(),
					"balance" : $('#balance3').val(),
					"groupId" : $('#groupId3').val()
				},
				success : function(data) {
					alert(data);
					$('#dlg').dialog('close');
					window.location.reload();
				}
			});
		}

		function deleteAjax() {
			jQuery.ajax({
				url : '../deleteUser',
				type : 'post',
				dataType : 'text',
				data : {
					"id" : $("#id").val(),
				},
				success : function(data) {
					alert(data);
					$('#dlg').dialog('close');
					window.location.reload();
				}
			});
		}
		
		function findAjax() {
			jQuery.ajax({
				url : '../findUser',
				type : 'post',
				dataType : 'text',
				data : {
					"id2" : $("#id2").val(),
				},
				success : function(data) {
					$('#detail').val(data);
					var json=eval('('+data+')'); 
					$('#id3').val(json.id);
					$('#id4').val(json.id);
					$('#pwd3').val(json.pwd);
					$('#name3').val(json.name);
					$('#phone3').val(json.phone);
					$('#email3').val(json.email);
					$('#sex3').val(json.sex);
					$('#balance3').val(json.balance);
					$('#groupId3').val(json.groupId);
					$('#dlg2').dialog('close');
					$('#dlg3').dialog('open');
				}
			});
		}
		$(function() {
			$('#dlg').dialog('close');
			$('#dlg1').dialog('close');
			$('#dlg2').dialog('close');
			$('#dlg3').dialog('close');
			var pager = $('#dg').datagrid().datagrid('getPager'); // get the pager of datagrid
			pager.pagination({
				buttons : [ {
					iconCls : 'icon-search',
					handler : function() {
						$('#dlg2').dialog('open');
					}
				}, {
					iconCls : 'icon-add',
					handler : function() {
						$('#dlg1').dialog('open');
					}
				}, {
					iconCls : 'icon-edit',
					handler : function() {
					}
				} ]
			});
		})
		function getSelected() {
			var row = $('#dg').datagrid('getSelected');
			if (row) {

			}
		}
	</script>
	<form action="find.html">
		<input type="hidden" name="detail" id="detail">
	</form>
	<div id="dlg" class="easyui-dialog" title="修改资料"
		style="width: 400px; height: 300px; padding: 10px"
		data-options="
				buttons: [{
					text:'确认修改',
					iconCls:'icon-ok',
					handler:function(){
						modifyAjax();
					}
				},{
					text:'销户',
					handler:function(){
						if(confirm('此操作不能恢复，确认销户么？')){
							deleteAjax();
						}
					}
				}]
			">
		<div class="easyui-panel" title=""
			style="width: 380px; padding: 10px 60px 20px 60px">
			<form action="../modifyUser" id="modifyUser" method="get">
				<table cellpadding="5">
					<tr>
						<td>User id:</td>
						<td><input id="id" name="id" type="hidden"><input
							id="id1" name="id1" class="easyui-validatebox textbox"
							data-options="required:true,validType:'length[3,10]'"
							disabled="disabled"></td>
					</tr>
					<tr>
						<td>User Name:</td>
						<td><input id="name" name="name"
							class="easyui-validatebox textbox"
							data-options="required:true,validType:'length[3,10]'"></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input id="email" name="email"
							class="easyui-validatebox textbox"
							data-options="required:true,validType:'email'"></td>
					</tr>
					<tr>
						<td>Phone:</td>
						<td><input id="phone" name="phone"
							class="easyui-validatebox textbox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>sex:</td>
						<td>male:<input id="sex" type="radio" name="sex" value="m"
							checked="true">&nbsp;&nbsp;&nbsp;&nbsp;female:<input
							id="sex" type="radio" name="sex" value="f"><input
							id="pwd" name="pwd" type="hidden"><input name="balance"
							id="balance" type="hidden"><input id="groupId"
							name="groupId" type="hidden"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<div id="dlg1" class="easyui-dialog" title="新开户"
		style="width: 400px; height: 400px; padding: 10px">
		<div class="easyui-panel" title=""
			style="width: 400px; padding: 10px 60px 20px 60px">
			<div style="padding: 0">
				<form id="ff" method="post" action="../register1">
					<table cellpadding="5">
						<tr>
							<td>Name:</td>
							<td><input class="easyui-textbox" type="text" name="name1"
								id="name1" data-options="required:true"></input><label
								id="lname1"></label></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><input class="easyui-textbox" type="text" name="pwd1"
								id="pwd1" data-options="required:true,validType:'length[6,20]'"></input></td>
						</tr>
						<tr>
							<td>RePassword:</td>
							<td><input class="easyui-textbox" type="text" name="repwd1"
								id="repwd1"
								data-options="required:true,validType:'length[6,20]'"></input><label
								id="lrepwd1"></label></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input class="easyui-textbox" type="text" name="email1"
								id="email1" data-options="required:true,validType:'email'"></input></td>
						</tr>
						<tr>
							<td>phone:</td>
							<td><input class="easyui-textbox" name="phone1" id="phone1"
								data-options="required:true,validType:'length[7,20]'"></input></td>
						</tr>
						<tr>
							<td>sex:</td>
							<td><select class="easyui-combobox" name="sex1" id="sex1">
									<option value="m">男</option>
									<option value="f">女</option>
							</select></td>
						</tr>
					</table>
				</form>
				<div style="text-align: center; padding: 5px">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="submitForm()">Submit</a> <a href="javascript:void(0)"
						class="easyui-linkbutton" onclick="clearForm()">Clear</a>
				</div>
			</div>
			<script>
				function submitForm() {
					jQuery.ajax({
						url : "../register1",
						type : "post",
						dataType : "text",
						data : {
							"name1" : $("#name1").val(),
							"pwd1" : $("#pwd1").val(),
							"phone1" : $("#phone1").val(),
							"email1" : $("#email1").val(),
							"sex1" : $("#sex1").val(),
						},
						success : function(data) {
							alert(data);
							$('#dlg1').dialog('close');
							window.location.reload();
						}
					});
				}
				function clearForm() {
					$('#ff').form('clear');
				}
			</script>
		</div>
	</div>

	<div id="dlg2" class="easyui-dialog" title="查找"
		style="width: 400px; height: 300px; padding: 10px"
		data-options="
				buttons: [{
					text:'确认',
					iconCls:'icon-ok',
					handler:function(){
						findAjax();
					}
				},{
					text:'取消',
					handler:function(){
						$('#dlg2').dialog('close');
					}
				}]
			">
		<div class="easyui-panel" title=""
			style="width: 380px; padding: 10px 60px 20px 60px">
			<form action="../modifyUser" id="modifyUser" method="get">
				<table cellpadding="5">
					<tr>
						<td>User id:</td>
						<td><input id="id2" name="id2"
							class="easyui-validatebox textbox"
							data-options="required:true,validType:'length[3,10]'"></td>
					</tr>
					<tr>
						<td>User Name:</td>
						<td><input id="name2" name="name2"
							class="easyui-validatebox textbox"
							data-options="required:true,validType:'length[3,10]'"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	
	<div id="dlg3" class="easyui-dialog" title="查找结果"
		style="width: 400px; height: 300px; padding: 10px"
		data-options="
				buttons: [{
					text:'确认修改',
					iconCls:'icon-ok',
					handler:function(){
						modifyAjax();
					}
				},{
					text:'销户',
					handler:function(){
						if(confirm('此操作不能恢复，确认销户么？')){
							deleteAjax();
						}
					}
				}]
			">
		<div class="easyui-panel" title=""
			style="width: 380px; padding: 10px 60px 20px 60px">
			<form action="../modifyUser" id="modifyUser" method="get">
				<table cellpadding="5">
					<tr>
						<td>User id:</td>
						<td><input id="id3" name="id3" type="hidden"><input
							id="id4" name="id4" class="easyui-validatebox textbox"
							data-options="required:true,validType:'length[3,10]'"
							disabled="disabled"></td>
					</tr>
					<tr>
						<td>User Name:</td>
						<td><input id="name3" name="name3"
							class="easyui-validatebox textbox"
							data-options="required:true,validType:'length[3,10]'"></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input id="email3" name="email3"
							class="easyui-validatebox textbox"
							data-options="required:true,validType:'email'"></td>
					</tr>
					<tr>
						<td>Phone:</td>
						<td><input id="phone3" name="phone3"
							class="easyui-validatebox textbox" data-options="required:true"></td>
					</tr>
					<tr>
						<td>sex:</td>
						<td>male:<input id="sex3" type="radio" name="sex3" value="m"
							checked="true">&nbsp;&nbsp;&nbsp;&nbsp;female:<input
							id="sex3" type="radio" name="sex3" value="f"><input
							id="pwd3" name="pwd3" type="hidden"><input name="balance3"
							id="balance3" type="hidden"><input id="groupId3"
							name="groupId3" type="hidden"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>