package com.amazon.netty.bl;


import java.util.HashMap;
import java.util.Map;

import com.amazon.netty.blm.AmazonCalendar;
import com.amazon.netty.blm.AmazonJsonParser;
import com.amazon.netty.cache.Cache;
import com.amazon.netty.cache.CacheKeySuffix;
import com.amazon.netty.dao.AppointmentDao;
import com.amazon.netty.dao.DaoManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AppointmentSvc {
	
	public String getAppointments(String storeId,String employeeId,String startDate,String endDate){
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.getAppointments(storeId,employeeId,startDate,endDate);
		return records;
	}
	
	
	public String getCustomer(String storeId, String search) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		String customerList = appointmentDao.getCustomer(storeId,search);
		JsonArray customerArray = AmazonJsonParser.getInstance().parseJson(customerList);  
		return customerArray.toString();
	}
	
	
	public String getAppointmentDetails(String storeId, String appointmentId) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String appointList = appointmentDao.getAppointmentDetails(storeId,appointmentId);
		return appointList;
	}


	public String getBookingList(String storeId, String employeeId, String filters, String startdate, String enddate,
			String skip, String limit) {
		// TODO Auto-generated method stub
		
		Map<String, String> dateRange = dateRange(filters, startdate, enddate);
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		String bookingList = appointmentDao.getBookingList(storeId,employeeId,dateRange.get("startDate"),dateRange.get("endDate"),skip,limit);
		JsonArray bookingArray = AmazonJsonParser.getInstance().parseJson(bookingList);  
		
		String bookingsummary = appointmentDao.getBookingListCount(storeId,employeeId,dateRange.get("startDate"),dateRange.get("endDate"));
		JsonArray summaryArray = AmazonJsonParser.getInstance().parseJson(bookingsummary);
		
		JsonObject records = new JsonObject();
		
		if(summaryArray!=null && bookingArray!=null){
			records.add("list", bookingArray);
			records.add("count", summaryArray.get(0));
		}
		
		return records.toString();
	}

	public String getBookingSummary(String storeId, String employeeId, String filters, String startdate, String enddate,
			String skip, String limit) {
		// TODO Auto-generated method stub
		Map<String, String> dateRange = dateRange(filters, startdate, enddate);
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		String employeeRecords = appointmentDao.getEmployeeBookingSummary(storeId,employeeId,dateRange.get("startDate"),dateRange.get("endDate"));
		String serviceRecords = appointmentDao.getServiceBookingSummary(storeId,employeeId,dateRange.get("startDate"),dateRange.get("endDate"));
		
		JsonArray employeeArray = AmazonJsonParser.getInstance().parseJson(employeeRecords); 
		JsonArray serviceArray = AmazonJsonParser.getInstance().parseJson(serviceRecords); 
		
		JsonObject records = new JsonObject();
		
		if(employeeArray!=null && serviceArray!=null){
			records.add("employee", employeeArray);
			records.add("service", serviceArray);
		}
		
		return records.toString();
	}

	public String getSalesList(String storeId, String employeeId, String filters, String startdate, String enddate,
			String skip, String limit) {
		// TODO Auto-generated method stub
		System.out.println("Booking list");
		Map<String, String> dateRange = dateRange(filters, startdate, enddate);
		System.out.println("startDate :"+ dateRange.get("startDate")+", endDate :"+ dateRange.get("endDate"));
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		String salesList = appointmentDao.getSalesList(storeId,dateRange.get("startDate"),dateRange.get("endDate"),skip,limit);
		String salesCount = appointmentDao.getSalesListCount(storeId,dateRange.get("startDate"),dateRange.get("endDate"));
		
		
		JsonArray salesArray = AmazonJsonParser.getInstance().parseJson(salesList); 
		JsonArray countArray = AmazonJsonParser.getInstance().parseJson(salesCount); 
		
		JsonObject records = new JsonObject();
		
		if(salesArray!=null && countArray!=null){
			records.add("list", salesArray);
			records.add("count", countArray);
		}
		
		return records.toString();
	}

	public String getEmployeeSalesSummary(String storeId, String employeeId, String filters,
			String startdate, String enddate, String skip, String limit) {
		// TODO Auto-generated method stub
		Map<String, String> dateRange = dateRange(filters, startdate, enddate);
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		String employeeSales = appointmentDao.getEmployeeSalesSummary(storeId,employeeId,dateRange.get("startDate"),dateRange.get("endDate"),skip,limit);
		JsonArray salesArray = AmazonJsonParser.getInstance().parseJson(employeeSales);
		if(salesArray!=null){
			return salesArray.toString();
		}else{
			return null;
		}
		
	}

	public String getServiceSalesSummary( String storeId, String employeeId, String filters,
			String startdate, String enddate, String skip, String limit) {
		// TODO Auto-generated method stub
		Map<String, String> dateRange = dateRange(filters, startdate, enddate);
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		String serviceSales = appointmentDao.getServiceSalesSummary(storeId,employeeId,dateRange.get("startDate"),dateRange.get("endDate"),skip,limit);
		JsonArray salesArray = AmazonJsonParser.getInstance().parseJson(serviceSales);
		if(salesArray!=null){
			return salesArray.toString();
		}else{
			return null;
		}
	}

	public String getProductSalesSummary(String storeId, String employeeId, String filters,
			String startdate, String enddate, String skip, String limit) {
		// TODO Auto-generated method stub
		Map<String, String> dateRange = dateRange(filters, startdate, enddate);
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		String productSales = appointmentDao.getProductSalesSummary(storeId,dateRange.get("startDate"),dateRange.get("endDate"),enddate);
		JsonArray productArray = AmazonJsonParser.getInstance().parseJson(productSales);
		
		if(productArray!=null){
			return productArray.toString();
		}else{
			return null;
		}
	}

	public String getCancelledBillList(String storeId, String employeeId, String filters, String startdate,
			String enddate, String skip, String limit) {
		// TODO Auto-generated method stub
		Map<String, String> dateRange = dateRange(filters, startdate, enddate);
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		
		
		String cancelledList = appointmentDao.getCancelledBillList(storeId,startdate,enddate,skip,limit);
		String cancelledCount = appointmentDao.getCancelledBillListCount(storeId,startdate,enddate);
		
		JsonArray listArray = AmazonJsonParser.getInstance().parseJson(cancelledList); 
		JsonArray countArray = AmazonJsonParser.getInstance().parseJson(cancelledCount); 
		
		JsonObject records = new JsonObject();
		
		if(listArray!=null && countArray!=null){
			records.add("list", listArray);
			records.add("count", countArray);
		}
		
		return records.toString();
	}
	
	
	public String getRecentSalesList(String storeId, String filters) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		//String records = appointmentDao.getRecentSalesList(storeId,filters);
		return null;
	}

	public String getUpcomingAppointmentList(String storeId, String filters) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		//String records = appointmentDao.getUpcomingAppointmentList(storeId,filters);
		return null;
	}

	public String getAppointmentConsolidatedReport(String storeId) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		//String records = appointmentDao.getAppointmentConsolidatedReport(storeId);
		return null;
	}

	
	private Map<String,String> dateRange(String filters,String startDate, String endDate){
		AmazonCalendar calendar = AmazonCalendar.getInstance();
		switch (filters) {
				case "month":
					startDate = calendar.getFirstDayOfMonth();
					endDate = calendar.addDay(0);
					break;
				case "today":
					startDate = calendar.addDay(0);
					endDate = calendar.addDay(0);
					break;
				case "yesterday":
					startDate = calendar.addDay(-1);
					endDate = calendar.addDay(-1);
					break;
				case "last7":
					startDate = calendar.addDay(-7);
					endDate = calendar.addDay(-1);
					break;
				case "last30":
					startDate = calendar.addDay(-30);
					endDate = calendar.addDay(-1);
					break;
				case "tomorrow":
					startDate = calendar.addDay(1);
					endDate = calendar.addDay(1);
					break;
				case "next7":
					startDate = calendar.addDay(1);
					endDate = calendar.addDay(7);
					break;
				case "next30":
					startDate = calendar.addDay(1);
					endDate = calendar.addDay(30);
					break;
				case "nextmonth":
					startDate = calendar.getFirstDayOfNextMonth();
					endDate = calendar.getLastDayOfNextMonth();
					break;
				case "custom":
					startDate = calendar.convertDateFormatasString(startDate, "MM/dd/yyyy");
					endDate = calendar.convertDateFormatasString(endDate, "MM/dd/yyyy");
					break;
				default:
					break;
		}
		startDate = startDate.replace(startDate.substring(11, 19), "00:00:00");
		endDate = endDate.replace(endDate.substring(11, 19), "23:59:59");
		Map<String, String> dateRange = new HashMap<String,String>();
		dateRange.put("startDate", startDate);
		dateRange.put("endDate", endDate);
		return dateRange;
	}


	public String checkEmployeeAvailability(String employeeId,String startTime, String endTime) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.checkEmployeeAvailability(employeeId,startTime,endTime);
		return records;
	}
	
	@Cache(index={0},suffix=CacheKeySuffix.EmployeeWorkingDays)
	public String getEmployeeWorkingDays(String employeeId){
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.getEmployeeWorkingDays(employeeId);
		return records;
	}


	public String getPriceLevel(String employeeId) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.getPriceLevel(employeeId);
		JsonObject levelId = AmazonJsonParser.getInstance().parseJson(records).get(0).getAsJsonObject(); 
		return levelId.getAsJsonPrimitive("service_level").getAsString();
	}


	public String getEmployeeServicePrice(String serviceId, String levelId) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.getEmployeeServicePrice(serviceId,levelId);
		return records;
	}


	public String getMembershipDiscount(String serviceId, String masterId) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.getMembershipDiscount(serviceId,masterId);
		return records;
	}


	public String getAppointmantCustomer(String appointmentId) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.getAppointmantCustomer(appointmentId);
		return records;
	}


	public String getAppointmentServices(String appointmentId) {
		// TODO Auto-generated method stub
		AppointmentDao appointmentDao = DaoManager.getInstance().getAppointmentDao();
		String records = appointmentDao.getAppointmentServices(appointmentId);
		return records;
	}


	
	
	
	
	
	
	
}
