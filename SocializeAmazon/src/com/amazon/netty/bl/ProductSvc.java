package com.amazon.netty.bl;

import com.amazon.netty.cache.Cache;
import com.amazon.netty.cache.CacheKeySuffix;
import com.amazon.netty.dao.DaoManager;
import com.amazon.netty.dao.ProductDao;

public class ProductSvc {
	
	@Cache(index={0},suffix=CacheKeySuffix.ProductList)
	public String getBillingProducts(String storeId){
		ProductDao productDao = DaoManager.getInstance().getProductDao();
		String records = productDao.getBillingProducts(storeId);
		return records;
	}
}
