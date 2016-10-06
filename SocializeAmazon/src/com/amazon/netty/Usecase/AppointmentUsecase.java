package com.amazon.netty.Usecase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.amazon.netty.bean.AppointmentBean;
import com.amazon.netty.bl.AppointmentSvc;
import com.amazon.netty.blm.Authenticate;
import com.amazon.netty.blm.PermissionKey;
import com.amazon.netty.blm.SettingServiceMgr;
import com.amazon.netty.blm.AmazonCalendar;
import com.amazon.netty.blm.AmazonJsonParser;
import com.amazon.netty.http.WHttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AppointmentUsecase {
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getAppoinments(WHttpRequest httpRequest,AppointmentBean bean){
		
		String startDate = null;
		String endDate = null;
		DateFormat  readformat = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(bean.getView().equalsIgnoreCase("agendaDay")){
			try {
				startDate = format.format(readformat.parse(bean.getDate())); 
				startDate = startDate.replaceAll(startDate.substring(11,19), "00:00:00");
				endDate = startDate.replaceAll(startDate.substring(11,19), "23:59:59");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}else if(bean.getView().equalsIgnoreCase("agendaWeek")){
			Calendar cal = Calendar.getInstance();    
			try {
				cal.setTime(readformat.parse(bean.getDate()));
				cal.add( Calendar.DATE, 7 );    
				endDate = format.format(cal.getTime()); 
				startDate = format.format(readformat.parse(bean.getDate()));
				startDate = startDate.replaceAll(startDate.substring(11,19), "00:00:00"); 
				endDate = endDate.replaceAll(endDate.substring(11,19), "23:59:59");
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
			
		}
		
		AppointmentSvc appointmentSvc = SettingServiceMgr.getInstance().getAppointmentSvc();
		String records = appointmentSvc.getAppointments(httpRequest.getSessionValue("defaultstore", "storeid"),bean.getEmployeeId(),startDate,endDate);
		return records;
		
	}
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getCustomer(WHttpRequest httpRequest, AppointmentBean appointmentBean) {
		// TODO Auto-generated method stub
		AppointmentSvc appointmentSvc = SettingServiceMgr.getInstance().getAppointmentSvc();
		String records = appointmentSvc.getCustomer(httpRequest.getSessionValue("defaultstore", "storeid"),appointmentBean.getSearch());
		return records;
	}
	
	@Authenticate(permission=PermissionKey.Appointments)
	public String getAppointmentDetails(WHttpRequest httpRequest, AppointmentBean appointmentBean) {
		// TODO Auto-generated method stub
		AppointmentSvc appointmentSvc = SettingServiceMgr.getInstance().getAppointmentSvc();
		String records = appointmentSvc.getAppointmentDetails(httpRequest.getSessionValue("defaultstore", "storeid"),appointmentBean.getAppointmentId());
		return records;
	}
	
	
	@Authenticate(permission=PermissionKey.Reports)
	public String getAppointmentReports(WHttpRequest httpRequest,AppointmentBean bean){
		String employeeId = bean.getEmployeeId();
		String reportType = bean.getReporttype();
		String storeId = httpRequest.getSessionValue("defaultstore", "storeid");
		//String networkId = httpRequest.getSessionValue("applogged_in", "networkid");
		String records = null;
		AppointmentSvc appointmentSvc = SettingServiceMgr.getInstance().getAppointmentSvc();
		switch (reportType) {
			case "bl":
				records = appointmentSvc.getBookingList(storeId,employeeId,bean.getFilters(),bean.getStartdate(),bean.getEnddate(),bean.getSkip(),bean.getLimit());
				break;
			case "bs":
				records = appointmentSvc.getBookingSummary(storeId,employeeId,bean.getFilters(),bean.getStartdate(),bean.getEnddate(),bean.getSkip(),bean.getLimit());
				break;
			case "sl":
				records = appointmentSvc.getSalesList(storeId,employeeId,bean.getFilters(),bean.getStartdate(),bean.getEnddate(),bean.getSkip(),bean.getLimit());
				break;
			case "se":
				records = appointmentSvc.getEmployeeSalesSummary(storeId,employeeId,bean.getFilters(),bean.getStartdate(),bean.getEnddate(),bean.getSkip(),bean.getLimit());
				break;
			case "ss":
				records = appointmentSvc.getServiceSalesSummary(storeId,employeeId,bean.getFilters(),bean.getStartdate(),bean.getEnddate(),bean.getSkip(),bean.getLimit());
				break;
			case "sp":
				records = appointmentSvc.getProductSalesSummary(storeId,employeeId,bean.getFilters(),bean.getStartdate(),bean.getEnddate(),bean.getSkip(),bean.getLimit());
				break;
			case "rs":
				records = appointmentSvc.getRecentSalesList(storeId,bean.getFilters());
				break;
			case "ua":
				records = appointmentSvc.getUpcomingAppointmentList(storeId,bean.getFilters());
				break;
			case "aa":
				records = appointmentSvc.getAppointmentConsolidatedReport(storeId);
				break;
			case "cb":
				records = appointmentSvc.getCancelledBillList(storeId,employeeId,bean.getFilters(),bean.getStartdate(),bean.getEnddate(),bean.getSkip(),bean.getLimit());
				break;
			default:
				break;
		}
		
		return records;
	}

	public String getEmployeeServicePrice(WHttpRequest httpRequest, AppointmentBean appointmentBean) {
		// TODO Auto-generated method stub
		
		AppointmentSvc appointmentSvc = SettingServiceMgr.getInstance().getAppointmentSvc();
		String levelId = appointmentSvc.getPriceLevel(appointmentBean.getEmployeeId());
		JsonObject obj = null;
		JsonObject result = new JsonObject();
		if(levelId!=null){
			String records =appointmentSvc.getEmployeeServicePrice(appointmentBean.getServiceId(),levelId);
			obj = AmazonJsonParser.getInstance().parseJson(records).get(0).getAsJsonObject();
		}
		
		if(appointmentBean.getMasterId()!=null){
			String master = appointmentSvc.getMembershipDiscount(appointmentBean.getServiceId(),appointmentBean.getMasterId());
			JsonArray member = AmazonJsonParser.getInstance().parseJson(master).getAsJsonArray(); 
			if(member!=null && member.size() > 0){
				JsonObject mem = member.get(0).getAsJsonObject();
				result.addProperty("discount", mem.getAsJsonPrimitive("discount")!=null?mem.getAsJsonPrimitive("discount").getAsString():"0");
				result.addProperty("discounttype", mem.getAsJsonPrimitive("discounttype")!=null?mem.getAsJsonPrimitive("discounttype").getAsString():"false");
			}
		}
		
		if(obj!=null){
				String spprice = "0",fprice = "0";
				spprice =  obj.getAsJsonPrimitive("e_special_price")!=null?obj.getAsJsonPrimitive("e_special_price").getAsString():"0";
				spprice =  obj.getAsJsonPrimitive("special_price")!=null?obj.getAsJsonPrimitive("special_price").getAsString():"0";
				result.addProperty("special_price", spprice);
				
				fprice =  obj.getAsJsonPrimitive("e_full_price")!=null?obj.getAsJsonPrimitive("e_full_price").getAsString():"0";
				fprice =  obj.getAsJsonPrimitive("full_price")!=null?obj.getAsJsonPrimitive("full_price").getAsString():"0";
				result.addProperty("full_price", spprice);
				result.addProperty("tax_name", obj.getAsJsonPrimitive("tax_name")!=null?obj.getAsJsonPrimitive("tax_name").getAsString():"");
				result.addProperty("tax_value", obj.getAsJsonPrimitive("tax_value")!=null?obj.getAsJsonPrimitive("tax_value").getAsString():"0");
				result.addProperty("duration", obj.getAsJsonPrimitive("duration")!=null?obj.getAsJsonPrimitive("duration").getAsString():"0");
			
		}
		return result.toString();
	}

	public String checkEmployeeAvailability(WHttpRequest httpRequest, AppointmentBean appointmentBean) {
		// TODO Auto-generated method stub
		AmazonCalendar amazonCalendar = AmazonCalendar.getInstance();
		AppointmentSvc appointmentSvc = SettingServiceMgr.getInstance().getAppointmentSvc();
		Date currentDate = AmazonCalendar.getInstance().convertDate(appointmentBean.getCurrentDate(), "E dd MMM, yyyy");
		int dayOfWeak = amazonCalendar.getDayOfWeak(currentDate);
		int duration = 0;
		if(appointmentBean.getDuration()!=null){
			try{
				duration = Integer.parseInt(appointmentBean.getDuration());
			}catch (Exception e) {
				System.out.println("integer conversion Error"+e.getMessage());
			}
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date startTime = amazonCalendar.convertDate(appointmentBean.getStartTime(), "h:mm a");
		cal.setTime(currentDate);
		cal.set(Calendar.HOUR_OF_DAY,startTime.getHours());
		cal.set(Calendar.MINUTE,startTime.getMinutes());
		startTime = cal.getTime();
		cal.add(Calendar.MINUTE, duration);
		Date endTime = cal.getTime();
		
		Boolean work = false;
		System.out.println("Check availability StartTime:"+ dateFormat.format(startTime)+", Endtime:"+dateFormat.format(endTime));
		String records = appointmentSvc.checkEmployeeAvailability(appointmentBean.getEmployeeId(),dateFormat.format(startTime),dateFormat.format(endTime));
		JsonArray recordsArray = AmazonJsonParser.getInstance().parseJson(records);
		
		String workingDay = appointmentSvc.getEmployeeWorkingDays(appointmentBean.getEmployeeId());
		JsonArray workingdayArray = AmazonJsonParser.getInstance().parseJson(workingDay);
		
		if(workingdayArray!=null && workingdayArray.size()>0){
			JsonParser parser = new JsonParser();
			JsonArray  workingDays = parser.parse(workingdayArray.get(0).getAsJsonObject().getAsJsonPrimitive("workinghour").getAsString()).getAsJsonArray();
			String st = workingDays.get(dayOfWeak).getAsJsonObject().getAsJsonPrimitive("start_time").getAsString();
			String et = workingDays.get(dayOfWeak).getAsJsonObject().getAsJsonPrimitive("end_time").getAsString();
			Date startWorkingTime = amazonCalendar.convertDate(st, "h:mm a");
			Date endWorkingTime = amazonCalendar.convertDate(et, "h:mm a");
			
			cal.set(Calendar.HOUR_OF_DAY,startWorkingTime.getHours());
			cal.set(Calendar.MINUTE,startWorkingTime.getMinutes());
			Date sTime = cal.getTime();
			
			cal.set(Calendar.HOUR_OF_DAY,endWorkingTime.getHours());
			cal.set(Calendar.MINUTE,endWorkingTime.getMinutes());
			Date eTime = cal.getTime();
			System.out.println("Working StartTime:"+ dateFormat.format(sTime)+", Endtime:"+dateFormat.format(eTime));
			if((sTime.compareTo(startTime) > 0) || (eTime.compareTo(endTime) < 0) || workingDays.get(dayOfWeak).getAsJsonObject().getAsJsonPrimitive("is_deleted").getAsBoolean()){
				work = true;
			}
		}	
		
		if(recordsArray!=null && recordsArray.size() > 0){
			return "This employee has another appointment booked at this time. You can still double book this employee if you wish.";
		}else if(work){
			return "Employee isn't scheduled to work at this time. This is just a warning, you can still create an appointment. Employee's working hours can be adjusted on the schedule.";
		}
		return "";
	}

	@Authenticate(permission=PermissionKey.Appointments)
	public String getBillingDetails(WHttpRequest httpRequest, AppointmentBean appointmentBean) {
		// TODO Auto-generated method stub
		String storeId = httpRequest.getSessionValue("defaultstore", "storeid");
		String networkId = httpRequest.getSessionValue("applogged_in", "networkid");
		String customerId = appointmentBean.getCustomerId();
		String appointmentId = appointmentBean.getAppointmentId();
		
		SettingServiceMgr mgr = SettingServiceMgr.getInstance();
		AppointmentSvc appointmentSvc = mgr.getAppointmentSvc();
		String customer =  appointmentSvc.getAppointmantCustomer(appointmentId);
		String offers = mgr.getCustomerSvc().getCustomerOffers(storeId,customerId);
		String points = mgr.getCustomerSvc().getCustomerPoints(networkId, customerId);
		String pointsActivity = mgr.getCustomerSvc().getCustomerPointsActivity(storeId, customerId, "0", "5");
		String masterPoint = mgr.getCommonSvc().getPointsMasterSetting(networkId);
		String services = appointmentSvc.getAppointmentServices(appointmentBean.getAppointmentId());
		

		JsonObject result = new JsonObject();
		result.add("appointment_details", AmazonJsonParser.getInstance().parseJson(customer).get(0));
		result.add("service_details", AmazonJsonParser.getInstance().parseJson(services));
		result.add("offers", resolveOffers(offers));
		result.add("points", AmazonJsonParser.getInstance().parseJsontoObject(points));
		result.add("pointstransaction", AmazonJsonParser.getInstance().parseJsontoObject(pointsActivity));
		result.add("masterpoints", AmazonJsonParser.getInstance().parseJsontoObject(masterPoint));
		
		return result.toString();
		
	}
	
	
	public JsonArray resolveOffers(String offers){
		JsonArray array = AmazonJsonParser.getInstance().parseJson(offers);
		JsonArray custom = new JsonArray();
		JsonArray voucher = new JsonArray();
		JsonArray greetings = new JsonArray();
		for (JsonElement elm : array) {
			if(elm.getAsJsonObject().getAsJsonPrimitive("table_class").getAsString().equalsIgnoreCase("custom_offer")){
				custom.add(elm);
			}else if(elm.getAsJsonObject().getAsJsonPrimitive("table_class").getAsString().equalsIgnoreCase("generated_vouchers")){
				voucher.add(elm);
			}else if(elm.getAsJsonObject().getAsJsonPrimitive("table_class").getAsString().equalsIgnoreCase("schedule_greetings")){
				greetings.add(elm);
			}
		}
		
		JsonArray result = new JsonArray();
		
		JsonObject object = new JsonObject();
		object.addProperty("name", "custom_offer");
		object.add("offers", custom);
		result.add(object);
		
		object = new JsonObject();
		object.addProperty("name", "vouchers");
		object.add("offers", voucher);
		result.add(object);
		
		object = new JsonObject();
		object.addProperty("name", "greetings");
		object.add("offers", greetings);
		result.add(object);
		
		return result;
	}
	
	
	
	

	
}
