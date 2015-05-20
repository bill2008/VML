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
<title>系统信息</title>
<LINK REL="SHORTCUT ICON" HREF="<%=basePath%>images/logo.png">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.3.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>js/jquery-easyui-1.3.5//themes/icon.css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
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
	var jsonVal="";
	var statusOpt={};
	statusOpt["0"] = "是";
	statusOpt["1"] = "否";
	function initComplate() {
		var grid_demo_id = "myGrid1";

		var dsConfig = {
			fields : [ {name : 'id',type : 'text'}, 
					   {name : 'name',type : 'text'}, 
					   {name : 'value',type : 'text'},
					   {name : 'description',type : 'text'},
					   {name : 'isDel',type : 'text'},
					  ]};

		var colsConfig = [ 
			{id : 'name',header : "参数名称",width : 200}, 
			{id : 'value',header : "值",width : 200},
			{id : 'description',header : "描述",width : 300},
			{id : 'isDel',header : "是否删除",width : 100,renderer : GT.Grid.mappingRenderer(statusOpt, '')}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlConfig_queryWmlConfig.action",
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
				var url = "<%=basePath%>page/systemManager/systemConfigUpdate.jsp";
				addTab("修改系统参数", url, jsonVal);
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
		var value=$("#value").val();
		var desp=$("#desp").val();
		mygrid.query({'wmlConfig.name':name,'wmlConfig.value':value,'wmlConfig.description':desp});
	}

	
	function addTab(title, url, data){
		if("修改系统参数"==title){
			if ($('#tt').tabs('exists', "修改系统参数")){
				$('#tt').tabs('close', "修改系统参数");
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
	}	
</script>
</head>
<body>
<div id="tt" class="easyui-tabs"  style="height: 720px;">
	<div title="系统参数"  class="gt-panel" >
		<div>
			<table>
				<tr>
					<td>参数名：</td>
					<td><input type="text" name="name" id="name"
						onkeydown="query(event)"></td>
						<td>值：</td>
					<td><input type="text" name="value" id="value"
						onkeydown="query(event)"></td>
						<td>描述：</td>
					<td><input type="text" name="desp" id="desp"
						onkeydown="query(event)"></td>
				</tr>
			</table>
		</div>
		<div class="gt-button-area">
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
		</div>
		<br/>
		<!-- grid的容器. -->
		<div id="grid1_container" style="width: 804px; height: 490px"></div>
	</div>
</div>	
</body>
</html>