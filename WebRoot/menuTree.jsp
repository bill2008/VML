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
	
	<div class="gt-panel"  style="padding: 0px; margin-top: 9px;width:210px; height:725px;">
	<div class="gt-panel-head"><center> <span>菜单栏</span></center></div>
	<div class="gt-panel-body" style="margin-top: 0px;">
		<ul style=" height:500px; width:160px; padding: 0px; margin-top: 0px;" id="navigation" class="ztree"></ul>
		</div>
	</div>


</body>
</html>
