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
		 List<Type> allType =  typeBiz.queryAllType();//��ѯ���е�����
		 request.setAttribute("allType", allType);
		 
		
		 int pageSize = 2;//ÿҳ������
		 Cookie[] cookies =   request.getCookies();//��ȡ������cookie�е�������鱾id
		 String book_ids = "";
		    if(cookies!=null){
			    for (Cookie cookie : cookies) {
					if(cookie.getName().equals("userIds")){
						book_ids=cookie.getValue();
					}
		    }
			 
	      String currentPageStr = request.getParameter("currentPage");
		  int currentPage = currentPageStr==null||"".equals(currentPageStr)?1:Integer.parseInt(currentPageStr);//��ȡ��ǰҳ
		  System.out.println("currengPage is" +currentPage);
		  request.setAttribute("currentPage", currentPage);
			 
	     List<Book> bookList= bookBiz.queryHistory(book_ids,currentPage,pageSize);//��ҳ��ѯ�����¼
		 request.setAttribute("bookList", bookList);
		 
		 int count = bookBiz.queryHistory(book_ids);//��ѯ����鼮������
		 request.setAttribute("count", count);
		 int totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
		 request.setAttribute("totalPage", totalPage);
		  int groupSize = 5;//��Ĵ�С����ʾÿ����Է�5ҳ
		  int totalGroup = totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//�ܵ�����

		  //���㵱ǰҳ���ڵڼ���
		  int  currentPageIsWhichGroup = currentPage%groupSize==0?currentPage/groupSize:currentPage/groupSize+1;
		  //���㵱ǰҳ���ڵ������һҳ��ʼ 
		  int startPage = (currentPageIsWhichGroup-1)*groupSize+1;
		  request.setAttribute("startPage", startPage);
		  
		//���㵱ǰҳ���ڵ��������λ��      ��������һ�飬�������λ�����ܵ�ҳ��
		  int endPage  =currentPageIsWhichGroup==totalGroup? totalPage: currentPageIsWhichGroup*groupSize;
		  request.setAttribute("endPage", endPage);
		 request.getRequestDispatcher("history.jsp").forward(request,response);
	}
	
}
}