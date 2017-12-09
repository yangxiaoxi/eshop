package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Order;

public interface OrderDao {
	
	/**
	 * ���붩��
	 * @param order
	 * @return
	 */
	boolean insertOrder (Order order);
	
	/**
	 * ��ѯĳ���û��Ķ�����Ϣ
	 * @param user_id
	 * @return
	 */
	List<Order> selectByUser_id(int user_id);
	
	/**
	 * �޸Ķ���Ϊ��֧��
	 * @param order_id
	 * @return
	 */
	boolean updateStatus_Pay(String order_id);
	/**
	 * �޸Ķ���Ϊ���ջ�
	 * @param order_id
	 * @return
	 */
	boolean updateStatus_Recevie(String order_id);
	/**
	 * �޸Ķ���Ϊ��ȡ��
	 * @param order_id
	 * @return
	 */
	boolean updateStatus(String order_id);


}
