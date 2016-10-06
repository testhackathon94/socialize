package com.amazon.netty.Usecase;

import java.net.URLDecoder;

import com.amazon.netty.bean.CustomerBean;
import com.amazon.netty.bl.CustomerSvc;
import com.amazon.netty.blm.Authenticate;
import com.amazon.netty.blm.PermissionKey;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.http.WHttpRequest;

public class CustomerUsecase {
	
	//@Authenticate(permission=PermissionKey.Appointments)
	public String storeSegmentCustomer(WHttpRequest httpRequest, CustomerBean customerBean){
		SettingServiceMgr serviceSettingMgr = SettingServiceMgr.getInstance();
		CustomerSvc customerSvc = serviceSettingMgr.getCustomerSvc();
		String storeId = httpRequest.getSessionValue("defaultstore", "storeid");
		String segment = customerBean.getSegment();
		String searchVal = URLDecoder.decode(customerBean.getSearch()).toUpperCase();
		return searchVal;
		//return customerSvc.storeSegmentCustomer(customerBean);
	}
}
