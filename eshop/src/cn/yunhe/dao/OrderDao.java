package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Order;

public interface OrderDao {
	
	/**
	 * 插入订单
	 * @param order
	 * @return
	 */
	boolean insertOrder (Order order);
	
	/**
	 * 查询某个用户的订单信息
	 * @param user_id
	 * @return
	 */
	List<Order> selectByUser_id(int user_id);
	
	/**
	 * 修改订单为已支付
	 * @param order_id
	 * @return
	 */
	boolean updateStatus_Pay(String order_id);
	/**
	 * 修改订单为以收货
	 * @param order_id
	 * @return
	 */
	boolean updateStatus_Recevie(String order_id);
	/**
	 * 修改订单为已取消
	 * @param order_id
	 * @return
	 */
	boolean updateStatus(String order_id);


}
