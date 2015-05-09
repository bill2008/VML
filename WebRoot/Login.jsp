<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>微米兰后台</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="login/css/icon.css" rel="stylesheet" type="text/css" />
	<link href="login/css/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript">
	function login(){
		var userAccount=$("#loginName").val();
		var userPwd= $("#userPwd").val();
		if(userAccount!=null && userPwd!=null){
			return true;
			
		}else{
			alert("请输入账号或密码!");
			return false;
		}
		
	}
</script>
	<title>登录</title>
  </head>
  <body>
      <body class="loginbody">
	        <form name="frm" id="frm" action="wmlAdmin_loginAdmin.action" method="post" >
					<div class="midbox">
		            <div class="loginhead">
		                <span><img src="login/images/lockIcon.png" /></span><span>登录到您的账户</span>
		            </div>
		            <dl class="logindl">
		                <dt>用户名</dt>
		                <dd>
		                    <input type="text" id="loginName" name="loginName" /><span>*</span>
		                </dd>
		                <dt>密码</dt>
		                <dd>
		                    <input type="password" id="userPwd" name="userPwd"/><span>*</span>
		                </dd>
		            </dl>
		            <div class="loginsub">
		                <span class="btn">
		                    <input type="image" src="login/images/loginbtn.jpg" width="97" height="27" border="0" tabindex="3"  />
		                </span>
		            </div>
		        </div>
		      </form>
    </body>
</html>