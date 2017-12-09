package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.TypeBiz;
import cn.yunhe.dao.TypeDao;
import cn.yunhe.dao.impl.TypeDaoImpl;
import cn.yunhe.entity.Type;

public class TypeBizImpl implements TypeBiz {
	private TypeDao typeDao = new TypeDaoImpl();

	@Override
	public List<Type> queryTopType() {
		return typeDao.queryTopType();
	}

	@Override
	public List<Type> queryAllType() {
		return typeDao.queryAllType();
	}

	@Override
	public String queryTypeNameById(int type_id) {
		// TODO Auto-generated method stub
		return typeDao.queryTypeNameById(type_id);
	}

	@Override
	public List<Type> queryChileType(int type_id) {
		// TODO Auto-generated method stub
		return typeDao.queryChileType(type_id);
	}

}
