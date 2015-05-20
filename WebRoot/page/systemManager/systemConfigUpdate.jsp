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
<title>修改系统配置信息</title>
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
	$("#value").val(jsonObj.value);
	$("#description").val(jsonObj.description);
	$("#id").val(jsonObj.id);
	$("#isDel option[value="+jsonObj.isDel+"]").attr("selected",'selected'); 
	}

function doSubmit(){
	var id= $("#id").val();
	var name= $("#name").val();
	var value= $("#value").val();
	var description= $("#description").val();
	var isDel= $("#isDel").val();
	var data = {
			"wmlConfig.id" : id,
			"wmlConfig.name" : name,
			"wmlConfig.value" : value,
			"wmlConfig.isDel" : isDel,
			"wmlConfig.description" : description
	};
	$.post("wmlConfig_updateWmlConfig.action",data,function(result){
		if(result == "fail"){
			alert("修改失败！");
		}
		else if(result == "optsuccess"){
			alert("修改成功！");
			window.opener.location.reload();
		}
	});
}
</script>
</head>
<body onload="init()">

<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>修改系统配置信息</span></center></div>
		<form id="frm">
	<div class="gt-panel-body" style="margin: 0px;">
	<table>
	<tr >
		<td>参数：</td>
		<td><input type="hidden" id="id" name="id"> <input type="text"  id="name" name="name" style="width: 350px" onblur="checkValue(this,'参数')"></td>
	</tr>
	<tr>
		<td>值：</td>
		<td><input type="text"  id="value" name="value" style="width: 350px" onblur="checkValue(this,'值')"></td>
	</tr>
	<tr>
		<td>描述：</td>
		<td>
		<input type="text"  id="description" name="description"  style="width: 350px" onblur="checkValue(this,'描述')"></td>
	</tr>
	
	<tr>
		<td>是否删除：</td>
		<td><select id="isDel" name="isDel" onblur=>
				<option value="1">否</option>
				<option value="0">是</option>
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