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
<title>客户安装数</title>
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
		
		var installtype={};
		installtype["0"]="安卓设备";
		installtype["1"]="苹果设备";
		
		var dsConfig = {
			fields : [ 
			           {name : 'id',type : 'text'}, 
					   {name : 'createDate',type : 'text'}, 
					   {name : 'count',type : 'text'},
					   {name : 'mac',type : 'text'},
					   {name : 'region',type : 'text'},
					   {name : 'type',type : 'text'}

					  ]};

		var colsConfig = [ 
			
			
			{id : 'mac',header : "mac地址",width : 150},
			{id : 'region',header : "地址",width : 200},
			{id : 'type',header : "安装类型",width : 200,renderer : GT.Grid.mappingRenderer(installtype, '')},
			{id : 'createDate',header : "安装时间",width : 150}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlInstallationlog_queryWmlInstallationlog.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize  | state',
			pageSize : 15,
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
		var mac = $("#mac").val();
		var region=$("#region").val();
		var type=$("#type").val();
		var startDate=$("#startDate").val();
		var endTime=$("#endDate").val();
		if(endTime!="" && endTime!=""){
			if(startDate<endTime){
		mygrid.query( {
			'wmlInstallationlog.mac' :mac,
			'wmlInstallationlog.region' :region,
			'wmlInstallationlog.type' :type,
			'wmlInstallationlog.createDate' :startDate,
			'wmlInstallationlog.endDate' :endTime
		});
		}else{
			alert("开始时间不能大于结束时间!")
		}
		}else{
		mygrid.query( {
			'wmlInstallationlog.mac' :mac,
			'wmlInstallationlog.region' :region,
			'wmlInstallationlog.type' :type
		});
		}
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
					<td>MAC地址：</td>
					<td style="width: 110px;"><input style="width: 100px;" name="mac" id="mac"
						onkeydown="query(event)"></td>
						<td>地址：</td>
					<td style="width: 110px;"><input style="width: 100px;"name="region" id="region"
						onkeydown="query(event)"></td>
						<td>安装类型：</td>
					<td style="width: 110px;"><select id="type">
						<option value=" ">--请选择--</option>
						<option value="0">--安卓设备--</option>
						<option value="1">--苹果设备--</option>
						</select>
						</td>
						<td>开始时间：</td>
					<td ><input type="text" name="startDate" id="startDate"onclick="timeSelect('startDate',null)" onkeydown="query(event)"/></td>
						<td>结束时间：</td>
					<td><input type="text" name="endDate" id="endDate" onclick="timeSelect('endDate',null)" onkeydown="query(event)" /></td>
				</tr>
			</table>
		</div>
		<div class="gt-button-area">
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
		</div>
	</div>
	<br/>

	<!-- grid的容器. -->
	<div id="grid1_container" style="width: 100%; height: 70%"></div>
	
</body>
</html>