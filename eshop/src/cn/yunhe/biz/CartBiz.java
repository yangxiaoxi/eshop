package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Book;
import cn.yunhe.entity.Cart;
import cn.yunhe.util.CartMap;

public interface CartBiz {
	
	/**
	 * 添加购物车
	 * @param cart
	 * @return
	 */
	boolean  addCart(int user_id,CartMap cartMap);
	
	/**
	 * 检查购物车中是否有该本书
	 * @return
	 */
	boolean checkBook(int book_id,int user_id);
	
	/**
	 * 查询某个用户的某本书 的Cart
	 * @param book_id
	 * @param user_id
	 * @return
	 */
	Cart queryBook(int book_id,int user_id);
	/**
	 * 检查购物车中是否有该本书
	 * @return
	 */
	List<Book> selectBook(CartMap cartMap);
	
	/**
	 * 查出某个用户的购物车信息
	 * @param user_id
	 * @return
	 */
	List<Cart> queryCartByUser_id(int user_id);
	

}
