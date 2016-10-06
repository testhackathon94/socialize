package com.amazon.netty.blm;

import com.amazon.netty.Usecase.AppointmentUsecase;
import com.amazon.netty.Usecase.CommonUsecase;
import com.amazon.netty.Usecase.CustomerUsecase;
import com.amazon.netty.Usecase.EmployeeUsecase;
import com.amazon.netty.Usecase.ProductUsecase;
import com.amazon.netty.Usecase.ServiceUsecase;

public class AuthenticateMgr extends AbstractComponentManager {
	private static AuthenticateMgr instance =null;
	
	private AuthenticateMgr(){
	}
	
	public static AuthenticateMgr getInstance(){
		if(instance == null){
			synchronized (AuthenticateMgr.class) {
				if(instance == null){
					instance = new AuthenticateMgr();
				}
			}
		}
		return instance;
	}
	
	
	public ServiceUsecase getServiceUsecase(){
		return (ServiceUsecase)getBean("serviceUsecase");
	}
	
	public EmployeeUsecase getEmployeeUsecase(){
		return (EmployeeUsecase)getBean("employeeUsecase");
	}
	
	public CommonUsecase getCommonUsecase(){
		return (CommonUsecase)getBean("commonUsecase");
	}
	
	public ProductUsecase getProductUsecase(){
		return (ProductUsecase)getBean("productUsecase");
	}
	
	public AppointmentUsecase getAppointmentUsecase(){
		return (AppointmentUsecase)getBean("appointmentUsecase");
	}
	
	public CustomerUsecase getCutomerUsecase(){
		return (CustomerUsecase)getBean("customerUsecase");
	}
}
