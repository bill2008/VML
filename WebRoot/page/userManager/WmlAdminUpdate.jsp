<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改员工信息</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/gt-grid/gt_grid.css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<%
	if(session.getAttribute("admin")==null){
		%>
		<script type="text/javascript">
			alert("用户没有登录,请重新登录后再操作!");
		window.parent.location.href="/VML";
		</script>
<%}%>
<script type="text/javascript">
function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}
function init(){
	
	var json=$("#data",parent.document).html();
	var jsonObj=JSON.parse(json);
	
    $("#name").val(jsonObj.name);
	$("#loginName").val(jsonObj.loginName);
	$("#phone").val(jsonObj.phone);
	$("#id").val(jsonObj.id);
	$("#updatePrice option[value="+jsonObj.updatePrice+"]").attr("selected",'selected'); 
	$("#status option[value="+jsonObj.status+"]").attr("selected",'selected');
	}

function doSubmit(){
	var id= $("#id").val();
	var name= $("#name").val();
	var loginName= $("#loginName").val();
	var phone= $("#phone").val();
	var status= $("#status").val();
	var updatePrice= $("#updatePrice").val();
	var password= $("#password").val();
	
	var data = {
			"wmladmin.id" : id,
			"wmladmin.name" : name,
			"wmladmin.loginName" : loginName,
			"wmladmin.phone" : phone,
			"wmladmin.status" : status,
			"wmladmin.password" : password,
			"wmladmin.updatePrice" : updatePrice
	};
	$.post("wmlAdmin_updateWmlAdmin.action",data,function(result){
		if(result == "fail"){
			alert("修改失败！");
		}
		else if(result == "optsuccess"){
			alert("修改成功！");
			window.opener.location.reload();
			window.close();
		}
	});
}
</script>
</head>
<body onload="init()">
<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>修改员工信息</span></center></div>
		<form id="frm">
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr >
		<td>员工姓名：</td>
		<td><input type="hidden" id="id" name="id"><input type="text"  id="name" name="name" onblur="checkValue(this,'员工姓名')"></td>
	</tr>
	<tr>
		<td>登录帐号：</td>
		<td><input type="text"  id="loginName" name="loginName" onblur="checkValue(this,'登录帐号')"></td>
	</tr>
	<tr>
		<td>密码：</td>
		<td><input type="password"  id="password" name="password" onblur="checkValue(this,'密码')"></td>
	</tr>
	<tr>
		<td>电话：</td>
		<td>
		<input type="text"  id="phone" name="phone" onblur="checkValue(this,'电话')"></td>
	</tr>
	<tr>
		<td>价格编辑：</td>
		<td><select id="updatePrice" name="updatePrice" onblur="checkValue(this,'价格编辑')">
				<option value="">--请选择--</option>
				<option value="1">否</option>
				<option value="0">是</option>
			</select></td>
	</tr>
	<tr>
		<td>状态：</td>
		<td><select id="status" name="status" onblur="checkValue(this,'状态')">
				<option value="">--请选择--</option>
				<option value="1">启用</option>
				<option value="0">停用</option>
			</select></td>
	</tr>
	</table>
	
<div>
	<input type="reset" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; " value="重置" />
	<input type="button" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; margin-right: 10px;" value="提交"   onclick="doSubmit()"/>
</div>
</div>
</form>
</div>

</body>
</html>