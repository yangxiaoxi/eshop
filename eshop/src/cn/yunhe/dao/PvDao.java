package cn.yunhe.dao;

public interface PvDao {
	
	
	/**
	 * 检查session_id存不存在
	 * @param session_id
	 * @return
	 */
	boolean checkPvExits(String session_id);
	
	/**
	 * 添加用户访问量
	 * @param session_id
	 * @return
	 */
	boolean addPv(String session_id);

}
