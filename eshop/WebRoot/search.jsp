<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta content="all" name="robots" />
    <meta name="author" content="Fisher" />
    <meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
    <meta name="description" content="reefdesign" />
    <meta name="keywords" content="reefdesign" />
    <title>电子书城</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
      <script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/searchtype.js"></script>
</head>
<body class="main">
     <%@include file="top.jsp" %>
    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td width="25%">
                    <form action="search" method="post">
                    
                    <table width="100%" border="0" cellspacing="0" style="margin-top: 30px">
                        <tr>
                            <td class="listtitle">
                                缩小搜索范围
                            </td>
                        </tr>
                        <tr>
                            <td class="listtd">
                                <br />
                                <p>
                                    关键字：<input type="text" name="textfield2" class="inputtable"  value="${requestScope.keyWordStr }"/></p>
                                <p>
                                    类&nbsp;&nbsp;&nbsp;&nbsp;别：<select id="select" name="select">
                                           </select>
                                </p>
                                <p style="text-align: center">
                                    <input name="确定" type="submit" class="inputbutton" value="提交" />
                                </p>
                            </td>
                        </tr>
                    </table>
                    </form>
                </td>
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;公告新闻&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;文章标题</div>
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td style="padding: 10px">
                                            以下 <strong>${requestScope.bookList.size()}</strong> 条结果按 <strong>销量</strong> 排列 每页显示<strong>20</strong>条<hr />

                                           
                                           <c:forEach items="${requestScope.bookList}" var="book">
                                           
                                              <table border="0" cellspacing="0" class="searchtable">
                                                <tr>
                                                    <td width="20%" rowspan="2">
                                                        <div class="divbookpic">
                                                            <p>
                                                                <a href="info.html">
                                                                    <img src="good_imgs/${book.book_logo }" width="102" height="130" border="0" /></a></p>
                                                        </div>
                                                    </td>
                                                    <td colspan="2">
                                                        <font class="bookname"> ${book.book_name }</font><br />
                                                        作者：${book.book_author } 著<br />
                                                     ${book_book_desciption }
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        售价：<font color="#FF0000">￥${book.book_price}</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${book.book_price_old }</s>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <a href="cart.html">
                                                            <img src="images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                           </c:forEach>
                                            <div class="pagination">
                                                <ul>
                                         <c:if test="${requestScope.currentPage!=1 }">
									           <li class="nextpage"><a
												     href="search?textfield=${requestScope.book_name}&textfield2=${requestScope.keyWordStr }&select=${ requestScope.optionTypeStr}&currentPage=${requestScope.currentPage-1}">上一页
													&lt;&lt;</a></li>
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
											      <li><a href='search?textfield=${requestScope.book_name}&textfield2=${requestScope.keyWordStr }&select=${ requestScope.optionTypeStr}&currentPage=${pageScope.pageCurrent }'>${pageScope.pageCurrent }
											         </a></li>
											 </c:if>
										</c:forEach>
                                                     
                                                    
                                                    
                                        <c:if test="${requestScope.currentPage!=requestScope.totalPage }">
											<li class="nextpage">
											   <a href="search?textfield=${requestScope.book_name}&textfield2=${requestScope.keyWordStr }&select=${ requestScope.optionTypeStr}&currentPage=${requestScope.currentPage+1}">下一页
													>></a></li>
										</c:if>
										<c:if test="${requestScope.currentPage==requestScope.totalPage }">
											<li class="disablepage"><a>下一页 >></a></li>
										</c:if>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
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
                <td rowspan="2" style="width: 10%">
                    <img src="images/bottomlogo.gif" width="195" height="50" style="margin-left: 175px" />
                </td>
                <td style="padding-top: 5px; padding-left: 50px">
                    <a href="#"><font color="#747556"><b>CONTACT US</b></font></a>
                </td>
            </tr>
            <tr>
                <td style="padding-left: 50px">
                    <font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b></font>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
