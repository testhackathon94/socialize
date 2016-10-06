package com.amazon.netty.blm;

import com.amazon.netty.bl.ProductSvc;

public class AmazonSocialServiceMgr extends AbstractComponentManager{

	
	private static AmazonSocialServiceMgr mgr = null;
	
	public static AmazonSocialServiceMgr getInstance(){
		if(mgr == null){
			synchronized (AmazonSocialServiceMgr.class) {
				if(mgr == null){
					mgr = new AmazonSocialServiceMgr();
				}
			}
		}
		return mgr;
	}
	

	
	public ProductSvc getServiceSettingSVC(){
		return (ProductSvc)getBean("productSvcSvc");
	}
	
}
