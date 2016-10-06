package com.amazon.netty.cache;

import java.util.Date;

import com.amazon.netty.cache.event.CacheBuildContext;
import com.amazon.netty.cache.event.CacheBuildType;
import com.amazon.netty.cache.event.ServiceEvent;

public class EmployeeCacheBuilder extends AbstractCacheBuilder {

	private static EmployeeCacheBuilder cacheBuilder = null;
	
	private EmployeeCacheBuilder() {
	}
	
	public static EmployeeCacheBuilder getInstance(){
		if(cacheBuilder == null){
			synchronized (EmployeeCacheBuilder.class) {
				if(cacheBuilder == null){
					cacheBuilder = new EmployeeCacheBuilder();
				}
			}
		}
		return cacheBuilder;
	}
	
	@Override
	public void rebuildCache(CacheBuildContext cacheBuildContext) {
		
		if(CacheBuildType.FULL_BUILD.equals(cacheBuildContext.getBuildType())){
			// call full build logic
		}else if(CacheBuildType.PARTIAL_BUILD_OBJECT_ID.equals(cacheBuildContext.getBuildType())){
			ServiceEvent serviceEvent = (ServiceEvent)cacheBuildContext.getCacheEvent();
			// call build by object Id being passed in cacheBuildContext
			System.out.println("Partial Cache building at employee cache builder");
		}else if(CacheBuildType.PARTIAL_BUILD_LAST_UPDATE_TIME.equals(cacheBuildContext.getBuildType())){
			Date lastUpdateTime = getLastBuildTime();
			// call build by last update Time
		}

	}

}
