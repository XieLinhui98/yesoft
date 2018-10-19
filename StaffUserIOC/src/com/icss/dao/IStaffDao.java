package com.icss.dao;

import java.sql.PreparedStatement;

import com.icss.entity.Staff;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


public interface IStaffDao extends IBaseDao{
	
	public default void addStaff(Staff staff) throws MySQLIntegrityConstraintViolationException,Exception{
		String sql = "insert into tstaff values(?,?,?,?,?)";
		this.openConnection();
		PreparedStatement ps = this.getConn().prepareStatement(sql);
		ps.setString(1, staff.getSno());
		ps.setString(2, staff.getName());
		ps.setDate(3, new java.sql.Date(staff.getBirthday().getTime()));
		ps.setInt(4, staff.getSex());
		ps.setDate(5, new java.sql.Date(staff.getIndate().getTime()));
		ps.executeUpdate();
		ps.close();	
	}
}
