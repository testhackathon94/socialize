package com.amazon.netty.cache.event;

public class ServiceEvent extends AbstractCacheEvent implements CacheEvent{
	
	public ServiceEvent(String storeId,String suffix) {
		this.storeId = storeId;
		this.suffix =  suffix;
	}
	
	public String getStoreId() {
		return storeId;
	}
	
	public String getCacheKey(){
		return storeId+"_"+suffix;
	}
	
}
