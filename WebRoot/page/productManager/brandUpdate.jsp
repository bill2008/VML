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
<title>修改品牌信息</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/gt-grid/gt_grid.css" />
<script type="text/javascript">
function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}

function doSubmit(){
	var id= $("#id").val();
	var createDate= $("#createDate").val();
	var name= $("#name").val();
	var letter= $("#letter").val();
	var keyword= $("#keyword").val();
	var isDel= $("#isDel").val();
	var data = {
			"wmlBrand.id" : id,
			"wmlBrand.createDate" : createDate,
			"wmlBrand.name" : name,
			"wmlBrand.letter" : letter,
			"wmlBrand.keyword" : keyword,
			"wmlBrand.isDel" : isDel
	}
	$.post("wmlBrand_updateWmlBrand.action",data,function(result){
		if(result == "fail"){
			alert("修改失败！");
		}
		else if(result == "optsuccess"){
			alert("修改成功！");
			window.opener.location.reload();
			window.close();
		}
	})
}
function init(){
	var json=window.opener;
	var jsonObj=JSON.parse(json.jsonVal);
	$("#id").val(jsonObj.id);
    $("#createDate").val(jsonObj.createDate);
    $("#name").val(jsonObj.name);
    $("#letter").val(jsonObj.letter);
    $("#keyword").val(jsonObj.keyword);
	$("#isDel option[value="+jsonObj.isDel+"]").attr("selected",'selected');

}
</script>
</head>
<body onload="init()">
<form id="frm">
<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>修改品牌信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr >
		<td>首字母：</td>
		<td>
		<input type="hidden" id="id" name="id"> 
		<input type="hidden" id="createDate" name="createDate">
		<input id="name" name="name" onblur="checkValue(this,'品牌名称')"></td>
	</tr>

	<tr>
		<td>关键字：</td>
		<td>
		
		<input type="text"   id="letter" name="letter" onblur="checkValue(this,'首字母')"></td>
	</tr>
	<tr>
		<td>品牌名称：</td>
		<td><input type="text" name="keyword" id="keyword" onblur="checkValue(this,'关键字')"></td>
	</tr>
	<tr>
		<td>是否删除：</td>
		<td><select id="isDel" name="isDel">
				<option value="0">是</option>
				<option value="1">否</option>
			</select></td>
	</tr>
	
	</table>
	
<div>
	<input type="reset" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; " value="重置" />
	
	<input type="button" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; margin-right: 10px;" value="提交"   onclick="doSubmit()"/>
</div>
</div>
</div>
</form>
</body>
</html>