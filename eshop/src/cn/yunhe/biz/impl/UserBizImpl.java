package cn.yunhe.biz.impl;

import java.io.Serializable;
import java.util.List;

import cn.yunhe.biz.UserBiz;
import cn.yunhe.dao.UserDao;
import cn.yunhe.dao.impl.UserDaoImpl;
import cn.yunhe.entity.User;

public class UserBizImpl implements UserBiz,Serializable{
	private UserDao userinDao = new UserDaoImpl();

	@Override
	public User Login(String userName, String psw) {
		return userinDao.Login(userName, psw);
	}

	@Override
	public User selectById(int user_id) {
		return userinDao.selectById(user_id);
	}

	@Override
	public List<String> queryAllEmail() {
		return userinDao.queryAllEmail();
	}

	@Override
	public List<String> queryAllUser_name() {
		return userinDao.queryAllUser_name();
	}

	@Override
	public int addUser(User user) {
		return userinDao.addUser(user);
	}

	@Override
	public boolean updateActicvated(int user_id) {
		// TODO Auto-generated method stub
		return userinDao.updateActicvated(user_id);
	}

	@Override
	public boolean updateIs_online(int is_online, int user_id) {
		// TODO Auto-generated method stub
		return userinDao.updateIs_online(is_online, user_id);
	}

}
