package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Type;

public interface TypeBiz {

	/**
	 * ��ѯ������typeList
	 * @return
	 */
	List<Type> queryTopType();
	
	
	/**
	 * ��ѯ���еĵ�typeList
	 * @return
	 */
	List<Type> queryAllType();
	
	/**
	 * ����type_id����type_name;
	 * @param type_id
	 * @return
	 */
	String queryTypeNameById(int type_id);
	
	/**
	 * ��ѯĳ�������µ�������
	 * @param type_id
	 * @return
	 */
	List<Type> queryChileType(int type_id);
}
