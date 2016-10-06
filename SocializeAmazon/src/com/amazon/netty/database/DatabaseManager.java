package com.amazon.netty.database;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;



public class DatabaseManager extends AbstractDatabaseManager {
	Logger log = LogManager.getLogger(DatabaseManager.class);
	public Object handleTransaction(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("Database Interceptor being called");
		String clazzName = pjp.getSignature().getDeclaringTypeName(); 
		String methodName = pjp.getSignature().getName();
		Boolean hasException = false;
		Connection conn = null;
		Object result = null;
		try {
			conn = openConnection();
			result = pjp.proceed(pjp.getArgs());
		}catch (Throwable e) {
			// TODO: handle exception
			log.error("Exception occured in trace "+clazzName+"."+methodName+"-"+e.getMessage());
			hasException = true;
		}finally {
			if(hasException){
				rollBack(conn);
			}else{
				commit(conn);
				endConnection(conn);
			}
			
		}
		return result;
	}
	
}
