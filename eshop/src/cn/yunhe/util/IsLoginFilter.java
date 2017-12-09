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
		String urlStr = request.getRequestURI();//获取访问资源的绝对路径
		String [] urlArray = urlStr.split("/");//切割获取的绝对资源路径，得到路劲数组
		String srcName = urlArray[urlArray.length-1]; //获取路劲数组最后一个路劲，就是访问资源的名称
		
		
		//如果访问的资源是我的账户页面或者订单页面，则检查用户是否登陆
		if(srcName.equals("my.jsp")||srcName.equals("order.jsp")){
			  //1,首先减产session是否用用户对象
			   User Login_user = (User) request.getSession().getAttribute(ParamUtil.LOGIN_USER);
			   if(Login_user==null){
				 //2,如果session，则检查cookie 
				   Cookie[] cookies = request.getCookies();//获取cookie；
				   String user_id = null;
				   for (Cookie cookie : cookies) {//遍历cookie，查找是否保存了user_id(记住登陆状态);
					 if(cookie.getName().equals(ParamUtil.USER_ID)){
						 user_id=cookie.getValue();
					 }
				  }
				   if(user_id==null){//如果user_id为空，则没有存cookie
					   //将用户请求的界面url保存进session 
					   request.getSession().setAttribute(ParamUtil.URL, srcName);
					   response.sendRedirect("login.jsp"); //重定向到登陆界面
				   }else{//如果user_id不为空，表示存了cookie
					   Login_user =  userBiz.selectById(Integer.parseInt(user_id));//通过 user_id茶查找用户
					   request.getSession().setAttribute(ParamUtil.LOGIN_USER, Login_user); //将用户对象存入session
					   filterChain.doFilter(request, response);//放行
				   }
			   }else{
				   //如果session不为空，则直接放行
				   filterChain.doFilter(request, response);
			   }
		}else{
			//如果访问的资源不是我的账户页面或者订单页面，则则直接放行
			
			filterChain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
