<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<html>
<head>
<title>商品管理</title>
<meta content="http://schemas.microsoft.com/intellisense/ie5" name="vs_targetSchema">
<link href="<%=basePath%>js/uploadify/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>js/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/uploadify/scripts/swfobject.js"></script>
<script type="text/javascript" src="<%=basePath%>js/uploadify/scripts/jquery.uploadify.v2.1.0.min.js"></script>

<jsp:include page="../../common/gtGridHead.jsp" />
<%
	if(session.getAttribute("admin")==null){
		%>
		<script type="text/javascript">
			alert("用户没有登录,请重新登录后再操作!");
		window.parent.location.href="/VML_Manager";
		</script>
<%}%>
<script type="text/javascript">
$(document).ready(function() {
	$("#name").val($("#name",parent.document).attr("value"));

	$.post("wmlProductType_queryWmlProductType.action", function(resultData) {
		var jsonObj = resultData.data;
		var selectId = $("#productType",parent.document).attr("value");
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = null;
			if(jsonObj[i].id==selectId){
				 $option = $("<option selected='selected'></option>");
			}else{
				 $option = $("<option></option>");
			}
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#productType").append($option);
		}
	});
	$.post("wmlOrgan_queryWmlOrgan.action", function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i< jsonObj.length; i++) {
			var $option = $("<option></option>");
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#organ").append($option);
		}
	});
	$.post("wmlBrand_queryWmlBrand.action", function(resultData) {
		var jsonObj = resultData.data;
		var selectId = $("#brandName",parent.document).attr("value");
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = null;
			if(jsonObj[i].id==selectId){
				 $option = $("<option selected='selected'></option>");
			}else{
				 $option = $("<option></option>");
			}
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].keyword);
			$("#brandName").append($option);
		}
	});
	
	$("#uploadType").get(0).selectedIndex = $("#uploadType",parent.document).attr("value");
	
	$("#uploadify").uploadify({
		'uploader'       : '<%=basePath%>js/uploadify/scripts/uploadify.swf',
		'script'         : 'wmlProduct_productUploadAdd.action',
		'cancelImg'      : '<%=basePath%>js/uploadify/cancel.png',
		'buttonImg'		 : '<%=basePath%>js/uploadify/buttonImg.png',
		'queueID'        : 'fileQueue',
		'auto'           : false,
		'multi'          : true,
		'simUploadLimit' : 1,
		'sizeLimit': 18874368,
		'queueSizeLimit ': 9,
		'wmode'			 : 'transparent',
		'fileExt'		 : '*.png;*.gif;*.jpg;*.bmp;*.jpeg',
		'fileDesc'		 : '图片文件(*.png;*.gif;*.jpg;*.bmp;*.jpeg)',
		'onAllComplete'  :function(event,data) 
		{
			$('#result').html(data.filesUploaded +'个图片上传成功');
		}
	});
	$("#property").get(0).selectedIndex = parseInt($("#Property",parent.document).attr("value")) + 1;
	$("#status").get(0).selectedIndex = parseInt($("#status",parent.document).attr("value")) + 1;
	
	
  	$(window).onunload(function(){
	    doCancel();
  	});
});



function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}

function doSubmit(){
	var name= $("#name").val();
	var description= $("#description").val();
	var productType= $("#productType").val();
	var organ= $("#organ").val();
	var brandName=$("#brandName").val();
	var property=$("#property").val();
	var uploadType=$("#uploadType").val();
	var price=$("#price").val();
	var status=$("#status").val();
	var operator=$("#operator").val();
	var data = {
			"wmlProduct.name" : name,
			"wmlProduct.tid" : productType,
			"wmlProduct.oid" : organ,
			"wmlProduct.bid" : brandName,
			"wmlProduct.uploadType" : uploadType,
			"wmlProduct.property" : property,
			"wmlProduct.status" : status,
			"wmlProduct.price" : price,
			"wmlProduct.description" : description,
			"productType":$("#productType").find("option:selected").text(),
			"operator":operator
	};
	$.post("wmlProduct_addWmlProduct.action",data,function(result){
		if(result == "fail"){
			alert("添加失败！");
		}
		else if(result == "optsuccess"){
			alert("添加成功！");
			document.getElementById("frm").reset(); 
		}else if(result == "sessionFail"){
			alert("会话过期请重新登录!");
			window.close();
			
		}
	});
}

