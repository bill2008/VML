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
<title>修改管理</title>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/gt-grid/gt_grid.css" />
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
	var json=$("#data",parent.document).html();
	var jsonObj=JSON.parse(json);
	var pkgid=jsonObj.pkgId;
	$.post("wmlProduct_queryWmlProduct.action", function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = null;
			if(jsonObj[i].id==pkgid){
				 $option = $("<option selected='selected'></option>");

			}else{
				 $option = $("<option></option>");
			}
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#porductName").append($option);
		}
	});
	
	
	$("#id").val(jsonObj.id);
    $("#name").val(jsonObj.name);
    $("#createDate").val(jsonObj.createDate);
    $("#imagePath").val(jsonObj.url);
    $("#description").val(jsonObj.description);
	$("#isDel option[value="+jsonObj.isDel+"]").attr("selected",'selected');
	var path='<%=basePath%>'+"posterUpload/"+jsonObj.url;
	$("img").attr("src",path);
});
</script>
</head>
<body>
<form action="wmlPoster_updateWmlPoster.action" method="post" enctype="multipart/form-data">
<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>修改海报信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr >
		<td>海报名称：</td>
		<td><input type="hidden" id="id" name="id"> 
				<input type="hidden" id="createDate" name="createDate">
				<input type="hidden" id="imagePath" name="imagePath">
		<input id="name" name="name"></td>
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
		<textarea rows="3" cols="25"  id="description" name="description"></textarea>
		</td>
	</tr>
	<tr>
		<td>图片：</td>
		<td><input type="file" name="myFile" id="myFile"></td>
	</tr>
	<tr>
		<td>是否删除：</td>
		<td><select id="isDel" name="isDel">
				<option value="0">是</option>
				<option value="1">否</option>
			</select></td>
	</tr>
	<tr>
	<td>已上传图片：</td>
		<td>
		  <img  width="100px;" height="100px;" >
		  </td>
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