package com.amazon.netty.actions;

import java.sql.SQLException;

import org.omg.CORBA.Request;

import com.amazon.netty.Usecase.EmployeeUsecase;
import com.amazon.netty.bean.EmployeeBean;
import com.amazon.netty.bl.EmployeeSettingSvc;
import com.amazon.netty.blm.AuthenticateMgr;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;

@Path(path="/java/settings")
public class EmployeeSettingsAction extends BaseAction{
	
	private EmployeeBean employeeBean;
	private AuthenticateMgr authenticateMgr = AuthenticateMgr.getInstance(); 
	
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
		return "Hello Test content";
	}
	
	
	@Path(path="/getAppointmentEmployees/")
	public String getAppointmentEmployees(){
		EmployeeUsecase employeeUsecase = authenticateMgr.getInstance().getEmployeeUsecase();
		return employeeUsecase.getAppointmentEmployees(this.httpRequest);
		
	}
	
	@Path(path="/getEmployees")
	public String getEmployees(){
		EmployeeUsecase employeeUsecase = authenticateMgr.getInstance().getEmployeeUsecase();
		return employeeUsecase.getEmployees(this.httpRequest, employeeBean);
	}
	
}
