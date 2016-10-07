package com.amazon.netty.bl;

import java.util.ArrayList;
import java.util.List;

import com.amazon.domain.bean.FriendsFeed;
import com.amazon.domain.bean.MyActivity;
import com.amazon.domain.bean.Order;
import com.amazon.domain.bean.Product;
import com.amazon.domain.bean.User;
import com.amazon.domain.bean.vo.FriendFeedVO;
import com.amazon.domain.bean.vo.MyActivityVO;
import com.amazon.domain.bean.vo.ProductVO;
import com.amazon.domain.bean.vo.UserVO;
import com.amazon.netty.dao.ProductDao;
import com.amazon.netty.database.Database;
import com.amazon.util.GsonUtil;

public class ProductSvc {
	

	@Database
	public String fetchLastOrder(int customerId){
		
		ProductDao productDao = new ProductDao();
		System.out.println("Calling product dao - fetch last order");
		Order order = productDao.fetchOrderByCustomerId(customerId);
		if(order == null){
			return "No order found";
		}
		return GsonUtil.toString(order);
	}
	
	
	@Database
	public String fetchCustomerId(int customerId){
		
		ProductDao productDao = new ProductDao();
		System.out.println("Calling product dao - fetch customerId");
		User user = productDao.fetchCustomer(customerId);
		if(user == null){
			return "No order found";
		}
		UserVO userVO = new UserVO(user);
		return GsonUtil.toString(userVO);
	}
	
	
	@Database
	public String review(Integer customerId, Integer productId, String review){
		try{
		ProductDao productDao = new ProductDao();
		System.out.println("Calling product dao - review");
		productDao.review(customerId, productId, review);
		return "SUCCESS";
		}catch(Throwable e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "failure";
	}
	
	
	@Database
	public String fetchMyFriendsActivity(int customerId){
		
		ProductDao productDao = new ProductDao();
		System.out.println("Calling product dao - fetch My Friends Activity");
		List<FriendsFeed> friendsFeedList = productDao.fetchFriendsActivity(customerId);
		
		List<FriendFeedVO> ffVoList = new ArrayList<FriendFeedVO>();
		
		for(FriendsFeed friendsFeed : friendsFeedList)
		{
			User user = friendsFeed.getUser();
			Product product = friendsFeed.getProduct();
			
			UserVO userVO = new UserVO(user);
			ProductVO productVO = new ProductVO(product);

			FriendFeedVO feedVO = new FriendFeedVO(friendsFeed, userVO, productVO);
			ffVoList.add(feedVO);
			
		}
	
		return GsonUtil.toString(ffVoList);
	}
	
	@Database
	public String getMyActivity(Integer customerId){
	
		ProductDao productDao = new ProductDao();
		System.out.println("Calling product dao - getMyActivity");
		List<MyActivity> myActivityList = productDao.getMyActivity(customerId);
		List<MyActivityVO> myActivityVOList = new ArrayList<MyActivityVO>();
		for(MyActivity myActivity : myActivityList)
		{
			User user = myActivity.getUser();
			Product product = myActivity.getProduct();
			
			UserVO userVO = new UserVO(user);
			ProductVO productVO = new ProductVO(product);

			MyActivityVO myActivityVO = new MyActivityVO(myActivity, userVO, productVO);
			myActivityVOList.add(myActivityVO);
			
		}
	
		return GsonUtil.toString(myActivityVOList);

	}
}