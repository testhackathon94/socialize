package com.amazon.netty.bean;


import java.util.List;
import java.util.Map;

import com.amazon.netty.validation.Digits;
import com.amazon.netty.validation.EmailID;
import com.amazon.netty.validation.NotNull;
import com.amazon.netty.validation.PatternMatch;
import com.amazon.netty.validation.Phone;
import com.amazon.netty.validation.Size;

public class ServiceBean extends AbstractBean {
	private String serviceId;
	private String name;
	private String duration;
	private String availablefor;
	private String serviceGroupId;
	private String serviceGroupName;
	private String serviceLevel;
	private String taxId;
	private List<String> assignedEmployeeId;
	private List<Map<String,Object>> pricing;
	
	@NotNull
	@Size(min=1,max=100)
	@EmailID
	@Phone
	@Digits
	@PatternMatch(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
	public String getServiceId() {
		return serviceId;
	}
	
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceGroupName() {
		return serviceGroupName;
	}
	
	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDuration() {
		return duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getAvailablefor() {
		return availablefor;
	}
	
	public void setAvailablefor(String availablefor) {
		this.availablefor = availablefor;
	}
	
	public String getServiceGroupId() {
		return serviceGroupId;
	}
	
	public void setServiceGroupId(String serviceGroupId) {
		this.serviceGroupId = serviceGroupId;
	}
	public String getServiceLevel() {
		return serviceLevel;
	}
	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public List<String> getAssignedEmployeeId() {
		return assignedEmployeeId;
	}
	public void setAssignedEmployeeId(List<String> assignedEmployeeId) {
		this.assignedEmployeeId = assignedEmployeeId;
	}
	public List<Map<String, Object>> getPricing() {
		return pricing;
	}
	public void setPricing(List<Map<String, Object>> pricing) {
		this.pricing = pricing;
	}
	

	
}
