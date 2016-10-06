package com.amazon.netty.bl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.amazon.netty.bean.EmployeeBean;
import com.amazon.netty.cache.Cache;
import com.amazon.netty.cache.CacheKeySuffix;
import com.amazon.netty.dao.DaoManager;
import com.amazon.netty.dao.EmployeeDao;
import com.google.gson.Gson;


public class EmployeeSettingSvc {
	
	
	@Cache(index={0}, suffix=CacheKeySuffix.AppointmentEmplloyeeList)
	public String getAppointmentEmployees(String storeId){
		EmployeeDao employeeDao = DaoManager.getInstance().getEmployeeDao();
		String records = employeeDao.getAppointmentEmployees(storeId);
		return records;
	}
	
	public String getEmployees(String storeId, EmployeeBean employeeBean){
		EmployeeDao employeeDao = DaoManager.getInstance().getEmployeeDao();
		return employeeDao.getEmployees(storeId, employeeBean);
	}
	
}
