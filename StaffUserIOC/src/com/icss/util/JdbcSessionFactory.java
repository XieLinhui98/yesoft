package com.icss.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcSessionFactory {
	private static final ThreadLocal<Connection> threadLocal ;
	
	static {
		threadLocal = new ThreadLocal<Connection>();
	}
	
	
	 public static Connection getConnection() throws Exception {
		 Connection conn = threadLocal.get();
		 if(conn == null || conn.isClosed()) {
			DbInfo dbinfo = DbInfo.newInstance();
			Class.forName(dbinfo.getDbdriver());		
			conn = DriverManager.getConnection(dbinfo.getDburl(),dbinfo.getUsername(),dbinfo.getPassword());
			threadLocal.set(conn);
		 }
		 
		 return conn;
	 }
	
	 public static void closeConnection() {
		 Connection conn = threadLocal.get();
		 threadLocal.set(null);
		 if(conn != null) {
			try {
				 conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			 
		 }
		 
	 }

}
