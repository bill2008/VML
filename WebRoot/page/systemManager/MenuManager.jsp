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
<title>菜单信息</title>
<LINK REL="SHORTCUT ICON" HREF="<%=basePath%>images/logo.png">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<jsp:include page="../../common/gtGridHead.jsp" />
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
			fields : [ {name : 'menuId',type : 'text'}, 
					   {name : 'menuName',type : 'text'}, 
					   {name : 'menuLink',type : 'text'},
					   {name : 'menuAid',type : 'text'},
					   {name : 'menuNo',type : 'text'},
					  ]};

		var colsConfig = [ 
			{id : 'menuNo',header : "菜单顺序",width : 80,editable : true,editor : {type : 'text'}},
			{id : 'menuId',header : "菜单ID",width : 100,editable : true,editor : {type : 'text'}}, 
			{id : 'menuName',header : "菜单名称",width : 150,editable : true,editor : {type : 'text'}},
			{id : 'menuLink',header : "链接地址",width : 250,editable : true,editor : {type : 'text'}},
			{id : 'menuAid',header : "菜单父ID",width : 100,editable : true,editor : {type : 'text'}}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlMenu_queryMenu.action",
			saveURL:"wmlMenu_updateMenu.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize  | state',
			pageSize : 15,
			pageSizeList : [ 15, 40, 80, 100 ],
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
		var name = $("#name").val();
		var link=$("#link").val();
		mygrid.query({'menu.menuName':name,'menu.menuLink':link});
	}
	function save() {
		mygrid.save();
	}

	function add() {
		mygrid.add();
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
					<td>菜单名：</td>
					<td><input type="text" name="name" id="name"
						onkeydown="query(event)"></td>
						<td>链接地址：</td>
					<td><input type="text" name="link" id="link"
						onkeydown="query(event)"></td>
						
				</tr>
			</table>
		</div>
		<div class="gt-button-area">
			<input type="button" class="gt-input-button" value="添加" onclick="add()" /> 
			<input type="button" class="gt-input-button" value="保存" onclick="save()" /> 
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
				
		</div>
	</div>
	<br/>
	
	<!-- grid的容器. -->
	<div id="grid1_container" style="width: 100%; height: 82%"></div>
	
</body>
</html>