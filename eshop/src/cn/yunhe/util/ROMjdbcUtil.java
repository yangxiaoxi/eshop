package cn.yunhe.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class ROMjdbcUtil {
	private static Logger logger = Logger.getLogger(ROMjdbcUtil.class);


	/**
	 * @param sql
	 *            ���Բ�ѯ�κ����ݵ�sql���
	 * @param clazz
	 *            ��Ҫ��ѯ�����ݿ�����Ӧ��ʵ���ࣨ��ʵ������ֶκ����ݿ��������ֶ���һ�£�;
	 * @return
	 */
	public static List query(String sql, Object[] objs,Class clazz) {
		List list = new ArrayList();
		try {
			JDBCutil.connection();
			ResultSet set = JDBCutil.query(sql, objs);
			while (set.next()) {
				// ClassInfo classInfo = new ClassInfo();
				Object classInfo = clazz.newInstance();
				Method[] methods = clazz.getDeclaredMethods();
				for (Method method : methods) {
					String mathodName = method.getName();
					String fieldName = mathodName.substring(3);
					if (mathodName.startsWith("set")) {
						Class[] fielType = method.getParameterTypes();
						if (isExitCloum(set,fieldName)) {// �ж����ݿ����Ƿ��и��ֶΣ�����û�и��ֶ�
							String paramType=method.getParameterTypes()[0].getSimpleName();
//							System.out.println("javaBean�е��������ͣ�"+paramType);
							Object value = set.getObject(fieldName);
							
							if(value!=null){
								String dbParamType=value.getClass().getSimpleName();
//								System.out.println("���ݿ��е��������ͣ�"+dbParamType);
								if ("Date".equals(dbParamType)) {
									SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
									String dateStr=sdf.format(value);
									method.invoke(classInfo, dateStr);
								}else{
									method.invoke(classInfo, value);
								}
							}
							
						}
					}
				}
				list.add(classInfo);
			}

		} catch (ClassNotFoundException | SQLException e) {
			logger.error(e);
		} catch (IllegalAccessException e) {
			logger.error(e);
		} catch (IllegalArgumentException e) {
			logger.error(e);
		} catch (SecurityException e) {
			logger.error(e);
		} catch (InstantiationException e) {
			logger.error(e);
		} catch (InvocationTargetException e) {
			logger.error(e);
		}   finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.error(e);
			}
		}
		return list;
	}

	private static boolean isExitCloum(ResultSet set, String cloum) {
		boolean flag = false;
		try {
			int isExits = set.findColumn(cloum);
			if (isExits >0) {
				flag = true;
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		return flag;
	}
}