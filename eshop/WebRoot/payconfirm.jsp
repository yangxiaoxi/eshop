<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'payconfirm.jsp' starting page</title>
  </head>
  
  <body>
    <form action="https://www.yeepay.com/app-merchant-proxy/node" method="post">
    	<input type="hidden" name="p0_Cmd" value="${requestScope.p0_Cmd }">  <!-- 代表接口类型（即时到帐支付接口、自动收款功能） -->
    	<input type="hidden" name="p1_MerId" value="${p1_MerId }">	<!-- APP_ID -->
    	<input type="hidden" name="p2_Order" value="${p2_Order }">	<!-- 订单号 -->
    	<input type="hidden" name="p3_Amt" value="${p3_Amt }">		<!-- 订单金额 -->
    	<input type="hidden" name="p4_Cur" value="${p4_Cur }">		<!-- 币种 -->
    	<input type="hidden" name="p5_Pid" value="${p5_Pid }">
    	<input type="hidden" name="p6_Pcat" value="${p6_Pcat }">
    	<input type="hidden" name="p7_Pdesc" value="${p7_Pdesc }">
    	<input type="hidden" name="p8_Url" value="${p8_Url }">		<!-- 回调地址 -->
    	<input type="hidden" name="p9_SAF" value="${p9_SAF }">
    	<input type="hidden" name="pa_MP" value="${pa_MP }">
    	<input type="hidden" name="pd_FrpId" value="${pd_FrpId }"> <!-- 选择的是哪一家银行，它的银行代码 -->
    	<input type="hidden" name="pr_NeedResponse" value="${pr_NeedResponse }"> <!-- 是否需要回调 -->
    	<input type="hidden" name="hmac" value="${hmac}">			<!-- 一串加密后的码（ 把所有参数拼接起来，再加密） -->
    	<input type="submit" value="确认支付">
    </form>
    
  </body>
</html>
