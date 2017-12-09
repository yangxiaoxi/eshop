package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import cn.yunhe.dao.IpDao;
import cn.yunhe.entity.Ip;
import cn.yunhe.util.JDBCutil;

public class IpDaoImpl implements IpDao {
   
	
	private  Logger logger = Logger.getLogger(IpDaoImpl.class);
	@Override
	public boolean addIp(Ip ip) {
		String sql = "insert into tb_ip (ip_address,count,time) values(?,?,now())";
		Object[] objs = new Object[]{ip.getIp_address(),ip.getCount()};
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
	public Ip checkIp(String ip_address) {
		String sql = "select * from tb_ip where ip_address=?";
		 Ip ip = null;
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, new Object[]{ip_address});
			 if(rs.next()){
				 ip = new Ip();
				 ip.setIp_address(ip_address);
				 ip.setCount(rs.getInt("count"));
				 ip.setTime(rs.getString("time"));
			 }
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ip;
	}

	@Override
	public boolean updateCount(Ip ip) { 
		String sql = "update  tb_ip set count=?,time=now() where ip_address=? ";
		Object[] objs = new Object[]{ip.getCount(),ip.getIp_address()};
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

}
