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
<title>添加客户信息</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/gt-grid/gt_grid.css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<%
	if(session.getAttribute("admin")==null){
		%>
		<script type="text/javascript">
			alert("用户没有登录,请重新登录后再操作!");
		window.parent.location.href="/VML";
		</script>
<%}%>
<script type="text/javascript">
$(document).ready( function() {
	$.post("wmlOrgan_queryWmlOrgan.action",null, function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = $("<option></option>");
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].code);
			$("#organ").append($option);
		}
	});
	$("#loginName").val($("#loginName",parent.document).attr("value"));
	$("#channel").val($("#channel",parent.document).attr("value"));
	$("#status").val($("#status",parent.document).attr("value"));
	$("#uid").val($("#uid",parent.document).attr("value"));
});


function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}

function doSubmit(){
	var organ= $("#organ").val();
	var loginName= $("#loginName").val();
	var name= $("#name").val();
	var status= $("#status").val();
	var channel= $("#channel").val();
	var permissions= $("#permissions").val();
	var type=$("#type").val();
	var uid= $("#uid").val();
	var phone= $("#phone").val();
	var data = {
			"wmlUser.organ" : organ,
			"wmlUser.loginName" : loginName,
			"wmlUser.name" : name,
			"wmlUser.status" : status,
			"wmlUser.channel" : channel,
			"wmlUser.type" : type,
			"wmlUser.permissions" : permissions,
			"wmlUser.uid" : uid,
			"wmlUser.phone" : phone
	};
	$.post("wmlUser_addWmlUser.action",data,function(result){
		if(result == "fail"){
			alert("添加失败！");
		}
		else if(result == "optsuccess"){
			alert("添加成功！");
			/* window.opener.location.reload(); */
		}else if(result=="uidNull"){
			alert("此推荐人不存在！");
		}
	});
	$("#tt",parent.document).tabs('close', "添加客户");
}

</script>
</head>
<body>
<form id="frm" >
<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>添加客户信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr>
		<td>所属商户：</td>
		<td>
		<select id="organ" name="organ" >
				<option value="">--请选择--</option>
			</select></td>
	</tr>
		<tr >
		<td>登录帐号：</td>
		<td><input type="text"  id="loginName" name="loginName" onblur="checkValue(this,'登录帐号')"></td>
	</tr>
	<tr >
		<td>手机号码：</td>
		<td><input type="text"  id="phone" name="phone" onblur="checkValue(this,'手机号码')"></td>
	</tr>
	<tr>
		<td>用户名：</td>
		<td><input type="text"  id="name" name="name" onblur="checkValue(this,'用户名')"></td>
	</tr>
	<tr>
		<td>状态：</td>
		<td><select id="status" name="status" onblur="checkValue(this,'用户状态')">
				<option value="">--请选择--</option>
				<option value="0">待审核</option>
				<option value="1">正常</option>
				<option value="2">禁用</option>
			</select></td>
	</tr>
		<tr>
		<td>登录类型：</td>
		<td><select id="channel" name="channel" onblur="checkValue(this,'登录类型')">
				<option value="">--请选择--</option>
				<option value="0">QQ</option>
				<option value="1">微博</option>
				<option value="2">微信</option>
				<option value="3">手机端</option>
			</select></td>
	</tr>
	<tr>
	<td>用户类型：</td>
		<td><select id="type" name="type" onblur="checkValue(this,'用户类型')">
				<option value="">--请选择--</option>
				<option value="0">用户</option>
				<option value="1">操作员</option>
				<option value="2">拥有商户者</option>
				<option value="3">游客</option>
			</select></td>
	</tr>
	<tr>
		<td>免审批：</td>
		<td><select id="permissions" name="permissions" >
				<option value="0">是</option>
				<option value="1">否</option>
			</select></td>
	</tr>
			<tr>
		<td>推荐人：</td>
		<td>
		<input type="text"  id="uid" name="uid" /></td>
	</tr>
	</table>
	
<div>
	<input type="reset" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; " value="重置" />
	
	<input type="button" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; margin-right: 10px;" value="提交"  onclick="doSubmit()"/>
</div>
</div>
</div>
</form>
</body>
</html>