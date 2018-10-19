package com.icss.ui;

import java.text.SimpleDateFormat;

import com.icss.biz.TransacrtionCglibProxy;
import com.icss.biz.UserBiz;
import com.icss.entity.Staff;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Test {
	
	public static void addUserTest() {		
		TransacrtionCglibProxy cglib=new TransacrtionCglibProxy();
		UserBiz biz=(UserBiz)cglib.getInstance(new UserBiz());
		Staff staff = new Staff();
		staff.setSno("s010");
		staff.setName("sss");
		staff.setSex(1);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			staff.setBirthday(sd.parse("1998-10-1"));	
			staff.setIndate(sd.parse("2014-7-1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			biz.addUser(staff);	
			System.out.println(staff.getName() + "--用户添加成功");
		} catch (MySQLIntegrityConstraintViolationException e) {
			System.out.println("该用户名已存在");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}	

	
	public static void main(String[] args) {
		
		Test.addUserTest();
		
		
		
	}

}
