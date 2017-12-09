package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Book;
import cn.yunhe.entity.Cart;
import cn.yunhe.util.CartMap;

public interface CartBiz {
	
	/**
	 * ��ӹ��ﳵ
	 * @param cart
	 * @return
	 */
	boolean  addCart(int user_id,CartMap cartMap);
	
	/**
	 * ��鹺�ﳵ���Ƿ��иñ���
	 * @return
	 */
	boolean checkBook(int book_id,int user_id);
	
	/**
	 * ��ѯĳ���û���ĳ���� ��Cart
	 * @param book_id
	 * @param user_id
	 * @return
	 */
	Cart queryBook(int book_id,int user_id);
	/**
	 * ��鹺�ﳵ���Ƿ��иñ���
	 * @return
	 */
	List<Book> selectBook(CartMap cartMap);
	
	/**
	 * ���ĳ���û��Ĺ��ﳵ��Ϣ
	 * @param user_id
	 * @return
	 */
	List<Cart> queryCartByUser_id(int user_id);
	

}
