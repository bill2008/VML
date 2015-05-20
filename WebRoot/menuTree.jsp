<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>菜单列表</title>
	
	<link rel="stylesheet" href="<%=basePath%>css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.ztree.core-3.1.js"></script>
	<jsp:include page="common/gtGridHead.jsp" />
	
	<%
	if(session.getAttribute("admin")==null){
		%>
		<script type="text/javascript">
			alert("用户没有登录,请重新登录后再操作!");
		window.parent.location.href="/VML";
		</script>
<%}%>
<script type="text/javascript">
	var zTreeObj;
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	$(document).ready(function() {
		$.ajax({
			url : "wmlMenu_AllMenu.action",
			type : "get",
			dataType : "json",
			success : initZtree
		});
	});
	function initZtree(strReult) {
		var zNodes = eval(strReult.menuTree);
		zTreeObj = $.fn.zTree.init($('#navigation'), setting, zNodes);
	}
</script>
</head>

<body>
	
	<div class="gt-panel"  style="padding: 0px; margin-top: 9px;width:190px; height:718px;">
	<div class="gt-panel-head"><center> <span>菜单栏</span></center></div>
	<div class="gt-panel-body" style="margin-top: 0px;">
		<ul style=" height:650px; width:80px; padding: 0px; margin-top: 0px;" id="navigation" class="ztree"></ul>
		</div>
	</div>


</body>
</html>
