 
//3.   通过给每个输入框设置焦点事件 实现验证
$(function() {
	$("input[name='email']").blur(function() {
		var userEmail = ($("input[name='email']")).val();
	//	alert(userEmail);
		if(userEmail == "") {
			$("#EmailSapn").show();
			$("#EmailSapn").html("邮箱不能为空");
			//			alert("邮箱不能为空");
		}else {//^\\d+@\.+(.com|.net)$
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");//
			if(!reg.test(userEmail)) {
				
				$("#EmailSapn").html("邮箱格式不正确");
				$("#EmailSapn").show();
			} else {
				$("#EmailSapn").hide();
				//如果输入格式正确，ajax请求是否存在
				var emailValue = $("#email").val();
				var url = "userServlet?method=ckEmain&email="+emailValue;//
				  ///ckEmail 当请求成功时的回调方法名
				ajaxrequest(url, "post","true", ckEmail,document);//开始ajax请求，如果成功回调ckEmail（）函数
			}
		}
	});

	$("input[name='userName']").blur(function() {
		var userName = $("input[name='userName']").val();
		if(userName == "") {
			$("#userNameSpan").show();
			$("#userNameSpan").html("会员名不能为空")
		} else {
			//ajax请求输入的用户名是否重复
			var user_name = $("#userName").val();
			var url = "userServlet?method=ckName&user_name="+user_name;
			//ckUserName 请求成功回调的方法名
			ajaxrequest(url, "get","true", ckUserName, document);//如果请求成功回调 ckUserName（）函数
			//$("#userNameSpan").hide();
		}
	});

	$("input[name='Firstpassword']").blur(function() {
		psw = $("input[name='Firstpassword']").val();
		if(psw == "" || psw.length < 6) {
			$("#firstPswSpan").show();
			$("#firstPswSpan").html("密码为空或密码长度小于6");
		} else {
			$("#firstPswSpan").hide();
		}
	});
	
	

	$("input[name='Secondpassword']").blur(function(){
		var spsw = $("input[name='Secondpassword']").val();
		if(spsw != psw) {
		$("#secondPswSpan").show();
			$("#secondPswSpan").html("两次密码不一致");
		}else{
			$("#secondPswSpan").hide();
	}
    });
	
	$("input[name ='text2']").blur(function(){
		if($("input[name ='text2']").val() == "") {
		  $("#numberSpan").show();
		  $("#numberSpan").html("联系方式不能为空");
		}else{
			$("#numberSpan").hide();
		}
	});
		
	$("input[name='text']").blur(function(){
		if($("input[name='text']").val() == "") {
			$("#addressSpan").show();
			$("#addressSpan").html("地址不能为空");
		}else{
		$("#addressSpan").hide();
		}
	});
	
});






//验证邮箱是否存在的回调函数
function ckEmail(response) {
	//alert(response);
	//解析Json数据
	var jsonObject=$.parseJSON(response);
	if(jsonObject.status==-200){//-200表示邮箱存在
		$("#EmailSapn").html("邮箱已存在");
		$("#EmailSapn").show();
	}else{
		$("#EmailSapn").hide();
	}
}
//验证用户名是否存在
function ckUserName(response) {
	var jsonObject = $.parseJSON(response);//解析响应的json字符串
	if(jsonObject.status==-200){//表示用户名已存在
		$("#userNameSpan").html("用户名已存在");
		$("#userNameSpan").show();
	}else{
		$("#userNameSpan").hide();
	}
	 
}
 
function getajaxHttp() {
	var xmlHttp;
	try {
		// Firefox, Opera 8.0+, Safari  
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		// Internet Explorer  
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持AJAX！");
				return false;
			}
		}
	}
	return xmlHttp;
}

/** 
 * 发送ajax请求
 * url--url 
 * methodtype(post/get) 
 * con (true(异步)|false(同步)) 
 * functionName(回调方法名，不需要引号,这里只有成功的时候才调用) 
 * (注意：这方法有二个参数，一个就是xmlhttp,一个就是要处理的对象) 
 * obj需要到回调方法中处理的对象 
 */
function ajaxrequest(url, methodtype, con, functionName, obj) {
	//1>实例化一个XMLHttpRequest对象
	var xmlhttp = getajaxHttp();
	//2>设置一个回调函数
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				//xmlhttp.responseText就是响应的内容
				functionName(xmlhttp.responseText);
			}
		}

	};
	//打开连接
	xmlhttp.open(methodtype, url, con);
	//开始请求
	xmlhttp.send();
}

function register(){
	/**
	 * 验证邮箱
	 * */
	var userEmail = $("input[name='email']").val();
	 alert(userEmail);
	 /**
	  * 1，验证邮箱
	  */
		if(userEmail == '') {
			show_err_msg("邮箱不能为空")
			//			alert("邮箱不能为空");
		}else {//^\\d+@\.+(.com|.net)$
			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");//
			if(!reg.test(userEmail)) {
				 show_err_msg("邮箱格式不正确")
			} else {
				if(	$("#EmailSapn").html()=='邮箱已存在'){
					show_err_msg("邮箱已存在");
				}else{
					/**
					 ,2， 验证用户名
					 */
						var userName = $("input[name='userName']").val();
						if(userName == "") {
							 show_err_msg("会员名不能为空")
						} else {
							if(	$("#userNameSpan").html()=='用户名已存在'){
								show_err_msg("用户已存在");
							}else{
								/**
								 * 3，验证第一次密码
								 * */
								var psw = $("input[name='Firstpassword']").val();
								if(psw == "" || psw.length < 6) {
									 show_err_msg("密码为空或密码长度小于6");
								}else{
									 /**
									  *4， 验证第二次密码
									  * */
									var spsw = $("input[name='Secondpassword']").val();
									if(spsw != psw) {
									$("#secondPswSpan").show();
										 show_err_msg("两次密码不一致");
									}else{
										/**
										 * 5,验证联系方式
										 * */
										if($("input[name ='text2']").val() == "") {
											   show_err_msg("联系方式不能为空");
											}else{
												 /**6,验证发货地址
												  * */
												if($("input[name='text']").val() == "") {
													 show_err_msg("地址不能为空");
												}else{
													/**7,验证密保问题
													  * */
													if($("#question option:selected").val()==""){
														show_err_msg("请选择密保问题");
													}else{
														/**8,验证密保问题答案
														  * */
														if($("#answer").val()==""){
															show_err_msg("请输入密保问题答案")
														}else{
															/**9,验证验证码是否输入
															  * */
															if($("#verifycode").val()==""){
																show_err_msg("请输入验证码");
															}else{
																$("#userRegister").submit();//提交表单
															}
														}
													}
										        }
								           }
								  }
							 }
						}
				}
			}
		}
	
	
	}

}