package cn.yunhe.dao.impl;

import java.util.ArrayList;
import java.util.List;

import cn.yunhe.dao.BookDao;
import cn.yunhe.entity.Book;
import cn.yunhe.util.ROMjdbcUtil;

public class BookDaoImpl  implements BookDao{

	@Override
	public List<Book> queryByTypeId(int type_id,int currentPage,int pageSize) {
		String sql = "select * from tb_book where type_id=? or type_id in"
				+ "(select type_id from tb_type where parent_id=? ) limit ?,?";
	 List<Book> bookLists = ROMjdbcUtil.query(sql, new Object[]{type_id,type_id,(currentPage-1)*pageSize,pageSize},Book.class);
		return  bookLists;
	}

	@Override
	public Book queryById(int book_id) {
		String sql = "select book_id,book_name,book_logo,book_price,book_price_old,book_author,book_description"
				+ " ,book_press,type_id from tb_book where book_id = ?";
	 List<Book> bookLists = ROMjdbcUtil.query(sql, new Object[]{book_id},Book.class);
		return  bookLists.size()>0?bookLists.get(0):null;
	}

	@Override
	public int queryByTypeId(int type_id) {
		String sql = "select * from tb_book where type_id=? or type_id in"
				+ "(select type_id from tb_type where parent_id=? ) ";
	 List<Book> bookLists = ROMjdbcUtil.query(sql, new Object[]{type_id,type_id},Book.class);
		return  bookLists.size();
	}

	
	@Override
	public List<Book> queryHistory(String book_ids,int currentPage,int pageSize) {
		String sql = "select * from tb_book where book_id IN ( "+book_ids+ ") limit ?,?";
	   List<Book> bookLists = ROMjdbcUtil.query(sql, new Object[]{(currentPage-1)*pageSize,pageSize},Book.class);
		return  bookLists;
	}

	@Override
	public List<Book> queryLike(String book_name,String keyWord, int type_id,int currentPage, int pageSize) {
		String sql = "select * from tb_book where book_name like ? ";
		List<Object> param = new ArrayList<Object>();
		param.add("%"+book_name+"%");
		if(!"".equals(keyWord)){
			sql+=" or book_name like ? ";
			param.add("%"+keyWord+"%");
		}
		if(type_id!=-1){
			sql+=" or type_id = ? ";
			param.add(type_id);
		}
		sql+=" limit ?,? ";
		param.add((currentPage-1)*pageSize);
		param.add(pageSize);
	  List<Book> bookLists = ROMjdbcUtil.query(sql,param.toArray(),Book.class);
		return  bookLists;
	}

	 

	@Override
	public int countSearch(String book_name, String keyWord, int type_id) {
		String sql = "select * from tb_book where book_name like ? ";
		List<Object> param = new ArrayList<Object>();
		param.add("%"+book_name+"%");
		if(!"".equals(keyWord)){
			sql+=" or book_name like ? ";
			param.add("%"+keyWord+"%");
		}
		if(type_id!=-1){
			sql+=" or type_id = ? ";
			param.add(type_id);
		}
	  List<Book> bookLists = ROMjdbcUtil.query(sql,param.toArray(),Book.class);
		return  bookLists.size();
	}

	@Override
	public int queryHistory(String book_ids) {
		String sql = "select * from tb_book where book_id IN ( "+book_ids+ ")";
		   List<Book> bookLists = ROMjdbcUtil.query(sql,null,Book.class);
		return  bookLists.size();
	}

	@Override
	public  List<Book> queryBookForCart(String book_ids) {
		String sql = "select book_id,book_name,book_price,book_price_old from tb_book where book_id IN ( "+book_ids+ ")";
		System.out.println("Cart sql is"+sql);
		   List<Book> bookLists = ROMjdbcUtil.query(sql,null,Book.class);
		return  bookLists;
	}

}
