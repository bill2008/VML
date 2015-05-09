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
<title>修改客户信息</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/HTML5Upload.js"></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/gt-grid/gt_grid.css"/>


<script type="text/javascript">
 function init() {
	var json=window.opener;
	var jsonObj=JSON.parse(json.jsonVal);
	var organid=jsonObj.organ;
	$.post("wmlOrgan_queryWmlOrgan.action",null, function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = null;
			if(jsonObj[i].id==organid){
				 $option = $("<option selected='selected'></option>");

			}else{
				 $option = $("<option></option>");
			}
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].code);
			$("#organ").append($option);
		}
	});
	console.info(jsonObj);
	$("#uid").val(jsonObj.uid);
	$("#useId").val(jsonObj.id);
	$("#loginName").val(jsonObj.loginName);
	$("#name").val(jsonObj.name);
	$("#createDate").val(jsonObj.createDate);
	$("#signature").val(jsonObj.signature);
	$("#phone").val(jsonObj.phone);
	$("#uploadFlag").val(jsonObj.uploadFlag);
	$("#lastDate").val(jsonObj.lastDate);
	$("#status option[value="+jsonObj.status+"]").attr("selected",'selected');
	$("#channel option[value="+jsonObj.channel+"]").attr("selected",'selected');
	$("#permissions option[value="+jsonObj.permissions+"]").attr("selected",'selected');
	$("#imgPreview").attr("src",'<%=basePath%>userpic/'+jsonObj.head);
	$("#isDel option[value="+jsonObj.isDel+"]").attr("selected",'selected');
	$("#type option[value="+jsonObj.type+"]").attr("selected",'selected');
	
}


function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}

function uploadHead() {
	$("#myFile").click();
}


</script>

</head>
<body onload="init()">

<div class="gt-panel" style="width: 800px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>修改客户信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<form action="wmlUser_updateWmlUser.action"  method="post"  enctype="multipart/form-data">	
		<table >
		<tr>
			<td>所属商户：</td>
			<td>
			<input type="hidden" id="useId" name="useId">
			<input type="hidden" id="createDate" name="createDate">
			<input type="hidden" id="lastDate" name="lastDate">
			<input type="hidden" id="uploadFlag" name="uploadFlag"> 
			<select id="organ" name="organ" >
				<option value="">--请选择--</option>
			</select></td>
			<td>头像：</td>
			<td rowspan=3><input type="file" id="myFile" name="myFile" style="display:none"/>
			<img id="imgPreview" style="width:200px;height:200px;border:1px solid #000000;" onclick="uploadHead()" /></td>
		</tr>
			<tr >
			<td>登录帐号：</td>
			<td><input type="text"  id="loginName" name="loginName" onblur="checkValue(this,'登录帐号')"></td>
		</tr>
			<tr >
			<td>手机号码：</td>
			<td><input type="text"  id="phone" name="phone" ></td>
		</tr>
		<tr>
			<td>用户名：</td>
			<td><input type="text"  id="name" name="name" onblur="checkValue(this,'用户名')"></td>
		</tr>
	
		<tr>
			<td>个性签名：</td>
			<td><input type="text"  name="signature" id="signature"></td>
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
			<input type="text"  id="uid" name="uid" />
			</td>
		</tr>
		<tr>
			<td>是否删除：</td>
			<td><select id="isDel" name="isDel" >
					<option value="0">是</option>
					<option value="1">否</option>
				</select></td>
		</tr>
		</table>
		
		<div>
			<input type="reset" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; " value="重置" />
			
			<input type="submit" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; margin-right: 10px;" value="提交"  />
		</div>
	</form>
	</div>		
</div>




</body>
</html>