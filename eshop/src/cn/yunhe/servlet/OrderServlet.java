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

	//  1,  ���ﳵ�б� ��cart.jsp�� --->  �����б�(order.jsp)
	private void orderFirst(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		 CartMap cartMap =(CartMap)req.getSession().getAttribute(ParamUtil.CART_MAP);
		 List<Book> bookList = cartBiz.selectBook(cartMap);
		 req.getSession().setAttribute(ParamUtil.BOOKlIST, bookList);
		 resp.sendRedirect("order.jsp");
		
	}
	
	// 2,  �����б�order.jsp��--->ȷ�������б�orderfinal.jsp��
	private void orderSecond(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		
		String address = new String(req.getParameter("user_address").getBytes("iso-8859-1"),"utf-8");//�����ջ���ַ
		req.setAttribute("address", address);
		String  user = new String(req.getParameter("user").getBytes("iso-8859-1"),"utf-8");//�����ջ���
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
		req.getSession().setAttribute("book_list",jo.toString());//��book_list д��json
		req.setAttribute(ParamUtil.BOOKlIST, bookList);
		req.getRequestDispatcher("orderfinal.jsp").forward(req, resp);
	}
	
	 // 3,  ȷ�������б�orderfinal.jsp��---> �ύ�����б�orderpay.jsp��
	private void orderPay(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		req.setCharacterEncoding("utf-8");
		//1, ������
		String orderNo="";
		String orderNo1 = req.getParameter("orderNo");
		//2,�����ܼ۸�;
		String order_price=req.getParameter("totalMoney");
		req.setAttribute("totalMoney", order_price);
		if(orderNo1==null){//���������Ϊ�գ��������µĶ�����
			orderNo = System.currentTimeMillis()+"";
			req.setAttribute("orderNo", orderNo);
			//2,�û�id
			int user_id = ((User)req.getSession().getAttribute(ParamUtil.LOGIN_USER)).getUser_id();
			String address = new String(req.getParameter("user_address").getBytes("iso-8859-1"),"utf-8");//�����ջ���ַ
			String phone = req.getParameter("phone");//������ϵ��ʽ
			String  user = new String(req.getParameter("user").getBytes("iso-8859-1"),"utf-8");//�����ջ���
			String order_contentsJson =(String) req.getSession().getAttribute("book_list");//��ȡbook_list��json�ַ���
			req.getSession().removeAttribute("book_list");
			
			Order order = new Order();
			order.setOrder_id(orderNo);
			order.setAddress(address);
			order.setPhone(phone);
			order.setUser_id(user_id);
			order.setPerson(user);
			order.setOrder_contents(order_contentsJson);
			order.setOrder_price(Double.parseDouble(order_price));
			 boolean isYes =  orderBiz.insertOrder(order);//�¶���
			 if(isYes){
				 //����¶����ɹ�������һ���µ��ɹ��ı�ǣ��ȵ�session�ۻ���ʱ�������session
				 req.getSession().setAttribute("orderSuccess","success");
				 req.getRequestDispatcher("orderpay.jsp").forward(req, resp);
			 }
		}else{//��������Ų�Ϊ��
			orderNo=orderNo1;
			req.setAttribute("orderNo", orderNo);
			req.getRequestDispatcher("orderpay.jsp").forward(req, resp);
		}
		
		
	}
	
	
	//4, ��ѯ�������б��б�
	private void orderList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException  {
		 int user_id =((User) req.getSession().getAttribute(ParamUtil.LOGIN_USER)).getUser_id();
		 List<Order> orderList =  orderBiz.selectByUser_id(user_id);
		  req.setAttribute("orderList", orderList);
		  req.getRequestDispatcher("orderlist.jsp").forward(req, resp);
	}
	
	
	//5, ȡ������
		private void cancleOrder(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException  {
			 String order_id = req.getParameter("order_id");
			 boolean isYes  =  orderBiz.updateStatus(order_id);
			 if(isYes){
				 resp.sendRedirect("order?method=orderList");
			 }
		}
		
		
		//6, ȷ���ջ���
		private void confirmOrder(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException  {
			 String order_id = req.getParameter("order_id");
			 boolean isYes  =  orderBiz.updateStatus_Recevie(order_id);
			 if(isYes){
				 resp.sendRedirect("order?method=orderList");
			 }
		}
}
