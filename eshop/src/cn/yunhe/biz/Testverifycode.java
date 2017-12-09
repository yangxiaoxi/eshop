package cn.yunhe.biz;
import cn.yunhe.util.VerifyCodeUtilByYock;


public class Testverifycode {
	
	public static void main(String[] args) {
		
	 String code  =  VerifyCodeUtilByYock.generateVerifyCode(4);
	 System.out.println(code);
	 
	//int verifycode_count = (int)request.getSession().getAttribute(ParamUtil.LONING_COUNT);//账号密码错误次数
			//	System.out.println("账号密码错误 次数"+verifycode_count);
//				if(verifycode_count>=3){
//					String verifyCode = (String) request.getSession().getAttribute(ParamUtil.VERIFY_CODE);//获取生成的验证码
//					System.out.println("verifyCode is"+verifyCode);
//					String inputVerifycode = request.getParameter("verifycode");//获取输入的验证码
//					System.out.println("inputVerifycode is"+inputVerifycode);
//					if(verifyCode!=null &&inputVerifycode!=null ){
//					if(!verifyCode.equals(inputVerifycode)){//如果验证码正确
//						//如果验证码不正确
//						try {//;window.location.href='login.jsp'
//							//response.sendRedirect("login.jsp");
//							response.getWriter().
//							 print("<script type='text/javascript'>alert('验证码错误错误');</script>");
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					} 
//				}
//				}
//		
	}

}
