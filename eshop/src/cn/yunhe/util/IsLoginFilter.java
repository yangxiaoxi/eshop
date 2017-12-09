package cn.yunhe.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.TODO;

import cn.yunhe.biz.UserBiz;
import cn.yunhe.biz.impl.UserBizImpl;
import cn.yunhe.entity.User;

public class IsLoginFilter implements Filter {
   private UserBiz userBiz = new UserBizImpl();
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String urlStr = request.getRequestURI();//��ȡ������Դ�ľ���·��
		String [] urlArray = urlStr.split("/");//�и��ȡ�ľ�����Դ·�����õ�·������
		String srcName = urlArray[urlArray.length-1]; //��ȡ·���������һ��·�������Ƿ�����Դ������
		
		
		//������ʵ���Դ���ҵ��˻�ҳ����߶���ҳ�棬�����û��Ƿ��½
		if(srcName.equals("my.jsp")||srcName.equals("order.jsp")){
			  //1,���ȼ���session�Ƿ����û�����
			   User Login_user = (User) request.getSession().getAttribute(ParamUtil.LOGIN_USER);
			   if(Login_user==null){
				 //2,���session������cookie 
				   Cookie[] cookies = request.getCookies();//��ȡcookie��
				   String user_id = null;
				   for (Cookie cookie : cookies) {//����cookie�������Ƿ񱣴���user_id(��ס��½״̬);
					 if(cookie.getName().equals(ParamUtil.USER_ID)){
						 user_id=cookie.getValue();
					 }
				  }
				   if(user_id==null){//���user_idΪ�գ���û�д�cookie
					   //���û�����Ľ���url�����session 
					   request.getSession().setAttribute(ParamUtil.URL, srcName);
					   response.sendRedirect("login.jsp"); //�ض��򵽵�½����
				   }else{//���user_id��Ϊ�գ���ʾ����cookie
					   Login_user =  userBiz.selectById(Integer.parseInt(user_id));//ͨ�� user_id������û�
					   request.getSession().setAttribute(ParamUtil.LOGIN_USER, Login_user); //���û��������session
					   filterChain.doFilter(request, response);//����
				   }
			   }else{
				   //���session��Ϊ�գ���ֱ�ӷ���
				   filterChain.doFilter(request, response);
			   }
		}else{
			//������ʵ���Դ�����ҵ��˻�ҳ����߶���ҳ�棬����ֱ�ӷ���
			
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
