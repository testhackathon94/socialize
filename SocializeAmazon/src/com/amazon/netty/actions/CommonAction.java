package com.amazon.netty.actions;

import com.amazon.netty.Usecase.CommonUsecase;
import com.amazon.netty.bean.CommonBean;
import com.amazon.netty.bl.CommonSvc;
import com.amazon.netty.blm.AuthenticateMgr;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;

@Path(path="/java/common")
public class CommonAction extends BaseAction {
	private CommonBean commonBean; 
	private AuthenticateMgr authenticateMgr = AuthenticateMgr.getInstance(); 
	public CommonAction(WHttpRequest httpRequest, WHTTPResponse httpResponse) {
		super(httpRequest, httpResponse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class beanClass() {
		// TODO Auto-generated method stub
		return CommonBean.class;
	}

	@Override
	public Object getBean() {
		// TODO Auto-generated method stub
		return this.commonBean;
	}

	@Override
	public void setBean(Object bean) {
		// TODO Auto-generated method stub
		this.commonBean = (CommonBean)bean;
	}
	
	@Path(path="/getNotification/")
	public String getNotification(){
		CommonUsecase commonUsecase = authenticateMgr.getCommonUsecase();
		String records = commonUsecase.getNotification(httpRequest,commonBean);
		return records;
	}
	@Path(path="/getNetworkInventoryDetails/")
	public String getNetworkInventoryDetails(){
		CommonUsecase commonUsecase = authenticateMgr.getCommonUsecase();
		String records = commonUsecase.getNetworkInventoryDetails(httpRequest);
		return records;
	}
	
	@Path(path="/getPaymentMode/")
	public String getPaymentMode(){
		CommonUsecase commonUsecase = authenticateMgr.getCommonUsecase();
		String records = commonUsecase.getPaymentMode(httpRequest);
		return records;
	}
	
	@Path(path="/getBillingSettings/")
	public String getBillingSettings(){
		CommonUsecase commonUsecase = authenticateMgr.getCommonUsecase();
		String records = commonUsecase.getBillingSettings(httpRequest);
		return records;
	}
	
	@Path(path="/getMiosalonPackages")
	public String getMiosalonPackages(){
		CommonUsecase commonUsecase = authenticateMgr.getCommonUsecase();
		String records = commonUsecase.getPackages("pos");
		return records;
	}
	
	@Path(path="/getSmsPackages")
	public String getSmsPackages(){
		CommonUsecase commonUsecase = authenticateMgr.getCommonUsecase();
		String records = commonUsecase.getPackages("sms");
		return records;
	}
	
	
}
