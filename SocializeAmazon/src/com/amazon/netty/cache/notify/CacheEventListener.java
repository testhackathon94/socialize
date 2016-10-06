package com.amazon.netty.cache.notify;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.amazon.netty.cache.AbstractCacheBuilder;
import com.amazon.netty.cache.event.CacheBuildContext;
import com.amazon.netty.cache.event.CacheBuildType;
import com.amazon.netty.cache.event.CacheEvent;

public class CacheEventListener {

	Executor executor = Executors.newFixedThreadPool(2);
	InternalEventQueueManager eventQueueManager = null;

	public CacheEventListener(InternalEventQueueManager eventQueueManager) {
		this.eventQueueManager = eventQueueManager;
		executor.execute(new CacheEventHandler(this));
		System.out.println("Initiated Cache event listener");
	}

	public class CacheEventHandler implements Runnable{
		
		public CacheEventHandler(CacheEventListener cacheEventListener) {
		}
		
		@Override
		public void run() {
			for(;;){ // listen to the queue forever
				CacheEvent cacheEvent;
				try {
					cacheEvent = eventQueueManager.getCacheEventQueue();
					System.out.println("Received a cache event " + cacheEvent);
					executor.execute(new CallCacheBuild(cacheEvent));
				} catch (InterruptedException e) {
					//log 
					System.out.println("Error in gettnig/listenning to caceh event");
					e.printStackTrace();
				} catch (Throwable e) {
					// error in getting/listening for cache event 
					System.out.println("Error in gettnig/listenning to caceh event");
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public class CallCacheBuild implements Runnable{
		
		CacheEvent cacheEvent;
		
		public CallCacheBuild(CacheEvent cacheEvent) {
			this.cacheEvent = cacheEvent;
		}
		
		@Override
		public void run() {
			try {
				CacheBuildContext cacheBuildContext = new CacheBuildContext(cacheEvent, CacheBuildType.PARTIAL_BUILD_OBJECT_ID);
				System.out.println("Built the cache conrtext for event build");
				AbstractCacheBuilder<CacheEvent> cacheBuilder = CacheBuilderResolver.resolveCacheBuilder(cacheEvent);
				System.out.println("Resolved cache builder");
				if(cacheBuilder == null){
					// could not resolve the specific cache builder
					return;
				}
				cacheBuilder.rebuildCache(cacheBuildContext);
				
			} catch (Throwable e) {
				// error in getting/listening for cache event 
			}
		}
	}
	

	
}
