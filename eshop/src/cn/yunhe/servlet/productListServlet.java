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


@WebServlet("/product")
public class productListServlet  extends HttpServlet{
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
		 String type_id = request.getParameter("type_id");
		 String currentPageStr = request.getParameter("currentPage");
		 int currentPage = currentPageStr==null||"".equals(currentPageStr)?1:Integer.parseInt(currentPageStr);
		 request.setAttribute("currentPage", currentPage);
		 int pageSize =8;
		 String type_name = typeBiz.queryTypeNameById(Integer.parseInt(type_id));
		 request.setAttribute("type_name", type_name);
		 request.setAttribute("type_id", type_id);
		  List<Type> typeLists =  typeBiz.queryAllType();
		  List<Type> typeChilds =  typeBiz.queryChileType(Integer.parseInt(type_id));
		  List<Book> bookList = bookBiz.queryByTypeId(Integer.parseInt(type_id),currentPage,pageSize);
		  int count = bookBiz.queryByTypeId(Integer.parseInt(type_id));
		  int totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
		  
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
		  
		  
		  request.setAttribute("totalPage", totalPage);
		  request.setAttribute("count", count);
		  request.setAttribute("bookList", bookList);
		  request.setAttribute("typeChilds", typeChilds);
		 request.setAttribute("typeLists", typeLists);
		 request.getRequestDispatcher("productlist.jsp").forward(request, response);;
		 
	}
	

}
