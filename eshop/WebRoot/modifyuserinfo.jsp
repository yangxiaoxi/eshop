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
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
<script src="js/jquery-1.8.2.min.js"></script>
<script src="js/question.js"></script>
<script src="js/modify.js"></script>
 
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
		  <a href="modifyuserinfo.jsp">用户信息修改</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="order?method=orderList">订单查询</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#">帐户余额</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#">我的收藏</a></td>
        </tr>
   
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="cart.html">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;用户信息修改</div>
       
        <table cellspacing="0" class="infocontent">
        <tr>
          <td>
		 
		  <table width="100%" border="0" cellspacing="2" class="upline">
  <tr>
    <td style="text-align:right; width:20%">会员邮箱：</td>
    <td style="width:40%; padding-left:20px">${sessionScope.user.user_email }</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td style="text-align:right">会员名：</td>
    <td style="padding-left:20px">${sessionScope.user.user_name }</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td style="text-align:right">修改密码：</td>
    <td><input type="password" id="password" class="textinput" value="${sessionScope.user.user_pwd }"/></td>
    <td><font color="#999999">密码设置至少6位，请区分大小写</font></td>
  </tr>
  <tr>
    <td style="text-align:right">重复密码：</td>
    <td><input type="password" class="textinput" value="${sessionScope.user.user_pwd}"/></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td style="text-align:right">性别：</td>
     <td colspan="2">&nbsp;&nbsp;
    <c:if test="${sessionScope.user.user_sex=='男'}">
       <input type="radio" name="radiobutton" value="radiobutton" checked="checked" /> 男
       <input type="radio" name="radiobutton" value="radiobutton" />女
    </c:if>
         
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <c:if test="${sessionScope.user.user_sex=='女'}">
         <input type="radio" name="radiobutton" value="radiobutton" /> 男
        <input type="radio" name="radiobutton" value="radiobutton"  checked="checked"/>女
        </c:if>
</td>

    </tr>
  <tr>
    <td style="text-align:right">联系方式：</td>
    <td colspan="2"><input name="text2" type="text" class="textinput" value="${ sessionScope.user.user_phone}"/></td>
  </tr>
  <tr>
    <td style="text-align:right">发货地址：</td>
    <td colspan="2"><input name="text" type="text" class="textinput" style="width:350px" value="${ sessionScope.user.user_address }"/></td>
    </tr>
  <tr>
    <td style="text-align:right">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
		  
		  
		  
		  <table width="100%" border="0" cellspacing="2" class="upline">
            <tr>
              <td width="16%" style="text-align:right; width:20%">密码保护问题：</td>
              <td width="47%" style="width:50%">
              
                <select id="question" name="select" class="textinput" style="width:230px; height:20px">
                </select>
              </td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td style="text-align:right">答案：</td>
              <td colspan="2"><input name="text3" type="text" class="textinput" value="${ sessionScope.user.question_answer }"/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="#999999">不能少于4个字符，不区分大小写</font></td>
            </tr>
            <tr>
              <td style="text-align:right">备用邮箱：</td>
              <td><input name="text3" type="text" class="textinput"/></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td style="text-align:right">&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table>
		  <table width="100%" border="0" cellspacing="2" class="upline">
            <tr>
              <td style="text-align:right; width:20%">输入校验码：</td>
              <td style="width:50%"><input name="text4" type="text" class="textinput"/></td>
              <td><a href="javascript:changeVerifyCode()"><img id="codeImg" src="verifyCodeServlet" alt="点击切换" ></img></a></td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td style="text-align:right">&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </table>
		  
		  <p style="text-align:center"><a id="modify" href="javascript: modifyuserInfo()"><img src="images/botton_gif_025.gif" border="0" /></a></p>
		  <p style="text-align:center">&nbsp;</p></td>
        </tr>
      </table></td>
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

<script type="text/javaScript">
  function changeVerifyCode(){//点击更换验证码
  document.getElementById("codeImg").src="verifyCodeServlet?code="+new Date().getTime();
}
</script>
</body>
</html>
