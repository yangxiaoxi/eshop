package cn.yunhe.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import sun.org.mozilla.javascript.internal.ObjArray;
import cn.yunhe.dao.CartDao;
import cn.yunhe.entity.Cart;
import cn.yunhe.entity.Ip;
import cn.yunhe.util.CartMap;
import cn.yunhe.util.JDBCutil;
import cn.yunhe.util.ROMjdbcUtil;

public class CartDaoImpl implements CartDao,Serializable{

	@Override
	public Cart checkBook(int book_id,int user_id) {
		String sql = "select t1.* ,book_price,book_price_old ,book_name from tb_cart t1"
				+ "inner join tb_book  t2  on t1.book_id = t2.book_id "
				+ " where t1.book_id=? AND user_id=?";
		 Cart cart = null;
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, new Object[]{book_id,user_id});
			 if(rs.next()){
				 cart = new Cart();
				 cart.setBook_id(rs.getInt("book_id"));
				 cart.setCart_id(rs.getInt("cart_id"));
				 cart.setNum(rs.getInt("num"));
				 cart.setUser_id(rs.getInt("user_id"));
				 cart.setBook_name(rs.getString("boo_name"));
				 cart.setBook_price(rs.getDouble("book_price"));
				 cart.setBook_price_old(rs.getDouble("book_price_old"));
			 }
		} catch (ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
		}finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cart;
	}

	@Override
	public boolean updatCart(Cart cart) {
		String sql = "update  tb_cart set num=?  where book_id=? and user_id=? ";
		Object[] objs = new Object[]{cart.getNum(),cart.getBook_id(),cart.getUser_id()};
		boolean flag = false;
		try {
			JDBCutil.connection();
		  int i = JDBCutil.update(sql, objs);
		  	if(i!=-1){
		  		flag = true;
		  	}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	@Override
	public boolean addCart(int user_id,CartMap cartMap) {
		boolean flag  = false;

		String [] sqls = new String[2];
		sqls[0]="delete from tb_cart where user_id = "+user_id;//删除sql语句
	    if (cartMap!=null&&cartMap.size()!=0) {
			Set<Integer> allkeys = cartMap.keySet();//获取购物车中的所有key（book_id）
			StringBuffer sbSql = new StringBuffer(
					"insert into tb_cart (book_id,num,user_id) values");//增加sql语句
			for (Integer book_id : allkeys) {
				sbSql.append("(" + book_id + "," + cartMap.get(book_id) + ","
						+ user_id + "),");
			}
			sbSql.deleteCharAt(sbSql.length() - 1);
			sqls[1] = sbSql.toString();
		}
	    try {
			JDBCutil.connection();
			int rs = JDBCutil.executeDML(sqls, null);
			flag = rs == -1 ? false : true;
		} catch (ClassNotFoundException | SQLException e) {
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
	public List<Cart> queryCartByUser_id(int user_id) {
		String sql = "select * from tb_cart where user_id = ?";
	  List<Cart> cartList =  ROMjdbcUtil.query(sql, new Object[]{user_id}, Cart.class);
		return cartList;
	}
}
