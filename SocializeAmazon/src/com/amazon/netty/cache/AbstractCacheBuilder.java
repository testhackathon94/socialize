package com.amazon.netty.cache;

import java.util.Date;

import com.amazon.netty.cache.event.CacheBuildContext;
import com.amazon.netty.cache.event.CacheEvent;

public abstract class AbstractCacheBuilder<T extends CacheEvent> {

	private Date lastBuildTime; 
	
	public abstract void rebuildCache(CacheBuildContext cacheBuildContext);
	
	public void setLastBuildTime(){
		this.lastBuildTime = new Date();
	}
	
	public Date getLastBuildTime(){
		return lastBuildTime;
	}
	
}


