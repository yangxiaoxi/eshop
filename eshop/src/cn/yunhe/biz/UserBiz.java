package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.User;

public interface UserBiz {
	
	/**
	 * ��֤��½�����û���Ϣ
	 * @param userName
	 * @param psw
	 * @return
	 */
	User Login(String userName,String psw);
	
	/**
	 * �����û�id�����û�
	 * @param user_id  �û�id
	 * @return
	 */
	User selectById(int user_id);

	
	/**
	 * ��ѯ���е��û�����
	 * @return
	 */
	List<String> queryAllEmail();
	
	/**
	 * ��ѯ���е��û���
	 * @return
	 */
	List<String> queryAllUser_name();
	
	/**
	 * ����û�
	 * @param user
	 * @return
	 */
	int addUser(User user);
	
	/**
	 *���û���״̬���Ϊ����״̬
	 * @return
	 */
	boolean updateActicvated(int user_id);
	
	/**
	 * �޸��û����Ƿ�����״̬
	 * @param is_online  �û��Ƿ����ߵ�״̬��  0 ---�����ߣ�1--����
	 * @param user_id
	 * @return
	 */
	boolean updateIs_online(int is_online,int user_id);


}
