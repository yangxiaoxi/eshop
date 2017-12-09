package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Book;

public interface BookBiz {
	
	/**
	 * ͨ��type_id��ѯ��
	 * @param type_id
	 * @return
	 */
	List<Book> queryByTypeId(int type_id,int currentPage,int pageSize);
	
	/**
	 * ��ѯĳ�����͵��鼮������
	 * @param type_id
	 * @return
	 */
	int queryByTypeId(int type_id);
	
	/**
	 * ͨ��book_id�����鼮������ҳ
	 * @param book_id
	 * @return
	 */
	Book queryById(int book_id);
	

    /**
     * ģ����ѯ�鼮
     * @param book_name
     * @return
     */
  List<Book> queryLike(String book_name,String keyWord, int type_id,int currentPage,int pageSize);
    
    /**
     * ������ѯ���鼮����
     * @param book_name
     * @param keyWord
     * @param type_id
     * @return
     */
    int countSearch(String book_name,String keyWord, int type_id);


	
	 
    /**
     * ��ѯ�����¼���鼮
     * @param book_ids
     * @return
     */
    List<Book>	queryHistory(String book_ids,int currentPage,int pageSize );
    
    /**
     * ��ѯ�����¼���鼮������
     * @param book_ids
     * @return
     */
    int	queryHistory(String book_ids );
    

}
