package cn.yunhe.dao;

public interface PvDao {
	
	
	/**
	 * ���session_id�治����
	 * @param session_id
	 * @return
	 */
	boolean checkPvExits(String session_id);
	
	/**
	 * ����û�������
	 * @param session_id
	 * @return
	 */
	boolean addPv(String session_id);

}
