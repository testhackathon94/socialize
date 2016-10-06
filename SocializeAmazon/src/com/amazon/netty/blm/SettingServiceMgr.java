package com.amazon.netty.blm;

import com.amazon.netty.bl.AppointmentSvc;
import com.amazon.netty.bl.CommonSvc;
import com.amazon.netty.bl.CustomerSvc;
import com.amazon.netty.bl.EmployeeSettingSvc;
import com.amazon.netty.bl.ProductSvc;
import com.amazon.netty.bl.ServiceSettingSvc;

public class SettingServiceMgr extends AbstractComponentManager{

	
	private static SettingServiceMgr mgr = null;
	
	public static SettingServiceMgr getInstance(){
		if(mgr == null){
			synchronized (SettingServiceMgr.class) {
				if(mgr == null){
					mgr = new SettingServiceMgr();
				}
			}
		}
		return mgr;
	}
	

	
	public ServiceSettingSvc getServiceSettingSVC(){
		return (ServiceSettingSvc)getBean("serviceSettingSvc");
	}
	
	public EmployeeSettingSvc getEmployeeSettingsSvc(){
		return (EmployeeSettingSvc)getBean("employeeSettingSvc");
	}
	
	public CommonSvc getCommonSvc(){
		return (CommonSvc)getBean("commonSvc");
	}
	
	public ProductSvc getProductSvc(){
		return (ProductSvc)getBean("productSvc");
	}
	
	public AppointmentSvc getAppointmentSvc(){
		return (AppointmentSvc)getBean("appointmentSvc");
	}
	
	public CustomerSvc getCustomerSvc(){
		return (CustomerSvc)getBean("customerSvc");
	}

}
