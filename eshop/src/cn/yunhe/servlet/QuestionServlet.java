package cn.yunhe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.yunhe.biz.QuestionBiz;
import cn.yunhe.biz.impl.QuestionBizImpl;
import cn.yunhe.entity.Question;
import cn.yunhe.entity.User;
import cn.yunhe.util.ParamUtil;


@WebServlet("/question")
public class QuestionServlet extends HttpServlet {
    private QuestionBiz questionBiz = new QuestionBizImpl();
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doPost(req, resp);
	}
	 @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 JSONObject jo = new JSONObject();
		User user = (User) request.getSession().getAttribute(ParamUtil.LOGIN_USER);
		 if(user!=null){
			 jo.put("user_id", user.getUser_id());
		 }
		 List<Question> qLists =  questionBiz.querytAll();
		 jo.put("qLists", qLists);	
		 response.getWriter().print(jo.toString());
	 }

}
