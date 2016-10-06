package com.amazon.netty.cache;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class InterceptMe {

	@Cache(suffix=CacheKeySuffix.EmployeeList)
	public void testInter(){
		System.out.println("Hello World");
	}
	
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("cache-Interceptor.xml", "application.xml");
	
		InterceptMe interceptMe = new InterceptMe();
		interceptMe.testInter();
	}
	
}
