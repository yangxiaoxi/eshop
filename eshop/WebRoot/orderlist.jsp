<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
          <td class="listtitle">我的帐户</td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
		  <a href="modifyuserinfo.html">用户信息修改</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="orderlist.html">订单查询</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">帐户余额</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">我的收藏</a></td>
        </tr>
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="my.html">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单查询</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td style="padding:20px"><p>欢迎${sessionScope.user.user_name }光临EShop商城！</p>
            <p>您有 <font style="color:#FF0000">${requestScope.orderList.size() }</font>笔交易</p>
            <table width="100%" border="0" cellspacing="0" class="tableopen">
              <tr>
                <td bgcolor="#A3E6DF" class="tableopentd01">订单号</td>
                <td bgcolor="#A3E2E6" class="tableopentd01">订单内容</td>
                <td bgcolor="#A3D7E6" class="tableopentd01">收件人</td>
                <td bgcolor="#A3CCE6" class="tableopentd01">订单时间</td>
                <td bgcolor="#A3B6E6" class="tableopentd01">状态</td>
                <td bgcolor="#A3B6E6" class="tableopentd01">操作</td>
              </tr>
              
            <c:forEach items="${requestScope.orderList }" var="order">
	              <tr>
	                <td class="tableopentd02">${order.order_id }</td>
	                 <td class="tableopentd02">
	                 <c:forEach items="${order.bookList}" var="book">
		                 <a href="productInfo?book_id=${ book.book_id}">${book.book_name }</a></br>
	                </c:forEach>
	                 </td>
	                <td class="tableopentd02">${order.person }</td>
	                <td class="tableopentd02">${order.order_date }</td>
	                <!-- 状态 -->
	                <c:if test="${order.status==1 && order.status_pay ==0 &&order.status_send==0 && order.status_receive==0 }">
	                   <td class="tableopentd03">待付款</td>
	                </c:if>
	                <c:if test="${order.status==1 && order.status_pay ==1 &&order.status_send==0 && order.status_receive==0 }">
	                   <td class="tableopentd03"><font style="color:green">已付款</font></td>
	                </c:if>
	                <c:if test="${order.status==1 && order.status_pay ==1 &&order.status_send==1 && order.status_receive==0 }">
	                   <td class="tableopentd03">已发货</td>
	                </c:if>
	                <c:if test="${order.status==1 && order.status_pay ==1 &&order.status_send==1 && order.status_receive==1 }">
	                   <td class="tableopentd03">已收货</td>
	                </c:if>
	                <c:if test="${order.status==0 }">
	                   <td class="tableopentd03"> <font style="color:#FF0000">已取消</font></td>
	                </c:if>
	                
	                <!-- 操作 -->
	                <c:if test="${order.status==1 && order.status_pay ==0 &&order.status_send==0 && order.status_receive==0 }">
	                   <td class="tableopentd03"><a href="order?method=orderPay&orderNo=${order.order_id }&totalMoney=${order.order_price}">付款</a>&nbsp;&nbsp;<a href="order?method=cancleOrder&order_id=${order.order_id }">取消订单</a></td>
	                </c:if>
	                <c:if test="${order.status==1 && order.status_pay ==1 &&order.status_send==0 && order.status_receive==0 }">
	                   <td class="tableopentd03"><a href="order?method=cancleOrder&order_id=${order.order_id }">取消订单</a></td>
	                </c:if>
	                <c:if test="${order.status==1 && order.status_pay ==1 &&order.status_send==1 && order.status_receive==0 }">
	                   <td class="tableopentd03"><a href="order?method=confirmOrder&order_id=${order.order_id }">确认收货 </a>&nbsp;&nbsp;<a href="order?method=cancleOrder&order_id=${order.order_id }">取消订单</a></td>
	                </c:if>
              </tr>
              </c:forEach>
            </table></td>
        </tr>
      </table>
	  
	  
	  </td>
    </tr>
  </table>
</div>
 <%@include file="foot.jsp" %>
</body>
</html>
