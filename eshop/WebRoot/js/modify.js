$(function () {
	
//	/**
//	 * 验证邮箱
//	 * */
//	var password = $("#password").val();
//	 alert(userEmail);
//	 /**
//	  * 1，验证邮箱
//	  */
//		if(password == '') {
//			show_err_msg("密码不能为空")
//			//			alert("邮箱不能为空");
//		}else {//^\\d+@\.+(.com|.net)$
//			var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");//
//			if(!reg.test(userEmail)) {
//				 show_err_msg("邮箱格式不正确")
//			} else {
//				if(	$("#EmailSapn").html()=='邮箱已存在'){
//					show_err_msg("邮箱已存在");
//				}else{
//					/**
//					 ,2， 验证用户名
//					 */
//						var userName = $("input[name='userName']").val();
//						if(userName == "") {
//							 show_err_msg("会员名不能为空")
//						} else {
//							if(	$("#userNameSpan").html()=='用户名已存在'){
//								show_err_msg("用户已存在");
//							}else{
//								/**
//								 * 3，验证第一次密码
//								 * */
//								var psw = $("input[name='Firstpassword']").val();
//								if(psw == "" || psw.length < 6) {
//									 show_err_msg("密码为空或密码长度小于6");
//								}else{
//									 /**
//									  *4， 验证第二次密码
//									  * */
//									var spsw = $("input[name='Secondpassword']").val();
//									if(spsw != psw) {
//									$("#secondPswSpan").show();
//										 show_err_msg("两次密码不一致");
//									}else{
//										/**
//										 * 5,验证联系方式
//										 * */
//										if($("input[name ='text2']").val() == "") {
//											   show_err_msg("联系方式不能为空");
//											}else{
//												 /**6,验证发货地址
//												  * */
//												if($("input[name='text']").val() == "") {
//													 show_err_msg("地址不能为空");
//												}else{
//													/**7,验证密保问题
//													  * */
//													if($("#question option:selected").val()==""){
//														show_err_msg("请选择密保问题");
//													}else{
//														/**8,验证密保问题答案
//														  * */
//														if($("#answer").val()==""){
//															show_err_msg("请输入密保问题答案")
//														}else{
//															/**9,验证验证码是否输入
//															  * */
//															if($("#verifycode").val()==""){
//																show_err_msg("请输入验证码");
//															}else{
//																$("#userRegister").submit();//提交表单
//															}
//														}
//													}
//										        }
//								           }
//								  }
//							 }
//						}
//				}
//			}
//		}
//	
//	
//	}
//	
//	
	
	
});