package com.amazon.netty.actions;

import com.amazon.netty.Usecase.AppointmentUsecase;
import com.amazon.netty.bean.AppointmentBean;
import com.amazon.netty.blm.AuthenticateMgr;
import com.amazon.netty.blm.AmazonProperty;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;

@Path(path="/java/appointment")
public class AppoinmentAction extends BaseAction {
	private AppointmentBean appointmentBean;
	AuthenticateMgr authenticateMgr = AuthenticateMgr.getInstance();
	public AppoinmentAction(WHttpRequest httpRequest, WHTTPResponse httpResponse) {
		super(httpRequest, httpResponse);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class beanClass() {
		// TODO Auto-generated method stub
		return AppointmentBean.class;
	}

	@Override
	public Object getBean() {
		// TODO Auto-generated method stub
		return this.appointmentBean;
	}

	@Override
	public void setBean(Object bean) {
		// TODO Auto-generated method stub
		this.appointmentBean = (AppointmentBean) bean;
	}
	
	@Path(path="/getAppointments/")
	public String getAppointments(){
		AppointmentUsecase appointmentUsecase = authenticateMgr.getAppointmentUsecase();
		String records = appointmentUsecase.getAppoinments(httpRequest,appointmentBean);
		return records;
	}
	
	@Path(path="/getAppointmentReports/")
	public String getAppointmentReports(){
		AppointmentUsecase appointmentUsecase = authenticateMgr.getAppointmentUsecase();
		String records = appointmentUsecase.getAppointmentReports(httpRequest,appointmentBean);
		return records;
	}
	
	@Path(path="/getCustomer/")
	public String getCustomer(){
		AppointmentUsecase appointmentUsecase = authenticateMgr.getAppointmentUsecase();
		String records = appointmentUsecase.getCustomer(httpRequest,appointmentBean);
		return records;
	}
	@Path(path="/getAppointmentDetails/")
	public String getAppointmentDetails(){
		AppointmentUsecase appointmentUsecase = authenticateMgr.getAppointmentUsecase();
		String records = appointmentUsecase.getAppointmentDetails(httpRequest,appointmentBean);
		return records;
	}
	@Path(path="/getEmployeeServicePrice/")
	public String getEmployeeServicePrice(){
		AppointmentUsecase appointmentUsecase = authenticateMgr.getAppointmentUsecase();
		String records = appointmentUsecase.getEmployeeServicePrice(httpRequest,appointmentBean);
		return records;
	}
	
	@Path(path="/checkEmployeeAvailability/")
	public String checkEmployeeAvailability(){
		AppointmentUsecase appointmentUsecase = authenticateMgr.getAppointmentUsecase();
		String records = appointmentUsecase.checkEmployeeAvailability(httpRequest,appointmentBean);
		return records;
	}
	
	@Path(path="/getBillingDetails/")
	public String getBillingDetails(){
		AppointmentUsecase appointmentUsecase = authenticateMgr.getAppointmentUsecase();
		String records = appointmentUsecase.getBillingDetails(httpRequest,appointmentBean);
		return records;
	}

}
