package com.icss.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.icss.util.DbInfo;


public class BaseDao implements IBaseDao{
	
	protected  Connection conn;
	
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void openConnection() throws ClassNotFoundException,SQLException,Exception {
		if(conn == null || conn.isClosed()) {
			DbInfo dbinfo = DbInfo.newInstance();
			Class.forName(dbinfo.getDbdriver());		
			conn = DriverManager.getConnection(dbinfo.getDburl(),dbinfo.getUsername(),dbinfo.getPassword());	
		}				
	}
	
	public void closeConnection() {
		if(this.conn != null) {
			  try {
				  this.conn.close();
			  } catch (Exception e) {
				e.printStackTrace();
			  }
		}		
	}
	
	public void beginTransaction() throws Exception {
		this.openConnection();
		this.conn.setAutoCommit(false);		
	}
	
	public void commit() throws Exception{
		if(this.conn != null) {
			this.conn.commit();
		}else {
			throw new Exception("没有打开的数据库要提交");
		}
	}
	
	public void rollback() throws Exception {
		if(this.conn != null) {
			this.conn.rollback();
		}else {
			throw new Exception("没有打开的数据库要提交");
		}
	}

}
