package cn.yunhe.servlet;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.biz.TypeBiz;
import cn.yunhe.biz.impl.TypeBizImpl;
import cn.yunhe.entity.Type;

@WebServlet("/index")
public class indexServet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final TypeBiz typeBiz = new TypeBizImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	     		throws ServletException, IOException {
	    doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//List<Type> typeTopLists  =   typeBiz.queryTopType();
		//List<Type> typeAllLists  =   typeBiz.queryAllType();
		//request.setAttribute("typeTopLists", typeTopLists);
		//request.setAttribute("typeAllLists", typeAllLists);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	

}
