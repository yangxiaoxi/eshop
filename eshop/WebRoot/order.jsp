<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<script type="text/javascript" src="js/jquery-1.8.2.min.js"></script>
<script src="js/tooltips.js"></script>
</head>

<body class="main">

 <!-- 包含头部jsp -->
 <%@ include file="top.jsp"%>


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
                <td><img src="images/buy2.gif" width="635" height="38" />
                  <p>早上好：${sessionScope.user.user_name}先生！欢迎您来到商城结算中心</p></td>
              </tr>
              <tr>
                <td><table cellspacing="1" class="carttable">
                  <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">市场价</td>
                    <td width="10%">优惠价</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
                 
                  </tr>
                    <c:set value="0" var="totalMoney"/>
				    <c:forEach items="${sessionScope.book_list }" varStatus="i"  var="book">
					   <table width="100%" border="0" cellspacing="0">
	                    <tr>
	                      <td width="10%">${i.index+1}</td>
	                      <td width="40%"><a href="productInfo?book_id=${book.book_id}"> ${book.book_name }</td></a>
	                      <td width="10%">${book.book_price_old }</td>
	                      <td width="10%">${book.book_price }</td>
	                      <td width="10%"><input id ="count" name="count" type="text" value="${book.book_count}" style="width:20px"/></td>
	                      <td width="10%">${book.book_price *book.book_count}<input type="hidden"  name="book_id" value="${book.book_id}"/></td>
	                      <c:set value="${totalMoney + book.book_price *book.book_count}" var="totalMoney"/>
	                    </tr>
	                  </table>
	                  
				  </c:forEach>
				  
				   <table cellspacing="1" class="carttable">
                     <tr>
                       <td style="text-align:right; padding-right:40px;"><font style="color:#FF0000">合计：&nbsp;&nbsp;${totalMoney }元</font></td>
                      </tr>
                   </table>	   	   
				   
				   <p>收货地址：<input name="user_address" id="user_address" type="text" value="${sessionScope.user.user_address }" style="width:350px"/>&nbsp;&nbsp;&nbsp;&nbsp; <br/>
				   收货人：&nbsp;&nbsp;&nbsp;&nbsp;<input  id="user" name="user" type="text"  style="width:150px"/>&nbsp;&nbsp;&nbsp;&nbsp; <br/>
				   联系方式：<input name="phone" id="phone" type="text" value="${sessionScope.user.user_phone }" style="width:150px"/>&nbsp;&nbsp;&nbsp;&nbsp;
				   </p>
				  <hr/> 
				   <p style="text-align:right"><a href="javascript: verifyOrder()"><img src="images/gif53_029.gif" width="204" height="51" border="0" /></a></p></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>


  <!-- 包含底部jsp -->
<%@ include file="foot.jsp" %>

<script type="text/javascript">

function verifyOrder() {
	 var user_address =  $("#user_address").val();
	 var user =  $("#user").val();//收货人
	 var phone =  $("#phone").val();//联系方式
	 var $count =  $("input[name ='count']")//获取数量
	 var $book_id =  $("input[name ='book_id']")//获取book_id;
	 var countArray = new Array();//存放订单中个书本的数量
	 var book_iuArray = new Array();//存放订单中个书本的数量
	 $book_id.each(function () {
		 book_iuArray.push(this.value);//将商品数量放进数组中
	    });
	 if(user_address==''){
		 show_err_msg("请输入用户名");
	 }else if(user==''){
		 show_err_msg("请输入收货人");
	 }else if(phone==''){
		 show_err_msg("请输入联系方式");
	 }else{
		 var havaCount = 0;
		 $count.each(function () {
				if(this.value==''){
					show_err_msg("请输入商品数量");
				}else{
					countArray.push(this.value);//将商品数量放进数组中
					havaCount++;
				}
		    });
		if($count.length==havaCount){//当商品数量和book_id 一 一 对应，才提交表单
			 location.href = "order?method=orderSecond&user_address="+user_address+"&user="+user+"&phone="+phone+"&book_id="+book_iuArray+"&count="+countArray;
		}
	 }
}
</script>
</body>
</html>
