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
		 //1,如果该ip还没有请求过该项目 ，则添加该ip的请求
		 if(ip==null){
			 ip = new Ip();
			 ip.setCount(1);
			 ip.setIp_address(ip_address);
			 flag=  ipDao.addIp(ip);
		 }else{//2,如果该用户ip已经请求过该项目，则修改请求项目的次数
			 ip.setCount(ip.getCount()+1);
			 ip.setIp_address(ip_address);
			 flag=ipDao.updateCount(ip);
  		 }
		 
		return flag;
	}

}
