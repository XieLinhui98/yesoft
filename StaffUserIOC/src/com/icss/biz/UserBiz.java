package com.icss.biz;

import java.util.Date;
import java.util.List;

import com.icss.consts.IRole;
import com.icss.dao.IStaffDao;
import com.icss.dao.IUserDao;
import com.icss.dao.StaffMyDao;
import com.icss.dao.UserDao;
import com.icss.dao.UserMyDao;
import com.icss.dto.UserStaff;
import com.icss.entity.Staff;
import com.icss.entity.User;
import com.icss.util.AppContext;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class UserBiz {
	
	/**
	 * 返回指定入职时间以后的用户及相关的员工信息
	 * @param indate
	 * @return
	 * @throws Exception
	 */
	@Transaction
	public List<UserStaff> getUsers(Date indate) throws Exception{
		List<UserStaff> userList = null;
		
		//IUserDao dao = new UserDao();
		IUserDao dao = (IUserDao)AppContext.getBean("userDao");
		try {
			userList = dao.getUsers(indate);
		} catch (Exception e) {
			throw e;
		}finally {
			dao.closeConnection();			
		}
		
		return userList;
		
	}
	
	/**
	 * 添加系统用户
	 * @param staff
	 * @throws Exception
	 */
	@Transaction(readOnly=false)
	public void addUser(Staff staff) throws MySQLIntegrityConstraintViolationException,Exception{
		IStaffDao sdao = (IStaffDao)AppContext.getBean("staffDao");
		try {
			sdao.beginTransaction();               //开启事务
			sdao.addStaff(staff);                  //添加员工
			IUserDao udao = (IUserDao)AppContext.getBean("userDao");
			udao.setConn(sdao.getConn());
			User user = new User();
			user.setUname(staff.getSno());
			user.setPwd("123456");
			user.setRole(IRole.CUSER);
			user.setSno(staff.getSno());
			udao.addUser(user);			           //添加用户        
			sdao.commit();   
		
		} catch (Exception e) {
		   sdao.rollback();                        //事务回滚
	       throw e;
		}finally {
			sdao.closeConnection();
		}		
	}

}
