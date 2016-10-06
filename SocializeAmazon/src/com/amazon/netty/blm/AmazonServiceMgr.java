package com.amazon.netty.blm;

import com.amazon.netty.bl.ProductSvc;

public class AmazonServiceMgr extends AbstractComponentManager{

	
	private static AmazonServiceMgr mgr = null;
	
	public static AmazonServiceMgr getInstance(){
		if(mgr == null){
			synchronized (AmazonServiceMgr.class) {
				if(mgr == null){
					mgr = new AmazonServiceMgr();
				}
			}
		}
		return mgr;
	}
	

	

	
	public ProductSvc getProductSvc(){
		return (ProductSvc)getBean("productSvc");
	}
	


}
