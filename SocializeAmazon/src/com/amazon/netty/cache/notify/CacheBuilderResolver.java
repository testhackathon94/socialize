package com.amazon.netty.cache.notify;

import com.amazon.netty.cache.AbstractCacheBuilder;
import com.amazon.netty.cache.ServiceCacheBuilder;
import com.amazon.netty.cache.event.CacheEvent;
import com.amazon.netty.cache.event.ServiceEvent;

public class CacheBuilderResolver {
 
	public static AbstractCacheBuilder<CacheEvent> resolveCacheBuilder(CacheEvent cacheEvent){
		if(cacheEvent instanceof ServiceEvent){
			return ServiceCacheBuilder.getInstance();
		}
			
		return null;
	}
	
	
	

	
/*	public static AbstractCacheBuilder<CacheEvent> resolveCacheBuilder(CacheEvent cacheEvent){
		if(cacheEvent instanceof ServiceEvent){
			return ServiceCacheBuilder.getInstance();
		}
			
		return null;
	}
*/
}
