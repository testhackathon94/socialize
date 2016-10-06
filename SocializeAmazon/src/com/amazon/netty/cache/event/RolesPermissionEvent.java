package com.amazon.netty.cache.event;

public class RolesPermissionEvent extends AbstractCacheEvent implements CacheEvent{

	public RolesPermissionEvent(String storeId) {
		this.storeId = storeId;
	}
	
	public String getStoreId() {
		return storeId;
	}
	
}
