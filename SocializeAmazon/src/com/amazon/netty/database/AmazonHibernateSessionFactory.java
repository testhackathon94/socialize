package com.amazon.netty.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class AmazonHibernateSessionFactory {

	private static AmazonHibernateSessionFactory amazonHibernateSessionFactory;
	SessionFactory factory;
	
	
	private AmazonHibernateSessionFactory(){
		initialze();
	}
	
	public static AmazonHibernateSessionFactory getInstance(){
		if(amazonHibernateSessionFactory == null){
			synchronized (AmazonHibernateSessionFactory.class) {
				if(amazonHibernateSessionFactory == null){
					amazonHibernateSessionFactory = new AmazonHibernateSessionFactory();
				}
			}
		}
		return amazonHibernateSessionFactory;
	}
	
	public void initialze(){
		Configuration cfg = new Configuration();
		// cfg.configure("hibernate.cfg.xml");//populates the data of the
		// configuration file

		// creating session factory object
		factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();


	}
	
	public SessionFactory getSessionFactory(){
		return factory;
	}
	
}
