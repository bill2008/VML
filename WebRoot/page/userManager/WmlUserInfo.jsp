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

a:hover {background-color:yellow;} 
img:hover {background-color:yellow;}

</style>
<script type="text/javascript">

	var mygrid = null;
	var jsonVal="";

	
	var statusOpt={};
	statusOpt["0"] = "待审核";
	statusOpt["1"] = "正常";
	statusOpt["2"] = "禁用";
	
	var typeOpt={};
	typeOpt["0"] = "用户";
	typeOpt["1"] = "操作员";
	typeOpt["2"] = "拥有商户者";
	typeOpt["3"] = "游客";
	
	var channelOpt={};
	channelOpt["0"] = "QQ";
	channelOpt["1"] = "微博";
	channelOpt["2"] = "微信";
	channelOpt["3"] = "手机端";
	
	var permiOpt={};
	permiOpt["0"] = "是";
	permiOpt["1"] = "否";

	
		var grid_demo_id = "myGrid1";

		var dsConfig = {
			fields : [ 
			           {name : 'id',type : 'text'}, 
					   {name : 'createDate',type : 'text'}, 
					   {name : 'uid',type : 'int'},
					   {name : 'uidName',type : 'text'},
					   {name : 'lastDate',type : 'text'},
					   {name : 'signature',type : 'text'},
					   {name : 'loginName',type : 'text'},
					   {name : 'name',type : 'text'},
					   {name : 'password',type : 'text'},
					   {name : 'phone',type : 'float'},
					   {name : 'head',type : 'text'},
					   {name : 'type',type : 'text'},
					   {name : 'organ',type : 'int'},
					   {name : 'organName',type : 'text'},
					   {name : 'permissions',type : 'int'},
					   {name : 'channel',type : 'int'},
					   {name : 'uploadFlag',type : 'text'},
					   {name : 'status',type : 'int'},
					   {name : 'isDel',type : 'int'}
					  ]};

		var colsConfig = [
					{id : 'id',header : "编号",width : 50},
					{id : 'organName',header : "所属商户",width : 60},
					{id : 'loginName',header : "登录帐号",width : 100},
					{id : 'name',header : "用户名",width : 100},
					{id : 'phone',header : "手机",width : 90},
					{id : 'signature',header : "个性签名",width : 120},
					{id : 'status',header : "状态",width : 40, align : 'center' , renderer : GT.Grid.mappingRenderer(statusOpt, '')},
					{id : 'channel',header : "登录类型",width : 60, align : 'center' , renderer : GT.Grid.mappingRenderer(channelOpt, '')},
					{id : 'type',header : "用户类型",width : 70, align : 'center' , renderer : GT.Grid.mappingRenderer(typeOpt, '')},
					{id : 'permissions',header : "免审批",width : 50, align : 'center' , renderer : PermissionsstyleClass },
					{id : 'lastDate',header : "最近登录时间",width :100},
					{id : 'createDate',header : "创建时间",width :100},
					{id : 'uidName',header : "推荐人",width : 99},
					{id : 'detail' , header : "详细" , width : 35, hdAlign : 'center' ,align : 'center' ,renderer : Detail },
					{id : 'detail' , header : "删除" , width : 35, hdAlign : 'center' ,align : 'center' ,renderer : Delete }
					];
		
		var gridConfig = {
			id : grid_demo_id,
			loadURL : "wmlUser_queryWmlUserPage.action",
			dataset : dsConfig,
			columns : colsConfig,
			container : 'grid1_container',
			toolbarPosition : 'bottom',
			toolbarContent : 'nav | goto | pagesize | state',
			pageSize : 20,
			pageSizeList : [ 20, 40, 80, 100 ],
			onCellDblClick : function(value, record , cell,row, colNO, rowNO,column,event){
				jsonVal=JSON.stringify(record);
				var url = "<%=basePath%>page/userManager/WmlUserUpdate.jsp";
				addTab("修改客户", url, jsonVal);
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
		
		var userId = $("#userId").val();
		var loginName = $("#loginName").val();
		var channel = $("#channel").val();
		var status = $("#status").val();
		var uid = $("#uid").val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();
		
		if(startDate!="" && endDate!=""){
			if(startDate<endDate){
		mygrid.query( {
			'wmlUser.id' :userId,
			'wmlUser.loginName' :loginName,
			'wmlUser.channel' :channel,
			'wmlUser.status' :status,
			'wmlUser.uid' :uid,
			'wmlUser.createDate' :startDate,
			'wmlUser.endDate' :endDate
		});
		}else{
			alert("开始时间不能大于结束时间!");
		}
		}else{
		mygrid.query( {
			'wmlUser.id' :userId,
			'wmlUser.loginName' :loginName,
			'wmlUser.channel' :channel,
			'wmlUser.status' :status,
			'wmlUser.uid' :uid,
		});
		}
	}


	function addUser() {
		var url = "<%=basePath%>page/userManager/WmlUserAdd.jsp";
		addTab("添加客户", url, null);
		/* mygrid.reload(); */
	}
	
	function showSellDetail(userId){
		var url = "wmlUser_queryUserDetail.action?wmlUser.id="+userId;
		addTab("详细信息", url, null);
		
	}
	//渲染列表方法   value列表值  record列表行对象    columnObj 表格对象   
	function  PermissionsstyleClass (value ,record,columnObj,grid,colNo,rowNo){
		var permissions;
		var useId=record['id'];
		if (record['permissions']==0){
			permissions = 1;
		}else{
			permissions = 0;
		}
		var url = "wmlUser_updateWmlUserPermissions.action?permissions="+permissions+"&useId="+useId;
		if(value==1){
			<%-- var imgPath="<%=basePath%>js/jquery-easyui-1.3.5/themes/icons/no.png"; --%>
			return "<a onclick=\"confirmWindow('"+url+"','你确定要修改免审批吗？')\"><font size=\"4\" color=\"red\">--</font></span>";
		}else{
			var imgPath="<%=basePath%>js/jquery-easyui-1.3.5/themes/icons/ok.png";
			return "<img onclick=\"confirmWindow('"+url+"','你确定要修改免审批吗？')\" src=\""+imgPath+"\"/>";
		};
	}

	function confirmWindow(url,title){
		if(window.confirm(title)){
			$.post(url,function(result){
				if(result == "fail"){
					alert("操作失败！");
				}
				else if(result == "optsuccess"){
					alert("操作成功！");
					mygrid.reload();
				}
			});
	        return true;
	     }else{
	        return false;
         };
	}	
	
	function  Detail (value ,record,columnObj,grid,colNo,rowNo){

		var imgPath="<%=basePath%>js/jquery-easyui-1.3.5/themes/icons/search.png";
		return "<img onclick=\"showSellDetail('"+record['id']+"')\" src=\""+imgPath+"\"/>";
		
	}	
	
	function  Delete (value ,record,columnObj,grid,colNo,rowNo){
		
		var url = "wmlUser_deleteWmlUser.action?useId="+record['id']; 

		var imgPath="<%=basePath%>js/jquery-easyui-1.3.5/themes/icons/cancel.png";
		return "<img onclick=\"confirmWindow('"+url+"','你确定要删除客户吗？')\" src=\""+imgPath+"\"/>";
		
	}	
	
	function addTab(title, url, data){
		if("详细信息"==title){
			if ($('#tt').tabs('exists', "详细信息")){
				$('#tt').tabs('close', "详细信息");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});			
		}else if("修改客户"==title){
			if ($('#tt').tabs('exists', "修改客户")){
				$('#tt').tabs('close', "修改客户");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			content = content + '<span id="data" style="display:none">' + data +'</span>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});				
			
		}else if("添加客户"==title){
			if ($('#tt').tabs('exists', "添加客户")){
				$('#tt').tabs('close', "添加客户");
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
	<div title="客户管理" class="gt-panel"  >
		<div>
			<table>
				<tr>
				<td>编号：</td>
					<td style="width: 50px;"><input style="width: 50px;" type="text" name="userId" id="userId"
						onkeydown="query(event)"></td>
					<td>登录帐号：</td>
					<td style="width: 110px;"><input style="width: 100px;" type="text" name="loginName" id="loginName"
						onkeydown="query(event)"></td>
						<td>登录类型：</td>
					<td style="width: 80px;"> <select id="channel">
						<option value=" ">全选</option>
						<option value="0">QQ</option>
						<option value="1">微博</option>
						<option value="2">微信</option>
						<option value="3">手机端</option>
					</select>	</td>
						<td>状态：</td>
					<td style="width: 80px;"> <select id="status">
						<option value=" ">全选</option>
						<option value="0">待审核</option>
						<option value="1">正常</option>
						<option value="2">禁用</option>
					</select></td>
						<td>推荐人ID：</td>
					<td style="width: 110px;"><input style="width: 100px;" type="text" name="uid" id="uid"	onkeydown="query(event)"></td>
						<td>开始时间：</td>
					<td ><input style="width: 80px;" type="text" name="startDate" id="startDate"onclick="timeSelect('startDate',null)" /></td>
						<td>结束时间：</td>
					<td><input style="width: 80px;" type="text" name="endDate" id="endDate" onclick="timeSelect('endDate',null)" /></td>
				</tr>
			</table>
		</div>
		<div class="gt-button-area">
			<input type="button" class="gt-input-button" value="添加" onclick="addUser()" /> 
			<input type="button" class="gt-input-button" value="查询" onclick="queryAuto()" /> 
		</div>
		<br/>
		<!-- grid的容器. -->
		<div id="grid1_container" style="width: 1124px; height: 490px;"></div>		
	</div>
</div>
</body>
</html>