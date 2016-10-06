package com.amazon.netty.cache.event;

public class CacheBuildContext {

	private CacheEvent cacheEvent;
	private CacheBuildType buildType;

	public CacheBuildContext(CacheEvent cacheEvent, CacheBuildType buildType) {
		super();
		this.cacheEvent = cacheEvent;
		this.buildType = buildType;
	}
	

	public CacheEvent getCacheEvent() {
		return cacheEvent;
	}
	public void setCacheEvent(CacheEvent cacheEvent) {
		this.cacheEvent = cacheEvent;
	}
	public CacheBuildType getBuildType() {
		return buildType;
	}
	public void setBuildType(CacheBuildType buildType) {
		this.buildType = buildType;
	}
	
	
}
