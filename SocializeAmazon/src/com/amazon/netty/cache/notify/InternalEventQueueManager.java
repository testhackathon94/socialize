package com.amazon.netty.cache.notify;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.amazon.netty.cache.event.CacheEvent;

public class InternalEventQueueManager {

	private BlockingQueue<CacheEvent> cacheEventQueue = new LinkedBlockingQueue<CacheEvent>();

	private static InternalEventQueueManager instance;
	private CacheEventListener cacheEventListener;
	
	private InternalEventQueueManager() {
		cacheEventListener = new CacheEventListener(this);
	}
	
	public static InternalEventQueueManager getInstance(){
		if(instance == null){
			synchronized (InternalEventQueueManager.class) {
				if(instance == null){
					instance = new InternalEventQueueManager();
				}
			}
		}
		return instance;
	}
	
	
	public void putCacheEvent(CacheEvent cacheEvent) throws InterruptedException{
		cacheEventQueue.put(cacheEvent);
	}
	
	public CacheEvent getCacheEventQueue() throws InterruptedException{
		return cacheEventQueue.take();
	}
 	
}
