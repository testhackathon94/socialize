package com.amazon.netty.actions;




import java.sql.SQLException;

import com.amazon.netty.Usecase.ServiceUsecase;
import com.amazon.netty.bean.ServiceBean;
import com.amazon.netty.blm.AuthenticateMgr;
import com.amazon.netty.controller.Path;
import com.amazon.netty.http.WHTTPResponse;
import com.amazon.netty.http.WHttpRequest;


@Path(path="/java/settings")
public class ServiceSettingAction extends BaseAction{

	private ServiceBean bean;
	
	private AuthenticateMgr authenticateMgr = AuthenticateMgr.getInstance(); 
	
	
	public ServiceSettingAction(WHttpRequest httpRequest, WHTTPResponse httpResponse) {
		super(httpRequest, httpResponse);
	}

	@Override
	public ServiceBean getBean() {
		// TODO Auto-generated method stub
		return this.bean;
	}
	
	@Override
	public void setBean(Object beanObject) {
		this.bean = (ServiceBean)beanObject;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Class beanClass() {
		return ServiceBean.class;
	} 
	
	@Path(path="/getServices/")
	public String getServiceGroupList() throws ClassNotFoundException, SQLException{
		ServiceUsecase ServiceUsecase = authenticateMgr.getServiceUsecase();
		return ServiceUsecase.getServiceGroups(httpRequest);
	}
	
	@Path(path="/addService")
	public void createService(){
		System.out.println("Add service is being called");
//		ServiceSettingSvc ServiceSettingsvc = serviceSettingMgr.getServiceSettingSVC();
//		ServiceSettingsvc.addService("storeId", "serviceName");
	}
	@Path(path="/addServiceGroup")
	public String createServiceGroup() throws SQLException, ClassNotFoundException{
		System.out.println("Add service  group is being called");
		//ServiceSettingSvc ServiceSettingsvc = serviceSettingMgr.getServiceSettingSVC();
		//ServiceSettingsvc.addServiceGroup(bean,httpRequest.getSessionValue("defaultstore", "storeid"));
		return "success";
	}
	
	
	
	public void getURI(){
		String uri = this.httpRequest.getURI();
		httpResponse.writeResponse(uri);
	}
	
}
