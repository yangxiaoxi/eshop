package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Cart;
import cn.yunhe.util.CartMap;

public interface CartDao {
	
	/**
	 * ��鹺�ﳵ���Ƿ��иñ���
	 * @return
	 */
	Cart checkBook(int book_id,int user_id);
	
	 /**
	  * �޸Ĺ��ﳵ���Ѿ������鱾������
	 * @param cart
	 * @return
	 */
	boolean updatCart(Cart cart);
	
	
	/**
	 * ��ӹ��ﳵ��û�е��鱾
	 * @param cart
	 * @return
	 */
	boolean addCart(int user_id,CartMap cartmap);
	
	/**
	 * ���ĳ���û��Ĺ��ﳵ��Ϣ
	 * @param user_id
	 * @return
	 */
	List<Cart> queryCartByUser_id(int user_id);
	
	


}
