package com.amazon.netty.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.amazon.domain.bean.Friend;
import com.amazon.domain.bean.FriendsFeed;
import com.amazon.domain.bean.Order;
import com.amazon.domain.bean.Product;
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
	
	public void review(Integer customerId, Integer productId, String review){
		Session session = ThreadSession.getThreadSession();
		
		String pq = "from com.amazon.domain.bean.Product p where p.id = " + productId;
		Query productQuery = session.createQuery(pq);
		Product product = (Product)productQuery.uniqueResult();
		
		
		String fq = "from com.amazon.domain.bean.Friend f where f.user.id = " + customerId;
		
		
		Query fhQuery = session.createQuery(fq);		
		
		List<Friend> friends = (List<Friend>)fhQuery.list();
		
		if(friends != null && friends.size() > 0){
			for(Friend friend : friends){
				FriendsFeed ff = new FriendsFeed();
				ff.setFriend(friend.getFriend());
				ff.setUser(friend.getUser());
				ff.setProduct(product);
				ff.setAction("Review");
				ff.setContent(review);
				ff.setEventTime(new Date());
				session.saveOrUpdate(ff);
			}
			System.out.println("published review to friends count: " + friends.size());
		}else{
			System.out.println("No friends found to publish review");
		}
		
	}
	
}
