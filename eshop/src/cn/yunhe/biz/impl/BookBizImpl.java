package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.BookBiz;
import cn.yunhe.dao.BookDao;
import cn.yunhe.dao.impl.BookDaoImpl;
import cn.yunhe.entity.Book;

public class BookBizImpl  implements BookBiz{
  private BookDao bookDao = new BookDaoImpl();
	@Override
	public List<Book> queryByTypeId(int type_id,int currentPage,int pageSize) {
		return bookDao.queryByTypeId(type_id,currentPage,pageSize);
	}
	@Override
	public Book queryById(int book_id) {
		return bookDao.queryById(book_id);
	}
	@Override
	public int queryByTypeId(int type_id) {
		return bookDao.queryByTypeId(type_id);
	}
	@Override
	public List<Book> queryHistory(String book_ids,int currentPage,int pageSize) {
		return bookDao.queryHistory(book_ids,currentPage,pageSize);
	}
	@Override
	public List<Book> queryLike(String book_name,String keyWord, int type_id,int currentPage,int pageSize) {
		return bookDao.queryLike(book_name, keyWord,  type_id,currentPage,pageSize);
	}
	@Override
	public int countSearch(String book_name, String keyWord, int type_id) {
		// TODO Auto-generated method stub
		return bookDao.countSearch(book_name,keyWord, type_id);
	}
	@Override
	public int queryHistory(String book_ids) {
		// TODO Auto-generated method stub
		return bookDao.queryHistory(book_ids);
	}

}
