package cn.yunhe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.biz.BookBiz;
import cn.yunhe.biz.CartBiz;
import cn.yunhe.biz.impl.BookBizImpl;
import cn.yunhe.biz.impl.CartBizImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Cart;
import cn.yunhe.entity.User;
import cn.yunhe.util.CartMap;
import cn.yunhe.util.ParamUtil;


@WebServlet("/cart")
public class CartServlte  extends HttpServlet{
	private CartBiz cartBiz = new CartBizImpl();
	private BookBiz  bookBiz = new BookBizImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("add".equals(method)){
			 addCart(request,response);
		}
		if("select".equals(method)){
			selectCart(request,response);
		}
		if("delete".equals(method)){
			deleteCart(request,response);
		}
	}
	
	/**
	 * ɾ��session�еĹ��ﳵ�е�ĳ����
	 * @param request
	 * @param response
	 */
	private void deleteCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String book_id = request.getParameter("book_id");
	    CartMap cartMap =(CartMap)request.getSession().getAttribute(ParamUtil.CART_MAP);
		if(cartMap.containsKey(Integer.parseInt(book_id))){
			cartMap.remove(Integer.parseInt(book_id));
		}
		request.getSession().setAttribute(ParamUtil.CART_MAP, cartMap);
		response.sendRedirect("cart?method=select");
	}


	/**
	 * ��ѯ���ﳵ �б�
	 * @param request
	 * @param response
	 */
	private void selectCart(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException  {
		 CartMap cartMap =(CartMap)request.getSession().getAttribute(ParamUtil.CART_MAP);
		 List<Book> bookList = cartBiz.selectBook(cartMap);
		 request.getSession().setAttribute(ParamUtil.BOOKlIST, bookList);
		 response.sendRedirect("cart.jsp");
	}


	/**
	 * ��ӹ��ﳵ
	 * @param request
	 * @param response
	 */
	private void addCart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//����Ҫ��ӵĹ��ﳵ��Ϣ�������ݿ�
	 
		String book_id = request.getParameter("book_id");//��ȡ��ӵ��鼮
		CartMap cartMap =(CartMap) request.getSession().getAttribute(ParamUtil.CART_MAP);
		if(cartMap==null){
			//1,���mapΪ�գ��򴴽�һ��map�������鱾id�������콣����map��
			cartMap = new CartMap();
			cartMap.put(Integer.parseInt(book_id), 1);
		}else{
			//2.���map���գ������map�����Ƿ����鱾id��mapzhong
			int key = Integer.parseInt(book_id);
			 boolean isExit =  cartMap.containsKey(key);//�Ƿ�����ñ����id
			 if(isExit){//2.1�������
				 cartMap.put(key,cartMap.get(key)+1);
			 }else{
				 //2.2���map�в����ڣ������map
				 cartMap.put(key,1); 
			 }
		}
		request.getSession().setAttribute(ParamUtil.CART_MAP,cartMap);
		try {
			response.sendRedirect("cart?method=select");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
