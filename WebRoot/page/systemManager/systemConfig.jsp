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
			{id : 'name',header : "参数名称",width : 100}, 
			{id : 'value',header : "值",width : 150},
			{id : 'description',header : "描述",width : 200},
			{id : 'isDel',header : "是否删除",width : 200,renderer : GT.Grid.mappingRenderer(statusOpt, '')}
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
			pageSize : 15,
			pageSizeList : [ 15, 40, 80, 100 ],
			autoLoad : false,
			onCellDblClick : function(value, record , cell,row, colNO, rowNO,column,event){
				jsonVal=JSON.stringify(record);
				var sheight = screen.height * 0.5;
				var swidth = screen.width * 0.4;
				var iTop = (window.screen.availHeight-30-sheight)/2; 
				var iLeft = (window.screen.availWidth-10-swidth)/2; 
				var url = "<%=basePath%>page/systemManager/systemConfigUpdate.jsp";
				window.open(url,null,'height='+sheight+'px,width='+swidth+'px,top='+iTop+'px,left='+iLeft+'px,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
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


	function add() {
		var sheight = screen.height * 0.5;
		var swidth = screen.width * 0.4;
		var iTop = (window.screen.availHeight-30-sheight)/2; 
		var iLeft = (window.screen.availWidth-10-swidth)/2; 
		var url = "<%=basePath%>page/systemManager/systemConfigAdd.jsp";
		window.open(url, null,'height='+sheight+'px,width='+swidth+'px,top='+iTop+'px,left='+iLeft+'px,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
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
			<input type="button" class="gt-input-button" value="添加" onclick="add()" /> 
			
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
				
		</div>
	</div>
	<br/>
	
	<!-- grid的容器. -->
	<div id="grid1_container" style="width: 100%; height: 82%"></div>
	
</body>
</html>