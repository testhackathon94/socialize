package com.amazon.netty.blm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class AmazonProperty {
	
	private static AmazonProperty instance = null;
	private Properties pro;
	private AmazonProperty(){
		init();
	}
	
	public static AmazonProperty getInstance(){
		if(instance == null){
			synchronized (AmazonProperty.class) {
				if(instance == null){
					instance = new AmazonProperty();
				}
			}
		}
		
		return instance;
	}
	
	private void init() {
		// TODO Auto-generated method stub
		pro = new Properties();
		try{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
			pro.load(inputStream);
		}catch(Exception e){
			System.out.println("Populating properties error"+e.getMessage());
		}
	}
	
	
	public String getPropertyValue(String key){
		return pro.getProperty(key);
	}
	
}
