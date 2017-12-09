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
		//��������
		String userId = request.getParameter("userId");
	    boolean isYes =  usreBiz.updateActicvated(Integer.parseInt(userId));//�޸��û��ļ�����Ϊ�ɵ�½״̬
		System.out.println("�޸�״̬��");
	    if(isYes){
		 System.out.println("����ɹ�");
		 User user =usreBiz.selectById(Integer.parseInt(userId));//ͨ���û�id����û����󣬴��session
		 request.getSession().setAttribute(ParamUtil.LOGIN_USER, user);
			//����ɹ����û���ֱ���ض���index.jsp
		 response.sendRedirect("index.jsp");
	 } 		 
	}
	

}
