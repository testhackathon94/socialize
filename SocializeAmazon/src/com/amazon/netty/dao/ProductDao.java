package com.amazon.netty.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.amazon.domain.bean.Order;
import com.amazon.domain.bean.User;
import com.amazon.netty.database.ThreadSession;

public class ProductDao {
	
	public User fetchCustomer(Integer customerId){
		
		String q = "from com.amazon.domain.bean.User u where u.id = " + customerId;
		
		Session session = ThreadSession.getThreadSession();
		
		Query query = session.createQuery(q);
		
		
		List<User> userList = (List<User>)query.list();
		
		if(userList != null && userList.size() > 0){
			System.out.println("User found..." + userList.size());
			return userList.get(0);
		}else{
			System.out.println("No user found...");
		}
		
		return null;
		
	}
	public Order fetchOrderByCustomerId(Integer customerId){
		
		String q = "from com.amazon.domain.bean.Order where user.id = " + customerId;
		
		Session session = ThreadSession.getThreadSession();
		
		Query query = session.createQuery(q);
		
		List<Order> orderList = (List<Order>)query.list();
		
		if(orderList != null && orderList.size() > 0){
			System.out.println("Order found..." + orderList.size());
			return orderList.get(0);
		}else{
			System.out.println("No Order found...");
		}
		
		return null;
		
	}
}
