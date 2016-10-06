package com.amazon.netty.database;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

public class ThreadSession {

	private static Map<Long, Session> hibernateSessions = new HashMap<>();
	
	public static void setSession(Session session){
		Long threadId = Thread.currentThread().getId();
		hibernateSessions.put(threadId, session);
	}
	
	public static Session getThreadSession(){
		Long threadId = Thread.currentThread().getId();
		return hibernateSessions.get(threadId);
	}
	
	
	
}
