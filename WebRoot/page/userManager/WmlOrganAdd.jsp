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
<title>添加商户信息</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/gt-grid/gt_grid.css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
$(document).ready( function() {

	
	$.post("wmlProductType_queryWmlProductType.action",null, function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = $("<option></option>");
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#typeId").append($option);
		}
	});
	
});


function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}

function doSubmit(){
	var code= $("#code").val();
	var name= $("#name").val();
	var userId= $("#userId").val();
	var typeId= $("#typeId").val();
	var phone= $("#phone").val();
	var address= $("#address").val();
	var permissions= $("#permissions").val();
	var data = {
			"wmlOrgan.code" : code,
			"wmlOrgan.name" : name,
			"wmlOrgan.userId" : userId,
			"wmlOrgan.typeId" : typeId,
			"wmlOrgan.phone" : phone,
			"wmlOrgan.address" : address,
			"wmlOrgan.permissions" : permissions,
	}
	$.post("wmlOrgan_addWmlOrgan.action",data,function(result){
		if(result == "fail"){
			alert("添加失败！");
		}
		else if(result == "optsuccess"){
			alert("添加成功！");
			window.opener.location.reload();
		}else if(result=="orgNull"){
			alert("所属商户不存在!");
		}
	})
}

</script>
</head>
<body>
<form id="frm" >
<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>添加商户信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr >
		<td>商户编码：</td>
		<td><input type="text"  id="code" name="code" onblur="checkValue(this,'商户编码')"></td>
	</tr>
	<tr>
		<td>商户名称：</td>
		<td><input type="text"  id="name" name="name" onblur="checkValue(this,'商户名称')"></td>
	</tr>
	<tr>
		<td>所属客户：</td>
		<td>
		<input type="text"  id="userId" name="userId" onblur="checkValue(this,'所属客户')">
		</td>
	</tr>
	<tr>
		<td>主买商品类型：</td>
		<td><select id="typeId" name="typeId" onblur="checkValue(this,'主买商品类型')">
				<option value="">--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td>联系电话：</td>
		<td>	<input type="text"  id="phone" name="phone" onblur="checkValue(this,'联系电话')"></td>
	</tr>
	<tr>
		<td>商户地址：</td>
		<td>	<input type="text"  id="address" name="address" onblur="checkValue(this,'商户地址')"></td>
	</tr>
	<tr>
		<td>免审批：</td>
		<td><select id="permissions" name="permissions" >
				<option value="0">是</option>
				<option value="1">否</option>
			</select></td>
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