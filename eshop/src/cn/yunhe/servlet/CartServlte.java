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
	 * 删除session中的购物车中的某本书
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
	 * 查询购物车 列表
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
	 * 添加购物车
	 * @param request
	 * @param response
	 */
	private void addCart(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//将需要添加的购物车信息存入数据库
	 
		String book_id = request.getParameter("book_id");//获取添加的书籍
		CartMap cartMap =(CartMap) request.getSession().getAttribute(ParamUtil.CART_MAP);
		if(cartMap==null){
			//1,如果map为空，则创建一个map，并把书本id和数量天剑进入map中
			cartMap = new CartMap();
			cartMap.put(Integer.parseInt(book_id), 1);
		}else{
			//2.如果map不空，则遍历map，看是否有书本id在mapzhong
			int key = Integer.parseInt(book_id);
			 boolean isExit =  cartMap.containsKey(key);//是否包含该本书的id
			 if(isExit){//2.1如果存在
				 cartMap.put(key,cartMap.get(key)+1);
			 }else{
				 //2.2如果map中不存在，则存入map
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
