package cn.yunhe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.biz.BookBiz;
import cn.yunhe.biz.TypeBiz;
import cn.yunhe.biz.impl.BookBizImpl;
import cn.yunhe.biz.impl.TypeBizImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Type;


@WebServlet("/search")
public class SearchServlet   extends HttpServlet{
	private TypeBiz typeBiz = new TypeBizImpl();
	private BookBiz bookBiz = new BookBizImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8");
		  String book_name = request.getParameter("textfield");//search框中输入的信息
		  request.setAttribute("book_name", book_name);
		  String keyWordStr = request.getParameter("textfield2");//输入关键字
		  request.setAttribute("keyWordStr", keyWordStr);
		  String optionTypeStr = request.getParameter("select");//获取被选中的类型
		  request.setAttribute("optionTypeStr", optionTypeStr);
		  String keyWord =  keyWordStr==null?"":keyWordStr;//关键字
		  int type = optionTypeStr==null||"".equals(optionTypeStr)?-1:Integer.parseInt(optionTypeStr);//选择的类型
		  List<Type> allType =  typeBiz.queryAllType();//传讯所有的类型
		  request.setAttribute("allType", allType); 
		  
		  
		  
		  String currentPageStr = request.getParameter("currentPage");//获取当前页
		  int currentPage = currentPageStr==null||"".equals(currentPageStr)?1:Integer.parseInt(currentPageStr);
		  request.setAttribute("currentPage", currentPage); 
		  int pageSize = 4;//每页几条
		  List<Book> bookList = bookBiz.queryLike(book_name,keyWord,type,currentPage,pageSize) ;//多条件分页输入框中的信息模糊查询
		  request.setAttribute("bookList", bookList); 
		  int count =bookBiz.countSearch(book_name, keyWord, type); // 根据条件查询总 记录数
		  request.setAttribute("count", count);
		  int totalPage= count%pageSize==0?count/pageSize:count/pageSize+1;//根据总记录数计算总页数
		  request.setAttribute("totalPage", totalPage);
 
		  int groupSize = 5;//组的大小，表示每组可以放3页
		  int totalGroup = totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//总的组数


		  //计算当前页属于第几组
		  int  currentPageIsWhichGroup = currentPage%groupSize==0?currentPage/groupSize:currentPage/groupSize+1;
		  //计算当前页所在的组从那一页开始 
		  int startPage = (currentPageIsWhichGroup-1)*groupSize+1;
		  request.setAttribute("startPage", startPage);
		  //就算当前页所在的组结束的位置      如果是最后一组，则结束的位置是总的页数
		  int endPage  =currentPageIsWhichGroup==totalGroup? totalPage: currentPageIsWhichGroup*groupSize;
		  request.setAttribute("endPage", endPage);
		  request.getRequestDispatcher("search.jsp").forward(request, response);
	}
	

}
