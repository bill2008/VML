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
<title>品牌添加</title>
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
		
		$("#name").val($("#name",parent.document).attr("value"));
		$("#letter").val($("#letter",parent.document).attr("value"));
		$("#keyword").val($("#keyword",parent.document).attr("value"));
	});
	
	function checkValue(item,widgetName){
			if(item.value=="" ){
				alert(widgetName+"不能为空!");
			}
			
		}
	
		function doSubmit(){
			var name= $("#name").val();
			var letter= $("#letter").val();
			var keyword= $("#keyword").val();
			var data = {
					"wmlBrand.name" : name,
					"wmlBrand.letter" : letter,
					"wmlBrand.keyword" : keyword,
			};
			$.post("wmlBrand_addWmlBrand.action",data,function(result){
				if(result == "fail"){
					alert("添加失败！");
				}
				else if(result == "optsuccess"){
					alert("添加成功！");
				}
			});
		}
</script>
</head>
<body>
<form id="frm">
<div class="gt-panel" style="width: 500px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>品牌添加</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table >
	<tr >
		<td>品牌名称：</td>
		<td><input id="name" name="name" onblur="checkValue(this,'品牌名称')"></td>
	</tr>

	<tr>
		<td>首字母：</td>
		<td>
		
		<input type="text"   id="letter" name="letter" onblur="checkValue(this,'首字母')"></td>
	</tr>
	<tr>
		<td>关键字：</td>
		<td><input type="text" name="keyword" id="keyword" onblur="checkValue(this,'关键字')"></td>
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