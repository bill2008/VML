<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>生成商品静态操作</title>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
function imgeCopy(){
	$.ajax({
		url : "wmlProduct_bulidHtml.action",
		type : "post",
		success : function(data) {
				 	if(data.message=="success"){
				 		alert("添加成功");
				 	}else{
				 		alert("添加失败");
				 	}
			}
		});
}
</script>
</head>
<body>
<input  type="button" value="生成商品静态操作" onclick="imgeCopy()"/>
</body>
</html>