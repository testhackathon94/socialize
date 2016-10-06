package com.amazon.netty.cache;

import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.amazon.netty.cache.event.CacheBuildContext;
import com.amazon.netty.cache.event.CacheBuildType;
import com.amazon.netty.cache.event.CacheEvent;

public class PeriodicCacheBuilder {

	private ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
	
	private Random intialRandomDelay = new Random();
	
	private int randomIntervalInSec = 5*60; // 5 minutes
	
	private static PeriodicCacheBuilder periodicCacheBuilder;
	
	private PeriodicCacheBuilder(){
		
	}
	
	public static PeriodicCacheBuilder getInstance(){
		if(periodicCacheBuilder == null){
			synchronized (PeriodicCacheBuilder.class) {
				if(periodicCacheBuilder == null){
					periodicCacheBuilder = new PeriodicCacheBuilder();
				}
				
			}
		}
		return periodicCacheBuilder;
	}
	
	public void registerCaches(){
		PeriodicCacheBuilder.getInstance().registerPeriodicCacheBuilder(ServiceCacheBuilder.getInstance(), 86400); // re-build for every day
		PeriodicCacheBuilder.getInstance().registerPeriodicCacheBuilder(EmployeeCacheBuilder.getInstance(), 86400); // re-build for every day
	}
	
	public void registerPeriodicCacheBuilder(AbstractCacheBuilder<CacheEvent> abstractCacheBuilder, long buildInterval){

		
		PeriodicBuilderThread periodicBuilderThread = new PeriodicBuilderThread(abstractCacheBuilder);
		int initialDelay = intialRandomDelay.nextInt(randomIntervalInSec);
		executor.scheduleWithFixedDelay(periodicBuilderThread, initialDelay, buildInterval, TimeUnit.SECONDS);
		System.out.println("Registering cache : " + abstractCacheBuilder.getClass() + " for periodic cache build process and with initial delay : " +  initialDelay);

	}
	
	public static  class PeriodicBuilderThread implements Runnable{
		
		AbstractCacheBuilder<CacheEvent> cacheBuilder;
		
		public PeriodicBuilderThread(AbstractCacheBuilder<CacheEvent> cacheBuilder) {
			this.cacheBuilder =  cacheBuilder;
		}
		
		@Override
		public void run() {
			CacheBuildContext cacheBuildContext = new CacheBuildContext(null, CacheBuildType.FULL_BUILD); 
			this.cacheBuilder.rebuildCache(cacheBuildContext);
		}
	}
	
	
}
