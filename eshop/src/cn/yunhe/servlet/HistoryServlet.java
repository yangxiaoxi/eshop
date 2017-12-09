package cn.yunhe.servlet;

import java.io.IOException;
import java.util.List;

import javax.jws.WebService;
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
import cn.yunhe.dao.BookDao;
import cn.yunhe.dao.impl.BookDaoImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Type;


@WebServlet("/history")
public class HistoryServlet  extends HttpServlet{
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
		 List<Type> allType =  typeBiz.queryAllType();//查询所有的类型
		 request.setAttribute("allType", allType);
		 
		
		 int pageSize = 2;//每页的数量
		 Cookie[] cookies =   request.getCookies();//获取保存在cookie中的浏览的书本id
		 String book_ids = "";
		    if(cookies!=null){
			    for (Cookie cookie : cookies) {
					if(cookie.getName().equals("userIds")){
						book_ids=cookie.getValue();
					}
		    }
			 
	      String currentPageStr = request.getParameter("currentPage");
		  int currentPage = currentPageStr==null||"".equals(currentPageStr)?1:Integer.parseInt(currentPageStr);//获取当前页
		  System.out.println("currengPage is" +currentPage);
		  request.setAttribute("currentPage", currentPage);
			 
	     List<Book> bookList= bookBiz.queryHistory(book_ids,currentPage,pageSize);//分页查询浏览记录
		 request.setAttribute("bookList", bookList);
		 
		 int count = bookBiz.queryHistory(book_ids);//查询浏览书籍的数量
		 request.setAttribute("count", count);
		 int totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
		 request.setAttribute("totalPage", totalPage);
		  int groupSize = 5;//组的大小，表示每组可以放5页
		  int totalGroup = totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//总的组数

		  //计算当前页属于第几组
		  int  currentPageIsWhichGroup = currentPage%groupSize==0?currentPage/groupSize:currentPage/groupSize+1;
		  //计算当前页所在的组从那一页开始 
		  int startPage = (currentPageIsWhichGroup-1)*groupSize+1;
		  request.setAttribute("startPage", startPage);
		  
		//就算当前页所在的组结束的位置      如果是最后一组，则结束的位置是总的页数
		  int endPage  =currentPageIsWhichGroup==totalGroup? totalPage: currentPageIsWhichGroup*groupSize;
		  request.setAttribute("endPage", endPage);
		 request.getRequestDispatcher("history.jsp").forward(request,response);
	}
	
}
}