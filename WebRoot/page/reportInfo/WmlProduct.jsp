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
	
	var productType={};
	var bandOpt={};
	var organOpt={};
	var userOpt={};
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
			  $.each(resultData.data, function(i, value) {
				  productType[value.id] = value.name;
				});	 
		});
		$.post("wmlOrgan_queryWmlOrgan.action", function(resultData) {
			  $.each(resultData.data, function(i, value) {
				  organOpt[value.id] = value.name;
				});	 
		});
		$.post("wmlUser_queryWmlUser.action", function(resultData) {
			  $.each(resultData.data, function(i, value) {
				  userOpt[value.id] = value.name;
				});	 
		});
		$.post("wmlBrand_queryWmlBrand.action", function(resultData) {
			var jsonObj = resultData.data;
			for ( var i = 0; i < jsonObj.length; i++) {
				var $option = $("<option></option>");
				$option.attr("value", jsonObj[i].id);
				$option.text(jsonObj[i].name);
				$("#brandName").append($option);
			}
			 $.each(resultData.data, function(i, value) {
				 bandOpt[value.id] = value.name;
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
					   {name : 'uid',type : 'int'},
					   {name : 'tid',type : 'int'},
					   {name : 'bid',type : 'int'},
					   {name : 'oid',type : 'int'},
					   {name : 'property',type : 'text'},
					   {name : 'uploadType',type : 'text'},
					   {name : 'description',type : 'text'},
					   {name : 'price',type : 'float'},
					   {name : 'forwar',type : 'int'},
					   {name : 'download',type : 'int'},
					   {name : 'collect',type : 'int'},
					   {name : 'status',type : 'int'},
					   {name : 'name',type : 'text'}
					  ]};

		var colsConfig = [ 
			{id : 'name',header : "商品名称",width : 80,editable : true}, 
			
			{id : 'bid',header : "商品品牌",width : 70,editable : true,renderer : GT.Grid.mappingRenderer(bandOpt, '')},
			{id : 'tid',header : "商品类型",width : 70,editable : true,renderer : GT.Grid.mappingRenderer(productType, '')},
			{id : 'property',header : "属性",width : 70,editable : true,renderer : GT.Grid.mappingRenderer(propertyOpt, '')},
			{id : 'oid',header : "商户",width : 70,editable : true,renderer : GT.Grid.mappingRenderer(organOpt, '')},
			{id : 'forwar',header : "转发数",width : 50},
			{id : 'download',header : "下载数",width : 50},
			{id : 'collect',header : "收藏数",width : 50}
			];

		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlProduct_queryWmlProduct.action",
			dataset : dsConfig,
			columns : colsConfig,
			remotePaging : false,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize | save | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			autoLoad : false,
			afterSave:function(respD,successs,grid){
		        var message=respD["message"];
		        alert(message);
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
		var name = $("#name").val();
		var productType = $("#productType").val();
		var brandName = $("#brandName").val();
		var uploadType = $("#uploadType").val();
		var Property = $("#Property").val();
		var status = $("#status").val();
		var startDate=$("#startDate").val();
		var endTime=$("#endDate").val();
		if(endTime!="" && endTime!=""){
			if(startDate<endTime){
		mygrid.query( {
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
			alert("开始时间不能大于结束时间!")
		}
		}else{
		mygrid.query( {
			'wmlProduct.name' :name,
			'wmlProduct.tid' :productType,
			'wmlProduct.bid' :brandName,
			'wmlProduct.uploadType' :uploadType,
			'wmlProduct.property' :Property,
			'wmlProduct.status' :status
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
				<td>商品名称：</td>
					<td ><input style="width: 100px;" type="text" name="name" id="name" onkeydown="query(event)"/></td>
					<td>分类：</td>
					<td > <select id="productType">
						<option value="-1">全选</option>
					</select></td>
					<td>品牌：</td>
					<td > <select id="brandName">
						<option value="-1">全选</option>
					</select></td>
					
						<td>商品属性：</td>
					<td > <select id="Property">
						<option value=" ">全选</option>
						<option value="0">男</option>
						<option value="1">女</option>
						<option value="2">通用</option>
					</select>	</td>
					</tr>
			</table>
		</div>
		<div class="gt-button-area">

			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
				
		</div>
	</div>
	<br/>
	<!-- grid的容器. -->
	<div id="grid1_container" style="width: 100%; height: 82%"></div>
</body>
</html>