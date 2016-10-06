package com.amazon.netty.blm;

import java.io.IOException;
import java.net.InetSocketAddress;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.spy.memcached.MemcachedClient;



public class MemcacheMgr {
	Logger log = LogManager.getLogger(MemcacheMgr.class);
	private MemcachedClient memInstance = null;
	private static MemcacheMgr instance = null; 
	private MemcacheMgr() {
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Memcahce instance creation error"+ e.getMessage());
		}
	}
	
	public static MemcacheMgr getInstance(){
		if(instance == null){
			synchronized (MemcacheMgr.class) {
				if(instance == null){
					instance = new MemcacheMgr();
				}
			}
		}
		
		return instance;
	}
	
	
	private void init() throws IOException{
		memInstance = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
		
	}
	
	public void set(String key,String value) {
		memInstance.set(key, 0, value);
	}
	
	public String get(String key) {
		Object ob = memInstance.get(key); 
		if(ob !=null){		
			return ob.toString();
		}else{
			return null;
		}
	}
		
	public void delete(String key){
		memInstance.delete(key);
	}
	

}
