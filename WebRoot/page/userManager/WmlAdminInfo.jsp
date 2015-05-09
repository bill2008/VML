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
<title>客户管理</title>
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

	var statusOpt={};
	statusOpt["0"] = "停用";
	statusOpt["1"] = "启用";
	var jsonVal="";
	
	var updatePriceOpt={};
	updatePriceOpt["0"] = "是";
	updatePriceOpt["1"] = "否";
	function initComplate() {
		var grid_demo_id = "myGrid1";

		var dsConfig = {
			fields : [ 
			           {name : 'id',type : 'text'}, 
					   {name : 'loginName',type : 'text'},
					   {name : 'name',type : 'text'},
					   {name : 'phone',type : 'text'},
					   {name : 'status',type : 'int'},
					   {name:'updatePrice',type:'int'}
					  ]};

		var colsConfig = [ 
			{id : 'name',header : "员工姓名",width : 200},
			{id : 'loginName',header : "登录帐号",width : 150},
			{id : 'phone',header : "电话",width : 200},
			{id : 'status',header : "状态",width : 200,renderer : GT.Grid.mappingRenderer(statusOpt, '')},
			{id : 'updatePrice',header : "编辑价格",width : 200,renderer : GT.Grid.mappingRenderer(updatePriceOpt, '')}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlAdmin_queryWmlAdmin.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			autoLoad : false,
			onCellDblClick : function(value, record , cell,row, colNO, rowNO,column,event){
				
				jsonVal=JSON.stringify(record);
				
				var sheight = screen.height * 0.5;
				var swidth = screen.width * 0.4;
				var iTop = (window.screen.availHeight-30-sheight)/2; 
				var iLeft = (window.screen.availWidth-10-swidth)/2; 
				var url =  "<%=basePath%>page/userManager/WmlAdminUpdate.jsp";
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
		var loginName=$("#LoginName").val();
		var phone=$("#phone").val();
		mygrid.query({'wmladmin.name':name,'wmladmin.loginName':loginName,'wmladmin.phone':phone});
	}


	function add() {
		var sheight = screen.height * 0.5;
		var swidth = screen.width * 0.4;
		var iTop = (window.screen.availHeight-30-sheight)/2; 
		var iLeft = (window.screen.availWidth-10-swidth)/2; 
		var url = "<%=basePath%>page/userManager/WmlAdminAdd.jsp";
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
					<td>员工姓名：</td>
					<td><input type="text" name="name" id="name"
						onkeydown="query(event)"></td>
						<td>登录帐号：</td>
					<td><input type="text" name="LoginName" id="LoginName"
						onkeydown="query(event)"></td>
						<td>联系电话：</td>
					<td><input type="text" name="phone" id="phone"
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