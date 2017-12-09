package cn.yunhe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.biz.BookBiz;
import cn.yunhe.biz.TypeBiz;
import cn.yunhe.biz.impl.BookBizImpl;
import cn.yunhe.biz.impl.TypeBizImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Type;


@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
	private BookBiz bookBiz = new BookBizImpl();
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
	     String book_id  = request.getParameter("book_id");//获取书本的id
	     Book book =   bookBiz.queryById(Integer.parseInt(book_id));
	     request.setAttribute("book", book);
	     List<Type> typeLists =  typeBiz.queryAllType();
	     request.setAttribute("typeLists", typeLists);
	     
	     //将book_id存进cookie
	    String historyUserIds=book_id;
	    Cookie[] cookies =   request.getCookies();
	    if(cookies==null){
	    	historyUserIds = book_id;
	    }else{
		    for (Cookie cookie : cookies) {
				if(cookie.getName().equals("userIds")){
					historyUserIds=cookie.getValue();
					historyUserIds=historyUserIds+","+book_id;
				}
			}
	    }
	     Cookie cookie  = new Cookie("userIds", historyUserIds);
	     cookie.setMaxAge(7*24*60*60);
	     response.addCookie(cookie);
	     request.getRequestDispatcher("info.jsp").forward(request, response);
	     
	    
	    
	}
  
}
