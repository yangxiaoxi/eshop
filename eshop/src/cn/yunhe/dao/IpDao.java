package cn.yunhe.dao;

import cn.yunhe.entity.Ip;

public interface IpDao {
	
	/**
	 * ��ӿͻ����ʵ�ip
	 * @return
	 */
	boolean addIp(Ip ip);
	
	/**
	 * ���ip_address�Ƿ����
	 * @param ip_address
	 * @return
	 */
	Ip checkIp(String ip_address);
	
	/**
	 * �޸�ĳ��ip����Ĵ���
	 * @param ip
	 * @return
	 */
	boolean  updateCount(Ip ip);

}
