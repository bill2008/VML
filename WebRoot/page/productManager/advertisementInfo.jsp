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
<title>广告管理</title>
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
	
	var delOpt={};
	delOpt["0"] = "是";
	delOpt["1"] = "否";

	
		var grid_demo_id = "myGrid1";

		var dsConfig = {
				fields : [ 
				           {name : 'id',type : 'text'}, 
						   {name : 'createDate',type : 'text'}, 
						   {name : 'name',type : 'text'},
						   {name : 'url',type : 'text'},
						   {name : 'productId',type : 'text'},
						   {name : 'productName',type : 'text'},
						   {name : 'isDel',type : 'text'},
						   {name : 'description',type : 'text'}
						  ]};

			var colsConfig = [ 
				{id : 'name',header : "广告名称",width : 100}, 
				{id : 'description',header : "文字描述",width : 200},
				{id : 'createDate',header : "创建时间",width : 150},
				{id : 'productName',header : "关联商品",width : 100}, 
				{id : 'isDel',header : "是否删除",width : 100,renderer : GT.Grid.mappingRenderer(delOpt, '')}
				];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlAdvertisement_queryWmlAdvertisement.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : null,
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			onClickCell:function(value, record , cell, row,  colNO, rowNO,columnObj,grid){
				$("#imgDiv").removeClass("banner").addClass("bannerOver"); 
				var imagepath=record["url"];
				var path='<%=basePath%>'+"advertisementUpload/"+imagepath;
				$("#imgDiv").attr("style", "position:absolute; left:200px; top:200px; width:100px; height:100px; display: block; ");
				$("#img").attr("style"," width:300px; height:200px;");
				$("#img").attr("src",path);
			},
			onCellDblClick : function(value, record , cell,row, colNO, rowNO,column,event){
				jsonVal=JSON.stringify(record);
				var url = "<%=basePath%>page/productManager/advertisementUpdate.jsp";
				addTab("修改广告信息", url, jsonVal);;
		}
		};



	$(function() {	
		 mygrid=new GT.Grid( gridConfig );
			mygrid.render();
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
		var description = $("#description").val();
		var startDate=$("#startDate").val();
		var endTime=$("#endDate").val();
		if(endTime!="" && endTime!=""){
			if(startDate<endTime){
			mygrid.query( {
				'wmlAdvertisement.name' :name,
				'wmlAdvertisement.description' :description,
				'wmlAdvertisement.createDate' :startDate,
				'wmlAdvertisement.endDate' :endTime
			});
			}else{
				alert("开始时间不能大于结束时间!");
			}
		}else{
			mygrid.query({
				'wmlAdvertisement.description' :description,
				'wmlAdvertisement.name' :name
			});
		}
	}

	function add() {
		var url = "<%=basePath%>page/productManager/advertisementAdd.jsp";
		addTab("添加广告信息", url, null);
		mygrid.reload();
	}
	function imgOut(){
		$("#imgDiv").attr("style", "position:absolute; left:200px; top:200px; width:100px; height:100px; display: none; ");
	}
	function addTab(title, url, data){
		if("修改广告信息"==title){
			if ($('#tt').tabs('exists', "修改广告信息")){
				$('#tt').tabs('close', "修改广告信息");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			content = content + '<span id="data" style="display:none">' + data +'</span>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});			
			
		}else if("添加广告信息"==title){
			if ($('#tt').tabs('exists', "添加广告信息")){
				$('#tt').tabs('close', "添加广告信息");
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
<div title="广告管理"  class="gt-panel" >
	<div>
		<table>
			<tr>
				<td>广告名称：</td>
				<td ><input  type="text" name="name" id="name"	onkeydown="query(event)"></td>
				<td>文字描述：</td>
				<td ><input  type="text" name="description" id="description"	onkeydown="query(event)"></td>
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
	<div id="grid1_container" style="width: 655px; height: 249px"></div>
	<div style="width:100px; height:100px; display: none; z-index: 2" id="imgDiv" onmousemove="imgOut()"> <img id="img"   src=""> </div>
	</div>
</div>
</body>
</html>