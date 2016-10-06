package com.amazon.netty.cache.event;

public enum CacheBuildType {

	FULL_BUILD(),
	PARTIAL_BUILD_OBJECT_ID(),
	PARTIAL_BUILD_LAST_UPDATE_TIME();
	
	private CacheBuildType() {
	}
	
}
