package com.amazon.netty.Usecase;

import java.sql.SQLException;

import com.amazon.netty.bl.ServiceSettingSvc;
import com.amazon.netty.blm.Authenticate;
import com.amazon.netty.blm.PermissionKey;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.http.WHttpRequest;

public class ServiceUsecase {
	
	@Authenticate(permission=PermissionKey.Settings)
	public String getServiceGroups(WHttpRequest httpRequest) throws SQLException{
		SettingServiceMgr serviceSettingMgr = SettingServiceMgr.getInstance(); 
		ServiceSettingSvc serviceSettingsvc = serviceSettingMgr.getServiceSettingSVC();
		return serviceSettingsvc.getServiceGroups(httpRequest.getSessionValue("defaultstore", "storeid"));
	}
	
}
