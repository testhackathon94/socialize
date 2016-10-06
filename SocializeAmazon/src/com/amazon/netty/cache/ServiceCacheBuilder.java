package com.amazon.netty.cache;

import java.util.Date;

import com.amazon.netty.actions.ServiceSettingAction;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.cache.event.CacheBuildContext;
import com.amazon.netty.cache.event.CacheBuildType;
import com.amazon.netty.cache.event.ServiceEvent;

public class ServiceCacheBuilder extends AbstractCacheBuilder {

	private static ServiceCacheBuilder cacheBuilder = null;
	
	private ServiceCacheBuilder() {
	}
	
	public static ServiceCacheBuilder getInstance(){
		if(cacheBuilder == null){
			synchronized (ServiceCacheBuilder.class) {
				if(cacheBuilder == null){
					cacheBuilder = new ServiceCacheBuilder();
				}
			}
		}
		return cacheBuilder;
	}
	
	@Override
	public void rebuildCache(CacheBuildContext cacheBuildContext) {
		
		if(CacheBuildType.FULL_BUILD.equals(cacheBuildContext.getBuildType())){
			// call full build logic
			System.out.println("Full build is being called");
			
		}else if(CacheBuildType.PARTIAL_BUILD_OBJECT_ID.equals(cacheBuildContext.getBuildType())){
			ServiceEvent serviceEvent = (ServiceEvent)cacheBuildContext.getCacheEvent();
			//SettingServiceMgr.getInstance().getServiceSettingSVC().getServiceGroups(serviceEvent.getStoreId());
			// call build by object Id being passed in cacheBuildContext
			System.out.println("Partial Cache building at service cache builder");
		}else if(CacheBuildType.PARTIAL_BUILD_LAST_UPDATE_TIME.equals(cacheBuildContext.getBuildType())){
			Date lastUpdateTime = getLastBuildTime();
			// call build by last update Time
		}

	}

}
