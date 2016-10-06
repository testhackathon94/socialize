package com.amazon.netty.Usecase;

import com.amazon.netty.bl.ProductSvc;
import com.amazon.netty.blm.Authenticate;
import com.amazon.netty.blm.PermissionKey;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.http.WHttpRequest;

public class ProductUsecase {
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getBillingProducts(WHttpRequest httpRequest){
		ProductSvc productSvc = SettingServiceMgr.getInstance().getProductSvc();
		String records = productSvc.getBillingProducts(httpRequest.getSessionValue("defaultstore", "storeid"));
		return records;
		
	}
}
