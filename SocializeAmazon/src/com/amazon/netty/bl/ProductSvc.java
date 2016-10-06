package com.amazon.netty.bl;

import com.amazon.domain.bean.Order;
import com.amazon.domain.bean.User;
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
	
}
