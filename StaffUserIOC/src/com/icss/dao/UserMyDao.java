package com.icss.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.icss.dto.UserStaff;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UserMyDao  extends BaseDao implements IUserDao{

	@Override
	public List<UserStaff> getUsers(Date indate) throws MySQLIntegrityConstraintViolationException,Exception {
		 List<UserStaff> userList;
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");		
			String sql = "Select s.BIRTHDAY,s.SEX, u.UNAME,u.SNO, s.NAME, s.INDATE, u.ROLE "
					+ "  From TSTAFF s  Inner Join TUSER u On u.SNO = s.SNO "
					+ " where s.indate > str_to_date('" + sd.format(indate) + "','%Y-%m-%d')";
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

}
