package com.icss.dao;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

import com.icss.dto.UserStaff;
import com.icss.entity.User;

public interface IUserDao extends IBaseDao{
	public List<UserStaff> getUsers(Date indate) throws Exception;
	
	public default void addUser(User user) throws Exception{
		String sql = "insert into tuser values(?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.getConn().prepareStatement(sql);
		ps.setString(1, user.getUname());
		ps.setString(2, user.getSno());
		ps.setString(3, user.getPwd());
		ps.setInt(4, user.getRole());
		ps.executeUpdate();
		ps.close();	
	}
}
