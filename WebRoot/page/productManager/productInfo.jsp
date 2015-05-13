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
<title>商品管理</title>
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

	//商品属性
	var propertyOpt={};
	propertyOpt["0"]="男";
	propertyOpt["1"]="女";
	propertyOpt["2"]="通用";
	//上传类型
	var UploadTypeOpt={};
	UploadTypeOpt["0"]="分后台";
	UploadTypeOpt["1"]="后台";
	UploadTypeOpt["2"]="手机";
	//状态操作
	var statusOpt={};
	statusOpt["0"]="上架";
	statusOpt["1"]="下架";
	statusOpt["2"]="未审核";
	function init(){
		//商品类型
		$.post("wmlProductType_queryWmlProductType.action", function(resultData) {
			var jsonObj = resultData.data;
			for ( var i = 0; i < jsonObj.length; i++) {
				var $option = $("<option></option>");
				$option.attr("value", jsonObj[i].id);
				$option.text(jsonObj[i].name);
				$("#productType").append($option);
			}	 
		});
		
		$.post("wmlBrand_queryWmlBrand.action", function(resultData) {
			var jsonObj = resultData.data;
			for ( var i = 0; i < jsonObj.length; i++) {
				var $option = $("<option></option>");
				$option.attr("value", jsonObj[i].id);
				$option.text(jsonObj[i].keyword);
				$("#brandName").append($option);
			}
		});
	}
	
	
		var grid_demo_id = "myGrid1";

		var dsConfig = {
			fields : [ 
			          {name : 'id',type : 'text'}, 
					   {name : 'createDate',type : 'text'}, 
					   {name : 'uid',type : 'int'},
					   {name : 'userName',type : 'text'},
					   {name : 'tid',type : 'int'},
					   {name : 'productType',type : 'text'},
					   {name : 'bid',type : 'int'},
					   {name : 'brandName',type : 'text'},
					   {name : 'oid',type : 'int'},
					   {name : 'organName',type : 'text'},
					   {name : 'uploadType',type : 'text'},
					   {name : 'description',type : 'text'},
					   {name : 'price',type : 'float'},
					   {name : 'forwar',type : 'int'},
					   {name : 'download',type : 'int'},
					   {name : 'viewCount',type : 'int'}, 
					   {name : 'collect',type : 'int'},
					   {name : 'status',type : 'int'},
					   {name : 'productImgPath',type : 'text'},
					   {name : 'name',type : 'text'},
					   {name : 'productImgWidth',type : 'int'},
					   {name : 'productImgHeight',type : 'int'},
					  ]};

		var colsConfig = [ 
			{id : 'id',header : "编号",width : 70}, 
			{id : 'name',header : "商品名称",width : 100}, 
			{id : 'description',header : "描述",width : 130,editable : true,editor : {type : 'text'}},
			{id : 'brandName',header : "商品品牌",width : 70},
			{id : 'productType',header : "商品类型",width : 70},
			{id : 'property',header : "属性",width : 70,renderer : GT.Grid.mappingRenderer(propertyOpt, '')},
			{id : 'brandName',header : "商户",width : 70},
			{id : 'uploadType',header : "上传类型",width : 70,renderer : GT.Grid.mappingRenderer(UploadTypeOpt, '')},
			{id : 'userName',header : "上传人",width : 70},
			{id : 'createDate',header : "上传时间",width : 70},
			{id : 'price',header : "价格",width : 50,editable : true,editor : {type : 'text'}},
			{id : 'viewCount',header : "浏览次数",width : 	50},
			{id : 'forwar',header : "转发",width : 50},
			{id : 'download',header : "下载",width : 50},
			{id : 'collect',header : "收藏",width : 50},
			{id : 'status',header : "状态",width : 70,renderer : GT.Grid.mappingRenderer(statusOpt, '')}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlProduct_queryPageProduct.action",
			saveURL:"wmlProduct_updateWmlProdutInfo.action",
			dataset : dsConfig,
			columns : colsConfig,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize | save | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			onClickCell:function(value, record , cell, row,  colNO, rowNO,columnObj,grid){
				var time=record["createDate"].substring(0,10);
				
				var timestr= new Array();
				str=time.split("-");
				
				for (var i = 0;i<str.length ;i++ ){
					timestr+=str[i];
				}
				
				var url="productUpload/"+record["productType"]+"/"+timestr+"/"+record["id"]+"/";
				var imagepath=record["productImgPath"];
				
				var path='<%=basePath%>'+url+imagepath;
				
				$("#imgDiv").attr("style", "position:absolute; left:200px; top:200px; width:100px; height:100px; display: block; ");
				$("#img").attr("style"," width:300px; height:200px;");
				$("#img").attr("src",path);
				
			},
			beforeEdit:function(){
				$("#imgDiv").attr("style", "position:absolute; left:200px; top:200px; width:100px; height:100px; display: none; ");
			},
		
			onCellDblClick : function(value, record , cell,row, colNO, rowNO,column,event){
					jsonVal=JSON.stringify(record);
					var url = "wmlProduct_queryProduct.action?wmlProduct.id="+record['id'];
					addTab("修改商品信息", url, jsonVal);
					
			},
	
			afterSave:function(respD,successs,grid){
		        var message=respD["message"];
		        alert(message);
				}
		};



	$(function() {
		init();
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
		var proId = $("#proId").val();
		var name = $("#name").val();
		var productType = $("#productType").val();
		var brandName = $("#brandName").val();
		var uploadType = $("#uploadType").val();
		var Property = $("#Property").val();
		var status = $("#status").val();
		var startDate=$("#startDate").val();
		var endTime=$("#endDate").val();
		if(startDate!="" && endTime!=""){
			if(startDate<endTime){
		mygrid.query( {
			'wmlProduct.id' :proId,
			'wmlProduct.name' :name,
			'wmlProduct.tid' :productType,
			'wmlProduct.bid' :brandName,
			'wmlProduct.uploadType' :uploadType,
			'wmlProduct.property' :Property,
			'wmlProduct.status' :status,
			'wmlUser.createDate' :startDate,
			'wmlUser.endDate' :endTime
		});
		}else{
			alert("开始时间不能大于结束时间!");
		}
		}else{
		mygrid.query( {
			'wmlProduct.id' :proId,
			'wmlProduct.name' :name,
			'wmlProduct.tid' :productType,
			'wmlProduct.bid' :brandName,
			'wmlProduct.uploadType' :uploadType,
			'wmlProduct.property' :Property,
			'wmlProduct.status' :status
		});
		}
	}
	function save() {
		mygrid.save();
	}

	function add() {
		var url = "<%=basePath%>page/productManager/productAdd.jsp";
/* 		var data = "{";
		data = data + "name:" +$("#name").val()+",";
		data = data + "productType:" +$("#productType").val()+",";
		data = data + "brandName:" +$("#brandName").val()+",";
		data = data + "uploadType:" +$("#uploadType").val()+",";
		data = data + "Property:" +$("#Property").val()+",";
		data = data + "status:" +$("#status").val()+","; */
		addTab("添加商品信息", url, null);
	}
	
	function imgOut(){
		$("#imgDiv").attr("style", "position:absolute; left:200px; top:200px; width:100px; height:100px; display: none; ");
	}
	
	function addTab(title, url, data){
		if("修改商品信息"==title){
			if ($('#tt').tabs('exists', "修改商品信息")){
				$('#tt').tabs('close', "修改商品信息");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});			
			
		}else if("添加商品信息"==title){
			if ($('#tt').tabs('exists', "添加商品信息")){
				$('#tt').tabs('close', "添加商品信息");
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
<div id="tt" class="easyui-tabs"  style="height: 725px;">
	<div title="商品管理" class="gt-panel" style="align: left; margin-left: 2px; ">
		<div>
			<table>
				<tr>
				<td>编号：</td>
					<td ><input style="width: 50px;" type="text" name="proId" id="proId" onkeydown="query(event)"/></td>
				<td>名称：</td>
					<td ><input style="width: 80px;" type="text" name="name" id="name" onkeydown="query(event)"/></td>
					<td>分类：</td>
					<td > <select id="productType" style="width: 60px;" >
						<option value=" ">全选</option>
					</select></td>
					<td>品牌：</td>
					<td > <select id="brandName" style="width: 60px;" >
						<option value=" ">全选</option>
					</select></td>
					<td>上传类型：</td>
					<td ><select id="uploadType" style="width: 60px;" >
						<option value=" ">全选</option>
						<option value="0">分后台</option>
						<option value="1">后台</option>
						<option value="2">手机</option>
					</select></td>
						<td>商品属性：</td>
					<td > <select id="Property" style="width: 60px;" >
						<option value=" ">全选</option>
						<option value="0">男</option>
						<option value="1">女</option>
						<option value="2">通用</option>
					</select>	</td>
						<td>状态：</td>
					<td > <select id="status" style="width: 60px;" >
						<option value=" ">全选</option>
						<option value="0">上架</option>
						<option value="1">下架</option>
						<option value="2">未审核</option>
					</select></td>
						<td>开始时间：</td>
					<td ><input style="width: 80px;" type="text" name="startDate" id="startDate"onclick="timeSelect('startDate',null)" onkeydown="query(event)"/></td>
						<td>结束时间：</td>
					<td><input style="width: 80px;" type="text" name="endDate" id="endDate" onclick="timeSelect('endDate',null)" onkeydown="query(event)" /></td>
				</tr>
			</table>
		</div>
		<div class="gt-button-area">
			<input type="button" class="gt-input-button" value="添加" onclick="add()" /> 
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 

		<br/>
		<br/>
		<!-- grid的容器. -->
		<div id="grid1_container" style="width: 1126px; height: 82%"></div>
		<div style="width:100px; height:100px; display: none; z-index: 2" id="imgDiv" onmousemove="imgOut()"> <img id="img"   src=""> </div>
				
		</div>
	</div>
</div>
</body>
</html>