package com.amazon.netty.dao;

import java.util.Date;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;

import com.amazon.domain.bean.Friend;

import com.amazon.domain.bean.FriendsFeed;
import com.amazon.domain.bean.GoodBuy;
import com.amazon.domain.bean.GoodBuyResponse;
import com.amazon.domain.bean.MyActivity;

import com.amazon.domain.bean.Product;

import com.amazon.domain.bean.User;

import com.amazon.netty.database.ThreadSession;

public class ActivityDAO {

	public void activity(Integer customerId, Integer productId, String content, String action) {

		Session session = ThreadSession.getThreadSession();

		String pq = "from com.amazon.domain.bean.Product p where p.id = " + productId;

		Query productQuery = session.createQuery(pq);

		Product product = (Product) productQuery.uniqueResult();

		String uq = "from com.amazon.domain.bean.User u where u.id = " + customerId;

		Query userQuery = session.createQuery(uq);

		User user = (User) userQuery.uniqueResult();
		
		GoodBuy goodBuy = null;
		if(action.equalsIgnoreCase("Good Buy")){
			goodBuy = new GoodBuy();
			goodBuy.setEventTime(new Date());
			goodBuy.setUser(user);
			goodBuy.setProduct(product);
			session.saveOrUpdate(goodBuy);
		}
		

		String fq = "from com.amazon.domain.bean.Friend f where f.user.id = " + customerId;

		Query fhQuery = session.createQuery(fq);

		List<Friend> friends = (List<Friend>) fhQuery.list();

		if (friends != null && friends.size() > 0) {

			for (Friend friend : friends) {

				FriendsFeed ff = new FriendsFeed();

				ff.setFriend(friend.getFriend());

				ff.setUser(friend.getUser());

				ff.setProduct(product);
				if(goodBuy != null){
					ff.setGoodbuy(goodBuy);
				}

				ff.setAction(action);

				ff.setContent(content);

				ff.setEventTime(new Date());

				session.saveOrUpdate(ff);

			}

			System.out.println("published review to friends count: " + friends.size());

		} else {

			System.out.println("No friends found to publish review");

		}

		MyActivity myActivity = new MyActivity();

		myActivity.setAction(action);

		myActivity.setContent(content);

		myActivity.setEventtime(new Date());

		myActivity.setProduct(product);

		myActivity.setUser(user);

		session.saveOrUpdate(myActivity);

	}
	
	public void writeGoodBuyResponse(Integer goodBuyId, Integer userId, String response, String comment){
		Session session = ThreadSession.getThreadSession();

		String pq = "from com.amazon.domain.bean.GoodBuy g where g.id = " + goodBuyId;

		Query productQuery = session.createQuery(pq);

		GoodBuy goodBuy = (GoodBuy) productQuery.uniqueResult();

		String uq = "from com.amazon.domain.bean.User u where u.id = " + userId;

		Query userQuery = session.createQuery(uq);

		User user = (User) userQuery.uniqueResult();
		
		GoodBuyResponse buyResponse = new GoodBuyResponse();
		buyResponse.setComment(comment);
		buyResponse.setEventTime(new Date());
		buyResponse.setFriend(user);
		buyResponse.setGoodBuy(goodBuy);
		buyResponse.setResponse(response);
		
		session.saveOrUpdate(buyResponse);
		
	}
	

}
