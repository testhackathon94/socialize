package com.amazon.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.amazon.domain.bean.Employee;

public class StoreUtil {
	public static void main(String[] args) {
		try {
			// creating configuration object
			Configuration cfg = new Configuration();
			// cfg.configure("hibernate.cfg.xml");//populates the data of the
			// configuration file

			// creating session factory object
			SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();

			// creating session object
			Session session = factory.openSession();

			
			// creating transaction object
			Transaction t = session.beginTransaction();

			Employee e1 = new Employee();
			e1.setId(419);
			e1.setFirstName("sonoo1");
			e1.setLastName("jaiswal1");

			session.save(e1);// persisting the object

			t.commit();// transaction is committed
			session.close();

			System.out.println("successfully saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}