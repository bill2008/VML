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
<title>客户详细信息</title>
<LINK REL="SHORTCUT ICON" HREF="<%=basePath%>images/logo.png">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<jsp:include page="../../common/gtGridHead.jsp" />
<style type="text/css">
 .fontStyle{
 font-weight: bold;
 font-size:15px;
 }
</style>
<script type="text/javascript">
var mygrid = null;
var mygrid1 = null;
var mygrid2 = null;

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

function initUserProduct(){
	var dsConfig2 = {
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
					   {name : 'collect',type : 'int'},
					   {name : 'status',type : 'int'},
					   {name : 'productImgPath',type : 'text'},
					   {name : 'name',type : 'text'}
					  ]};

		var colsConfig2 = [ 
			{id : 'id',header : "编号",width : 50}, 
			{id : 'name',header : "商品名称",width : 100}, 
			{id : 'description',header : "描述",width : 167},
			{id : 'brandName',header : "商品品牌",width : 70},
			{id : 'productType',header : "商品类型",width : 70},
			{id : 'property',header : "属性",width : 50,renderer : GT.Grid.mappingRenderer(propertyOpt, '')},
			{id : 'brandName',header : "商户",width : 70},
			{id : 'uploadType',header : "上传类型",width : 70,renderer : GT.Grid.mappingRenderer(UploadTypeOpt, '')},
			{id : 'userName',header : "上传人",width : 60},
			{id : 'createDate',header : "上传时间",width : 70},
			{id : 'price',header : "价格",width : 50},
			{id : 'forwar',header : "转发",width : 50},
			{id : 'download',header : "下载",width : 50},
			{id : 'collect',header : "收藏",width : 50},
			{id : 'status',header : "状态",width : 50,renderer : GT.Grid.mappingRenderer(statusOpt, '')}
			];
		var gridConfig2 = {
				id : 'grid2',
				loadURL : "wmlProduct_queryPageProduct.action",
				dataset : dsConfig2,
				columns : colsConfig2,
				container : 'grid1_container2',
				toolbarPosition : 'bottom',
				toolbarContent : 'nav | goto | pagesize | save | state',
				pageSize : 14
			};
		mygrid2=new GT.Grid( gridConfig2 );
		mygrid2.render();
}
function initUserAttention(){
	var dsConfig = {
		fields : [ {name : 'id',type : 'text'}, 
				   {name : 'createDate',type : 'text'},
				   {name : 'byId',type : 'text'},
				   {name : 'byName',type : 'text'},
				   {name : 'forId',type : 'float'},
				   {name : 'forName',type : 'int'},
				   {name : 'isDel',type : 'text'}
				   ]
		};
	var colsConfig = [ 
	{headAlign : 'center',align : 'center',id : 'forId',header : "关注人ID",	width : 101},
	{headAlign : 'center',align:'center',  id : 'forName', header : "关注人" , width : 261},
	{headAlign : 'center',align : 'center',id : 'createDate',header : "关注时间",	width : 152}
	];
	var gridConfig = {
			id: 'grid1', 
		 	loadURL : "wmlAttention_queryWmlAttention.action",
	  	    dataset: dsConfig ,
	        columns: colsConfig ,  
	        container: 'grid1_container',   
	        toolbarPosition: 'bottom',  
	        remotePaging : false,
	        autoLoad : false,
	        pageSize : 8,
	        toolbarContent : ' nav | state ' 
		};
		mygrid=new GT.Grid( gridConfig );
		mygrid.render();
}
function initUserBeAttention(){
	var dsConfig1 = {
			fields : [ {name : 'id',type : 'text'}, 
					   {name : 'createDate',type : 'text'},
					   {name : 'byId',type : 'text'},
					   {name : 'byName',type : 'text'},
					   {name : 'forId',type : 'float'},
					   {name : 'forName',type : 'int'},
					   {name : 'isDel',type : 'text'}
					   ]
			};
		var colsConfig1 = [ 
		{headAlign : 'center',align : 'center',id : 'byId',header : "被关注人ID",	width : 101},
		{headAlign : 'center',align:'center',  id : 'byName', header : "被关注人" , width : 261},
		{headAlign : 'center',align : 'center',id : 'createDate',header : "关注时间",	width : 152}
		];
		var gridConfig1 = {
				id: 'grid', 
			 	loadURL : "wmlBeAttention_queryWmlAttentionList.action",
		  	    dataset: dsConfig1 ,
		        columns: colsConfig1 ,  
		        container: 'grid1_container1',   
		        toolbarPosition: 'bottom',  
		        remotePaging : false,
		        autoLoad : false,
		        pageSize : 8,
		        toolbarContent : ' nav | state ' 
			};
			mygrid1=new GT.Grid( gridConfig1 );
			mygrid1.render();
}




function init(userId) {
	initUserBeAttention();
	initUserAttention();
	initUserProduct();
	mygrid.queryParameters["wmlAttention.byId"]=userId;
	mygrid1.queryParameters["wmlAttention.forId"]=userId;
	mygrid2.queryParameters["wmlProduct.uid"]=userId;
	
	mygrid.reload();
	mygrid1.reload();
	mygrid2.reload();
}

</script>
</head>
<body  onload="init('${wmlUserDetail.id}')">

<table style="width: 1030px;">
   <tr>
    <td width="51">用户ID</td>
    <td  width="209"><input class="fontStyle" type="text" value="${wmlUserDetail.id}" readonly="readonly" /></td>
    <td  width="232" rowspan="2"><img  width="100px;" height="100px;"  src="<%=basePath%>userpic/${wmlUserDetail.head}" /></td>
    <td colspan="2" rowspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td  width="51">用户名</td>
    <td><input class="fontStyle" type="text" value="${wmlUserDetail.name}" readonly="readonly" /></td>
  </tr>
  <tr>
    <td colspan="3"><div id="grid1_container" style="width: 518px; height: 200px;"></div></td>
    <td width="524"><div id="grid1_container1" style="width: 518px; height: 200px" ></div></td>
    <td width="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="5"><div id="grid1_container2" style="width: 1042px; height: 358px;" ></div></td>
  </tr>
</table>

	
</body>
</html>