package com.amazon.netty.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class DatabaseManager extends AbstractDatabaseManager {
	Logger log = LogManager.getLogger(DatabaseManager.class);
	public Object handleTransaction(ProceedingJoinPoint pjp) throws Throwable {
		
		SessionFactory factory = AmazonHibernateSessionFactory.getInstance().getSessionFactory();
		System.out.println("Session factory initialized...");
		
		Session currentSession = factory.openSession();
		ThreadSession.setSession(currentSession);
		Transaction t = currentSession.beginTransaction();
		Boolean hasException = false;
		Object result = null;
		try {
			result = pjp.proceed(pjp.getArgs());
		}catch (Throwable e) {
			hasException = true;
		}finally {
			if(hasException){
				if(t != null && t.isActive()){
					t.rollback();
				}
			}else{
				if(t != null && t.isActive()){
					t.commit();
				}
			}
			
		}
		return result;
	}
	
}
