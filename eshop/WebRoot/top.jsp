<%@page import="cn.yunhe.entity.Type"%>
<%@page import="java.util.List"%>
<%@page import="cn.yunhe.biz.impl.TypeBizImpl"%>
<%@page import="cn.yunhe.biz.TypeBiz"%>
<%@page import="cn.yunhe.biz.impl.UserBizImpl"%>
<%@page import="cn.yunhe.biz.UserBiz"%>
<%@page import="cn.yunhe.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet" rev="stylesheet" href="css/style.css"
	type="text/css" media="all" /><base>
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="js/top.js"></script>
</head>
<body>
	<!-- Logo -->
	<div id="divhead">
		<table cellspacing="0" class="headtable">
			<tr>
				<td><a href="index.jsp"><img src="images/logo.gif"
						width="95" height="30" border="0" /></a></td>
				<td style="text-align: right">&nbsp; 
				  <c:set value="${sessionScope.user}" var="user" /> 
				  <c:if test="${ user!=null}">
						<a href='my.jsp'>【${user.getUser_name()}】</a>   &nbsp; &nbsp;&nbsp;   <a
							href='userServlet?method=exit'>退出</a>　|&nbsp;&nbsp;
				  </c:if>
				  <c:if test="${ user==null}">
						 <c:set value="${cookie.userId.getName()}" var="userName" />
						   <c:if test="${cookieName == 'userId' }">
							   <c:set value="${cookie.userId}" var="user_id" scope="page"/>
							   <%
									   Cookie user_idCookie = (Cookie)pageContext.getAttribute("user_id");
							           String user_id = user_idCookie.getValue();
									   UserBiz userBiz = new UserBizImpl();
									   User user =  userBiz.selectById(Integer.parseInt(user_id));
									   session.setAttribute("user",user );
									   out.print("<a href='my.jsp'>【"+user.getUser_name()+"】</a>   &nbsp; &nbsp;&nbsp;   <a href='userServlet?method=exit'>退出</a>　|&nbsp;&nbsp;");
							   %>
				           </c:if>

					     <c:if test="${cookieName !='userId'}">
				           <a href='login.jsp'>亲，请先登录</a>  &nbsp;   |&nbsp; <a href="register.jsp">新用户注册</a>
				        </c:if>
				</c:if> 
				
				<img src="images/cart.gif" width="26" height="23"
					style="margin-bottom: -4px" />&nbsp;&nbsp;&nbsp;<a href="cart?method=select">购物车</a>
					|&nbsp;&nbsp;&nbsp;&nbsp; <a href="history">浏览记录</a> |&nbsp;&nbsp; <a href="my.jsp">我的帐户</a> &nbsp;&nbsp;
					
				</td>
			</tr>
		</table>
	</div>
	<!-- Logo end -->
	<!-- menu -->
	<div id="divmenu">
		 
		
	</div>
	<!-- menu end -->
	<!-- search -->
	<div id="divsearch">
	<form action="search?currentPage=${requestScope.currentPage}" id="submitForm" method="post">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td style="text-align: right; padding-right: 220px">Search <input
					type="text" name="textfield" class="inputtable"  value="${requestScope.book_name}" /> <!--<input name="searchbutton" type="image" src="images/serchbutton.gif" style=" margin-bottom:-4px"/>-->
					<a href="javascript:search()"><img src="images/serchbutton.gif"
						border="0" style="margin-bottom: -4px" /></a></td>
			</tr>
		</table>
   </form>

	</div>
<script type="text/javascript">

     function search() {
		$("#submitForm").submit();
	}
</script>
</body>
</html>