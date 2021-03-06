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
<meta http-equiv="kiben" content="no-cache">
<title>员工管理</title>
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

a:hover {background-color:yellow;} 
img:hover {background-color:yellow;}

</style>
<script type="text/javascript">

	var mygrid = null;

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
			{id : 'name',header : "员工姓名",width : 100},
			{id : 'loginName',header : "登录帐号",width : 100},
			{id : 'phone',header : "电话",width : 200},
			{id : 'status',header : "状态",width : 50, headAlign:'center ', align : 'center ',renderer : statusClass },
			{id : 'updatePrice',header : "编辑价格",width : 60, headAlign:'center ',align : 'center ',renderer : updatePriceClass }
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
				var url =  "<%=basePath%>page/userManager/WmlAdminUpdate.jsp";
				addTab("修改员工信息", url, jsonVal);
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
		var url = "<%=basePath%>page/userManager/WmlAdminAdd.jsp";
		addTab("添加员工信息", url, null);
	}
	
	function addTab(title, url, data){
		if("修改员工信息"==title){
			if ($('#tt').tabs('exists', "修改员工信息")){
				$('#tt').tabs('close', "修改员工信息");
			}
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;">';
			content = content + '</iframe>';
			content = content + '<span id="data" style="display:none">' + data +'</span>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true
			});			
			
		}else if("添加员工信息"==title){
			if ($('#tt').tabs('exists', "添加员工信息")){
				$('#tt').tabs('close', "添加员工信息");
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
	
	function  statusClass (value ,record,columnObj,grid,colNo,rowNo){
		var url = "";
		if(value==1){
			url = "wmlAdmin_updateWmlAdminStatus.action?status=0&id="+record['id'];
			return "<a onclick=\"confirmWindow('"+url+"','你确定要修改员工状态吗？')\" /><font color=\"black\">启用</font></span>";
		}else if (value==0 || value == null){
			url = "wmlAdmin_updateWmlAdminStatus.action?status=1&id="+record['id'];
			return "<a onclick=\"confirmWindow('"+url+"','你确定要修改员工状态吗？')\"><font  color=\"red\">停用</font></span>";
		};
	}

	function  updatePriceClass (value ,record,columnObj,grid,colNo,rowNo){

		var url = "";
		var imgPath="<%=basePath%>js/jquery-easyui-1.3.5/themes/icons/ok.png";
		if(value==1){
			url = "wmlAdmin_updateWmlAdminUpdatePrice.action?updatePrice=0&id="+record['id'];
			return "<img onclick=\"confirmWindow('"+url+"','你确定要修改编辑价格权限吗？')\" src=\""+imgPath+"\"/>";
		}else if (value==0 || value == null){
			url = "wmlAdmin_updateWmlAdminUpdatePrice.action?updatePrice=1&id="+record['id'];
			return "<a onclick=\"confirmWindow('"+url+"','你确定要修改编辑价格权限吗？')\"><font size=\"4\" color=\"red\">--</font></span>";
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
</script>
</head>
<body>
<div id="tt" class="easyui-tabs"  style="height: 720px;">
	<div title="员工管理" class="gt-panel" >
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
		<br/>
		
		<!-- grid的容器. -->
		<div id="grid1_container" style="width: 515px; height: 490px"></div>			
		
	</div>

</div>		
</body>
</html>
