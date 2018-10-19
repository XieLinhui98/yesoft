package com.icss.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class DbInfo {
	private static DbInfo dbInfo;              //单例
	private String dbdriver;
	private String dburl;
	private String username;
	private String password;	
	
	public String getDbdriver() {
		return dbdriver;
	}

	public void setDbdriver(String dbdriver) {
		this.dbdriver = dbdriver;
	}

	public String getDburl() {
		return dburl;
	}

	public void setDburl(String dburl) {
		this.dburl = dburl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private DbInfo() {
		
	}
	
	public static DbInfo newInstance() throws Exception{
		if (dbInfo == null) {
			dbInfo = new DbInfo();
			getProperties();
		}
			
		
		return dbInfo;
	}
	
	/**
	 * 读取配置文件
	 */
	private static void getProperties() throws Exception{
		FileInputStream fis = null;
		
		try {
			String uString = DbInfo.class.getResource("/").toURI().getPath() + AppContext.getDbFile();
			File file = new File(uString);
			Properties properties = new Properties();
			
			fis = new FileInputStream(file);;
			properties.load(fis);
			dbInfo.setDbdriver(properties.getProperty("dbdriver"));
			dbInfo.setDburl(properties.getProperty("dburl"));
			dbInfo.setUsername(properties.getProperty("username"));
			dbInfo.setPassword(properties.getProperty("password"));
		} catch (FileNotFoundException e) {
			Log.logger.error(e.getMessage());
			throw e;
		} catch (IOException e) {
			Log.logger.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			Log.logger.error(e.getMessage());
			throw e;
		}finally{
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					Log.logger.error(e.getMessage());
				}
			}			
		}
	}	

}
