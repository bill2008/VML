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
<title>品牌管理</title>
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

	var jsonVal="";
	var isDelOpt={};
	isDelOpt["0"] = "是";
	isDelOpt["1"] = "否";
	function initComplate() {
		var grid_demo_id = "myGrid1";

		var dsConfig = {
			fields : [ 
			           {name : 'id',type : 'text'}, 
					   {name : 'createDate',type : 'text'}, 
					   {name : 'name',type : 'text'},
					   {name : 'letter',type : 'text'},
					   {name : 'isDel',type : 'text'},
					   {name : 'keyword',type : 'text'}
					  ]};

		var colsConfig = [ 
			{id : 'name',header : "品牌名称",width : 200},
			{id : 'letter',header : "首字母",width : 150},
			{id : 'keyword',header : "关键字",width : 150},
			{id : 'createDate',header : "创建时间",width : 150},
			{id : 'isDel',header : "是否删除",width : 200,renderer : GT.Grid.mappingRenderer(isDelOpt, '')}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlBrand_queryWmlBrand.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize  | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			onCellDblClick : function(value, record , cell,row, colNO, rowNO,column,event){
				jsonVal=JSON.stringify(record);
				var url = "<%=basePath%>page/productManager/brandUpdate.jsp";
				addTab("修改品牌信息", url, jsonVal);
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
		var letter = $("#letter").val();
		var keyword = $("#keyword").val();
		var startDate=$("#startDate").val();
		var endTime=$("#endDate").val();
		if(endTime!="" && endTime!=""){
			if(startDate<endTime){
		mygrid.query( {
			'wmlBrand.name' :name,
			'wmlBrand.letter' :letter,
			'wmlBrand.keyword' :keyword,
			'wmlBrand.createDate' :startDate,
			'wmlBrand.endDate' :endTime
		});
		}else{
			alert("开始时间不能大于结束时间!");
		}
		}else{
		mygrid.query( {
			'wmlBrand.name' :name,
			'wmlBrand.letter' :letter,
			'wmlBrand.keyword' :keyword
		});
		}
	}
	function add() {
		var url = "<%=basePath%>page/productManager/brandAdd.jsp";
		addTab("添加品牌信息", url, null);
		mygrid.reload();
	}
	
	function addTab(title, url, data){
		if("修改品牌信息"==title){
			if ($('#tt').tabs('exists', "修改品牌信息")){
				$('#tt').tabs('close', "修改品牌信息");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			content = content + '<span id="data" style="display:none">' + data +'</span>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});			
			
		}else if("添加品牌信息"==title){
			if ($('#tt').tabs('exists', "添加品牌信息")){
				$('#tt').tabs('close', "添加品牌信息");
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
	<div title="品牌管理"  class="gt-panel" >
		<div>
			<table>
				<tr>
					<td>品牌名称：</td>
					<td style="width: 120px;"><input style="width: 100px;" type="text" name="name" id="name"
						onkeydown="query(event)"></td>
						<td>首字母：</td>
					<td style="width: 120px;"><input style="width: 100px;" type="text" name="letter" id="letter"
						onkeydown="query(event)"></td>
						<td>关键字：</td>
					<td style="width: 120px;"><input style="width: 100px;" type="text" name="keyword" id="keyword"
						onkeydown="query(event)"></td>
						<td>开始时间：</td>
					<td ><input type="text" name="startDate" id="startDate"onclick="timeSelect('startDate',null)" onkeydown="query(event)"/></td>
						<td>结束时间：</td>
					<td><input type="text" name="endDate" id="endDate" onclick="timeSelect('endDate',null)" onkeydown="query(event)" /></td>
				</tr>
			</table>
		</div>
		<div class="gt-button-area">
			<input type="button" class="gt-input-button" value="添加" onclick="add()" /> 
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
		</div>		
		<br/>
		<!-- grid的容器. -->
		<div id="grid1_container" style="width: 855px; height: 490px"></div>
	</div>	
</div>	
</body>
</html>