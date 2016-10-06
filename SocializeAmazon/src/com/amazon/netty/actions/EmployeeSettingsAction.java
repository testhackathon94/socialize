package com.amazon.netty.actions;

import com.amazon.netty.bean.EmployeeBean;
import com.amazon.netty.bl.ProductSvc;
import com.amazon.netty.blm.AmazonServiceMgr;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;

@Path(path="/java/settings")
public class EmployeeSettingsAction extends BaseAction{
	
	private EmployeeBean employeeBean;
	
	public EmployeeSettingsAction(WHttpRequest request,WHTTPResponse response){
		super(request,response);
	}

	@Override
	public Object getBean() {
		// TODO Auto-generated method stub
		return employeeBean;
	}

	@Override
	public void setBean(Object bean) {
		// TODO Auto-generated method stub
		this.employeeBean = (EmployeeBean) bean;
	}

	@Override
	public Class beanClass() {
		// TODO Auto-generated method stub
		return EmployeeBean.class;
	}
	
	@Path(path="/amazon/testAPI")
	public String testContent(){
		ProductSvc productSvc = AmazonServiceMgr.getInstance().getProductSvc();
//		productSvc.
		return "Hello Test content";
	}
	
	

}
