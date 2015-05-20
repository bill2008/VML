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
<title>短信群发</title>
<LINK REL="SHORTCUT ICON" HREF="<%=basePath%>images/logo.png">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<jsp:include page="../../common/gtGridHead.jsp" />
<%
	if(session.getAttribute("admin")==null){
		%>
		<script type="text/javascript">
			alert("用户没有登录,请重新登录后再操作!");
		window.parent.location.href="/VML";
		</script>
<%}%>
<style type="text/css">
.gt-row-selected td {
	background-color: #004da8;
	color: #ffffff;
}
</style>
<script type="text/javascript">

	var mygrid = null;

	function initComplate() {
		var grid_demo_id = "myGrid1";

		var dsConfig = {
			fields : [ 
			           {name : 'id',type : 'text'}, 
					   {name : 'createDate',type : 'text'}, 
					   {name : 'count',type : 'text'},
					   {name : 'mac',type : 'text'},
					   {name : 'region',type : 'text'},
					   {name : 'type',type : 'text'},

					  ]};

		var colsConfig = [ 
			{id : 'id',header : "ID",width : 100,editable : true,editor : {type : 'text'}}, 
			{id : 'createDate',header : "安装时间",width : 150,editable : true,editor : {type : 'text'}},
			{id : 'mac',header : "mac地址",width : 150,editable : true,editor : {type : 'text'}},
			{id : 'region',header : "地址",width : 200,editable : true,editor : {type : 'text'}},
			{id : 'type',header : "安装类型",width : 200,editable : true,editor : {type : 'text'}}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlInstallationlog_queryWmlInstallationlog.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize | save | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			autoLoad : false
		};

		mygrid = new GT.Grid(gridConfig);
		mygrid.render();
	}

	$(function() {
		initComplate();
		mygrid.reload();
	});

</script>
</head>
<body>

	<!-- grid的容器. -->
	<div id="grid1_container" style="width: 100%; height: 82%"></div>
</body>
</html>