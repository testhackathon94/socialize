package com.amazon.netty.bean;

import java.net.URLDecoder;

public class AppointmentBean{
	private String date;
	private String employeeId;
	private String view;
	private String startdate;
	private String enddate;
	private String filters;
	private String reporttype;
	private String skip;
	private String limit;
	private String search;
	private String appointmentId;
	private String masterId;
	private String serviceId;
	private String startTime;
	private String duration;
	private String currentDate;
	private String customerId;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = URLDecoder.decode(startTime);
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = URLDecoder.decode(duration);
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = URLDecoder.decode(currentDate);
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = URLDecoder.decode(masterId);
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = URLDecoder.decode(serviceId);
	}
	public String getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(String appointmentId) {
		this.appointmentId = URLDecoder.decode(appointmentId);
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = URLDecoder.decode(search);
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = URLDecoder.decode(startdate);
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = URLDecoder.decode(enddate);
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = URLDecoder.decode(filters);
	}
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = URLDecoder.decode(reporttype);
	}
	public String getSkip() {
		return skip;
	}
	public void setSkip(String skip) {
		this.skip = URLDecoder.decode(skip);
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = URLDecoder.decode(limit);
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = URLDecoder.decode(date);
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employee) {
		this.employeeId = URLDecoder.decode(employee);
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = URLDecoder.decode(view);
	}
	
}
