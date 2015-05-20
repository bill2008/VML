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
<title>添加员工信息</title>
<jsp:include page="../../common/gtGridHead.jsp" />
<%
	if(session.getAttribute("admin")==null){
		%>
		<script type="text/javascript">
			alert("用户没有登录,请重新登录后再操作!");
		window.parent.location.href="/VML";
		</script>
<%}%>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

$(document).ready( function() {
	
	$("#name").val($("#name",parent.document).attr("value"));
	$("#loginName").val($("#loginName",parent.document).attr("value"));
	$("#phone").val($("#phone",parent.document).attr("value"));
	
});

function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}

function doSubmit(){
	var name= $("#name").val();
	var loginName= $("#loginName").val();
	var phone= $("#phone").val();
	var updatePrice= $("#updatePrice").val();
	var data = {
			"wmladmin.name" : name,
			"wmladmin.loginName" : loginName,
			"wmladmin.phone" : phone,
			"wmladmin.updatePrice" : updatePrice
	};
	$.post("wmlAdmin_addWmlAdmin.action",data,function(result){
		if(result == "fail"){
			alert("添加失败！");
		}
		else if(result == "optsuccess"){
			alert("添加成功！");
			window.opener.location.reload();
			window.close();
		}
	});
}
</script>
</head>
<body>

<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>添加员工信息</span></center></div>
		<form id="frm">
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr >
		<td>员工姓名：</td>
		<td><input type="text"  id="name" name="name" onblur="checkValue(this,'员工姓名')"></td>
	</tr>
	<tr>
		<td>登录帐号：</td>
		<td><input type="text"  id="loginName" name="loginName" onblur="checkValue(this,'登录帐号')"></td>
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
				<option value="1">是</option>
				<option value="0">否</option>
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