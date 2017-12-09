package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Cart;
import cn.yunhe.util.CartMap;

public interface CartDao {
	
	/**
	 * 检查购物车中是否有该本书
	 * @return
	 */
	Cart checkBook(int book_id,int user_id);
	
	 /**
	  * 修改购物车中已经存在书本的数量
	 * @param cart
	 * @return
	 */
	boolean updatCart(Cart cart);
	
	
	/**
	 * 添加购物车中没有的书本
	 * @param cart
	 * @return
	 */
	boolean addCart(int user_id,CartMap cartmap);
	
	/**
	 * 查出某个用户的购物车信息
	 * @param user_id
	 * @return
	 */
	List<Cart> queryCartByUser_id(int user_id);
	
	


}
