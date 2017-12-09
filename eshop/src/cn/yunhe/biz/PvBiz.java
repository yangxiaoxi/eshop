package cn.yunhe.biz;

public interface PvBiz {
	
	/**
	 * 添加用户访问量
	 * @param session_id
	 * @return
	 */
	boolean addPv(String session_id);

}
