package com.amazon.netty.actions;

import com.amazon.netty.Usecase.ProductUsecase;
import com.amazon.netty.bean.ProductBean;
import com.amazon.netty.blm.AuthenticateMgr;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;

@Path(path="/java/settings")
public class ProductSettingAction extends BaseAction{
	private ProductBean productBean;
	private AuthenticateMgr authenticateMgr = AuthenticateMgr.getInstance();
	public ProductSettingAction(WHttpRequest httpRequest, WHTTPResponse httpResponse) {
		super(httpRequest, httpResponse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class beanClass() {
		// TODO Auto-generated method stub
		return ProductBean.class;
	}

	@Override
	public Object getBean() {
		// TODO Auto-generated method stub
		return this.productBean;
	}

	@Override
	public void setBean(Object bean) {
		// TODO Auto-generated method stub
		this.productBean = (ProductBean)bean;
	}
	
	@Path(path="/getBillingProducts/")
	public String getBillingProducts(){
		ProductUsecase productUsecase = authenticateMgr.getProductUsecase();
		String records = productUsecase.getBillingProducts(httpRequest);
		return records;
	}
	
	
	
	
}
