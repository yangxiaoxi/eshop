package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.yunhe.util.ParamUtil;
import cn.yunhe.util.VerifyCodeUtilByYock;

@WebServlet("/verifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger.getLogger(VerifyCodeServlet.class).debug("进入验证码");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
	    
		 String verifyCode = VerifyCodeUtilByYock.generateVerifyCode(4);//生成思维随机的验证码
	      request.getSession().setAttribute(ParamUtil.VERIFY_CODE, verifyCode);
		 VerifyCodeUtilByYock.outputImageStream(200,50,response.getOutputStream(),verifyCode);	
	}
}
