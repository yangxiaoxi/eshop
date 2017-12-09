package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Book;

public interface BookBiz {
	
	/**
	 * 通过type_id查询书
	 * @param type_id
	 * @return
	 */
	List<Book> queryByTypeId(int type_id,int currentPage,int pageSize);
	
	/**
	 * 查询某种类型的书籍的数量
	 * @param type_id
	 * @return
	 */
	int queryByTypeId(int type_id);
	
	/**
	 * 通过book_id查找书籍的详情页
	 * @param book_id
	 * @return
	 */
	Book queryById(int book_id);
	

    /**
     * 模糊查询书籍
     * @param book_name
     * @return
     */
  List<Book> queryLike(String book_name,String keyWord, int type_id,int currentPage,int pageSize);
    
    /**
     * 条件查询的书籍数量
     * @param book_name
     * @param keyWord
     * @param type_id
     * @return
     */
    int countSearch(String book_name,String keyWord, int type_id);


	
	 
    /**
     * 查询浏览记录的书籍
     * @param book_ids
     * @return
     */
    List<Book>	queryHistory(String book_ids,int currentPage,int pageSize );
    
    /**
     * 查询浏览记录的书籍的数量
     * @param book_ids
     * @return
     */
    int	queryHistory(String book_ids );
    

}
