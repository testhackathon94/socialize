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
		System.out.println("Calling product dao");
		Order order = productDao.fetchOrderByCustomerId(customerId);
		if(order == null){
			return "No order found";
		}
		return GsonUtil.toString(order);
	}
	
	
	@Database
	public String fetchCustomerId(int customerId){
		
		ProductDao productDao = new ProductDao();
		System.out.println("Calling product dao");
		User user = productDao.fetchCustomer(customerId);
		if(user == null){
			return "No order found";
		}
		UserVO userVO = new UserVO(user);
		return GsonUtil.toString(userVO);
	}
}
