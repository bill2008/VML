<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>������ʾ</title>
    <style type="text/css">
  
#man_zone table{
    background-color:#DBE6E3;
}
#man_zone table tr{
    background-color:#fff;
}
#man_zone table th{
    background-color:gray;
    color:white;
}
    </style>
  </head>
  
  <body>
  <div id="man_zone" style="height: 480px" align="center">
<table width="300" height="130" border="0" align="center" cellpadding="3" cellspacing="1" class="table_style">
  <tr>
    <td height="20" class="td2"> 
    <img src="images/hi.gif" >������Ϣ</td>
  </tr>
  <tr>
    <td height="110" valign="top" class="td1"><br>    
      <br>      <table width="80%"  border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><div align="center"><span class="l2">${message}<BR/>
              <BR>
              <A href="javascript:history.back();"   >
              <div align="center">����</div></A></span></div></td>
      </tr>
    </table></td>
  </tr>
</table>
 </div>    
  </body>
</html>
