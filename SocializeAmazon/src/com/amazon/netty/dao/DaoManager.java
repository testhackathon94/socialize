package com.amazon.netty.dao;


import com.amazon.netty.blm.AbstractComponentManager;

public class DaoManager extends AbstractComponentManager{
	private static DaoManager mgr = null;
	
	public static DaoManager getInstance(){
		if(mgr == null){
			synchronized (DaoManager.class) {
				if(mgr == null){
					mgr = new DaoManager();
				}
			}
		}
		return mgr;
	}
	

	
	public ServiceDao getServiceDao(){
		return (ServiceDao)getBean("serviceDao");
	}
	
	public EmployeeDao getEmployeeDao(){
		return (EmployeeDao)getBean("employeeDao");
	}
	
	public CommonDao getCommonDao(){
		return (CommonDao)getBean("commonDao");
	}
	
	public ProductDao getProductDao(){
		return (ProductDao)getBean("productDao");
	}
	
	public AppointmentDao getAppointmentDao(){
		return (AppointmentDao)getBean("appointmentDao");
	}
	
	public CustomerDao getCustomerDao(){
		return (CustomerDao)getBean("customerDao");
	}

}
