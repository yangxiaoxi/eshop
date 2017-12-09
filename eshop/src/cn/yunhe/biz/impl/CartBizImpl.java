package cn.yunhe.biz.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.yunhe.biz.CartBiz;
import cn.yunhe.dao.BookDao;
import cn.yunhe.dao.CartDao;
import cn.yunhe.dao.impl.BookDaoImpl;
import cn.yunhe.dao.impl.CartDaoImpl;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.Cart;
import cn.yunhe.util.CartMap;

public class CartBizImpl implements CartBiz {

	private CartDao cartDao = new CartDaoImpl();
	private BookDao bookDao = new BookDaoImpl();

	@Override
	public boolean addCart(int user_id, CartMap cartMap) {
		boolean flag = false;
		flag = 	cartDao.addCart(user_id, cartMap);
		return flag;
	}

	@Override
	public boolean checkBook(int book_id, int user_id) {
		Cart cart = cartDao.checkBook(book_id, user_id);
		boolean flag = false;
		if (cart != null) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Cart queryBook(int book_id, int user_id) {
		return cartDao.checkBook(book_id, user_id);
	}

	@Override
	public List<Book> selectBook(CartMap cartMap) {
		List<Book> bookList = new ArrayList<Book>();
		if (cartMap != null&&cartMap.size()!=0) {// 如果map不为空
			Collection<Integer> keys = cartMap.keySet();// 取出map中的所有key
			Iterator<Integer> it = keys.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				Integer key = (Integer) it.next();// book_id
				sb.append(key+",");
			}
			sb.deleteCharAt(sb.length()-1);
			 bookList = bookDao.queryBookForCart(sb.toString());
			 for (Book book : bookList) {
				 if(book!=null){
					 int count = cartMap.get(book.getBook_id());
					 book.setBook_count(count);
				 }
			}
		} 
		return bookList;
	}

	@Override
	public List<Cart> queryCartByUser_id(int user_id) {
		return cartDao.queryCartByUser_id(user_id);
	}

}
