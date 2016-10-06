package com.amazon.netty.cache;

public enum CacheKeySuffix {

	Pratice("pra"),
	EmployeeList("emp"),
	ServiceList("ser"),
	AppointmentEmplloyeeList("app_emp"),
	ProductList("pro"),
	PaymentMode("pay_mode"),
	BillingSettings("bill_set"),
	NotificationList("noti"),
	EmployeeWorkingDays("work_days"),
	MasterPoints("point"),
	Package("pack"),
	Permission("per");
	
	
	String suffix;
	
	private CacheKeySuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return suffix;
	}
}
