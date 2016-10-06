package com.amazon.netty.cache.event;

public class CustomerProfileEvent extends AbstractCacheEvent implements CacheEvent{

	private String customerId;

	public CustomerProfileEvent(String storeId, String customerId) {
		this.storeId = storeId;
		this.customerId = customerId;
	}
	
	
	public String getCustomerId() {
		return customerId;
	}



	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}



	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}



	public String getStoreId() {
		return storeId;
	}
	
}
