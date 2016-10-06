package com.amazon.netty.Usecase;

import java.sql.SQLException;

import com.amazon.netty.bean.EmployeeBean;
import com.amazon.netty.bl.EmployeeSettingSvc;
import com.amazon.netty.blm.Authenticate;
import com.amazon.netty.blm.PermissionKey;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.http.WHttpRequest;

public class EmployeeUsecase {
	@Authenticate(permission=PermissionKey.Appointments)
	public String getAppointmentEmployees(WHttpRequest httpRequest){
		SettingServiceMgr serviceSettingMgr = SettingServiceMgr.getInstance(); 
		EmployeeSettingSvc employeeSettingsSvc = serviceSettingMgr.getEmployeeSettingsSvc();
		return employeeSettingsSvc.getAppointmentEmployees(httpRequest.getSessionValue("defaultstore", "storeid"));
	}
	@Authenticate(permission=PermissionKey.Appointments)
	public String getEmployees(WHttpRequest httpRequest, EmployeeBean bean){
		SettingServiceMgr serviceSettingMgr = SettingServiceMgr.getInstance();
		EmployeeSettingSvc employeeSettingsSvc = serviceSettingMgr.getEmployeeSettingsSvc();
		return employeeSettingsSvc.getEmployees(httpRequest.getSessionValue("defaultstore", "storeid"), bean);
	}
	
}
