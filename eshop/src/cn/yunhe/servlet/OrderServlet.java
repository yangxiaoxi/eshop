package cn.yunhe.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.yunhe.biz.CartBiz;
import cn.yunhe.biz.OrderBiz;
import cn.yunhe.biz.impl.CartBizImpl;
import cn.yunhe.biz.impl.OrderBizImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Order;
import cn.yunhe.entity.User;
import cn.yunhe.util.CartMap;
import cn.yunhe.util.ParamUtil;


@WebServlet("/order")
public class OrderServlet  extends HttpServlet{
	private CartBiz cartBiz = new CartBizImpl();
	private OrderBiz orderBiz = new OrderBizImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 String method = req.getParameter("method");
		 
		 if("orderFirst".equals(method)){
			 orderFirst(req,resp);
		 }
		 if("orderSecond".equals(method)){
			 orderSecond(req,resp);
		 }
		 if("orderPay".equals(method)){
			 orderPay(req,resp);
		 }
		 if("orderList".equals(method)){
			 orderList(req,resp);
		 }
		 if("cancleOrder".equals(method)){
			 cancleOrder(req,resp);
		 }
		 if("confirmOrder".equals(method)){
			 confirmOrder(req,resp);
		 }
	}

	//  1,  购物车列表 （cart.jsp） --->  订单列表(order.jsp)
	private void orderFirst(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		 CartMap cartMap =(CartMap)req.getSession().getAttribute(ParamUtil.CART_MAP);
		 List<Book> bookList = cartBiz.selectBook(cartMap);
		 req.getSession().setAttribute(ParamUtil.BOOKlIST, bookList);
		 resp.sendRedirect("order.jsp");
		
	}
	
	// 2,  订单列表（order.jsp）--->确定订单列表（orderfinal.jsp）
	private void orderSecond(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		
		String address = new String(req.getParameter("user_address").getBytes("iso-8859-1"),"utf-8");//订单收货地址
		req.setAttribute("address", address);
		String  user = new String(req.getParameter("user").getBytes("iso-8859-1"),"utf-8");//订单收货人
		req.setAttribute("user", user);
		String countStr = req.getParameter("count");
		 String book_idsStr = req.getParameter("book_id");
		 String[] nums = countStr.split(",");
		 String[] book_ids = book_idsStr.split(",");
		 CartMap cartMap =(CartMap) req.getSession().getAttribute(ParamUtil.CART_MAP);
		 for (int i=0;i<book_ids.length;i++) {
			 cartMap.put(Integer.parseInt(book_ids[i]), Integer.parseInt(nums[i]));
		 }
		JSONObject  jo = new JSONObject();
	    List<Book> bookList = cartBiz.selectBook(cartMap);
	    jo.put("book_list", bookList);
		req.getSession().setAttribute(ParamUtil.CART_MAP, cartMap);
		req.getSession().setAttribute("book_list",jo.toString());//将book_list 写入json
		req.setAttribute(ParamUtil.BOOKlIST, bookList);
		req.getRequestDispatcher("orderfinal.jsp").forward(req, resp);
	}
	
	 // 3,  确定订单列表（orderfinal.jsp）---> 提交订单列表（orderpay.jsp）
	private void orderPay(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		req.setCharacterEncoding("utf-8");
		//1, 订单号
		String orderNo="";
		String orderNo1 = req.getParameter("orderNo");
		//2,订单总价格;
		String order_price=req.getParameter("totalMoney");
		req.setAttribute("totalMoney", order_price);
		if(orderNo1==null){//如果订单号为空，则生成新的订单号
			orderNo = System.currentTimeMillis()+"";
			req.setAttribute("orderNo", orderNo);
			//2,用户id
			int user_id = ((User)req.getSession().getAttribute(ParamUtil.LOGIN_USER)).getUser_id();
			String address = new String(req.getParameter("user_address").getBytes("iso-8859-1"),"utf-8");//订单收货地址
			String phone = req.getParameter("phone");//订单联系方式
			String  user = new String(req.getParameter("user").getBytes("iso-8859-1"),"utf-8");//订单收货人
			String order_contentsJson =(String) req.getSession().getAttribute("book_list");//获取book_list的json字符串
			req.getSession().removeAttribute("book_list");
			
			Order order = new Order();
			order.setOrder_id(orderNo);
			order.setAddress(address);
			order.setPhone(phone);
			order.setUser_id(user_id);
			order.setPerson(user);
			order.setOrder_contents(order_contentsJson);
			order.setOrder_price(Double.parseDouble(order_price));
			 boolean isYes =  orderBiz.insertOrder(order);//下订单
			 if(isYes){
				 //如果下订单成功，设置一个下单成功的标记，等到session钝化的时候在清除session
				 req.getSession().setAttribute("orderSuccess","success");
				 req.getRequestDispatcher("orderpay.jsp").forward(req, resp);
			 }
		}else{//如果订单号不为空
			orderNo=orderNo1;
			req.setAttribute("orderNo", orderNo);
			req.getRequestDispatcher("orderpay.jsp").forward(req, resp);
		}
		
		
	}
	
	
	//4, 查询订单；列表列表
	private void orderList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		 int user_id =((User) req.getSession().getAttribute(ParamUtil.LOGIN_USER)).getUser_id();
		 List<Order> orderList =  orderBiz.selectByUser_id(user_id);
		  req.setAttribute("orderList", orderList);
		  req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
	}
	
	
	//5, 取消订单
		private void cancleOrder(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException  {
			 String order_id = req.getParameter("order_id");
			 boolean isYes  =  orderBiz.updateStatus(order_id);
			 if(isYes){
				 resp.sendRedirect("order?method=orderList");
			 }
		}
		
		
		//6, 确认收货；
		private void confirmOrder(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException  {
			 String order_id = req.getParameter("order_id");
			 boolean isYes  =  orderBiz.updateStatus_Recevie(order_id);
			 if(isYes){
				 resp.sendRedirect("order?method=orderList");
			 }
		}
}
