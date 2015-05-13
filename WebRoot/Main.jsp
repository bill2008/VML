<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="Chomo" />

<style type="text/css"> 
* { margin:0; padding:0; list-style:none;}

.content {width:100%;height:100%; }
.top {  height:50px; width:100%;
background-image:url('images/header_bg.png');
background-position:0px -50px;
}
.side {  width:220px; height:750px; float:left}
.main {  width:1150px; height:850px; float:left}
.center{width:1400px;height:860px; margin:auto;padding:auto;}

</style>
<script type="text/javascript">
window.onbeforeunload = function()
{
	window.location.href="wmlAdmin_outLogin.action?loginName=${loginName}";
};

</script>
</head>
<body>
<div class="content">
<div class="top">
	<div style="position: absolute; top:2px; right: 5px  ">
	
		<a href="wmlAdmin_outLogin.action?loginName=${loginName}" style="font-size:12px;color:#FFFFFF">ÍË³ö</a>
	</div>
</div>
	<div class="center">
	<div class="side">
		<iframe height="100%" width="100%" frameborder="0" src="menuTree.jsp" name="leftFrame" id="leftFrame" title="leftFrame"></iframe>
	</div>
	<div class="main"><iframe height="100%" width="100%"  frameborder="0" src="page/reportInfo/WmlUserinstallInfo.jsp" name="rightFrame" id="rightFrame" title="rightFrame"></iframe></div>
	</div>
</div>
</body>
</html>
