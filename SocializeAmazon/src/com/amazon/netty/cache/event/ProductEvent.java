package com.amazon.netty.cache.event;

public class ProductEvent  extends AbstractCacheEvent implements CacheEvent{

	public ProductEvent(String storeId) {
		this.storeId = storeId;
	}
	
	public String getStoreId() {
		return storeId;
	}
	
}
