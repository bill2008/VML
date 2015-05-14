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
<script type="text/javascript">
function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}
function init(){
	
	var json=$("#data",parent.document).html();
	var jsonObj=JSON.parse(json);
	
	$("#menuNo").val(jsonObj.menuNo);
	$("#id").val(jsonObj.menuId);
    $("#name").val(jsonObj.menuName);
	$("#link").val(jsonObj.menuLink);
	$("#aid").val(jsonObj.menuAid);
	}

function doSubmit(){
	var id= $("#id").val();
	var name= $("#name").val();
	var link= $("#link").val();
	var aid= $("#aid").val();
	var menuNo= $("#menuNo").val();
	var data = {
			"menu.menuId" : id,
			"menu.menuName" : name,
			"menu.menuLink" : link,
			"menu.menuAid" : aid,
			"menu.menuNo" : menuNo,
	};
	$.post("wmlMenu_updateMenu.action",data,function(result){
		if(result == "fail"){
			alert("修改失败！");
		}
		else if(result == "optsuccess"){
			alert("修改成功！");
		}
	});
}
</script>
</head>
<body onload="init()">

<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>修改菜单信息</span></center></div>
	<form id="frm">
	<div class="gt-panel-body" style="margin: 0px;">
	<table>
	<tr>
		<td>菜单顺序：</td>
		<td><input type="text"  id="menuNo" name="menuNo" style="width: 350px" onblur="checkValue(this,'值')"></td>
	</tr>	
	<tr >
		<td>菜单名：</td>
		<td><input type="hidden" id="id" name="id"> <input type="text"  id="name" name="name" style="width: 350px" onblur="checkValue(this,'参数')"></td>
	</tr>
	<tr>
		<td>链接地址：</td>
		<td><input type="text"  id="link" name="link" style="width: 350px" onblur="checkValue(this,'值')"></td>
	</tr>
	<tr>
		<td>父菜单：</td>
		<td><input type="text"  id="aid" name="aid" style="width: 350px" onblur="checkValue(this,'值')"></td>
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