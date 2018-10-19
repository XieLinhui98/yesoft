package com.icss.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IBaseDao {
	public Connection getConn() ;
	public void setConn(Connection conn) ;
	public void openConnection() throws ClassNotFoundException,SQLException,Exception ;
	public void closeConnection() ;
	public void beginTransaction() throws Exception ;
	public void commit() throws Exception;
	public void rollback() throws Exception ;
}
