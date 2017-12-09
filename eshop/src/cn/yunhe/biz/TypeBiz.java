package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Type;

public interface TypeBiz {

	/**
	 * 查询顶部的typeList
	 * @return
	 */
	List<Type> queryTopType();
	
	
	/**
	 * 查询所有的的typeList
	 * @return
	 */
	List<Type> queryAllType();
	
	/**
	 * 根据type_id查找type_name;
	 * @param type_id
	 * @return
	 */
	String queryTypeNameById(int type_id);
	
	/**
	 * 查询某个类型下的子类型
	 * @param type_id
	 * @return
	 */
	List<Type> queryChileType(int type_id);
}
