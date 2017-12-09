package cn.yunhe.dao;

import cn.yunhe.entity.Ip;

public interface IpDao {
	
	/**
	 * 添加客户访问的ip
	 * @return
	 */
	boolean addIp(Ip ip);
	
	/**
	 * 检测ip_address是否存在
	 * @param ip_address
	 * @return
	 */
	Ip checkIp(String ip_address);
	
	/**
	 * 修改某个ip请求的次数
	 * @param ip
	 * @return
	 */
	boolean  updateCount(Ip ip);

}
