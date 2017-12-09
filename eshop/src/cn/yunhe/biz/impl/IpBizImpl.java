package cn.yunhe.biz.impl;

import cn.yunhe.biz.IpBiz;
import cn.yunhe.dao.IpDao;
import cn.yunhe.dao.impl.IpDaoImpl;
import cn.yunhe.entity.Ip;

public class IpBizImpl  implements IpBiz{
  private IpDao ipDao = new IpDaoImpl();
	@Override
	public boolean addIp(String ip_address) {
		 Ip ip = ipDao.checkIp(ip_address);
		 boolean flag = false;
		 //1,�����ip��û�����������Ŀ ������Ӹ�ip������
		 if(ip==null){
			 ip = new Ip();
			 ip.setCount(1);
			 ip.setIp_address(ip_address);
			 flag=  ipDao.addIp(ip);
		 }else{//2,������û�ip�Ѿ����������Ŀ�����޸�������Ŀ�Ĵ���
			 ip.setCount(ip.getCount()+1);
			 ip.setIp_address(ip_address);
			 flag=ipDao.updateCount(ip);
  		 }
		 
		return flag;
	}

}
