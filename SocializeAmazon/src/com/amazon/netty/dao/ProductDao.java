package com.amazon.netty.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.amazon.domain.bean.Friend;
import com.amazon.domain.bean.FriendsFeed;
import com.amazon.domain.bean.GoodBuyResponse;
import com.amazon.domain.bean.MyActivity;
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
		List<Order> orderList = null;
		try{
			orderList = (List<Order>)query.list();
		}catch(Throwable e){
			e.printStackTrace();
		}
		
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

	public List<FriendsFeed> fetchProductActivity(Integer productId){
		
		String q = "from com.amazon.domain.bean.FriendsFeed ff where ff.product.id = " + productId + " order by id desc";
		
		Session session = ThreadSession.getThreadSession();
		
		Query query = session.createQuery(q);
		
		List<FriendsFeed> friendsFeedList = (List<FriendsFeed>)query.list();
		
		if(friendsFeedList != null && friendsFeedList.size() > 0){
			System.out.println("Friends Feed found..." + friendsFeedList.size());
			return friendsFeedList;
		}else{
			System.out.println("No Friends Feed found...");
		}
		
		return null;
		
	}
	
	public List<FriendsFeed> fetchFriendsActivity(Integer customerId){
		
		String q = "from com.amazon.domain.bean.FriendsFeed where friend.id = " + customerId  + " order by id desc";
		
		Session session = ThreadSession.getThreadSession();
		
		Query query = session.createQuery(q);
		
		List<FriendsFeed> friendsFeedList = (List<FriendsFeed>)query.list();
		
		if(friendsFeedList != null && friendsFeedList.size() > 0){
			System.out.println("Friends Feed found..." + friendsFeedList.size());
			return friendsFeedList;
		}else{
			System.out.println("No Friends Feed found...");
		}
		
		return null;
		
	}
	
	public List<GoodBuyResponse> fetchGoodBuyResponse(Integer goodBuyId){
		
		String q = "from com.amazon.domain.bean.GoodBuyResponse g where g.goodBuy.id = " + goodBuyId;
		
		Session session = ThreadSession.getThreadSession();
		
		Query query = session.createQuery(q);
		List<GoodBuyResponse> goodBuyResponse  = null;
		try{
			goodBuyResponse = (List<GoodBuyResponse>)query.list();
		}catch(Throwable e){
			e.printStackTrace();
		}
		if(goodBuyResponse != null && goodBuyResponse.size() > 0){
			System.out.println("goodBuyResponse found..." + goodBuyResponse.size());
			return goodBuyResponse;
		}else{
			System.out.println("No goodBuyResponse found...");
		}
		
		return null;
		
	}
	
	public List<MyActivity> getMyActivity(Integer customerId){
		Session session = ThreadSession.getThreadSession();
		
		String maq = "from com.amazon.domain.bean.MyActivity where user_id = "+ customerId;
		Query myActivityQuery = session.createQuery(maq);
		
		List<MyActivity> myActivityList = (List<MyActivity>)myActivityQuery.list();
		
		if(myActivityList != null && myActivityList.size() > 0)
		{
			System.out.println("My Activity found..." + myActivityList.size());
			return myActivityList;
		}
		else
		{
			System.out.println("No Activity found for the customer");
		}
		return null;
	}
	

	
}
