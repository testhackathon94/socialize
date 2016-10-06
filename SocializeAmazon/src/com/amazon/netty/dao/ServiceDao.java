package com.amazon.netty.dao;

import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.amazon.exceptions.AmazonException;
import com.amazon.netty.database.Database;
import com.amazon.netty.database.DatabaseConnectionMgr;
import com.amazon.netty.database.DatabaseHTTPMgr;

public class ServiceDao {
	//@Database
	public String getServiceGroups(String storeId) throws SQLException{
		String sql = "select @rid as rid,in('employee_has_store_service').@rid as service_employee,is_deleted,@class as class,name,"
				+ "service_name,duration,full_price,special_price,price,service_level.name as service_level,available_for,tax as tax ,"
				+ "tax.tax_name as tax_name,tax.tax_value as tax_value,in('employee_has_store_service')['is_deleted = false']['enable_appointment = true'].person_name.asList() as employee,"
				+ "in('employee_has_store_service')['is_deleted = false']['enable_appointment = true'].@rid as employee_rid "
				+ "from (traverse out('service_has_pricing') from (traverse out('service_group_has_store_service') from (select from (select expand(out('store_has_service_group')['is_deleted = false']) "
				+ "from "+storeId+") order by created_date desc) while is_deleted = false ) while is_deleted = false) skip 0 limit -1";
//		System.out.println("Query : "+ sql);
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
}
