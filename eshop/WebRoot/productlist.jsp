<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="cn.yunhe.entity.GoodsInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots" />
<meta name="author" content="Fisher" />
<meta name="Copyright"
	content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico">
<link rel="stylesheet" rev="stylesheet" href="css/style.css"
	type="text/css" media="all" />
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/productlist.js"></script>
</head>
<body class="main">
	<%@include file="top.jsp"%>
	<div id="divpagecontent">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td width="25%">
				<table width="100%" border="0" cellspacing="0" id="alltype"
						style="margin-top: 30px">
						<tr>
							<td class="listtitle">分类</td>
						</tr>
			 </table>
			 </td>
				<td>
					<div style="text-align: right; margin: 5px 10px 5px 0px">
						<a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;旅游&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
					</div>

					<table cellspacing="0" class="listcontent">
						<tr>
							<td>
								<h1>商品目录</h1>
								<hr />
								<h1>${requestScope.type_name }类</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${requestScope.count}种商品
								<hr /> <img src="images/miniicon2.gif" />&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.type_name }&nbsp;&nbsp;&nbsp;&nbsp;
								<c:forEach items="${requestScope.typeChilds }" var="childType">
								      |&nbsp;&nbsp;&nbsp;&nbsp;<a
										href="product?type_id=${requestScope.type_id}">${childType.type_name }</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</c:forEach>

								<div style="margin-top: 20px; margin-bottom: 5px">
									<img src="images/productlist.gif" width="631" height="38" />
								</div>
								<table cellspacing='0' class='booklist'>
									<c:set var="rows"
										value="${requestScope.bookList.size()%4==0?requestScope.bookList.size()/4:requestScope.bookList.size()/4+1}" />
									<c:if test="${rows>0}">
										<c:forEach var="i" begin="0" end="${rows-1}">
											<tr>
												<c:forEach var="j" begin="0" end="3">
													<c:if test="${i*4+j<=requestScope.bookList.size()-1}">
														<td><div class="divbookpic">
																<p>
																	<a
																		href="productInfo?book_id=${requestScope.bookList.get(i*4+j).book_id }">
																		<img
																		src="good_imgs/${requestScope.bookList.get(i*4+j).book_logo }"
																		width="83" height="135" />
																	</a>
																</p>
															</div>
															<div class="divlisttitle">${requestScope.bookList.get(i*4+j).book_name }<br />
																${requestScope.bookList.get(i*4+j).book_price }
															</div></td>
													</c:if>
													<c:if test="${i*4+j>requestScope.bookList.size()-1}">
														<td></td>
													</c:if>
												</c:forEach>
											</tr>
										</c:forEach>
									</c:if>
								</table>
								<div class="pagination">
									<ul>
										<c:if test="${requestScope.currentPage!=1 }">
											<li class="nextpage"><a
												href="product?type_id=${requestScope.type_id}&currentPage=${requestScope.currentPage-1}">上一页
													>></a></li>
										</c:if>
										<c:if test="${requestScope.currentPage==1}">
											<li class="disablepage"><< 上一页</li>
										</c:if>

										<c:forEach var="pageCurrent" varStatus="status"
											begin="${requestScope.startPage }" end="${requestScope.endPage }">
											<c:if test="${pageScope.pageCurrent==requestScope.currentPage }">
											    <li class="currentpage"> ${pageScope.pageCurrent }</li>
											</c:if>
											 <c:if test="${pageScope.pageCurrent!=requestScope.currentPage }">
											      <li><a href='product?type_id=${requestScope.type_id}&currentPage=${pageScope.pageCurrent }'>${pageScope.pageCurrent }
											          </a></li>
											 </c:if>
											
											
										</c:forEach>
										<c:if
											test="${requestScope.currentPage!=requestScope.totalPage }">
											<li class="nextpage"><a
												href="product?type_id=${requestScope.type_id}&currentPage=${requestScope.currentPage+1}">下一页
													>></a></li>
										</c:if>
										<c:if
											test="${requestScope.currentPage==requestScope.totalPage }">
											<li class="disablepage">下一页 >></li>
										</c:if>
									</ul>
								</div>


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
				<td rowspan="2" style="width: 10%"><img
					src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left: 175px" /></td>
				<td style="padding-top: 5px; padding-left: 50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b></font></a></td>
			</tr>
			<tr>
				<td style="padding-left: 50px"><font color="#CCCCCC"><b>COPYRIGHT
							2008 &copy; eShop All Rights RESERVED.</b></font></td>
			</tr>
		</table>
	</div>


</body>
</html>
