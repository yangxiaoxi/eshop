package cn.yunhe.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;

public class JDBCutil {
	private static Connection conn = null;
	private static ResultSet cursor = null;
	private static PreparedStatement prSate = null;
	private static Statement state = null;
	private static DataSource ds = null;
	private static Logger logger = Logger.getLogger(JDBCutil.class);
	static {
		Properties perProperties = new Properties();
		try {
			perProperties.load(JDBCutil.class
					.getResourceAsStream("/eshop.properties"));
			ds = BasicDataSourceFactory.createDataSource(perProperties);

		} catch (IOException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * �������ݿ�
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void connection() throws ClassNotFoundException, SQLException {
		conn = ds.getConnection();
	}

	/**
	 * @param sql
	 *            �ⲿ������Ҫִ�е�sql���
	 * @param obj
	 *            ����sql����в����б�
	 * @return ����Ӱ������
	 * @throws SQLException
	 */
	public static int update(String sql, Object[] obj) throws SQLException {
		prSate = conn.prepareStatement(sql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				prSate.setObject(i + 1, obj[i]);
			}
		}

		int result = prSate.executeUpdate();
		return result;
	}

	/**
	 * @param sql
	 *            �ⲿ������Ҫִ�е�sql���
	 * @param obj
	 *            ����sql����в����б�
	 * @return ���ز�ѯ���Ľ����
	 * @throws SQLException
	 */
	public static int executeDML(String sqls[],Object[][] objs){
		  int rs = -1;
			try {
				conn.setAutoCommit(false);//ȡ���Զ��ύ
				for (int i = 0; i < sqls.length; i++) {
					if (sqls[i]!=null) {
						String sql = sqls[i];
						prSate = conn.prepareStatement(sql);
						if (objs != null) {
							for (int j = 0; j < objs[i].length; j++) {
								if (objs[i] != null) {
									prSate.setObject(i + 1, objs[i][j]);//���?  
								}
							}
						}
						rs = prSate.executeUpdate();
						System.out.println("rs is" + rs);
						System.out.println("ִ����sql���");
					}
			 }
			   conn.commit();//�ύ����
				
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();//��������쳣��ع�
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		 
		return rs;
	}

	public static ResultSet query(String sql, Object[] obj) throws SQLException {
		prSate = conn.prepareStatement(sql);
		if (obj != null) {
			for (int i = 0; i < obj.length; i++) {
				prSate.setObject(i + 1, obj[i]);
			}
		}
		cursor = prSate.executeQuery();
		return cursor;
	}

	/**
	 * �ͷ���Դ
	 * 
	 * @throws SQLException
	 */
	public static void closeSource() throws SQLException {

		if (cursor != null) {
			cursor.close();
			cursor = null;
		}
		if (prSate != null) {
			prSate.close();
			prSate = null;
		}

		if (conn != null) {
			conn.close();
			conn = null;
		}
	}

}