function doCancel(){
	var operator=$("#operator").val();
	
	var data = {
			"productType":$("#productType").find("option:selected").text(),
			"operator":operator
	};
	$.post("wmlProduct_cancelAddWmlProduct.action",data,function(result){
		window.opener.location.reload();
		/* window.close(); */
	});
}

function uploasFile(){   
    //校验  
   var productName=$("#name").val();
    var productType =$("#productType").find("option:selected").text(); 
    var operator=$("#operator").val();
    
  	if(productName==null){
  		alert("商品名称不能为空");
  		return false;
  	}else if(productType=="--请选择--"){
  		alert("商品类型不能为空");
  		return false;
  	}
    //设置 scriptData 的参数
    $('#uploadify').uploadifySettings('scriptData',{'operator':operator,'productName':productName,'productType':productType});  
    //上传  
    
    jQuery('#uploadify').uploadifyUpload();
}  
</script>

</head>
<body >
<form id="frm">
<div class="gt-panel" style="width: 600px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>添加商品信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table  >
	<tr >
		<td style="width: 50px;">商品名称：</td>
		<td style="width: 400px;">
		<input id="name" name="name" onblur="checkValue(this,'商品名称')">
		<input type="hidden" id= "operator" name="operator" value="<%=session.getAttribute("adminId")%>">
		</td>
	</tr>
		<tr >
		<td style="width: 50px;">商品价格：</td>
		<td style="width: 400px;"><input id="price" name="price" onblur="checkValue(this,'商品价格')"></td>
	</tr>
<tr>
		<td style="width: 50px;">商品类型：</td>
		<td  style="width: 400px;"><select id="productType" name="productType" onblur="checkValue(this,'商品类型')">
				<option value="">--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 50px;">所属商户：</td>
		<td  style="width: 400px;"><select id="organ" name="organ" >
				<option value="">--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 50px;">所属品牌：</td>
		<td  style="width: 400px;"><select id="brandName" name="brandName"  >
				<option value="">--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 50px;">属性：</td>
		<td  style="width: 400px;"><select id="property" name="property"  >
				<option value="">--请选择--</option>
				<option value="0">男</option>
				<option value="1">女</option>
				<option value="2">通用</option>
			</select></td>
	</tr>
		<tr>
		<td  style="width: 50px;">上传类型：</td>
		<td  style="width: 400px;"><select id="uploadType" name="uploadType" >
				<option value="">--请选择--</option>
				<option value="0">分后台</option>
				<option value="1">后台</option>
				<option value="2">手机</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 50px;">状态：</td>
		<td  style="width: 400px;"><select id="status" name="status"  >
				<option value="">--请选择--</option>
				<option value="0">上架</option>
				<option value="1">下架</option>
				<option value="2">未审核</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 50px;">描述：</td>
		<td style="width: 400px;">
		<textarea rows="2" cols="25"  id="description" name="description" onblur="checkValue(this,'描述')"></textarea>
	</tr>
	
	<tr>
		<td  style="width: 50px;">图片：</td>
		<td style="width: 400px;" >
			<input type="file" class="gt-input-button"  style=" float: left;"  name="uploadify" id="uploadify" />
			
		</td>
		<tr>
		<td  style="width: 50px;"></td>
		<td style="width: 400px;">
			<div id="fileQueue" style="width: 400px;height: 120px; border: 2px solid green;"></div>
		</td>
		</tr>
		<tr>
		<td  style="width: 50px;"></td>
		<td style="width: 400px;">
			<input type="button" class="gt-input-button"  onclick="uploasFile()" value="开始上传">
　			<input type="button" class="gt-input-button"  onclick="doCancel();jQuery('#uploadify').uploadifyClearQueue()" value="取消上传">
			<span id="result" style="font-size: 13px;color: red"></span>
		</td>
		</tr>
	
	</table>
	
<div>
	<input type="reset" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; " value="重置" onclick="doCancel()"/>
	<input type="button" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; margin-right: 10px;" value="提交"   onclick="doSubmit()"/>
</div>
</div>
</div>
</form>
	
		
</body>
</html>