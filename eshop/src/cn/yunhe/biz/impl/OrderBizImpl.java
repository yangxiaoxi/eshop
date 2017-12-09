package cn.yunhe.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import cn.yunhe.biz.OrderBiz;
import cn.yunhe.dao.OrderDao;
import cn.yunhe.dao.impl.OrderDaoImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Order;

public class OrderBizImpl implements OrderBiz {
	private OrderDao orderDao = new OrderDaoImpl();

	@Override
	public boolean insertOrder(Order order) {
		return orderDao.insertOrder(order);
	}

	@Override
	public List<Order> selectByUser_id(int user_id) {
		// ĳ���û������ж�������
		List<Order> orderList = orderDao.selectByUser_id(user_id);
		for (Order order : orderList) {
			String contentJson  =order.getOrder_contents();
			JSONObject jo  = JSONObject.fromObject(contentJson);//���������ݿ�ȡ������json���ݣ�order_contents��
			JSONArray jsonArray =  jo.getJSONArray("book_list");//json�� ���������
			List<Book> bookList = new ArrayList<Book>();
			for(int i=0;i<jsonArray.size();i++){
				JSONObject  jsonObject =  jsonArray.getJSONObject(i);//ÿ��json������ ��ÿ��json����book����
				Book book = new Book();
				book.setBook_name(jsonObject.getString("book_name"));
				book.setBook_id(jsonObject.getInt("book_id"));
				book.setBook_price(jsonObject.getDouble("book_price"));
				book.setBook_price_old(jsonObject.getDouble("book_price_old"));
				book.setBook_count(jsonObject.getInt("book_count"));
				bookList.add(book);
			}
			order.setBookList(bookList);//��ÿ�����������bookList���Ը�ֵ
		}
		return orderList;
	}

	@Override
	public boolean updateStatus_Pay(String order_id) {
		return orderDao.updateStatus_Pay(order_id);
	}

	@Override
	public boolean updateStatus_Recevie(String order_id) {
		return orderDao.updateStatus_Recevie(order_id);
	}

	@Override
	public boolean updateStatus(String order_id) {
		return orderDao.updateStatus(order_id);
	}

}
