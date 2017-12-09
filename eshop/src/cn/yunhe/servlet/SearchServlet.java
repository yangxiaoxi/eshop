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
		  String book_name = request.getParameter("textfield");//search�����������Ϣ
		  request.setAttribute("book_name", book_name);
		  String keyWordStr = request.getParameter("textfield2");//����ؼ���
		  request.setAttribute("keyWordStr", keyWordStr);
		  String optionTypeStr = request.getParameter("select");//��ȡ��ѡ�е�����
		  request.setAttribute("optionTypeStr", optionTypeStr);
		  String keyWord =  keyWordStr==null?"":keyWordStr;//�ؼ���
		  int type = optionTypeStr==null||"".equals(optionTypeStr)?-1:Integer.parseInt(optionTypeStr);//ѡ�������
		  List<Type> allType =  typeBiz.queryAllType();//��Ѷ���е�����
		  request.setAttribute("allType", allType); 
		  
		  
		  
		  String currentPageStr = request.getParameter("currentPage");//��ȡ��ǰҳ
		  int currentPage = currentPageStr==null||"".equals(currentPageStr)?1:Integer.parseInt(currentPageStr);
		  request.setAttribute("currentPage", currentPage); 
		  int pageSize = 4;//ÿҳ����
		  List<Book> bookList = bookBiz.queryLike(book_name,keyWord,type,currentPage,pageSize) ;//��������ҳ������е���Ϣģ����ѯ
		  request.setAttribute("bookList", bookList); 
		  int count =bookBiz.countSearch(book_name, keyWord, type); // ����������ѯ�� ��¼��
		  request.setAttribute("count", count);
		  int totalPage= count%pageSize==0?count/pageSize:count/pageSize+1;//�����ܼ�¼��������ҳ��
		  request.setAttribute("totalPage", totalPage);
 
		  int groupSize = 5;//��Ĵ�С����ʾÿ����Է�3ҳ
		  int totalGroup = totalPage%groupSize==0?totalPage/groupSize:totalPage/groupSize+1;//�ܵ�����


		  //���㵱ǰҳ���ڵڼ���
		  int  currentPageIsWhichGroup = currentPage%groupSize==0?currentPage/groupSize:currentPage/groupSize+1;
		  //���㵱ǰҳ���ڵ������һҳ��ʼ 
		  int startPage = (currentPageIsWhichGroup-1)*groupSize+1;
		  request.setAttribute("startPage", startPage);
		  //���㵱ǰҳ���ڵ��������λ��      ��������һ�飬�������λ�����ܵ�ҳ��
		  int endPage  =currentPageIsWhichGroup==totalGroup? totalPage: currentPageIsWhichGroup*groupSize;
		  request.setAttribute("endPage", endPage);
		  request.getRequestDispatcher("search.jsp").forward(request, response);
	}
	

}
