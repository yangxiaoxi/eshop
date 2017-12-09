package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.User;

public interface UserBiz {
	
	/**
	 * 验证登陆返回用户信息
	 * @param userName
	 * @param psw
	 * @return
	 */
	User Login(String userName,String psw);
	
	/**
	 * 根据用户id查找用户
	 * @param user_id  用户id
	 * @return
	 */
	User selectById(int user_id);

	
	/**
	 * 查询所有的用户邮箱
	 * @return
	 */
	List<String> queryAllEmail();
	
	/**
	 * 查询所有的用户名
	 * @return
	 */
	List<String> queryAllUser_name();
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int addUser(User user);
	
	/**
	 *将用户的状态码改为激活状态
	 * @return
	 */
	boolean updateActicvated(int user_id);
	
	/**
	 * 修改用户的是否在线状态
	 * @param is_online  用户是否在线的状态码  0 ---不在线；1--在线
	 * @param user_id
	 * @return
	 */
	boolean updateIs_online(int is_online,int user_id);


}
