<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

function init(productType,organ,brand,property,uploadType,status,isDel) {
	
	$.post("wmlProductType_queryWmlProductType.action", function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = null;
			if(jsonObj[i].id==productType){
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
			var $option = null;
			if(jsonObj[i].id==organ){
				 $option=$("<option selected='selected'></option>");
			}else{
				$option = $("<option></option>");
			}
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].name);
			$("#organ").append($option);
		}
	});
	$.post("wmlBrand_queryWmlBrand.action", function(resultData) {
		var jsonObj = resultData.data;
		for ( var i = 0; i < jsonObj.length; i++) {
			var $option = null;
			if(jsonObj[i].id==brand){
				 $option=$("<option selected='selected'></option>");
			}else{
				$option = $("<option></option>");
			}
			$option.attr("value", jsonObj[i].id);
			$option.text(jsonObj[i].keyword);
			$("#brandName").append($option);
		}
	});
	
	$("#uploadify").uploadify({
		'uploader'       : '<%=basePath%>js/uploadify/scripts/uploadify.swf',
		'script'         : 'wmlProduct_UploadProductUpdate.action',
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
	
	$("#property option[value="+property+"]").attr("selected",'selected'); 
	$("#uploadType option[value="+uploadType+"]").attr("selected",'selected'); 
	$("#status option[value="+status+"]").attr("selected",'selected'); 
	$("#isDel option[value="+isDel+"]").attr("selected",'selected'); 
	
  	$(window).onunload(function(){
	    doCancel();
  	});	
}	
	
  
function selectProductType(productType){

	  var type = document.getElementById("productType");
	  for(var i = 0;i<=type.options.length;i++){
		if(type.options[i].value == productType){
		type.options[i].selected = 'selected';
		}
	}
}

function checkValue(item,widgetName){
	if(item.value=="" ){
		alert(widgetName+"不能为空!");
	}
	
}

function doSubmit(){
	var productId=$("#id").val();
	var productName=$("#name").val();
    var time=$("#createDate").val();
    var strtime=time.substring(0,10);
	var str= new Array();
	str=strtime.split("-");
	var timestr="";
	for (var i=0;i<str.length ;i++ ){
		timestr+=str[i];
	}
	var operator=$("#operator").val();
	
	var name= $("#name").val();
	var description= $("#description").val();
	var productType= $("#productType").val();
	var organ= $("#organ").val();
	var brandName=$("#brandName").val();
	var property=$("#property").val();
	var uploadType=$("#uploadType").val();
	var price=$("#price").val();
	var status=$("#status").val();
	var id =$("#id").val();
	var isDel =$("#isDel").val();
	var uid =$("#uid").val();
	var createDate =$("#createDate").val();
	var forwar =$("#forwar").val();
	var download =$("#download").val();
	var collect =$("#collect").val();
	var data = {
			"wmlProduct.id" : id,
			"wmlProduct.isDel" : isDel,
			"wmlProduct.createDate" : createDate,
			"wmlProduct.name" : name,
			"wmlProduct.uid" : uid,
			"wmlProduct.tid" : productType,
			"wmlProduct.oid" : organ,
			"wmlProduct.bid" : brandName,
			"wmlProduct.uploadType" : uploadType,
			"wmlProduct.property" : property,
			"wmlProduct.status" : status,
			"wmlProduct.price" : price,
			"wmlProduct.forwar" : forwar,
			"wmlProduct.download" : download,
			"wmlProduct.collect" : collect,
			"wmlProduct.description" : description,
			"productId":productId,
			"productName":productName,
			"productType":$("#productType").find("option:selected").text(),
			"timestr":timestr,
			"operator":operator
	};
	
	$.post("wmlProduct_updateWmlProduct.action",data,function(result){
		window.opener.location.reload();
		window.close();
	});
}
function doCancel(){
	var productId=$("#id").val();
	var productName=$("#name").val();
    var time=$("#createDate").val();
    var strtime=time.substring(0,10);
	var str= new Array();
	str=strtime.split("-");
	var timestr="";
	for (var i=0;i<str.length ;i++ ){
		timestr+=str[i];
	}
	var operator=$("#operator").val();
	var data = {
			"productId":productId,
			"productName":productName,
			"productType":$("#productType").find("option:selected").text(),
			"timestr":timestr,
			"operator":operator
	};
	$.post("wmlProduct_cancelUpdateWmlProduct.action",data,function(result){
		window.opener.location.reload();
		/* window.close(); */
	});
}
//设置缩略图
function doFirst(imgId,imgUrl,imgProductId,imgIsDel){
	var data = {
			"wmlProductImage.id" : imgId,
			"wmlProductImage.url" : imgUrl,
			"wmlProductImage.productId" : imgProductId,
			"wmlProductImage.isFirst" : "1",
			"wmlProductImage.isDel" : imgIsDel
	};
	$.post("wmlProductImage_updateWmlProductImage.action",data,function(result){
		if(result == "fail"){
			alert("设置失败！");
		}
		else if(result == "optsuccess"){
			alert("设置成功！");
		}
	});
}


function uploasFile(){   
	var productId=$("#id").val();
	var productName=$("#name").val();
    var productType =$("#productType").find("option:selected").text(); 
    var time=$("#createDate").val();
    var operator=$("#operator").val();
    
    var strtime=time.substring(0,10);
	var str= new Array();
	str=strtime.split("-");
	var timestr="";
	for (var i=0;i<str.length ;i++ ){
		timestr+=str[i];
	}
	//校验 
  	if(productName==null){
  		alert("商品名称不能为空");
  		return false;
  	}else if(productType=="--请选择--"){
  		alert("商品类型不能为空");
  		return false;
  	}
    //设置 scriptData 的参数  
    $('#uploadify').uploadifySettings('scriptData',{'operator':operator,'productId':productId,'productName':productName,'productType':productType,'timestr':timestr});  
    //上传 

    jQuery('#uploadify').uploadifyUpload();
    
    
} 
</script>

</head>
<body onload="init('${product.tid}','${product.oid}','${product.bid}','${product.property}','${product.uploadType}','${product.status}','${product.isDel}');">
<form id="frm">
<div class="gt-panel" style="width: 900px; margin-left: 10px; ">
	<div class="gt-panel-head"><center> <span>修改商品信息</span></center></div>
	<div class="gt-panel-body" style="margin: 0px;">
	<table   width="890px;">
	<tr >
		<td style="width: 100px;">商品名称：</td>
		<td style="width: 790px;">
		<input type="hidden" id= "operator" name="operator" value="<%=session.getAttribute("adminId")%>">
		<input type="hidden" id="id" name="id" value="${product.id}"> 
		<input type="hidden" id="createDate" name="createDate" value="${product.createDate}"> 
		<input type="hidden" id="forwar" name="forwar" value="${product.forwar}"> 
		<input type="hidden" id="download" name="download" value="${product.download}"> 
		<input type="hidden" id="collect" name="collect" value="${product.collect}"> 
		<input type="hidden" id="uid" name="uid" value="${product.uid}"> 
		<input id="name" name="name" value="${product.name}" onblur="checkValue(this,'商品名称')"></td>
	</tr>
		<tr >
		<td style="width: 100px;">商品价格：</td>
		<td style="width: 790px;"><input id="price" name="price" value="${product.price}" onblur="checkValue(this,'商品价格')"></td>
	</tr>
<tr>
		<td style="width: 100px;">商品类型：</td>
		<td  style="width: 790px;"><select id="productType" name="productType" onblur="checkValue(this,'商品类型')">
				<option value="" >--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 100px;">所属商户：</td>
		<td  style="width: 790px;"><select id="organ" name="organ"  >
				<option value="" >--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 100px;">所属品牌：</td>
		<td  style="width: 790px;"><select id="brandName" name="brandName" >
				<option value="" >--请选择--</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 100px;">属性：</td>
		<td  style="width: 790px;"><select id="property" name="property" >
				<option value="">--请选择--</option>
				<option value="0">男</option>
				<option value="1">女</option>
				<option value="2">通用</option>
			</select></td>
	</tr>
		<tr>
		<td  style="width: 100px;">上传类型：</td>
		<td  style="width: 790px;"><select id="uploadType" name="uploadType"  >
				<option value="">--请选择--</option>
				<option value="0">分后台</option>
				<option value="1">后台</option>
				<option value="2">手机</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 100px;">状态：</td>
		<td  style="width: 790px;"><select id="status" name="status"  >
				<option value="">--请选择--</option>
				<option value="0">上架</option>
				<option value="1">下架</option>
				<option value="2">未审核</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 100px;">描述：</td>
		<td style="width: 790px;">
			<textarea rows="1" cols="25"  id="description" name="description"  onblur="checkValue(this,'描述')">${product.description}</textarea>
	</tr>
	<tr>
		<td style="width: 100px;">是否删除：</td>
		<td  style="width: 790px;"><select id="isDel" name="isDel">
				<option value="">--请选择--</option>
				<option value="0">是</option>
				<option value="1">否</option>
			</select></td>
	</tr>
	<tr>
		<td  style="width: 100px;">图片上传：</td>
		<td style="width: 790px;" >
			<input type="file" class="gt-input-button"  style=" float: left;"  name="uploadify" id="uploadify" />		
		</td>
		<tr>
		<td  style="width: 100px;"></td>
		<td style="width: 790px;">
			<div id="fileQueue" style="width: 400px;height: 80px; border: 2px solid green;"></div>
		</td>
		</tr>
		<tr>
		<td  style="width: 100px;"></td>
		<td style="width: 790px;">
			<input type="button" class="gt-input-button"  onclick="uploasFile()"  value="开始上传">
　			<input type="button" class="gt-input-button"  onclick="doCancel();jQuery('#uploadify').uploadifyClearQueue()" value="取消上传">
			<span id="result" style="font-size: 13px;color: red"></span>
		</td>
		</tr>
	<tr>
		<td style="width: 100px;">已上传图片</td>
		<td  style="width: 790px;">
			<table>
			<tr>
			   <s:iterator value="%{imagePathList}" id="art">
			   	<td>
			   	<input type="hidden" id="imgId" name="imgId" value="${art.id}">
			    <input type="hidden" id="imgUrl" name="imgUrl" value="${art.url}">
			    <input type="hidden" id="imgProductId" name="imgProductId" value="${art.productId}">
			   	<input type="hidden" id="imgIsDel" name="imgIsDel" value="${art.isDel}">
			   	
			   <img  width="80px;" height="80px;"  src="<%=basePath%>productUpload/${path}${art.url}">
			   <td>
			   </s:iterator>
			   </tr>
			   <tr>
			   <s:iterator value="%{imagePathList}" id="img">
			   	<td>
			   <input type="button" class="gt-input-button"  value="默认显示" onclick="doFirst('${img.id}','${img.url}','${img.productId}','${img.isDel}')"/><br/>
			   <input type="text" style="width: 90px;" value=" <%=basePath%>productUpload/${path}${img.url}" readonly="readonly"/>
			   <td>
			   </s:iterator>
			   </tr>
			   </table>
  		 </td>
	</tr>
	</table>
	
<div>
	<input type="reset" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; " value="重置"  onclick="doCancel()"/>
	
	<input type="button" class="gt-input-button" style="width: 70px; float: right; margin-top: 20px; margin-right: 10px;" value="提交"   onclick="doSubmit()"/>
</div>
</div>
</div>
</form>

		
</body>
</html>