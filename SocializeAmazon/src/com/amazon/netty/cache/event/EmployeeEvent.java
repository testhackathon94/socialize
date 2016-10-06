package com.amazon.netty.cache.event;

public class EmployeeEvent extends AbstractCacheEvent implements CacheEvent{

	public EmployeeEvent(String storeId) {
		this.storeId = storeId;
	}
	
	public String getStoreId() {
		return storeId;
	}
	
}
