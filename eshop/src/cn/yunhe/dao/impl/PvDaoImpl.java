package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import cn.yunhe.dao.PvDao;
import cn.yunhe.util.JDBCutil;

public class PvDaoImpl  implements PvDao {

	@Override
	public boolean addPv(String session_id) {
		String sql = "insert into tb_pv (session_id,time) values(?,now())";
		Object[] objs = new Object[]{session_id};
		boolean flag = false;
		try {
			JDBCutil.connection();
		  int i = JDBCutil.update(sql, objs);
		  	if(i!=-1){
		  		flag = true;
		  	}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean checkPvExits(String session_id) {
		String sql = "select count(*) from tb_pv where session_id = ?";
		Object[] objs = new Object[]{session_id};
		boolean flag = false;
		try {
			JDBCutil.connection();
		  ResultSet rs  = JDBCutil.query(sql, objs);
		   if(rs.next()){
			   flag = rs.getInt(1)==0?false:true;
		   }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
