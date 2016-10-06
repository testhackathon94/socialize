package com.amazon.netty.actions;

import com.amazon.netty.controller.BeanIdentifier;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;

public abstract class BaseAction implements BeanIdentifier{

	protected WHttpRequest httpRequest;
	protected WHTTPResponse httpResponse;
	
	public BaseAction(WHttpRequest httpRequest, WHTTPResponse httpResponse){
		this.httpRequest = httpRequest;
		this.httpResponse = httpResponse;
	}
	
}
