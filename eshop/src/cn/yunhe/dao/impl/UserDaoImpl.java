package cn.yunhe.dao.impl;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.UserDao;
import cn.yunhe.entity.Book;
import cn.yunhe.entity.User;
import cn.yunhe.util.JDBCutil;
import cn.yunhe.util.ROMjdbcUtil;

public class UserDaoImpl  implements UserDao,Serializable{
 //  private Logger logger = Logger.getLogger(UserDaoImpl.class);
	@Override
	public User Login(String userName, String psw) {
		 String sql = "select * from tb_user where user_name = ? and user_pwd=? and is_activated = 1";
		 User user = null;
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, new Object[]{userName,psw});
			 if(rs.next()){
				 int user_id = rs.getInt("user_id");
				 String user_name = rs.getString("user_name");
				 String user_pws = rs.getString("user_pwd");
				 String user_email = rs.getString("user_email");
				 String user_sex = rs.getString("user_sex");
				 String user_phone=rs.getString("user_phone");
				 String user_address =rs.getString("user_address");
				 int question_id =rs.getInt("question_id") ;
				 String question_answer =rs.getString("question_answer") ;
				 int is_actived=rs.getInt("is_activated");
				 user= new User(user_id, user_name, user_pws, user_email, user_sex, user_phone, user_address, question_id, question_answer, is_actived);
			 }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User selectById(int user_id) {
		 
		 String sql = "select * from tb_user where user_id = ?";
		 User user = null;
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, new Object[]{user_id});
			 if(rs.next()){
				 String user_name = rs.getString("user_name");
				 String user_pws = rs.getString("user_pwd");
				 String user_email = rs.getString("user_email");
				 String user_sex = rs.getString("user_sex");
				 String user_phone=rs.getString("user_phone");
				 String user_address =rs.getString("user_address");
				 int question_id =rs.getInt("question_id") ;
				 String question_answer =rs.getString("question_answer") ;
				 int is_actived=rs.getInt("is_activated");
				 user= new User(user_id,user_name, user_pws, user_email, user_sex, user_phone, user_address, question_id, question_answer, is_actived);
			 }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<String> queryAllEmail() {
		String sql = "select user_email from tb_user";
	   List<String> userEmail = new ArrayList<String>();
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, null);
			 while(rs.next()){
				 userEmail.add(rs.getString(1));
			 }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userEmail;
	}

	
	@Override
	public List<String> queryAllUser_name() {
		String sql = "select user_name from tb_user";
	  List<String> userName = new ArrayList<String>();
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, null);
			 while(rs.next()){
				 userName.add(rs.getString(1));
			 }
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return  userName;
	}
	
	
	@Override
	public int addUser(User user) {
		String sql = "insert into tb_user"
				+ "(user_name,user_pwd,user_email,user_sex,user_phone,user_address,question_id,question_answer) "
				+ " values(?,?,?,?,?,?,?,?)";
		int user_id = -1;
		Object[] objs = {user.getUser_name(),user.getUser_pws(),
				user.getUser_email(),user.getUser_sex(),
				user.getUser_phone(),user.getUser_address(),user.getQuestion_id(),
				user.getQuestion_answer()};
		boolean isAdd = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			isAdd = true;
			if(isAdd){//如果添加成功，返回用户id
				String sql2 = "select last_insert_id()";
			    ResultSet rs = 	JDBCutil.query(sql2, null);
			    if(rs.next()){
			    	user_id = rs.getInt(1);
			    }
			    rs.close();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user_id;
	}

	@Override
	public boolean updateActicvated(int user_id) {
		String sql = "update tb_user set is_activated = 1 where user_id = ?";
		boolean isYes = true;
			try {
				JDBCutil.connection();
		        JDBCutil.update(sql, new Object[]{user_id});
				isYes=true;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				try {
					JDBCutil.closeSource();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				isYes = false;
			}finally{
				try {
					JDBCutil.closeSource();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 return  isYes;
	}

	@Override
	public boolean updateIs_online(int is_online,int user_id) {
		String sql = "update tb_user set is_online = ? where user_id = ?";
		boolean isYes = true;
			try {
				JDBCutil.connection();
		        int i = JDBCutil.update(sql, new Object[]{is_online,user_id});
		        if(i!=-1){
		        	isYes=true;
		        }
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
				try {
					JDBCutil.closeSource();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				isYes = false;
			}finally{
				try {
					JDBCutil.closeSource();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		 return  isYes;
	}

}
