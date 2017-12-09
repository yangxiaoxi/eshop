<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>

<body class="main">

<%@include file="top.jsp" %>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">配送方式、时间及费用</td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="news.html">普通快递送货上门</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">申通快递送货上门</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">普通邮寄</a></td>
        </tr>
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="cart.html">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单</div>
        
		
		<table cellspacing="0" class="infocontent">
        <tr>
          <td><table width="100%" border="0" cellspacing="0">
              <tr>
                <td>
                
                <form action="payRequest" id="form" method="post" >
                <input type="hidden" name="orderNo" value="${requestScope.orderNo }" />
			    <input type="hidden" name="price" value="${requestScope.totalMoney }" />
                <p>
                	您的订单号是：${requestScope.orderNo }
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	订单总金额为：<strong style="color: red">￥${requestScope.totalMoney }</strong>
                </p>
                <br/><br/>
                <p>
                	<strong>请您选择在线支付银行</strong>
                	<select name="pd_FrpId">
                		<option value="CMBCHINA-NET">招商银行</option>
                		<option value="ICBC-NET">工商银行</option>
                		<option value="ABC-NET">农业银行</option>
                		<option value="CCB-NET">建设银行</option>
                		<option value="CMBC-NET">中国民生银行总行</option>
                		<option value="CEB-NET">光大银行</option>
                		<option value="BOCO-NET">交通银行</option>
                		<option value="SDB-NET">深圳发展银行</option>
                		<option value="BCCB-NET">北京银行</option>
                		<option value="SPDB-NET">上海浦东发展银行</option>
                		<option value="CIB-NET">兴业银行</option>
                		<option value="ECITIC-NET">中信银行</option>
                	</select>
                </p>
                </form>
                 </td>
              </tr>
              <tr>
                <td>
				   <p style="text-align:center">
				   <a href="javascript:document.getElementById('form').submit();"><img src="images/finalbutton.gif" width="204" height="51" border="0" /></a>
				   </p>
				</td>
              </tr>
            </table>
       </td>
    </tr>
  </table>
</div>



<div id="divfoot">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td rowspan="2" style="width:10%"><img src="images/bottomlogo.gif" width="195" height="50" style="margin-left:175px"/></td>
      <td style="padding-top:5px; padding-left:50px"><a href="#"><font color="#747556"><b>CONTACT US</b></font></a></td>
    </tr>
    <tr>
      <td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b></font></td>
    </tr>
  </table>
</div>


</body>
</html>
