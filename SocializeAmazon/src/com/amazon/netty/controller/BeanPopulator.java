package com.amazon.netty.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.netty.http.WHttpRequest;
import com.google.gson.Gson;

public class BeanPopulator {

	private static BeanPopulator instance;
	private Logger log = LogManager.getLogger(BeanPopulator.class);
	
	public static BeanPopulator getInstance(){
		if(instance == null){
			synchronized (BeanPopulator.class) {
				if(instance == null){
					instance = new BeanPopulator();
				}
			}
		}
		return instance;
	}

	
	public Object populateBeanByParam(WHttpRequest httpRequest, Class beanClass){
		
		Object beanObject = null;
		try {
			beanObject = beanClass.newInstance();
		} catch (InstantiationException e1) {
			log.error("Error in creating object for bean class : " + beanClass, e1);
			return null;
		} catch (IllegalAccessException e1) {
			log.error("Error in creating object for bean class : " + beanClass, e1);
			return null;
		}
		
		Map<String, List<String>> reqParamMap = httpRequest.getRequestParameterMap();
		System.out.println("Request parameter : "+httpRequest.getRequestParameterMap().size());
		Set<Entry<String, List<String>>> entries = reqParamMap.entrySet();
		
		for(Entry<String, List<String>> entry : entries){
			String paramName = entry.getKey();
			List<String> paramValues = entry.getValue();
			if(paramValues != null){
				for(String value : paramValues){
					try {
						BeanUtils.copyProperty(beanObject, paramName, value);
						System.out.println("Populating : "+ paramName + " With value "+ value + " in bean "+ beanClass);
					} catch (IllegalAccessException e) {
						log.error("Error in populating " + paramName + " with value " + value + " in bean " + beanClass, e);
					} catch (InvocationTargetException e) {
						log.error("Error in populating " + paramName + " with value " + value + " in bean " + beanClass, e);
					} 
				}
			}
		}
		return beanObject;
	}
	
	public Object populateBeanByJSON(WHttpRequest request, Class bean){
		
		String bodyConent = request.getBodyContent();
		
		Gson gson = new Gson();
		Object beanObject = gson.fromJson(bodyConent, bean);
		
		return beanObject;
		
	}
}
