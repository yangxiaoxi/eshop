package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.biz.UserBiz;
import cn.yunhe.biz.impl.UserBizImpl;
import cn.yunhe.entity.User;
import cn.yunhe.util.ParamUtil;


@WebServlet("/verifyEmailServlet")
public class VerifyEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz usreBiz = new UserBizImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//激活邮箱
		String userId = request.getParameter("userId");
	    boolean isYes =  usreBiz.updateActicvated(Integer.parseInt(userId));//修改用户的激活码为可登陆状态
		System.out.println("修改状态码");
	    if(isYes){
		 System.out.println("激活成功");
		 User user =usreBiz.selectById(Integer.parseInt(userId));//通过用户id查出用户对象，存近session
		 request.getSession().setAttribute(ParamUtil.LOGIN_USER, user);
			//激活成功，用户则直接重定向到index.jsp
		 response.sendRedirect("index.jsp");
	 } 		 
	}
	

}
