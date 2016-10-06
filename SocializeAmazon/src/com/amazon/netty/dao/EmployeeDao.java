package com.amazon.netty.dao;


import java.net.URLEncoder;

import com.amazon.netty.bean.EmployeeBean;
import com.amazon.netty.database.DatabaseHTTPMgr;

public class EmployeeDao {
	
	public String getAppointmentEmployees(String storeId){
		String sql = "select person_name,last_name,@rid,out('employee_has_employee_working_hours').data as working_hours, out('employee_has_employee_working_hours')[0] as working_id,service_level.is_standard as is_standard "
				+ "from (select expand(out('store_has_service_person')) from "+storeId+") where is_deleted = false and enable_appointment = true order by created_date asc skip 0 limit -1";
		return DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
	}

	public String getEmployees(String storeId, EmployeeBean bean){
		String condition = "";
		String skip = "0";
		String limit = "-1";
		if(bean.getSearchName() != null && bean.getSearchName() != ""){
			condition += "and ( mobile_no.toUpperCase() like '%"+bean.getSearchName().toUpperCase()+"%' or email_id.toUpperCase() like '%"+bean.getSearchName().toUpperCase()+"%' or person_name.toUpperCase() like '%"+bean.getSearchName().toUpperCase()+"%' or lastname.toUpperCase() like '%"+bean.getSearchName().toUpperCase()+"%' )";
		}
		if(bean.getSkip()!= null && bean.getSkip() != "" && Integer.valueOf(bean.getSkip()) > 0){
			skip = bean.getSkip(); 
		}
		if(bean.getLimit()!= null && bean.getLimit() != "" && Integer.valueOf(bean.getLimit()) > 0){
			limit = bean.getLimit(); 
		}
		String sql = "select *,@rid as emp_id,image_id.original_image_url as image_path,out_employee_has_store_service as services,out('employee_has_employee_working_hours').data as working_hours, out('employee_has_employee_working_hours')[0] as working_id,"
				+ " out('employee_has_business_user')[0].email_id as business_email_id, out('employee_has_business_user')[0] as user_id, service_level.is_standard as is_standard from (select expand(out('store_has_service_person')) from "+storeId+") where is_deleted = false "+condition+" order by created_date asc skip "+skip+" limit "+limit+"";
		return DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
	}
}
