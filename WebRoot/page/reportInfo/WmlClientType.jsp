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
<title>苹果/安卓用户数量</title>
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
					   {name : 'count',type : 'text'},
					   {name : 'type',type : 'text'}
					  ]};

		var colsConfig = [ 
			{id : 'type',header : "安装类型",width : 200},
			{id : 'count',header : "安装数量",width : 200}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlInstallationlog_queryClientType.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize  | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			autoLoad : false
		};

		mygrid = new GT.Grid(gridConfig);
		mygrid.render();
	}

	$(function() {
		initComplate();
		queryAuto();
	});
	function query(e) {
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			queryAuto();
		}
	}
	
	function queryAuto() {
		var clientType=$("#clientType").val();

		mygrid.query( {
			'clientType' :clientType
		});
	}
</script>
</head>
<body>
<div class="gt-panel" style="width: 100%;  height: 15%">
		<div class="gt-panel-head">
			<span>查 询</span>
		</div>
		<div>
			<table>
				<tr>
					<td>安装类型：</td>
					<td style="width: 110px;"><input style="width: 100px;" name="clientType" id="clientType"
						onkeydown="query(event)"></td>
						
					
				</tr>
			</table>
		</div>
		<div class="gt-button-area">
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
		</div>
	</div>
	<br/>

	<!-- grid的容器. -->
	<div id="grid1_container" style="width: 100%; height: 82%"></div>
	
</body>
</html>