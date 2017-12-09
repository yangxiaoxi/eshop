package cn.yunhe.biz.impl;

import cn.yunhe.biz.PvBiz;
import cn.yunhe.dao.PvDao;
import cn.yunhe.dao.impl.PvDaoImpl;

public class PvBizImpl implements PvBiz {
	private PvDao pvDao = new PvDaoImpl();

	@Override
	public boolean addPv(String session_id) {
		 boolean flag = pvDao.checkPvExits(session_id);
		 if(!flag){
			 flag =  pvDao.addPv(session_id);
		 }
		return flag;
	}

}
