package com.amazon.netty.actions;

import com.amazon.netty.Usecase.CustomerUsecase;
import com.amazon.netty.bean.CustomerBean;
import com.amazon.netty.blm.AuthenticateMgr;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;

@Path(path="/java/customer")
public class CustomerAction extends BaseAction{
	
	private CustomerBean customerBean;
	private AuthenticateMgr authenticateMgr = AuthenticateMgr.getInstance();
	public CustomerAction(WHttpRequest httpRequest, WHTTPResponse httpResponse) {
		super(httpRequest, httpResponse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class beanClass() {
		// TODO Auto-generated method stub
		return CustomerBean.class;
	}

	@Override
	public Object getBean() {
		// TODO Auto-generated method stub
		return customerBean;
	}

	@Override
	public void setBean(Object bean) {
		// TODO Auto-generated method stub
		customerBean = (CustomerBean) bean;
	}
	
	@Path(path="/getStoreSegmentCustomer")
	public String storeSegmentCustomer(){
		CustomerUsecase customerUsecase = authenticateMgr.getInstance().getCutomerUsecase();
		return customerUsecase.storeSegmentCustomer(this.httpRequest, customerBean);
	}

}
