package cn.yunhe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.yunhe.biz.TypeBiz;
import cn.yunhe.biz.impl.TypeBizImpl;
import cn.yunhe.entity.Type;


@WebServlet("/typeServlet")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TypeBiz typeBiz = new TypeBizImpl();
	
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
		 String method = request.getParameter("method");
		 if("top".equals(method)){
			 topType(request,response);
		 }
		 
		 if("all".equals(method)){
			 allType(request,response);
		 }
		 
	}
	private void allType(HttpServletRequest request,
			HttpServletResponse response) {
			  List<Type> typeLsit = typeBiz.queryAllType();
			  JSONObject jo = new JSONObject();
			  jo.put("typeLsit", typeLsit);
			  try {
				response.getWriter().print(jo.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}
	private void topType(HttpServletRequest request,
			HttpServletResponse response) {
	  List<Type> typeLsit = 	typeBiz.queryTopType();
	  JSONObject jo = new JSONObject();
	  jo.put("typeLsit", typeLsit);
	  try {
		response.getWriter().print(jo.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	 

}
