package com.icss.ui;

import java.text.SimpleDateFormat;
import java.util.List;

import com.icss.biz.UserBiz;
import com.icss.dto.UserStaff;

public class Test2 {
	
	public static void getUsersTest() {
		UserBiz biz = new UserBiz();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<UserStaff> ufs = biz.getUsers(sd.parse("2012-1-1"));
			for(UserStaff us : ufs) {				
				System.out.println(us.getName() + "--" + us.getSno() + "-" + us.getIndate());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Test2.getUsersTest();
	}

}
