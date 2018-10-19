package com.icss.biz;

import java.lang.reflect.Method;
import java.sql.Connection;

import com.icss.util.JdbcSessionFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TransacrtionCglibProxy implements MethodInterceptor{
	
	private Object target;
	
	public Object getInstance(Object target) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		
		Object objRet = null;
		
		Method[] mth = target.getClass().getDeclaredMethods();
		for (Method m : mth) {
			if (method.getName() == m.getName()) {		
				Transaction t = m.getAnnotation(Transaction.class);		
				if (t != null) {
					try {
						Connection conn = JdbcSessionFactory.getConnection();  //只要有@transaction标记，就需要开启数据库
							if (t.readOnly()==false) {	                  
								conn.setAutoCommit(false);             //开启事务	
								try {
									objRet=proxy.invokeSuper(obj, args);
									conn.commit();                      //提交事务
								} catch (Exception e) {
									conn.rollback();                  //异常回滚
									e.printStackTrace();
								}
							} else {	//没有打开事务
								objRet = method.invoke(target, args);	
							}
					} catch (Exception e) {
						throw e;
					}finally {
						JdbcSessionFactory.closeConnection();
					}					
				}				
			}
		}

		return objRet;
	}

}
