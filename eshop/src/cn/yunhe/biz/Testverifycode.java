package cn.yunhe.biz;
import cn.yunhe.util.VerifyCodeUtilByYock;


public class Testverifycode {
	
	public static void main(String[] args) {
		
	 String code  =  VerifyCodeUtilByYock.generateVerifyCode(4);
	 System.out.println(code);
	 
	//int verifycode_count = (int)request.getSession().getAttribute(ParamUtil.LONING_COUNT);//�˺�����������
			//	System.out.println("�˺�������� ����"+verifycode_count);
//				if(verifycode_count>=3){
//					String verifyCode = (String) request.getSession().getAttribute(ParamUtil.VERIFY_CODE);//��ȡ���ɵ���֤��
//					System.out.println("verifyCode is"+verifyCode);
//					String inputVerifycode = request.getParameter("verifycode");//��ȡ�������֤��
//					System.out.println("inputVerifycode is"+inputVerifycode);
//					if(verifyCode!=null &&inputVerifycode!=null ){
//					if(!verifyCode.equals(inputVerifycode)){//�����֤����ȷ
//						//�����֤�벻��ȷ
//						try {//;window.location.href='login.jsp'
//							//response.sendRedirect("login.jsp");
//							response.getWriter().
//							 print("<script type='text/javascript'>alert('��֤��������');</script>");
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					} 
//				}
//				}
//		
	}

}
