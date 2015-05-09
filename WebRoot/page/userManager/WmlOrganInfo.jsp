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
<title>商户管理</title>
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
	var ProductTypeopt={};
	var userOpt={};
	var permiOpt={}
	permiOpt["0"] = "是";
	permiOpt["1"] = "否";
	function init(){
		  $.post("wmlUser_queryWmlUser.action", function(resultData) {
			  	 $.each(resultData.data, function(i, value) {
			  		userOpt[value.id] = value.name;
					});				
				});
	  $.post("wmlProductType_queryWmlProductType.action", function(resultData) {
	  	 $.each(resultData.data, function(i, value) {
	  		ProductTypeopt[value.id] = value.name;
			});
			mygrid=new GT.Grid( gridConfig );
			mygrid.render();
			mygrid.query();
		});
	}

	
		var grid_demo_id = "myGrid1";

		var dsConfig = {
			fields : [ 
			           {name : 'id',type : 'text'}, 
					   {name : 'createDate',type : 'text'}, 
					   {name : 'display',type : 'int'},
					   {name : 'name',type : 'text'},
					   {name : 'address',type : 'text'},
					   {name : 'phone',type : 'text'},
					   {name : 'typeId',type : 'text'},
					   {name : 'typeName',type : 'text'},
					   {name : 'permissions',type : 'int'},
					   {name: 'userId',type:'int'},
					   {name: 'isDel',type:'int'},
					   {name : 'code',type : 'text'}
					  ]};

		var colsConfig = [ 
			{id : 'code',header : "商户编码",width : 150},
			{id : 'name',header : "商户名称",width : 150},
			{headAlign : 'center',header : "拥有者",align:'center',id : 'userId', width : 100,renderer : GT.Grid.mappingRenderer(userOpt, '')},
			{headAlign : 'center',header : "主卖商品类型",align:'center',id : 'typeId', width : 100,renderer : GT.Grid.mappingRenderer(ProductTypeopt, '')},
			{id : 'phone',header : "联系电话",width : 150},
			{id : 'address',header : "商户地址",width : 200},
			{headAlign : 'center',header : "免审批",align:'center',id : 'permissions', width : 100,renderer : GT.Grid.mappingRenderer(permiOpt, '')},
			{headAlign : 'center',header : "是否删除",align:'center',id : 'isDel', width : 100,	renderer : GT.Grid.mappingRenderer(permiOpt, '')}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlOrgan_queryWmlOrgan.action",
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
				var url = "<%=basePath%>page/userManager/WmlOrganUpdate.jsp";
				window.open(url,null,'height='+sheight+'px,width='+swidth+'px,top='+iTop+'px,left='+iLeft+'px,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
		}
		};


	$(function() {
		init();
	});
	
	function query(e) {
		var theEvent = e || window.event;
		var code = theEvent.keyCode || theEvent.which || theEvent.charCode;
		if (code == 13) {
			queryAuto();
		}
	}
	
	function queryAuto() {
		var name = $("#organName").val();
		var phone=$("#phone").val();
		var code=$("#organCode").val();
		mygrid.query({'wmlOrgan.name':name,'wmlOrgan.phone':phone,'wmlOrgan.code':code});
	}

	function add() {
		var sheight = screen.height * 0.5;
		var swidth = screen.width * 0.4;
		var iTop = (window.screen.availHeight-30-sheight)/2; 
		var iLeft = (window.screen.availWidth-10-swidth)/2; 
		var url = "<%=basePath%>page/userManager/WmlOrganAdd.jsp";
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
					<td>商户编码：</td>
					<td><input type="text" name="organCode" id="organCode"
						onkeydown="query(event)"></td>
						<td>商户名称：</td>
					<td><input type="text" name="organName" id="organName"
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