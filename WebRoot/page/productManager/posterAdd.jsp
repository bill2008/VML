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
<title>海报管理</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/gt-grid/gt_grid.css" />
<script type="text/javascript">
$(document).ready( function() {
	$.post("wmlProduct_queryWmlProduct.action", function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = $("<option></option>");
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#porductName").append($option);
		}
	});
});
</script>
</head>
<body>
<form action="wmlPoster_addWmlPoster.action" method="post" enctype="multipart/form-data">
<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>添加海报信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr >
		<td>海报名称：</td>
		<td><input id="wmlPoster.name" name="wmlPoster.name"></td>
	</tr>
	<tr>
		<td>关联商品：</td>
		<td><select id="porductName" name="porductName">
				<option value="">--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td>文字描述：</td>
		<td>
		<textarea rows="3" cols="25"  id="wmlPoster.description" name="wmlPoster.description"></textarea>
		</td>
	</tr>
	<tr>
		<td>图片：</td>
		<td><input type="file" name="myFile" id="myFile"></td>
	</tr>
	
	</table>
	
<div>
	<input type="reset" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; " value="重置" />
	
	<input type="submit" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; margin-right: 10px;" value="提交"  />
</div>
</div>
</div>
</form>
</body>
</html>