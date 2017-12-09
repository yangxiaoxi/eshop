package cn.yunhe.dao.impl;

import java.awt.Robot;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.TypeDao;
import cn.yunhe.entity.Type;
import cn.yunhe.util.JDBCutil;
import cn.yunhe.util.ROMjdbcUtil;

public class TypeDaoImpl implements TypeDao{
	private  Logger logger =  Logger.getLogger(TypeDaoImpl.class);
	@Override
	public List<Type> queryTopType() {
		String sql = "select * from tb_type limit 9";
		   List<Type> lists = new ArrayList<Type>();
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, null);
			  while(rs.next()){
				 Type type = new Type();
				 type.setType_id(rs.getInt("type_id"));
				 type.setType_name(rs.getString("type_name"));
				 lists.add(type);
			  }
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
			return lists;
	}

	@Override
	public List<Type> queryAllType() {
		String sql = "select * from tb_type where parent_id = 0";
		   List<Type> lists = new ArrayList<Type>();
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, null);
			  while(rs.next()){
				 Type type = new Type();
				 type.setType_id(rs.getInt("type_id"));
				 type.setType_name(rs.getString("type_name"));
				 lists.add(type);
			  }
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
			return lists;
	}

	@Override
	public String queryTypeNameById(int type_id) {
		String sql = "select type_name from tb_type where type_id = ?";
		   String type_name ="";
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, new Object[]{type_id});
			  if(rs.next()){
				  type_name = rs.getString(1);
			  }
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
			return type_name;
	}

	@Override
	public List<Type> queryChileType(int type_id) {
		String sql = "select * from tb_type where parent_id = ?";
		   List<Type> lists = new ArrayList<Type>();
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, new Object[]{type_id});
			  while(rs.next()){
				 Type type = new Type();
				 type.setType_id(rs.getInt("type_id"));
				 type.setType_name(rs.getString("type_name"));
				 lists.add(type);
			  }
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
			return lists;
	}

}
