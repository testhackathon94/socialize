package com.amazon.netty.dao;

import java.net.URLEncoder;

import com.amazon.netty.database.DatabaseHTTPMgr;

public class AppointmentDao {
	
	public String getAppointments(String storeId,String employeeId,String startDate,String endDate){
		System.out.println("StartDate:"+startDate+", EndDate:"+endDate);
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0){
				condition = " and out('appointment_services_has_employee') in ("+employeeId+")";
			}
			
			String sql = "select in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0].out('customer_has_activity')[store_id = "+storeId+"].size() as newcustomer,$defect.size() as defect,"
					+ "$active.size() as active,@rid,service.out('service_has_pricing')['service_level.is_standard = true'].price as price,start_time,"
					+ "end_time,service.service_name as service,service.duration as duration,"
					+ "out('appointment_services_has_employee')[0] as employeeid,out('appointment_services_has_employee')[0].person_name as employee_name,"
					+ "in('appointment_has_appointment_services')[0] as appointid,in('appointment_has_appointment_services')[0].appoint_id as appoint_no,in('appointment_has_appointment_services')[0].status as status,"
					+ "in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0].name as customer_name,in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0].mobile_no as mobile_no,"
					+ "in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0] as customer_id,in('appointment_has_appointment_services')[0].out('appointment_has_billing')[0] as bill_no,"
					+ "in('appointment_has_appointment_services')[0].out('appointment_has_billing')[0].bill_number as bill_number from (select expand(out('appointment_has_appointment_services')['is_deleted = false']) "
					+ "from (select from (select expand(out('store_has_appointment')['is_deleted = false']) from "+storeId+")) where (status <> 5 and status <> 4) or (status = 4 and out('appointment_has_billing')[0].is_cancel = 0))  where start_time between '"+startDate+"' to '"+endDate+"'"+condition+" skip 0 limit -1";
			
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}else{
			return "Date is not valid";
		}
		
	}
	
	public String getCustomer(String storeId, String search) {
		// TODO Auto-generated method stub
		String condition= "";
		if(search!=null && search.length() > 0){
			condition = "mobile_no.toUpperCase() containstext '"+search+"'";
		}
		String sql = "select @rid as customer_id, mobile_no, email_id, name, location, gender, in_store_customer[out = "+storeId+"]['@rid'] as store_customer_id,in_store_customer[out = "+storeId+"].created_date.format('dd-MM-yyyy') as created_date,"
				+ " in_store_customer[out = "+storeId+"].is_active as is_active "
				+ "from (select expand(in['is_deleted = false']) from (select expand(out_store_customer) from "+storeId+")) where "+condition+" skip 0 limit 20";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}
	
	
	public String getAppointmentDetails(String storeId, String appointmentId) {
		// TODO Auto-generated method stub
		String sql = "select @rid,in('appointment_has_appointment_services')[0].appoint_id as appoint_id,in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0] as customer,in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0].mobile_no as mobile_no,"
				+ "in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0].name as customer_name,in('appointment_has_appointment_services')[0].created_date as appoint_date,in('appointment_has_appointment_services')[0].status as status,"
				+ "in('appointment_has_appointment_services')[0].note as note,start_time,end_time,service,service.service_name as service_name,service.duration as duration,"
				+ "out('appointment_services_has_employee')[0] as employee from (select expand(out('appointment_has_appointment_services')['is_deleted = false']) "
				+ "from "+appointmentId+") order by start_time asc skip 0 limit -1";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}


	public String getBookingList(String storeId, String employeeId, String startDate, String endDate, String skip,
			String limit) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0 && !employeeId.equalsIgnoreCase("all")){
				condition = " and out('appointment_services_has_employee') in ("+employeeId+")";
			}
			condition += " order by start_time desc skip "+skip+" limit "+limit;
			String sql = "select ref_id,@rid,start_time,end_time,service.service_name as service,service.out('service_has_pricing')[0].price as price,service.duration as duration,"
					+ "out('appointment_services_has_employee')[0] as employeeid,out('appointment_services_has_employee')[0].person_name as employee_frist_name,out('appointment_services_has_employee')[0].last_name as employee_last_name,"
					+ "in('appointment_has_appointment_services')[0] as appointid,in('appointment_has_appointment_services')[0].status as status,in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0].name as customer_name,"
					+ "in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0] as customer_id from (select expand(out('appointment_has_appointment_services')['is_deleted = false']) "
					+ "from (select expand(out('store_has_appointment')['is_deleted = false']) from "+storeId+")) where start_time between '"+startDate+"' to '"+endDate+"'"+condition;
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getBookingListCount(String storeId, String employeeId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0 && !employeeId.equalsIgnoreCase("all")){
				condition = " and out('appointment_services_has_employee') in ("+employeeId+")";
			}
			String sql = "select count(*),sum(price) as price from (select ref_id,@rid,start_time,end_time,service.service_name as service,service.out('service_has_pricing')[0].price as price,"
					+ "service.duration as duration,out('appointment_services_has_employee')[0] as employeeid,out('appointment_services_has_employee')[0].person_name as employee_frist_name,out('appointment_services_has_employee')[0].last_name as employee_last_name,"
					+ "in('appointment_has_appointment_services')[0] as appointid,in('appointment_has_appointment_services')[0].status as status,in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0].name as customer_name,"
					+ "in('appointment_has_appointment_services')[0].in('customer_has_appointment')[0] as customer_id from (select expand(out('appointment_has_appointment_services')['is_deleted = false']) "
					+ "from (select expand(out('store_has_appointment')['is_deleted = false']) from "+storeId+")) where start_time between '"+startDate+"' to '"+endDate+"'"+condition+")";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getEmployeeBookingSummary(String storeId, String employeeId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0 && !employeeId.equalsIgnoreCase("all")){
				condition = employeeId; 
			}else{
				condition = "(select expand(out('store_has_service_person')['is_deleted = false']) from "+storeId+")";
			}
			String sql = "select count(*) as booking_count,out('appointment_services_has_employee')[0].person_name as employee_frist_name,out('appointment_services_has_employee')[0].last_name as employee_last_name,sum(service.out('service_has_pricing')[0].price) as price from (select *,out('appointment_services_has_employee')[0] as rid from "
					+ "(select expand(in('appointment_services_has_employee')['is_deleted = false']) from "+condition+") where "
					+ "in('appointment_has_appointment_services')[0].status <> 3 and in('appointment_has_appointment_services')[0].status <> 5 and start_time between '"+startDate+"' to '"+endDate+"') group by rid";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getServiceBookingSummary(String storeId, String employeeId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0 && !employeeId.equalsIgnoreCase("all")){
				condition = " and out_appointment_services_has_employee in ("+employeeId+")";
			}
			String sql = "select service.service_name as name,count(*) as count,sum(service.out('service_has_pricing')[0].price) as price from (select expand(out('appointment_has_appointment_services')['is_deleted = false']) "
					+ "from (select expand(out('store_has_appointment')) from "+storeId+")) where "
					+ "out('appointment_services_has_employee')[0].is_deleted = false and in('appointment_has_appointment_services')[0].status <> 3 and in('appointment_has_appointment_services')[0].status <> 5 and start_time between '"+startDate+"' to '"+endDate+"'"+condition+" group by service";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getSalesList(String storeId,String startDate, String endDate, String skip,
			String limit) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String sql = "select @rid,bill_number,gross_value,discount_value,payment_mode.name as paymentmode,in('appointment_has_billing')[0].in('customer_has_appointment')[0].name as customer_name,created_date from (select  expand(out('store_has_billing')['is_deleted = false']) "
					+ "from "+storeId+") where created_date between '"+startDate+"' and  '"+endDate+"' and is_cancel = 0 order by created_date desc skip "+skip+" limit "+limit;
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getSalesListCount(String storeId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String sql = "select count(1), sum(gross_value) as gross_value, payment_mode.name as paymentmode from "
					+ "(select expand(out('store_has_billing')['is_deleted = false']) from "+storeId+") where created_date between '"+startDate+"' and  '"+endDate+"' and is_cancel = 0 group by payment_mode skip 0 limit -1";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getEmployeeSalesSummary(String storeId, String employeeId, String startDate, String endDate,
			String skip, String limit) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0 && !employeeId.equalsIgnoreCase("all")){
				condition = employeeId; 
			}else{
				condition = "(select expand(out('store_has_service_person')['is_deleted = false']) from "+storeId+")";
			}
			
			String sql = "select out('billing_services_has_employee')[0].person_name as employee_frist_name,out('billing_services_has_employee')[0].last_name as employee_last_name,"
					+ "sum(service_value) as service_value,sum(gross_value) as gross_amount,sum(discount_value) as discount_amount,sum(tax_value) as tax_amount from (select *,out('billing_services_has_employee')[0] as rid "
					+ "from (select expand(in('billing_services_has_employee')['is_deleted = false']) from "+condition+") where in('billing_has_billing_services')[0].is_cancel = 0 and created_date between '"+startDate+"' and '"+endDate+"') group by rid skip 0 limit -1";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getServiceSalesSummary(String storeId, String employeeId, String startDate, String endDate,
			String skip, String limit) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0 && !employeeId.equalsIgnoreCase("all")){
				condition = " and out('billing_services_has_employee') in ("+employeeId+")"; 
			}
			
			String sql = "select sum(quantity) as count,out('billing_services_has_service')[0].service_name as service_name,sum(gross_value) as gross_amount,sum(service_value) as service_amount,sum(tax_value) as tax_amount,sum(discount_value) as discount_amount  "
					+ "from (select *,out('billing_services_has_service')[0] as rid from (select expand(in('billing_services_has_service')['is_deleted = false']) from "
					+ "(select expand(out('store_has_store_service')['is_deleted = false']) from "+storeId+")) where in('billing_has_billing_services')[0].is_cancel = 0 and created_date between '"+startDate+"' and '"+endDate+"'"+condition+") group by rid skip 0 limti -1";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getProductSalesSummary(String storeId, String employeeId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String condition= "";
			if(employeeId!=null && employeeId.length() > 0 && !employeeId.equalsIgnoreCase("all")){
				condition = " and out('billing_products_has_employee') in ("+employeeId+")"; 
			}
			
			String sql = "select out('billing_products_has_products')[0].name as product_name,sum(gross_value) as gross_amount,sum(service_value) as service_amount,sum(tax_value) as tax_amount,sum(discount_value) as discount_amount,"
					+ "sum(quantity) as count  from (select *,out('billing_products_has_products')[0] as rid from (select expand(in('billing_products_has_products')['is_deleted = false']) "
					+ "from (select expand(out('store_has_pos_product')['is_deleted = false']) from "+storeId+")) where in('billing_has_billing_products')[0].is_cancel = 0 and created_date between '"+startDate+"' and '"+endDate+"'"+condition+") group by rid skip 0 limit -1";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getCancelledBillList(String storeId, String startDate, String endDate, String skip, String limit) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String sql = "select @rid,bill_number,gross_value,discount_value,payment_mode.name as paymentmode,in('appointment_has_billing')[0].in('customer_has_appointment')[0].name as customer_name,created_date, "
					+ "out('billing_has_cancel_bill')[0].reason as reason, out('billing_has_cancel_bill')[0].cancelled_by.display_name as staff_name from (select expand(out('store_has_billing')) "
					+ "from "+storeId+") where created_date between '"+startDate+"' and  '"+endDate+"' and is_cancel = 1 and is_deleted = 1 order by created_date desc skip "+skip+" limit "+limit;
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String getCancelledBillListCount(String storeId, String startDate, String endDate) {
		// TODO Auto-generated method stub
		if(startDate!=null && endDate!=null){
			String sql = "select count(1), sum(gross_value) as gross_value, payment_mode.name as paymentmode from (select expand(out('store_has_billing')) "
					+ "from "+storeId+") where created_date between '"+startDate+"' and  '"+endDate+"' and is_cancel = 1 and is_deleted = 1 group by payment_mode skip 0 limit -1";
			String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
			return records;
		}
		return null;
	}

	public String checkEmployeeAvailability(String employeeId,String startTime,String endTime) {
		// TODO Auto-generated method stub
		String sql = "select from (select expand(in('appointment_services_has_employee')['is_deleted = false']) from "+employeeId+") "
				+ "where in('appointment_has_appointment_services')[0].status <>= 5 and in('appointment_has_appointment_services')[0].status <>= 4 and "
				+ "(start_time between '"+startTime+"' and '"+endTime+"' or end_time between '"+startTime+"' and '"+endTime+"') or (start_time <= '"+startTime+"' and end_time>='"+endTime+"')";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
		
	}

	public String getEmployeeWorkingDays(String employeeId) {
		// TODO Auto-generated method stub
		String sql = "select data as workinghour from (select expand(out('employee_has_employee_working_hours')['is_deleted = false']) from "+employeeId+")";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getPriceLevel(String employeeId) {
		// TODO Auto-generated method stub
		String sql = "select service_level from "+employeeId;
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getEmployeeServicePrice(String serviceId, String levelId) {
		// TODO Auto-generated method stub
		String sql = "select duration,tax.tax_value as tax_value,tax.tax_name as tax_name,duration,out('service_has_pricing')['service_level.is_standard = true']['is_deleted = false'].price.aslist()[0] as special_price, "
				+ "out('service_has_pricing')['service_level.is_standard = true']['is_deleted = false'].full_price.aslist()[0] as full_price,out('service_has_pricing')['service_level = "+levelId+"']['is_deleted = false'].full_price.aslist()[0] as e_full_price,"
				+ "out('service_has_pricing')['service_level = "+levelId+"']['is_deleted = false'].price.aslist()[0] as e_special_price from "+serviceId;
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getMembershipDiscount(String serviceId, String masterId) {
		// TODO Auto-generated method stub
		String sql = "select discount,out.discounttype as discounttype from  (select expand(out_membership_has_service[in="+serviceId+"]) "
				+ "from (select expand(in('master_has_membership')) from "+masterId+" where expiry_date > sysdate() or expiry_date is null))";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getAppointmantCustomer(String appointmentId) {
		// TODO Auto-generated method stub
		String sql = "select @rid as appointment_id, in('customer_has_appointment')[0].name as customer_name, in('customer_has_appointment')[0].email_id as email_id, in('customer_has_appointment')[0].mobile_no as mobile_no, "
				+ "in('customer_has_appointment')[0].location as location, in('customer_has_appointment')[0] as customer_id, appoint_id, note, status, is_deleted, "
				+ "created_user, out('appointment_has_appointment_services').aslist() as service_ids, created_date from "+appointmentId+" where is_deleted = false";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	public String getAppointmentServices(String appointmentId) {
		// TODO Auto-generated method stub
		String sql = "select @rid as app_ser_id, service as service_id, service.service_name as service_name, out('appointment_services_has_employee')[0] as employee_id, out('appointment_services_has_employee')[0].person_name as employee_name, "
				+ "is_deleted, ref_id, start_time, end_time, service.duration as duration out('appointment_services_has_employee')[0].service_level as level_id, created_date from (select expand(out('appointment_has_appointment_services')) from "+appointmentId+") where is_deleted = false";
		String records = DatabaseHTTPMgr.getInstance().read(URLEncoder.encode(sql));
		return records;
	}

	

	
}
