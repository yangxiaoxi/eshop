<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico" >
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>

<body class="main">

 
<!-- 包含头部jsp -->
 <%@ include file="top.jsp"%>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">我的帐户</td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
		  <a href="modifyuserinfo.jsp">用户信息修改</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="order?method=orderList">订单查询</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.jsp">帐户余额</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.jsp">我的收藏</a></td>
        </tr>
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="my.jsp">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;欢迎</div>
     
        <table cellspacing="0" class="infocontent">
        <tr>
          <td style="padding:20px"><p><img src="ad/myad.jpg" width="631" height="436" /></p>
            </td>
        </tr>
      </table>
	  </td>
    </tr>
  </table>
</div>



  <!-- 包含底部jsp -->
<%@ include file="foot.jsp" %>


</body>
</html>
