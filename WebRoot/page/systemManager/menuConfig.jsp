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
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.3.5//themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
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
			{id : 'menuNo',header : "菜单顺序",width : 80},
			{id : 'menuId',header : "菜单ID",width : 100}, 
			{id : 'menuName',header : "菜单名称",width : 150},
			{id : 'menuLink',header : "链接地址",width : 250},
			{id : 'menuAid',header : "菜单父ID",width : 100}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlMenu_queryMenu.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize  | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			autoLoad : false,
			onCellDblClick : function(value, record , cell,row, colNO, rowNO,column,event){
				jsonVal=JSON.stringify(record);
				var url = "<%=basePath%>page/systemManager/menuConfigUpdate.jsp";
				addTab("修改菜单配置", url, jsonVal);
			}
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
		var url = "<%=basePath%>page/systemManager/menuConfigAdd.jsp";
		addTab("添加菜单项", url, null);
	}
	
	function addTab(title, url, data){
		if("修改菜单配置"==title){
			if ($('#tt').tabs('exists', "修改菜单配置")){
				$('#tt').tabs('close', "修改菜单配置");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			content = content + '<span id="data" style="display:none">' + data +'</span>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});
		}
		if("添加菜单项"==title){
			if ($('#tt').tabs('exists', "添加菜单项")){
				$('#tt').tabs('close', "添加菜单项");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});
		}		
	}
	
</script>
</head>
<body>
<div id="tt" class="easyui-tabs"  style="height: 720px;">
<div title="菜单配置"  class="gt-panel" >

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
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
		</div>		
		<br/>
		
		<!-- grid的容器. -->
		<div id="grid1_container" style="width: 685px; height: 490px"></div>
	</div>
</div>	
</body>
</html>