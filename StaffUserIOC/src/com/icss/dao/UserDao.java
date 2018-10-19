package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.dto.UserStaff;
import com.icss.entity.User;

public class UserDao extends BaseDao implements IUserDao{
	
	public List<UserStaff> getUsers(Date indate) throws Exception{
        List<UserStaff> userList;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");		
		String sql = "Select s.BIRTHDAY,s.SEX, u.UNAME,u.SNO, s.NAME, s.INDATE, u.ROLE "
				+ "  From TSTAFF s  Inner Join TUSER u On u.SNO = s.SNO "
				+ " where s.indate > to_date('" + sd.format(indate) + "','yyyymmdd')";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		userList = new ArrayList<>();
		while(rs.next()) {
			UserStaff us = new UserStaff();
			us.setBirthday(rs.getDate("BIRTHDAY"));
			us.setIndate(rs.getDate("INDATE"));
			us.setName(rs.getString("name"));
			us.setRole(rs.getInt("role"));
			us.setSex(rs.getInt("sex"));
			us.setSno(rs.getString("sno"));
			us.setUname(rs.getString("uname"));
			userList.add(us);
		}
		rs.close();
		ps.close();
		
		return userList;
		
	}
	
	/**
	 * 返回指定入职时间以后的用户及相关的员工信息
	 * @param indate
	 * @return
	 * @throws Exception
	 */
	public List<UserStaff> getUsers33(Date indate) throws Exception{
		List<UserStaff> userList;
		
		String sql = "Select s.BIRTHDAY,s.SEX, u.UNAME,u.SNO, s.NAME, s.INDATE, u.ROLE "
				+ "  From TSTAFF s  Inner Join TUSER u On u.SNO = s.SNO where s.indate > ?";
		this.openConnection();
		PreparedStatement ps = this.conn.prepareStatement(sql);
		ps.setDate(1, new java.sql.Date(indate.getTime()));
		ResultSet rs = ps.executeQuery();
		userList = new ArrayList<>();
		while(rs.next()) {
			UserStaff us = new UserStaff();
			us.setBirthday(rs.getDate("BIRTHDAY"));
			us.setIndate(rs.getDate("INDATE"));
			us.setName(rs.getString("name"));
			us.setRole(rs.getInt("role"));
			us.setSex(rs.getInt("sex"));
			us.setSno(rs.getString("sno"));
			us.setUname(rs.getString("uname"));
			userList.add(us);
		}
		rs.close();
		ps.close();
		
		return userList;
		
	}
	
}
