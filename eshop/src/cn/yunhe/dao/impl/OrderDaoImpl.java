package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.yunhe.dao.OrderDao;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Order;
import cn.yunhe.util.JDBCutil;
import cn.yunhe.util.ROMjdbcUtil;

public class OrderDaoImpl implements OrderDao{

	@Override
	public boolean insertOrder(Order order) {
		String sql = "insert into tb_order"
				+ "(order_id,user_id,person,address,phone,order_price,order_contents,order_date) "
				+ " values(?,?,?,?,?,?,?,now())";
		Object[] objs = {order.getOrder_id(),order.getUser_id(),order.getPerson(),order.getAddress(),order.getPhone(),order.getOrder_price(),order.getOrder_contents()};
		boolean isAdd = false;
		try {
			JDBCutil.connection();
			 int i = JDBCutil.update(sql, objs);
			 isAdd = i==-1?false:true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isAdd;
	}

	@Override
	public List<Order> selectByUser_id(int user_id) {
		String sql = "select order_id,order_price,order_contents,person,order_date,status_send,status_pay,status_receive,status from tb_order where user_id = ?";
		List<Order> lists = new ArrayList<Order>();
	try {
		JDBCutil.connection();
		ResultSet rs = JDBCutil.query(sql,new Object[]{user_id});	
		while (rs.next()) {
			Order order = new Order();
			order.setOrder_id(rs.getString("order_id"));
			order.setOrder_price(rs.getDouble("order_price"));
			order.setOrder_contents(rs.getString("order_contents"));
			order.setPerson(rs.getString("person"));
			order.setOrder_date(new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").format(rs.getDate("order_date")));
			order.setStatus(rs.getInt("status"));
			order.setStatus_pay(rs.getInt("status_pay"));
			order.setStatus_send(rs.getInt("status_send"));
			order.setStatus_receive(rs.getInt("status_receive"));
			lists.add(order);
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			JDBCutil.closeSource();
		} catch (SQLException e) {
			 e.printStackTrace();
	   }
	 }
	return lists;
  }

	@Override
	public boolean updateStatus_Pay(String order_id) {
		String sql = "update tb_order set status_pay=1,status_send=0,status_receive=0 ,status=1 where order_id=?";
		Object[] objs = {order_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (SQLException e) {
			 e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		}
		return flag;

	}

	@Override
	public boolean updateStatus_Recevie(String order_id) {
		String sql = "update tb_order set status_pay=1,status_send=1,status_receive=1, status=1 where order_id=?";
		Object[] objs = {order_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (SQLException e) {
			 e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean updateStatus(String order_id) {
		String sql = "update tb_order set  status= 0 where order_id=?";
		Object[] objs = {order_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (SQLException e) {
			 e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				 e.printStackTrace();
			}
		}
		return flag;
	}
}
